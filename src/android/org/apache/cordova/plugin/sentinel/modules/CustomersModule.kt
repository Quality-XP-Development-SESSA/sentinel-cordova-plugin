package org.apache.cordova.plugin.sentinel.modules

import com.qxdev.sentinel_sdk.SentinelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.apache.cordova.CallbackContext
import org.apache.cordova.plugin.sentinel.interfaces.ModuleDelegate
import org.json.JSONArray

class CustomersModule : ModuleDelegate {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private val cloudProvider = SentinelProvider.cloudProvider
    private val customersServices = cloudProvider.customersCloudServices

    override fun executeAction(
        action: String,
        args: JSONArray,
        callbackContext: CallbackContext,
    ): Boolean {
        return when (action) {
            "getCustomerList" -> {
                getCustomerList(callbackContext)
                true
            }
            "getFilterCustomerList" -> {
                val filterValue = args.getString(0)
                getFilterCustomerList(filterValue, callbackContext)
                true
            }
            else -> false
        }
    }

    private fun getCustomerList(callbackContext: CallbackContext) {
        coroutineScope.launch {
            val responseResult = kotlin.runCatching { customersServices.getCustomerList() }
            val result = responseResult.getOrNull()
            if (result != null) {
                if (result.success) {
                    val jsonList = Json.encodeToString(result.data)
                    callbackContext.success(jsonList.toString())
                } else {
                    callbackContext.error(result.errorCode.toString())
                }
            } else {
                callbackContext.error("Request failed with Exception ${responseResult.exceptionOrNull()?.message}")
            }
        }
    }

    private fun getFilterCustomerList(
        filterValue: String,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult = kotlin.runCatching { customersServices.filterCustomerList(filterValue) }
            val result = responseResult.getOrNull()
            if (result != null) {
                if (result.success) {
                    val jsonList = Json.encodeToString(result.data)
                    callbackContext.success(jsonList.toString())
                } else {
                    callbackContext.error(result.errorCode.toString())
                }
            } else {
                callbackContext.error("Request failed with Exception ${responseResult.exceptionOrNull()?.message}")
            }
        }
    }
}
