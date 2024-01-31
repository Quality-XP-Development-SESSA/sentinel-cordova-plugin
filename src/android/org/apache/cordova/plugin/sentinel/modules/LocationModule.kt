package org.apache.cordova.plugin.sentinel.modules

import com.qxdev.sentinel_sdk.SentinelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.cordova.CallbackContext
import org.apache.cordova.plugin.sentinel.interfaces.ModuleDelegate
import org.json.JSONArray

class LocationModule : ModuleDelegate {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private val cloudProvider = SentinelProvider.cloudProvider
    private val locationServices = cloudProvider.locationsCloudServices

    override fun executeAction(
        action: String,
        args: JSONArray,
        callbackContext: CallbackContext,
    ): Boolean {
        return when (action) {
            "getLocations" -> {
                getLocations(callbackContext)
                true
            }
            "getFilterLocation" -> {
                val filterValue = args.getString(0)
                getFilterLocation(filterValue, callbackContext)
                true
            }
            "getSensors" -> {
                val locationId = args.getString(0)
                val filterValue = args.getString(1)
                getSensors(locationId, filterValue, callbackContext)
                true
            }
            "getGateways" -> {
                val locationId = args.getString(0)
                val filterValue = args.getString(1)
                getGateways(locationId, filterValue, callbackContext)
                true
            }
            "deleteLocation" -> {
                val locationId = args.getString(0)
                val filterValue = args.getString(1)
                getGateways(locationId, filterValue, callbackContext)
                true
            }
            else -> false
        }
    }

    private fun getLocations(callbackContext: CallbackContext) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching {
                    locationServices.getLocation()
                }

            val result = responseResult.getOrNull()

            if (result != null) {
                if (result.success) {
                    val jsonList = Json.encodeToString(result.data)
                    callbackContext.success(jsonList)
                } else {
                    callbackContext.error(result.errorCode.toString())
                }
            } else {
                callbackContext.error("Request failed with Exception ${responseResult.exceptionOrNull()?.message}")
            }
        }
    }

    private fun getFilterLocation(
        filterValue: String,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching {
                    locationServices.filterLocationList(filterValue)
                }

            val result = responseResult.getOrNull()

            if (result != null) {
                if (result.success) {
                    val jsonList = Json.encodeToString(result.data)
                    callbackContext.success(jsonList)
                } else {
                    callbackContext.error(result.errorCode.toString())
                }
            } else {
                callbackContext.error("Request failed with Exception ${responseResult.exceptionOrNull()?.message}")
            }
        }
    }

    private fun getSensors(
        locationId: String,
        filterValue: String,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching {
                    locationServices.getSensors(locationId, filterValue)
                }

            val result = responseResult.getOrNull()

            if (result != null) {
                if (result.success) {
                    val jsonList = Json.encodeToString(result.data)
                    callbackContext.success(jsonList)
                } else {
                    callbackContext.error(result.errorCode.toString())
                }
            } else {
                callbackContext.error("Request failed with Exception ${responseResult.exceptionOrNull()?.message}")
            }
        }
    }

    private fun getGateways(
        locationId: String,
        filterValue: String,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching {
                    locationServices.getGateways(locationId, filterValue)
                }

            val result = responseResult.getOrNull()

            if (result != null) {
                if (result.success) {
                    val jsonList = Json.encodeToString(result.data)
                    callbackContext.success(jsonList)
                } else {
                    callbackContext.error(result.errorCode.toString())
                }
            } else {
                callbackContext.error("Request failed with Exception ${responseResult.exceptionOrNull()?.message}")
            }
        }
    }

    private fun deleteLocation(
        locationId: Int,
        transferLocationId: String?,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching {
                    locationServices.deleteLocation(locationId, transferLocationId)
                }

            val result = responseResult.getOrNull()

            if (result != null) {
                if (result.success) {
                    callbackContext.success(result.data.toString())
                } else {
                    callbackContext.error(result.errorCode.toString())
                }
            } else {
                callbackContext.error("Request failed with Exception ${responseResult.exceptionOrNull()?.message}")
            }
        }
    }
}
