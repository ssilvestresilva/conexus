'use strict';

var integracaoSrv = function($http, CONSTANTS) {
    var CONSTANTE_INTEGRACAO = CONSTANTS.integracao;

    return {
        listar: function(params) {
            return $http.get(CONSTANTE_INTEGRACAO.baseUrl, { params: params });
        },
        receberDados: function() {
            return $http.put(CONSTANTE_INTEGRACAO.baseUrl);
        },
        enviarDados: function() {
            return $http.post(CONSTANTE_INTEGRACAO.baseUrl);
        }
    }
};

integracaoSrv.$inject = ['$http', 'CONSTANTS'];

app.service('IntegracaoSrv', integracaoSrv);