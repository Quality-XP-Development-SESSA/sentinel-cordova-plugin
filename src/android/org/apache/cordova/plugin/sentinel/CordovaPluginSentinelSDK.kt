package org.apache.cordova.plugin.sentinel

import org.apache.cordova.CallbackContext
import org.apache.cordova.CordovaInterface
import org.apache.cordova.CordovaPlugin
import org.apache.cordova.plugin.sentinel.interfaces.ModuleDelegate
import org.apache.cordova.plugin.sentinel.modules.AuthModule
import org.apache.cordova.plugin.sentinel.modules.CustomersModule
import org.apache.cordova.plugin.sentinel.modules.DevicesModule
import org.apache.cordova.plugin.sentinel.modules.LocationModule
import org.apache.cordova.plugin.sentinel.modules.WifiDiscovery
import org.json.JSONArray

class CordovaPluginSentinelSDK : CordovaPlugin() {
    private var appContext: Context? = null

    override fun initialize(cordova: CordovaInterface) {
        super.initialize(cordova)
        appContext = cordova.activity?.applicationContext
    }

    private val authModuleDelegate: ModuleDelegate = AuthModule()
    private val customersModuleDelegate: ModuleDelegate = CustomersModule()
    private val devicesModuleDelegate: ModuleDelegate = DevicesModule()
    private val locationModuleDelegate: ModuleDelegate = LocationModule()
    private val wifiDiscoveryModuleDelegate: ModuleDelegate = WifiDiscovery(appContext)

    override fun execute(
        action: String,
        args: JSONArray,
        callbackContext: CallbackContext,
    ): Boolean {
        val (module, actionExec) = action.split(":")

        return when (module) {
            "AuthModule" -> authModuleDelegate.executeAction(actionExec, args, callbackContext)
            "CustomersModule" -> customersModuleDelegate.executeAction(actionExec, args, callbackContext)
            "DevicesModule" -> devicesModuleDelegate.executeAction(actionExec, args, callbackContext)
            "LocationModule" -> locationModuleDelegate.executeAction(actionExec, args, callbackContext)
            "WifiDiscovery" -> wifiDiscoveryModuleDelegate.executeAction(actionExec, args, callbackContext)
            else -> false
        }
    }
}
