package com.kb.cbt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kb.cbt.screen.login.LoginScreen
import com.kb.cbt.screen.splash.SplashScreen
import com.kb.cbt.ui.theme.Kb_androidTheme

@Composable
fun MyApp() {
    Kb_androidTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            val appState = rememberAppState()
            NavHost(
                navController = appState.navController,
                startDestination = "SplashScreen",
            ) {
                navGraph(appState)
            }
        }
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
) =
    remember(navController) {
        MyAppState(navController)
    }

fun NavGraphBuilder.navGraph(appState: MyAppState) {
    composable("SplashScreen") {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable("LoginScreen") {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
}