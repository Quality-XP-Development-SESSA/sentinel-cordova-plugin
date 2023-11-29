// Device Module
exports.getSensors = function(filterValue, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'DevicesModule:getSensors', [filterValue]);
}

exports.deleteSensors = function(ids, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'DevicesModule:deleteSensors', [ids]);
}

exports.editSensorName = function(id, name, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'DevicesModule:editSensorName', [id, name]);
}

exports.getGateways = function(filterValue, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'DevicesModule:getGateways', [filterValue]);
}

exports.deleteGateways = function(id, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'DevicesModule:deleteGateways', [id]);
}

exports.editGatewaysName = function(id, name, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'DevicesModule:editGatewaysName', [id, name]);
}

exports.getCustomerDevices = function(customerId, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, 'CordovaPluginSentinelSDK', 'DevicesModule:getCustomerDevices', [customerId]);
}


