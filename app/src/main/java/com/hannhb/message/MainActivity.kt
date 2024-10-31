package com.hannhb.message

import com.hannhb.message.core.ui.navigation.NavigationActivity

class MainActivity: NavigationActivity() {
    override fun getNavGraphId(): Int {
        return R.navigation.message_nav_graph
    }

}