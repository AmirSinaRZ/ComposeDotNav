# Simple bottom bar with smooth animation
a simple bottom bar nav with smooth animation for Jetpack Compose.

## Usage

```kotlin
Scaffold(
 bottomBar = {
	AnimatedBottomNavigation(
	items = navItems,
	selectedItem = selectedItem,
	onItemSelected = { selectedItem = it },
	containerColor = Color(0xff191931), // your color
	iconColor = Color(0xff9496BF) // your color
	)
 }
) {
	// content
}

```

## Demo


![Demo GIF](https://raw.githubusercontent.com/AmirSinaRZ/ComposeDotNav/refs/heads/main/srcd/Screen_recording_20241029_162622.gif)
