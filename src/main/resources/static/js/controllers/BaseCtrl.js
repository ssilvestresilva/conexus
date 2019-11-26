'use strict';
var baseCtrl = function($http, $state, $scope, $rootScope, AuthSrv) {
    $scope.logout = function() {
        AuthSrv.user = null;
        $rootScope.$broadcast('LogoutSuccessful');
        $state.go('login');
    };
}

baseCtrl.$inject = ['$http', '$state', '$scope', '$rootScope', 'AuthSrv'];

app.controller('BaseCtrl', baseCtrl);