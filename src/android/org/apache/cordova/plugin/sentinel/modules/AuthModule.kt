package org.apache.cordova.plugin.modules

import com.qxdev.sentinel_sdk.SentinelProvider
import com.qxdev.sentinel_sdk.cloud.data.auth.User
import com.qxdev.sentinel_sdk.cloud.data.auth.UserData
import com.qxdev.sentinel_sdk.di.Koin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.apache.cordova.CallbackContext
import org.koin.core.context.GlobalContext.startKoin

class AuthModule {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private val cloudProvider = SentinelProvider.cloudProvider
    private val authServices = cloudProvider.authCloudServices

    init {
        startKoin {
            modules(
                Koin.sentinelSDKModule("https://api-stage.sensys-iot.com"),
            )
        }
    }

    fun signIn(
        username: String,
        password: String,
        callbackContext: CallbackContext,
    ) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching { authServices.login(User(username.trim(), password.trim())) }

            val result = responseResult.getOrNull()

            if (result != null) {
                if (result.success) {
                    callbackContext.success(result.data.toString())
                } else {
                    callbackContext.error("Oops! Something went wrong")
                }
            } else {
                callbackContext.error("Request failed with Exception ${responseResult.exceptionOrNull()?.message}")
            }
        }
    }

    fun signOut(callbackContext: CallbackContext) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching { authServices.logout() }

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

    fun accountType(callbackContext: CallbackContext) {
        coroutineScope.launch {
            val responseResult =
                kotlin.runCatching { authServices.getAccountType() }
            val result = responseResult.getOrNull()

            if (result != null) {
                callbackContext.success(result.toString())
            } else {
                callbackContext.error("Request failed with Exception ${responseResult.exceptionOrNull()?.message}")
            }
        }
    }

    fun recoverPassword(
        email: String,
        callbackContext: CallbackContext,
    ) {
        if (email == null) {
            callbackContext.error("error")
        } else {
            coroutineScope.launch {
                val responseResult =
                    kotlin.runCatching { authServices.changePassword(UserData(email.trim())) }

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
}
