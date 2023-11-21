package org.apache.cordova.plugin.modules

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.cordova.CallbackContext

import com.qxdev.sentinel_sdk.cloud.data.Locations
import com.qxdev.sentinel_sdk.cloud.data.Response
import com.qxdev.sentinel_sdk.SentinelProvider


class LocationModule {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private val cloudProvider = SentinelProvider.cloudProvider
    private val locationServices = cloudProvider.locationsCloudServices

    fun getLocations(callbackContext: CallbackContext) {
        coroutineScope.launch {
            val responseResult = kotlin.runCatching {
                locationServices.getLocation()
            }

            val result = responseResult.getOrNull()

            if(result != null){
                if (result.success){
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

    fun getFilterLocation(filterValue: String, callbackContext: CallbackContext) {
        coroutineScope.launch {
            val responseResult = kotlin.runCatching {
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

    fun getSensors(locationId: String, filterValue: String, callbackContext: CallbackContext) {
        coroutineScope.launch {
            val responseResult = kotlin.runCatching {
                locationServices.getSensors(locationId, filterValue)
            }

            val result = responseResult.getOrNull()

            if(result != null) {
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

    fun getGateways(locationId: String, filterValue: String, callbackContext: CallbackContext) {
            coroutineScope.launch {
            val responseResult = kotlin.runCatching {
                locationServices.getGateways(locationId, filterValue)
            }

            val result = responseResult.getOrNull()

            if(result != null) {
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

    fun deleteLocation(locationId: Int, transferLocationId: String?, callbackContext: CallbackContext){
        coroutineScope.launch {
            val responseResult = kotlin.runCatching {
                locationServices.deleteLocation(locationId, transferLocationId)
            }

            val result = response.getOrNull()

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