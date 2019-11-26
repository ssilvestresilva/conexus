'use strict';

var loginSrv = function($http) {

    return {
        autenticar: function(params) {
            return $http.post('/auth', JSON.stringify(params));
        }
    }
};

loginSrv.$inject = ['$http'];

app.service('LoginSrv', loginSrv);