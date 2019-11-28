'use strict';

var comprasSrv = function($http, CONSTANTS) {
    var CONSTANTE_COMPRAS = CONSTANTS.compras;

    return {
        listar: function(params) {
            return $http.get(CONSTANTE_COMPRAS.baseUrl, { params: params });
        }
    }
};

comprasSrv.$inject = ['$http', 'CONSTANTS'];

app.service('ComprasSrv', comprasSrv);