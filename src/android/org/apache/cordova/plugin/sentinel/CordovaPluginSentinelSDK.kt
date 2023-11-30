package org.apache.cordova.plugin.sentinel

import android.content.Context
import com.qxdev.sentinel_sdk.di.Koin
import com.qxdev.sentinel_sdk.onboarding.ConnectionToDevice
import com.qxdev.sentinel_sdk.onboarding.interfaces.WifiManager
import org.apache.cordova.CallbackContext
import org.apache.cordova.CordovaPlugin
import org.apache.cordova.plugin.sentinel.interfaces.ModuleDelegate
import org.apache.cordova.plugin.sentinel.modules.AuthModule
import org.apache.cordova.plugin.sentinel.modules.CustomersModule
import org.apache.cordova.plugin.sentinel.modules.DeviceManager
import org.apache.cordova.plugin.sentinel.modules.DevicesModule
import org.apache.cordova.plugin.sentinel.modules.LocationModule
import org.json.JSONArray
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
// import org.apache.cordova.plugin.sentinel.modules.WifiDiscovery
// import android.content.Context

/*
    Delete this imports

    import org.koin.dsl.module
    import org.koin.core.context.GlobalContext.startKoin
    import com.qxdev.sentinel_sdk.di.Koin
    import com.qxdev.sentinel_sdk.onboarding.ConnectionToDevice
    import com.qxdev.sentinel_sdk.onboarding.interfaces.WifiManager
 */

class CordovaPluginSentinelSDK : CordovaPlugin() {
    private val cordovaContext: Context = cordova.context.applicationContext

    // Delete this init
    init {
        startKoin {
            modules(
                module {
                    single<WifiManager> { ConnectionToDevice(cordovaContext) }
                },
                Koin.sentinelSDKModule("https://api-stage.sensys-iot.com"),
            )
        }
    }
    //

    private val authModuleDelegate: ModuleDelegate = AuthModule()

    private val deviceManagerDelegate: ModuleDelegate = DeviceManager()
    private val devicesModuleDelegate: ModuleDelegate = DevicesModule()
    private val locationModuleDelegate: ModuleDelegate = LocationModule()
    private val customersModuleDelegate: ModuleDelegate = CustomersModule()
    // private val wifiDiscoveryModuleDelegate: ModuleDelegate = WifiDiscovery(cordova.activity.applicationContext)

    override fun execute(
        action: String,
        args: JSONArray,
        callbackContext: CallbackContext,
    ): Boolean {
        val (module, actionExec) = action.split(":")

        return when (module) {
            "AuthModule" -> authModuleDelegate.executeAction(actionExec, args, callbackContext)
            "DeviceManager" -> deviceManagerDelegate.executeAction(actionExec, args, callbackContext)
            "CustomersModule" -> customersModuleDelegate.executeAction(actionExec, args, callbackContext)
            "DevicesModule" -> devicesModuleDelegate.executeAction(actionExec, args, callbackContext)
            "LocationModule" -> locationModuleDelegate.executeAction(actionExec, args, callbackContext)
            // "WifiDiscovery" -> wifiDiscoveryModuleDelegate.executeAction(actionExec, args, callbackContext)
            else -> false
        }
    }
}
