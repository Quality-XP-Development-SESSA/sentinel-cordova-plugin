var exec = require('cordova/exec');

// exports.coolMethod = function (arg0, success, error) {
//     exec(success, error, 'cordova-plugin-sentinel-sdk', 'coolMethod', [arg0]);
// };

var SentinelSDK = {};

SentinelSDK.login = function(username, password, successCallback, errorCallback){
    if(username != null || password != null) return
    
    cordova.exec(successCallback, errorCallback, "CordovaPluginSentinelSDK", "AuthModule:signIn", [username, password]);
}

module.exports = SentinelSDK;


