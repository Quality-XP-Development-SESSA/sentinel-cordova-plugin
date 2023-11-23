package org.apache.cordova.plugin

import org.apache.cordova.CallbackContext
import org.apache.cordova.CordovaPlugin
import org.apache.cordova.plugin.modules.AuthModule
import org.json.JSONArray

class CordovaPluginSentinelSDK : CordovaPlugin() {
    private val authModule = AuthModule()

    override fun execute(
        action: String,
        args: JSONArray,
        callbackContext: CallbackContext,
    ): Boolean {
        if (action == "signIn") {
            val username = args.getString(0)
            val password = args.getString(1)
            authModule.signIn(username, password, callbackContext)
            return true
        }
        return false
    }
}
