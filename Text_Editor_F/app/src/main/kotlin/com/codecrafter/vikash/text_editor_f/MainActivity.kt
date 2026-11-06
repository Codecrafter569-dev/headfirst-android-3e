package com.codecrafter.vikash.text_editor_f
  
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import java.io.File

class MainActivity : AppCompatActivity() {

    // ---------- Views ----------
    private lateinit var toolbar: Toolbar
    private lateinit var editor: EditText
    private lateinit var status: TextView
    private lateinit var findBar: View
    private lateinit var findInput: EditText
    private lateinit var replaceInput: EditText

    // ---------- State ----------
    private var currentUri: Uri? = null
    private var currentName = "Untitled"
    private var isModified = false
    private var isRestoring = false      // true while WE change the text (not the user)

    private val undoStack = ArrayDeque<String>()
    private val redoStack = ArrayDeque<String>()
    private var lastText = ""

    private var fontSize = 16f
    private var monospace = false

    private val prefs by lazy { getSharedPreferences("editor_prefs", Context.MODE_PRIVATE) }

    // ---------- File picker launchers ----------
    private val openLauncher =
        registerForActivityResult(OpenDocumentReadWrite()) { uri: Uri? ->
            if (uri != null) openFile(uri)
        }

    private val saveAsLauncher =
        registerForActivityResult(ActivityResultContracts.CreateDocument("text/plain")) { uri: Uri? ->
            if (uri != null) {
                persistPermission(uri)
                writeToUri(uri)
            }
        }

