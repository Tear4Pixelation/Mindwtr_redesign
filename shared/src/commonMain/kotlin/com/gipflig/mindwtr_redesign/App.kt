package com.gipflig.mindwtr_redesign

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gipflig.mindwtr_redesign.screens.FocusScreen
import com.gipflig.mindwtr_redesign.screens.Screen
import com.materialkolor.PaletteStyle
import com.materialkolor.material3.DynamicMaterialTheme
import org.jetbrains.compose.resources.painterResource

import mindwtr_redesign.shared.generated.resources.Res
import mindwtr_redesign.shared.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    // Navigation placeholder
    var currentScreen by remember { mutableStateOf(Screen.Focus) }
    // Generate Material Themes at https://next.materialkolor.com/
    DynamicMaterialTheme(
        seedColor = Color(0xFF359BA9),
        isDark = isSystemInDarkTheme(),
        style = PaletteStyle.Expressive  // More vibrant, more options for elevated components
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    for (screen in Screen.entries) {
                        NavigationBarItem(
                            selected = screen == currentScreen,
                            onClick = { currentScreen = screen },
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(screen.screenName) }
                        )
                    }
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                when (currentScreen) {
                    Screen.Focus -> FocusScreen()
                }
            }
        }
    }
}