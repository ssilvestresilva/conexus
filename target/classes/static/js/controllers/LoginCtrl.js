'use strict';
var loginCtrl = function($http, $state, $rootScope, AuthSrv, loginSrv, toaster) {
    var vm = this;
    vm.entrar = entrar;

    function entrar() {
        if (!vm.email || !vm.senha) {
            toaster.pop('info', 'Informe as credenciais para acesso');
        }

        loginSrv.autenticar({ email: vm.email, senha: vm.senha }).then(function(res) {
            vm.senha = null;
            if (res.data.data.token) {
                $http.defaults.headers.common['Authorization'] = 'Bearer ' + res.data.data.token;
                AuthSrv.user = res.data.data.user;
                $rootScope.$broadcast('LoginSuccessful');
                //$location.path('/dashboard');
                $state.go('dashboard');
            } else {
                toaster.pop('error', 'Falha ao Autenticar !');
            }
        }, function(res) {
            toaster.pop('error', "Erro", res.data.erro);
        })
    }
}

loginCtrl.$inject = ['$http', '$state', '$rootScope', 'AuthSrv', 'LoginSrv', 'toaster'];

app.controller('LoginCtrl', loginCtrl);