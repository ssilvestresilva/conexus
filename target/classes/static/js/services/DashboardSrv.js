'use strict';

var dashboardSrv = function($http, CONSTANTS) {
    var CONSTANTE_RESULTADO = CONSTANTS.resultado;

    return {
        fonteAprovacoes: function() {
            return $http.get(CONSTANTE_RESULTADO.baseUrl + "/aprovacoes");
        },
        fonteFaixaEtaria: function() {
            return $http.get(CONSTANTE_RESULTADO.baseUrl + "/faixa-etaria");
        },
        fonteMediaNotas: function() {
            return $http.get(CONSTANTE_RESULTADO.baseUrl + "/media-notas");
        },
        fontePredominancia: function() {
            return $http.get(CONSTANTE_RESULTADO.baseUrl + "/predominancia");
        }
    }
};

dashboardSrv.$inject = ['$http', 'CONSTANTS'];

app.service('DashboardSrv', dashboardSrv);