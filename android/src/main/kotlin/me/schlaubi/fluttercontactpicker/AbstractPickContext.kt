package me.schlaubi.fluttercontactpicker

import android.content.Intent
import io.flutter.plugin.common.PluginRegistry

abstract class AbstractPickContext : PickContext {

    private val listeners = mutableListOf<PluginRegistry.ActivityResultListener>()

    override fun removeActivityResultListener(listener: PluginRegistry.ActivityResultListener) = listeners.remove(listener).run { Unit }

    override fun addActivityResultListener(listener: PluginRegistry.ActivityResultListener) = listeners.add(listener).run { Unit }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        listeners.forEach { it.onActivityResult(requestCode, resultCode, data) }
        return true
    }
}