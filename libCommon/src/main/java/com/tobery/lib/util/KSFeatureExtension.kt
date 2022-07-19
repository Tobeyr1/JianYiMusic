package com.tobery.lib.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.view.forEach
import androidx.navigation.*
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.ref.WeakReference




fun switchTabItem(
    destinationId: Int,
    navController: NavController,
    args: Bundle? = null,
): Boolean {
    val builder = NavOptions.Builder()
        .setLaunchSingleTop(true)
    if (navController.currentDestination?.parent?.findNode(destinationId) is ActivityNavigator.Destination) {
        builder.setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
            .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
            .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
    } else {
        builder.setEnterAnim(androidx.navigation.ui.R.animator.nav_default_enter_anim)
            .setExitAnim(androidx.navigation.ui.R.animator.nav_default_exit_anim)
            .setPopEnterAnim(androidx.navigation.ui.R.animator.nav_default_pop_enter_anim)
            .setPopExitAnim(androidx.navigation.ui.R.animator.nav_default_pop_exit_anim)
    }
//    if (item.order and Menu.CATEGORY_SECONDARY == 0) {
    builder.setPopUpTo(findStartDestination(navController.graph)?.id ?: 0, false)
//    }
    val options = builder.build()
    return try {
        navController.navigate(destinationId, args, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}

fun findStartDestination(graph: NavGraph): NavDestination? {
    var startDestination: NavDestination? = graph
    while (startDestination is NavGraph) {
        val parent = startDestination
        startDestination = parent.findNode(parent.startDestinationId)
    }
    return startDestination
}

fun BottomNavigationView.setupWithNavController(navController: NavController) {
    setupWithNavControllerCustom(this, navController)
}

fun setupWithNavControllerCustom(
    bottomNavigationView: BottomNavigationView,
    navController: NavController
) {
    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
        if (ClickEvent.lastClickTime + VIEW_CLICK_INTERVAL_TIME < System.currentTimeMillis()) {
            ClickEvent.lastClickTime = System.currentTimeMillis()
            NavigationUI.onNavDestinationSelected(
                item,
                navController
            )
        } else {
            false
        }
    }

    val weakReference = WeakReference(bottomNavigationView)
    navController.addOnDestinationChangedListener(
        object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination, arguments: Bundle?
            ) {
                val view = weakReference.get()
                if (view == null) {
                    navController.removeOnDestinationChangedListener(this)
                    return
                }
                view.menu.forEach { item ->
                    if (matchDestination(destination, item.itemId)) {
                        item.isChecked = true
                    }
                }
            }
        })
}

fun matchDestination(
    destination: NavDestination,
    @IdRes destId: Int
): Boolean {
    var currentDestination: NavDestination? = destination
    while (currentDestination!!.id != destId && currentDestination.parent != null) {
        currentDestination = currentDestination.parent
    }
    return currentDestination.id == destId
}