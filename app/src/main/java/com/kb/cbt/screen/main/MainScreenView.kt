package com.kb.cbt.screen.main

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.insu.tripmoto_compose.screen.main.BottomNavigation
import com.kb.cbt.navGraph
import com.kb.cbt.rememberAppState
import com.kb.cbt.screen.main.home.HomeScreen
import com.kb.cbt.screen.main.info.InfoScreen
import com.kb.cbt.screen.main.quiz.QuizScreen


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenView() {
    val appState = rememberAppState()
    val isBottomBarVisible = remember { mutableStateOf(false) }

//    val navBackStackEntry = appState.navController.currentBackStackEntryAsState()
//    navBackStackEntry.value?.destination?.route

    appState.navController.currentBackStackEntryAsState().value?.destination?.route.let { route ->
        Log.d(TAG, "route: $route")
        isBottomBarVisible.value = when(route) {
            "quiz" -> true
            "home" -> true
            "info" -> true
            else -> false
        }
    }

    Scaffold(
        bottomBar = {
            if(isBottomBarVisible.value) {
                BottomNavigation(appState)
            }
        }
    ) {
        NavHost(appState.navController, startDestination = BottomNavItem.Home.screen_route) {
            navGraph(appState)
        }
    }
}
