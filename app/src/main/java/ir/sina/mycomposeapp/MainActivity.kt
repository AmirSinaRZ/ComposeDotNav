package ir.sina.mycomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ir.sina.mycomposeapp.custom.AnimatedBottomNavigation
import ir.sina.mycomposeapp.custom.BottomNavItem
import ir.sina.mycomposeapp.ui.theme.MyComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComposeAppTheme {
                val navItems = listOf(
                    BottomNavItem("Home", Icons.Default.Home, "home"),
                    BottomNavItem("Search", Icons.Default.Search, "search"),
                    BottomNavItem("Profile", Icons.Default.Person, "profile"),
                    BottomNavItem("Settings", Icons.Default.Settings, "settings")
                )

                var selectedItem by remember { mutableStateOf("home") }

                Scaffold(
                    bottomBar = {
                        AnimatedBottomNavigation(
                            items = navItems,
                            selectedItem = selectedItem,
                            onItemSelected = { selectedItem = it },
                            containerColor = Color(0xff191931),
                            iconColor = Color(0xff9496BF)
                        )
                    }
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Hello World",
                            modifier = Modifier.padding(it)
                        )
                    }
                }
            }
        }
    }
}