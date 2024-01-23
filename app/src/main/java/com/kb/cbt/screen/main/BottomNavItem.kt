package com.kb.cbt.screen.main

import com.kb.cbt.R.drawable as AppIcon

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Quiz: BottomNavItem("Quiz", AppIcon.ic_question, "quiz")
    object Home: BottomNavItem("Home", AppIcon.ic_home, "home")
    object Info: BottomNavItem("Info", AppIcon.ic_info, "info")
}