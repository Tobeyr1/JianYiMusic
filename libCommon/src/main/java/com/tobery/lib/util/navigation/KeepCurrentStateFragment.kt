package com.tobery.lib.util.navigation

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import java.lang.reflect.Field
import java.util.*


@Navigator.Name("keep_state_fragment")
class KeepCurrentStateFragment(
    private val mContext: Context,
    private val mFragmentManager: FragmentManager,
    private val mContainerId: Int
) : FragmentNavigator(mContext, mFragmentManager, mContainerId) {

    @Nullable
    override fun navigate(
        destination: Destination,
        @Nullable args: Bundle?,
        @Nullable navOptions: NavOptions?,
        @Nullable navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        if (mFragmentManager.isStateSaved) {
            return null
        }

        var className = destination.className
        if (className[0] == '.') {
            className = mContext.packageName + className
        }
        var frag: Fragment? = mFragmentManager.findFragmentByTag(className)
        if (null == frag) {
            frag = mFragmentManager.fragmentFactory.instantiate(mContext.classLoader, className)
        }
        frag.arguments = args
        val ft: FragmentTransaction = mFragmentManager.beginTransaction()
        var enterAnim = navOptions?.enterAnim ?: -1
        var exitAnim = navOptions?.exitAnim ?: -1
        var popEnterAnim = navOptions?.popEnterAnim ?: -1
        var popExitAnim = navOptions?.popExitAnim ?: -1
        if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
            enterAnim = if (enterAnim != -1) enterAnim else 0
            exitAnim = if (exitAnim != -1) exitAnim else 0
            popEnterAnim = if (popEnterAnim != -1) popEnterAnim else 0
            popExitAnim = if (popExitAnim != -1) popExitAnim else 0
            ft.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim)
        }

        val fragments: List<Fragment> = mFragmentManager.fragments
        for (fragment in fragments) {
            ft.setMaxLifecycle(frag, Lifecycle.State.STARTED)
            ft.hide(fragment)
        }
        if (!frag.isAdded) {
            ft.add(mContainerId, frag, className)
        }
        ft.setMaxLifecycle(frag, Lifecycle.State.RESUMED)
        ft.show(frag)
        ft.setPrimaryNavigationFragment(frag)
        @IdRes val destId = destination.id

        val mBackStack: ArrayDeque<Int>? = try {
            val field: Field = FragmentNavigator::class.java.getDeclaredField("mBackStack")
            field.isAccessible = true
            field.get(this) as? ArrayDeque<Int>
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        val initialNavigation = mBackStack?.isEmpty() == true
        val isSingleTopReplacement = (navOptions != null && !initialNavigation
                && navOptions.shouldLaunchSingleTop()
                && mBackStack?.peekLast() == destId)
        val isAdded: Boolean = when {
            initialNavigation -> {
                true
            }
            isSingleTopReplacement -> {
                if (mBackStack?.size ?: 0 > 1) {
                    mFragmentManager.popBackStack(
                        generateBackStackName(mBackStack?.size ?: 0, mBackStack?.peekLast() ?: 0),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                    ft.addToBackStack(generateBackStackName(mBackStack?.size ?: 0, destId))
                }
                false
            }
            else -> {
                ft.addToBackStack(generateBackStackName(mBackStack?.size ?: 0 + 1, destId))
                true
            }
        }
        if (navigatorExtras is Extras) {
            for ((key, value) in navigatorExtras.sharedElements) {
                ft.addSharedElement(key, value)
            }
        }
        ft.setReorderingAllowed(true)
        ft.commit()
        return if (isAdded) {
            mBackStack?.add(destId)
            destination
        } else {
            null
        }
    }

    private fun generateBackStackName(backStackIndex: Int, destId: Int): String {
        return "$backStackIndex-$destId"
    }
}
