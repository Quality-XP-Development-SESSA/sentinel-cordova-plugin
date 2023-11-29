// exports.coolMethod = function (arg0, success, error) {
//     exec(success, error, 'cordova-plugin-sentinel-sdk', 'coolMethod', [arg0]);
// };


// Auth Module
exports.login =  function(username, password, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, "CordovaPluginSentinelSDK", "AuthModule:signIn", [username, password]);
}

exports.logout =  function(successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, "CordovaPluginSentinelSDK", "AuthModule:signOut", []);
}

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

// Customer Module
exports.getCustomerList = function(successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, "CordovaPluginSentinelSDK", "CustomersModule:getCustomerList", []);
}

exports.getFilterCustomerList = function(filterValue, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, "CordovaPluginSentinelSDK", "CustomersModule:getFilterCustomerList", [filterValue]);
}

