package org.apache.cordova.plugin.sentinel.interfaces

import org.apache.cordova.CallbackContext
import org.json.JSONArray

interface ModuleDelegate {
    fun executeAction(
        action: String,
        args: JSONArray,
        callbackContext: CallbackContext,
    ): Boolean
}
