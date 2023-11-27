package org.apache.cordova.plugin.sentinel.modules

import com.qxdev.sentinel_sdk.onboarding.DeviceOnboardingControllerImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.cordova.CallbackContext
import org.apache.cordova.plugin.sentinel.interfaces.ModuleDelegate

class DeviceManager : ModuleDelegate {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private val deviceConnect = DeviceOnboardingControllerImpl()

    override fun executeAction(
        action: String,
        args: JSONArray,
        callbackContext: CallbackContext,
    ): Boolean {
        return when (action) {
            "connectDevice" -> {
                val ssid = args.getString(0)
                connectDevice(ssid, callbackContext)
                true
            }
            "getNetworksAvailable" -> {
                getNetworksAvailable(callbackContext)
                true
            }
            "setupOnboarding" -> {
                val locationId = args.getString(0)
                val ssid = args.getString(1)
                val password = args.getString(2)
                val deviceType = args.getString(3)
                val customerId = args.getString(4)
                setupOnboarding(locationId, ssid, password, deviceType, customerId, callbackContext)
                true
            }
            else -> false
        }
    }

    private fun connectDevice(
        ssid: String,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            try {
                deviceConnect.connectToDevice(ssid) { result ->
                    callbackContext.success(result.toString())
                }
            } catch (e: Exception) {
                callbackContext.error(e.message)
            }
        }
    }

    private fun getNetworksAvailable(callbackContext: CallbackContext) {
        coroutineScope.launch {
            try {
                val responseResult = deviceConnect.listNetworks()
                val jsonList = Json.encodeToString(responseResult)
                callbackContext.success(jsonList)
            } catch (e: Exception) {
                callbackContext.error("Request failed with Exception ${responseResult.exceptionOrNull()?.message}")
            }
        }
    }

    private fun setupOnboarding(
        locationId: String,
        ssid: String,
        password: String,
        deviceType: String,
        customerId: String?,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching {
                    deviceConnect.startOnboarding(locationId, ssid, password, customerId, deviceType)
                }

            val result = responseResult.getOrNull()

            if (result != null) {
                if (result.success) {
                    val jsonValue = Json.encodeToString(result.data)
                    callbackContext.success(jsonValue.toString())
                } else {
                    callbackContext.error(result.errorCode.toString())
                }
            } else {
                callbackContext.error("Request failed with Exception ${responseResult.exceptionOrNull()?.message}")
            }
        }
    }
}
