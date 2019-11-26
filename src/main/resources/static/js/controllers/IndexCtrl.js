'use strict';
var indexCtrl = function($http, $state, $scope, $rootScope, AuthSrv) {
    $rootScope.$on('LoginSuccessful', function() {
        $scope.user = AuthSrv.user;
    });

    $rootScope.$on('LogoutSuccessful', function() {
        $scope.user = null;
    });

    $scope.logout = function() {
        AuthSrv.user = null;
        $rootScope.$broadcast('LogoutSuccessful');
        $state.go('login');
    };
}

indexCtrl.$inject = ['$http', '$state', '$scope', '$rootScope', 'AuthSrv'];

app.controller('IndexCtrl', indexCtrl);