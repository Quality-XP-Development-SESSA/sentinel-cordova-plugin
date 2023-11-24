package org.apache.cordova.plugin.sentinel

import org.apache.cordova.CallbackContext
import org.apache.cordova.CordovaPlugin
import org.apache.cordova.plugin.sentinel.interfaces.ModuleDelegate
import org.apache.cordova.plugin.sentinel.modules.AuthModule
import org.apache.cordova.plugin.sentinel.modules.LocationModule
import org.json.JSONArray

class CordovaPluginSentinelSDK : CordovaPlugin() {
    private val authModule = AuthModule()

    private val authModuleDelegate: ModuleDelegate = AuthModule()
    private val locationModuleDelegate: ModuleDelegate = LocationModule()

    override fun execute(
        action: String,
        args: JSONArray,
        callbackContext: CallbackContext,
    ): Boolean {
        val (module, actionExec) = action.split(":")

        return when (module) {
            "AuthModule" -> authModuleDelegate.executeAction(actionExec, args, callbackContext)
            "LocationModule" -> locationModuleDelegate.executeAction(actionExec, args, callbackContext)
            else -> false
        }
    }
}
