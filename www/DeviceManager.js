// Device Manager Module
exports.connectDevice = function(ssid, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'DeviceManager:connectDevice', [ssid]);
}

exports.getNetworksAvailable = function(successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'DeviceManager:getNetworksAvailable', []);
}

exports.setupOnboarding = function(idLocation, ssid, password, deviceType, customerId, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'DeviceManager:setupOnboarding', [idLocation, ssid, password, deviceType, customerId]);
}