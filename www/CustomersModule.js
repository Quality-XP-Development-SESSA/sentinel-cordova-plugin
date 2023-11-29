// Customers Module
exports.getCustomerList = function(successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, "CordovaPluginSentinelSDK", "CustomersModule:getCustomerList", []);
}

exports.getFilterCustomerList = function(filterValue, successCallback, errorCallback){
    cordova.exec(successCallback, errorCallback, "CordovaPluginSentinelSDK", "CustomersModule:getFilterCustomerList", [filterValue]);
}