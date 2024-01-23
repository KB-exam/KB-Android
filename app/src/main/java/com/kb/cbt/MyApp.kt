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
import com.kb.cbt.screen.main.BottomNavItem
import com.kb.cbt.screen.main.MainScreen
import com.kb.cbt.screen.main.home.HomeScreen
import com.kb.cbt.screen.main.info.InfoScreen
import com.kb.cbt.screen.main.quiz.QuizScreen
import com.kb.cbt.screen.register.RegisterScreen
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
        SplashScreen(openAndPopUp = { route -> appState.navigate(route) })
    }
    composable("LoginScreen") {
        LoginScreen(openAndPopUp = { route -> appState.navigate(route) })
    }
    composable("RegisterScreen") {
        RegisterScreen(openAndPopUp = { route -> appState.navigate(route) })
    }
    composable("MainScreen") {
        MainScreen(openAndPopUp = { route -> appState.clearAndNavigate(route) })
    }

    composable(
        route = BottomNavItem.Quiz.screen_route,
    ) {
        QuizScreen()
    }
    composable(
        route = BottomNavItem.Home.screen_route,
    ) {
        HomeScreen()
    }
    composable(
        route = BottomNavItem.Info.screen_route,
    ) {
        InfoScreen()
    }
}