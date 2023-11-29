// Location Module
exports.getLocation = function(successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, "CordovaPluginSentinelSDK", "LocationModule:getLocations", []);
}

exports.getFilterLocation = function(filerValue, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, "CordovaPluginSentinelSDK", "LocationModule:getLocations", [filerValue]);
}

exports.getSensors = function(locationId, filterValue, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'LocationModule:getSensors', [locationId, filterValue]);
}

exports.getGateways = function(locationId, filterValue, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'LocationModule:getGateways', [locationId, filterValue]);
}

exports.deleteLocation = function(locationId, transferLocationId, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'LocationModule:deleteLocation', [locationId, transferLocationId]);
}