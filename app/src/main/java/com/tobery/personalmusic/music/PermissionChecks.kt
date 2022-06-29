package com.tobery.personalmusic.music

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class PermissionChecks(context: Context) {
    private var permissionCallback: PermissionCallback? = null

    private var permissionRegister: ActivityResultLauncher<Array<String>>? = null


    init {
        if (context is ComponentActivity) {
            register(context)
        }
    }

    private fun register(activity: ComponentActivity){
        permissionRegister =
            activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ list ->
                val grantedList = list.filterValues { it }.mapNotNull { it.key }
                val allGranted = grantedList.size == grantedList.size
                permissionCallback?.invoke(allGranted)
            }
    }

    fun requestPermission(
        permission: String,
        callback: PermissionCallback? = null
    ) {
        this.permissionCallback = callback
        permissionRegister?.launch(arrayOf(permission))
    }

    fun requestPermissions(
        permissions: Array<String>,
        callback: PermissionCallback? = null
    ) {
        this.permissionCallback = callback
        permissionRegister?.launch(permissions)
    }
}
typealias PermissionCallback = (isGranted: Boolean) -> Unit