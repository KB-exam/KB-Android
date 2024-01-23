package com.insu.tripmoto_compose.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kb.cbt.MyAppState
import com.kb.cbt.R
import com.kb.cbt.screen.main.BottomNavItem


@Composable
fun BottomNavigation(appState: MyAppState) {
    val items = listOf(
        BottomNavItem.Quiz,
        BottomNavItem.Home,
        BottomNavItem.Info,
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor = colorResource(R.color.white),
        contentColor = Color.Black,
        modifier = Modifier
            .padding(bottom = 12.dp, start = 8.dp, end = 8.dp)
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(15.dp)
                shadowElevation = 20f
            },
        elevation = 20.dp
    ) {
        val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                modifier = Modifier,
                icon = {
                    Icon(
                        painterResource(item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                //label = { Text(text = item.title, fontSize = 9.sp) },
                selectedContentColor = colorResource(R.color.primary_1),
                unselectedContentColor = colorResource(R.color.primary_gray1),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    appState.navController.navigate(item.screen_route) {
                        appState.navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}
