package org.apache.cordova.plugin.sentinel.modules

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.apache.cordova.CallbackContext
import org.apache.cordova.plugin.sentinel.interfaces.ModuleDelegate
import org.json.JSONArray

class DeviceManager : ModuleDelegate {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    // private val deviceConnect = DeviceOnboardingControllerImpl()

    override fun executeAction(
        action: String,
        args: JSONArray,
        callbackContext: CallbackContext,
    ): Boolean {
        return when (action) {
            // "connectDevice" -> {
            //     val ssid = args.getString(0)
            //     connectDevice(ssid, callbackContext)
            //     true
            // }
            "getNetworksAvailable" -> {
                getNetworksAvailable(callbackContext)
                true
            }
            // "setupOnboarding" -> {
            //     val idLocation = args.getString(0)
            //     val ssid = args.getString(1)
            //     val password = args.getString(2)
            //     val deviceType = args.getString(3)
            //     val customerId = args.getString(4)
            //     setupOnboarding(idLocation, ssid, password, deviceType, callbackContext, customerId)
            //     true
            // }
            else -> false
        }
    }

    // private fun connectDevice(
    //     ssid: String,
    //     callbackContext: CallbackContext,
    // ) {
    //     coroutineScope.launch {
    //         try {
    //             val responseResult =
    //                 deviceConnect.connectToDevice(ssid) { result ->
    //                     callbackContext.success(result.toString())
    //                 }
    //         }
    //         catch(e: Exception) {
    //             callbackContext.error(e.toString())
    //         }
    //     }
    // }

    private fun getNetworksAvailable(callbackContext: CallbackContext) {
        coroutineScope.launch {
            callbackContext.success("Hola")
            // val responseResult = kotlin.runCatching { deviceConnect.listNetworks() }
            // callbackContext.success(responseResult.toString())
            // val jsonList = Json.encodeToString(responseResult)
            // callbackContext.success(jsonList)
        }
    }

    // private fun setupOnboarding(
    //     id_Location: String,
    //     ssid: String,
    //     password: String,
    //     deviceType: String,
    //     callbackContext: CallbackContext,
    //     customerId: String?,
    // ) {
    //     coroutineScope.launch {
    //         val responseResult =
    //             kotlin.runCatching {
    //                 deviceConnect.startOnboarding(id_Location, ssid, password, customerId, deviceType)
    //             }

    //         val result = responseResult.getOrNull()

    //         if (result != null) {
    //             if (result.success) {
    //                 val jsonValue = Json.encodeToString(result.data)
    //                 callbackContext.success(jsonValue.toString())
    //             } else {
    //                 callbackContext.error(result.errorCode.toString())
    //             }
    //         } else {
    //             callbackContext.error("Request failed with Exception")
    //         }
    //     }
    // }
}
