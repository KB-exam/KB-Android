package com.kb.cbt

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kb.cbt.composable.snackbar.SnackbarManager
import com.kb.cbt.screen.login.LoginScreen
import com.kb.cbt.screen.main.BottomNavItem
import com.kb.cbt.screen.main.MainScreen
import com.kb.cbt.screen.main.home.HomeScreen
import com.kb.cbt.screen.main.info.InfoScreen
import com.kb.cbt.screen.main.quiz.QuizScreen
import com.kb.cbt.screen.register.RegisterScreen
import com.kb.cbt.screen.splash.SplashScreen
import com.kb.cbt.ui.theme.Kb_androidTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun MyApp() {
    Kb_androidTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
        ) {
            val appState = rememberAppState()
            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                        }
                    )
                },
                scaffoldState = appState.scaffoldState
            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = "SplashScreen",
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    navGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        MyAppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
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
        QuizScreen(openAndPopUp = { route -> appState.navigate(route) })
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