    // =====================================================
    //  Lifecycle
    // =====================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply the saved theme BEFORE the screen is created
        AppCompatDelegate.setDefaultNightMode(
            prefs.getInt("night_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        editor = findViewById(R.id.editor)
        status = findViewById(R.id.status)
        findBar = findViewById(R.id.findBar)
        findInput = findViewById(R.id.findInput)
        replaceInput = findViewById(R.id.replaceInput)

        fontSize = prefs.getFloat("font_size", 16f)
        monospace = prefs.getBoolean("mono", false)
        applyFont()

        restoreSession()

        editor.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (isRestoring) return
                val now = s?.toString() ?: ""
                if (now == lastText) return
                undoStack.addLast(lastText)
                if (undoStack.size > 100) undoStack.removeFirst()
                redoStack.clear()
                lastText = now
                isModified = true
                updateTitle()
                updateStatus()
            }
        })

        findViewById<Button>(R.id.btnFindNext).setOnClickListener { findNext() }
        findViewById<Button>(R.id.btnReplace).setOnClickListener { replaceOne() }
        findViewById<Button>(R.id.btnReplaceAll).setOnClickListener { replaceAll() }
        findViewById<Button>(R.id.btnCloseFind).setOnClickListener { findBar.visibility = View.GONE }
    }

    override fun onPause() {
        super.onPause()
        saveSession()
    }

    // =====================================================
    //  Menu
    // =====================================================
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_new -> { confirmDiscard { newFile() }; true }
            R.id.action_open -> {
                confirmDiscard {
                    openLauncher.launch(arrayOf("text/*", "application/json", "application/xml"))
                }
                true
            }
            R.id.action_save -> { save(); true }
            R.id.action_save_as -> { saveAsLauncher.launch(suggestedFileName()); true }
            R.id.action_undo -> { undo(); true }
            R.id.action_redo -> { redo(); true }
            R.id.action_find -> { toggleFindBar(); true }
            R.id.action_font_size -> { showFontSizeDialog(); true }
            R.id.action_mono -> {
                monospace = !monospace
                prefs.edit().putBoolean("mono", monospace).apply()
                applyFont()
                true
            }
            R.id.action_theme -> { toggleTheme(); true }
            R.id.action_share -> { shareText(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // =====================================================
    //  File actions
    // =====================================================
    private fun newFile() {
        currentUri = null
        currentName = "Untitled"
        setEditorText("")
        isModified = false
        updateTitle()
        updateStatus()
    }

    private fun openFile(uri: Uri) {
        try {
            val text = contentResolver.openInputStream(uri)
                ?.bufferedReader()?.use { it.readText() } ?: ""
            persistPermission(uri)
            currentUri = uri
            currentName = queryName(uri)
            setEditorText(text)
            isModified = false
            updateTitle()
            updateStatus()
        } catch (e: Exception) {
            toast("Could not open file: ${e.message}")
        }
    }

    private fun save() {
        val uri = currentUri
        if (uri == null) {
            saveAsLauncher.launch(suggestedFileName())
        } else {
            writeToUri(uri)
        }
    }

    private fun writeToUri(uri: Uri) {
        try {
            contentResolver.openOutputStream(uri, "wt")?.use {
                it.write(editor.text.toString().toByteArray(Charsets.UTF_8))
            }
            currentUri = uri
            currentName = queryName(uri)
            isModified = false
            updateTitle()
            toast("Saved")
        } catch (e: Exception) {
            toast("Could not save, try Save As: ${e.message}")
        }
    }

    private fun suggestedFileName(): String =
        if (currentName.contains('.')) currentName else "$currentName.txt"

    private fun queryName(uri: Uri): String {
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (index >= 0 && cursor.moveToFirst()) return cursor.getString(index)
        }
        return "Untitled"
    }

    private fun persistPermission(uri: Uri) {
        try {
            contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )
        } catch (e: Exception) {
            // Some providers do not allow it. Saving may then need "Save As" after a restart.
        }
    }

    private fun confirmDiscard(action: () -> Unit) {
        if (!isModified) {
            action()
            return
        }
        AlertDialog.Builder(this)
            .setTitle("Unsaved changes")
            .setMessage("Your changes will be lost. Continue?")
            .setPositiveButton("Discard") { _, _ -> action() }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // =====================================================
    //  Undo / Redo
    // =====================================================
    private fun undo() {
        if (undoStack.isEmpty()) return
        redoStack.addLast(lastText)
        applySnapshot(undoStack.removeLast())
    }

    private fun redo() {
        if (redoStack.isEmpty()) return
        undoStack.addLast(lastText)
        applySnapshot(redoStack.removeLast())
    }

    private fun applySnapshot(text: String) {
        isRestoring = true
        editor.setText(text)
        editor.setSelection(text.length)
        isRestoring = false
        lastText = text
        isModified = true
        updateTitle()
        updateStatus()
    }

    // Replace the whole text without creating an undo step (new file, open file, restore)
    private fun setEditorText(text: String) {
        isRestoring = true
        editor.setText(text)
        isRestoring = false
        lastText = text
        undoStack.clear()
        redoStack.clear()
    }

    // =====================================================
    //  Find / Replace
    // =====================================================
    private fun toggleFindBar() {
        findBar.visibility = if (findBar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        if (findBar.visibility == View.VISIBLE) findInput.requestFocus()
    }

    private fun findNext() {
        val query = findInput.text.toString()
        if (query.isEmpty()) return
        val text = editor.text.toString()
        var index = text.indexOf(query, editor.selectionEnd.coerceAtLeast(0), ignoreCase = true)
        if (index == -1) index = text.indexOf(query, 0, ignoreCase = true)   // wrap around
        if (index == -1) {
            toast("Not found")
            return
        }
        editor.requestFocus()
        editor.setSelection(index, index + query.length)
    }

    private fun replaceOne() {
        val query = findInput.text.toString()
        if (query.isEmpty()) return
        val start = minOf(editor.selectionStart, editor.selectionEnd)
        val end = maxOf(editor.selectionStart, editor.selectionEnd)
        if (start >= 0 && editor.text.substring(start, end).equals(query, ignoreCase = true)) {
            editor.text.replace(start, end, replaceInput.text.toString())
        }
        findNext()
    }

    private fun replaceAll() {
        val query = findInput.text.toString()
        if (query.isEmpty()) return
        val text = editor.text.toString()
        val count = text.split(query, ignoreCase = true).size - 1
        if (count == 0) {
            toast("Not found")
            return
        }
        editor.setText(text.replace(query, replaceInput.text.toString(), ignoreCase = true))
        toast("Replaced $count")
    }

    // =====================================================
    //  Look and feel
    // =====================================================
    private fun showFontSizeDialog() {
        val sizes = arrayOf(12, 14, 16, 18, 20, 24, 28, 32)
        val labels = sizes.map { "$it sp" }.toTypedArray()
        AlertDialog.Builder(this)
            .setTitle("Font size")
            .setItems(labels) { _, which ->
                fontSize = sizes[which].toFloat()
                prefs.edit().putFloat("font_size", fontSize).apply()
                applyFont()
            }
            .show()
    }

    private fun applyFont() {
        editor.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize)
        editor.typeface = if (monospace) Typeface.MONOSPACE else Typeface.DEFAULT
    }

    private fun toggleTheme() {
        val isNightNow =
            (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ==
                Configuration.UI_MODE_NIGHT_YES
        val newMode =
            if (isNightNow) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
        prefs.edit().putInt("night_mode", newMode).apply()
        AppCompatDelegate.setDefaultNightMode(newMode)   // this recreates the screen
    }

    private fun updateTitle() {
        supportActionBar?.title = (if (isModified) "* " else "") + currentName
    }

    private fun updateStatus() {
        val text = editor.text.toString()
        val chars = text.length
        val words = text.trim().split(Regex("\\s+")).count { it.isNotEmpty() }
        status.text = "Words: $words   Characters: $chars   Lines: ${editor.lineCount}"
    }

    // =====================================================
    //  Share
    // =====================================================
    private fun shareText() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, currentName)
            putExtra(Intent.EXTRA_TEXT, editor.text.toString())
        }
        startActivity(Intent.createChooser(intent, "Share text"))
    }

    // =====================================================
    //  Auto-save and restore last session
    // =====================================================
    private fun saveSession() {
        File(filesDir, "draft.txt").writeText(editor.text.toString())
        prefs.edit()
            .putString("uri", currentUri?.toString())
            .putString("name", currentName)
            .putBoolean("modified", isModified)
            .putInt("cursor", editor.selectionStart.coerceAtLeast(0))
            .apply()
    }

    private fun restoreSession() {
        val draft = File(filesDir, "draft.txt")
        if (draft.exists()) {
            val text = draft.readText()
            setEditorText(text)
            editor.setSelection(prefs.getInt("cursor", 0).coerceIn(0, text.length))
        }
        currentUri = prefs.getString("uri", null)?.let { Uri.parse(it) }
        currentName = prefs.getString("name", "Untitled") ?: "Untitled"
        isModified = prefs.getBoolean("modified", false)
        updateTitle()
        updateStatus()
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

// A small helper: the normal "open file" picker, but it also asks for
// WRITE access and for permission that lasts after the app restarts.
class OpenDocumentReadWrite : ActivityResultContracts.OpenDocument() {
    override fun createIntent(context: Context, input: Array<String>): Intent {
        return super.createIntent(context, input).apply {
            addFlags(
                Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION or
                    Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
            )
        }
    }
}
