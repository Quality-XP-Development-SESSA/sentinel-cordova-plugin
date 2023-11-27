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

class DevicesModule : ModuleDelegate {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private val cloudProvider = SentinelProvider.cloudProvider
    private val deviceServices = cloudProvider.devicesCloudServices

    override fun executeAction(
        action: String,
        args: JSONArray,
        callbackContext: CallbackContext,
    ): Boolean {
        val result =
            when (action) {
                "getSensors" -> {
                    val filterValue = args.getString(0)
                    getSensors(filterValue, callbackContext)
                    true
                }
                "deleteSensors" -> {
                    val ids = args.getString(0)
                    deleteSensors(ids, callbackContext)
                    true
                }
                "editSensorName" -> {
                    val id = args.getString(0)
                    val name = args.getString(1)
                    editSensorName(id, name, callbackContext)
                    true
                }
                "getGateways" -> {
                    val filterValue = args.getString(0)
                    getGateways(filterValue, callbackContext)
                    true
                }
                "deleteGateways" -> {
                    val id = args.getString(0)
                    deleteGateways(id, callbackContext)
                    true
                }
                "editGatewaysName" -> {
                    val id = args.getString(0)
                    val name = args.getString(1)
                    editGatewaysName(id, name, callbackContext)
                    true
                }
                "getCustomerDevices" -> {
                    val customerId = args.getString(0)
                    getCustomerDevices(customerId, callbackContext)
                    true
                }
                else -> false
            }

        return result
    }

    private fun getSensors(
        filter: String?,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching { deviceServices.getSensors(filter) }

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

    private fun deleteSensors(
        ids: String,
        callbackContext: CallbackContext,
    ) {
        val sensors: Array<Int> = arrayOf(ids.toInt())
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching {
                    deviceServices.deleteSensors(sensors)
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

    private fun editSensorName(
        id: String,
        name: String,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching {
                    deviceServices.editSensorName(id.toInt(), name)
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

    private fun getGateways(
        filter: String?,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching { deviceServices.getGateways(filter) }

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

    private fun deleteGateways(
        id: String,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching {
                    deviceServices.deleteGateway(id.toInt())
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

    private fun editGatewaysName(
        id: String,
        name: String,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching {
                    deviceServices.editGateways(id.toInt(), name)
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

    private fun getCustomerDevices(
        customerId: String,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching {
                    deviceServices.getCustomerDevices(customerId)
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
}
