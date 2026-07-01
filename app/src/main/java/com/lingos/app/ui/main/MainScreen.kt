package com.lingos.app.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lingos.app.R
import com.lingos.app.ui.chat.ChatScreen
import com.lingos.app.ui.dashboard.DashboardScreen
import com.lingos.app.ui.theme.LINGOSColors
import com.lingos.app.ui.theme.LINGOSTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LING OS", style = LINGOSTypography.titleMedium, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { /* 菜单 */ }) {
                        Icon(Icons.Default.Menu, contentDescription = stringResource(R.string.settings_title), tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = viewModel::toggleMode) {
                        Icon(
                            Icons.Default.SwapHoriz,
                            contentDescription = "Toggle mode",
                            tint = if (uiState.currentMode == MainMode.AI) LINGOSColors.AccentRed else Color.White
                        )
                    }
                    Surface(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(28.dp),
                        shape = MaterialTheme.shapes.small,
                        color = if (uiState.currentMode == MainMode.AI) LINGOSColors.AccentRed.copy(alpha = 0.2f)
                                else LINGOSColors.Success.copy(alpha = 0.2f)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            Text(
                                text = if (uiState.currentMode == MainMode.AI) "AI" else "SYS",
                                style = LINGOSTypography.labelSmall,
                                color = if (uiState.currentMode == MainMode.AI) LINGOSColors.AccentRed
                                    else LINGOSColors.Success
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LINGOSColors.Background,
                    scrolledContainerColor = LINGOSColors.Background
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = LINGOSColors.Background,
                tonalElevation = 0.dp
            ) {
                MainTab.entries.forEach { tab ->
                    NavigationBarItem(
                        icon = { Icon(tab.icon, contentDescription = tab.label, modifier = Modifier.size(24.dp)) },
                        label = { Text(tab.label, style = LINGOSTypography.labelSmall) },
                        selected = uiState.currentTab == tab,
                        onClick = { viewModel.selectTab(tab) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = LINGOSColors.AccentRed,
                            selectedTextColor = LINGOSColors.AccentRed,
                            unselectedIconColor = LINGOSColors.TextHint,
                            unselectedTextColor = LINGOSColors.TextHint,
                            indicatorColor = LINGOSColors.AccentRed.copy(alpha = 0.15f)
                        )
                    )
                }
            }
        },
        containerColor = LINGOSColors.Background
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (uiState.currentTab) {
                MainTab.CHAT -> ChatScreen()
                MainTab.DASHBOARD -> DashboardScreen()
            }
        }
    }
}
