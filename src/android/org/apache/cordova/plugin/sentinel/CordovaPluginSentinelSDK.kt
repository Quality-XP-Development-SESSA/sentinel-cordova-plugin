package org.apache.cordova.plugin

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.apache.cordova.CallbackContext
import org.apache.cordova.CordovaPlugin
import org.json.JSONArray
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

import com.qxdev.sentinel_sdk.cloud.data.auth.User
import com.qxdev.sentinel_sdk.cloud.data.auth.UserData
import com.qxdev.sentinel_sdk.cloud.data.Response
import com.qxdev.sentinel_sdk.di.Koin
import com.qxdev.sentinel_sdk.SentinelProvider

import org.apache.cordova.plugin.modules.AuthModule


class CordovaPluginSentinelSDK : CordovaPlugin() {

   private val authModule = AuthModule()


   override fun execute(
         action: String,
         args: JSONArray,
         callbackContext: CallbackContext
   ): Boolean {
      if (action == "coolMethod") {
         val firstNumber = args.getInt(0)
         val secondNumber = args.getInt(1)
         this.coolMethod(firstNumber, secondNumber, callbackContext)
         return true
      }
      if (action == "signIn") {
         val username = args.getString(0)
         val password = args.getString(1)
         authModule.signIn(username, password, callbackContext)
         return true
      }
      return false
   }

   private fun coolMethod(firstNumber: Int, secondNumber: Int, callbackContext: CallbackContext) {
      if (firstNumber !== null && secondNumber !== null) {
         val result = firstNumber + secondNumber
         val message = "El resultado de $firstNumber + $secondNumber es $result"
         callbackContext.success(message)
      } else {
         callbackContext.error("Expected one non-empty string argument.")
      }
   }


}
