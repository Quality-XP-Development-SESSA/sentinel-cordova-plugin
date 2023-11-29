// Auth Module
exports.login =  function(username, password, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, "CordovaPluginSentinelSDK", "AuthModule:signIn", [username, password]);
}

exports.logout =  function(successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, "CordovaPluginSentinelSDK", "AuthModule:signOut", []);
}