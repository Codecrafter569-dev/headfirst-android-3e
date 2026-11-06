# 100 Jetpack Compose Projects (Basic to Master)

Each line is: project, then the concepts it teaches. Do them in order, since every level builds on the one before.

## Level 1: Compose Basics (1-10)
1. Hello Compose: @Composable, setContent, @Preview, Text
2. Business Card: Column, Row, Image, Modifier, padding
3. Birthday Greeting: Box, alignment, painterResource, TextStyle
4. Counter App: remember, mutableStateOf, recomposition, Button
5. Tip Calculator: TextField, state hoisting, KeyboardOptions
6. Dice Roller: state-driven UI, `when`, image swapping
7. Unit Converter: OutlinedTextField, DropdownMenu, derived values
8. Dark/Light Toggle: Switch, MaterialTheme.colorScheme
9. Like Button: IconToggleButton, animateColorAsState
10. Quote Card: Card, Typography, custom fonts

## Level 2: Lists and Layouts (11-20)

11. In-memory To-Do: LazyColumn, items, keys
12. Shopping Checklist: Checkbox, mutableStateListOf
13. Contacts List: avatar rows, Surface, clickable
14. Photo Grid: LazyVerticalGrid
15. Horizontal Carousel: LazyRow, snap fling behavior
16. Affirmations App: string resources, Card lists
17. Flip Flashcards: click state, graphicsLayer rotation
18. Color Palette Picker: grid, Box backgrounds
19. Static Weather Cards: layout composition, Spacer, weight
20. Profile Screen: ConstraintLayout, Scaffold

## Level 3: Material 3 and Theming (21-30)
21. Themed Notes UI: color scheme, typography, shapes
22. Dynamic Color App: Material You, dynamicColorScheme
23. TopAppBar Demo: Scaffold, scroll behaviors
24. Bottom Navigation: NavigationBar, selected state
25. Navigation Drawer: ModalNavigationDrawer, scope.launch
26. Tabs with Pager: HorizontalPager, TabRow
27. Dialogs and Snackbars: AlertDialog, SnackbarHost
28. Bottom Sheet Demo: ModalBottomSheet
29. Settings Screen: Slider, RadioButton, Switch rows
30. Onboarding Screens: pager, dot indicators

## Level 4: Navigation and Architecture (31-40)
31. Multi-screen App: NavHost, NavController
32. Argument Passing: routes, NavType
33. Login/Signup Form: validation, focus, IME actions
34. Recipe Book: list to detail flow
35. Movie List (static): detail screens, shared data
36. Quiz App: ViewModel, StateFlow, UiState
37. ViewModel Counter: collectAsStateWithLifecycle
38. Stopwatch: LaunchedEffect, coroutines
39. Pomodoro Timer: side effects, DisposableEffect
40. Tic-Tac-Toe: state modeling, game logic separation

## Level 5: Persistence and DI (41-50)
41. Notes with Room: Entity, Dao, Database
42. To-Do with Room + Flow: reactive queries
43. Expense Tracker: Room relations, totals
44. Habit Tracker: DataStore, streak logic
45. Persisted Theme Switcher: Preferences DataStore
46. Bookmark Manager: CRUD, search
47. Diary with Search: SearchBar, filtering
48. Room Flashcards: spaced repetition logic
49. Water Intake Tracker: Room + simple Canvas chart
50. Shopping List with Hilt: dependency injection, @HiltViewModel

## Level 6: Networking (51-60)
51. Joke App: Retrofit, JSON parsing
52. Weather App: API, loading/error/success states
53. Movie Browser (TMDB): Coil, image loading
54. News Reader: Paging 3
55. Crypto Tracker: periodic refresh, flows
56. Dictionary App: search API, empty states
57. GitHub User Search: debounce, flatMapLatest
58. Offline-first Recipes: Room as single source of truth
59. Currency Converter: API plus caching
60. Unsplash Gallery: LazyVerticalStaggeredGrid

## Level 7: Animation and Gestures (61-70)
61. Animated Splash: Animatable, transitions
62. Expandable Cards: AnimatedVisibility, animateContentSize
63. Swipe-to-Dismiss List: SwipeToDismissBox
64. Drag-Reorder List: pointerInput, drag gestures
65. Pull-to-Refresh: PullToRefreshBox
66. Loading Indicators: rememberInfiniteTransition
67. Custom Toggle Switch: updateTransition
68. Shared Element Gallery: SharedTransitionLayout
69. Swipe Cards (Tinder-style): detectDragGestures, offset, rotation
70. Pinch-Zoom Viewer: transformable, detectTransformGestures

## Level 8: Canvas and Custom UI (71-80)
71. Analog Clock: Canvas, drawLine, rotate
72. Paint App: Path, touch drawing
73. Bar/Line/Pie Charts: DrawScope math
74. Circular Progress: drawArc, animation
75. Flow Layout: custom `Layout`, measure and place
76. Speedometer/Compass: sensors plus Canvas
77. Signature Pad: Path, bitmap export
78. Snake Game: game loop, withFrameNanos
79. Flappy-style Game: physics, collision
80. Memory Match: state machines, delays

## Level 9: System Integration (81-90)
81. Camera App: CameraX, permissions
82. QR Scanner: ML Kit, analyzers
83. Maps App: Maps Compose, location
84. Music Player: Media3, foreground service
85. Voice Recorder: MediaRecorder, permissions
86. Reminder App: WorkManager, notifications
87. Home Widget: Glance
88. Biometric Vault: BiometricPrompt, encryption
89. Chat UI: reverse LazyColumn, keyboard insets
90. Firebase Social App: Auth, Firestore, real-time data

## Level 10: Mastery (91-100)
91. Wear OS App: Compose for Wear
92. Compose Multiplatform To-Do: Android plus Desktop
93. Design System Library: CompositionLocal, theming tokens
94. Performance Lab: stability, derivedStateOf, recomposition counts, baseline profiles
95. UI Test Suite: ComposeTestRule, semantics
96. Accessibility-first App: TalkBack, semantics, touch targets
97. Adaptive Layouts: WindowSizeClass, foldables, list-detail
98. Multi-module Clean Architecture Shop: feature modules, UseCases
99. Offline-first Sync App: WorkManager, conflict handling
100. Capstone: production app with CI, Macrobenchmark, Play Store release

## How to get the most from this
- Build each one without a tutorial first, then compare with the official docs.
- After each level, refactor an earlier project using the new skills (for example, add a ViewModel to Level 1 apps).
- Since you use Termux, Levels 1-4 can be built in Android Studio on a PC or in a cloud IDE. Which setup are you using to build apps?

If you'd like, I can go level by level and give you full from-scratch specs for each project: requirements, composables to use, state design, and common mistakes. Which level should we start with?