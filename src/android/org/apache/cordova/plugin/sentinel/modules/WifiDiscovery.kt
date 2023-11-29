package org.apache.cordova.plugin.sentinel.modules

import android.content.Context
import com.qxdev.sentinel_sdk.wifi.wifiDiscovery.WifiScannerImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.cordova.CallbackContext
import org.apache.cordova.plugin.sentinel.interfaces.ModuleDelegate
import org.json.JSONArray

class WifiDiscovery(context: Context) : ModuleDelegate {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private val wifiScanner = WifiScannerImpl(context)

    override fun executeAction(
        action: String,
        args: JSONArray,
        callbackContext: CallbackContext,
    ): Boolean {
        return when (action) {
            "startScan" -> {
                val deviceType = args.getString(0)
                startScan(deviceType, callbackContext)
                true
            }
            else -> false
        }
    }

    private fun startScan(
        deviceType: String,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            try {
                wifiScanner.startScan(deviceType) { result ->
                    val jsonList = Json.encodeToString(result)
                    callbackContext.success(jsonList)
                }
            } catch (e: Exception) {
                callbackContext.error(e.message)
            }
        }
    }
}
