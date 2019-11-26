'use strict';

var produtoSrv = function($http, CONSTANTS) {
    var CONSTANTE_PRODUTO = CONSTANTS.produto;

    return {
        listar: function(params) {
            return $http.get(CONSTANTE_PRODUTO.baseUrl, { params: params });
        },
        atualizar: function(params) {
            return $http.put(CONSTANTE_PRODUTO.baseUrl, params);
        }
    }
};

produtoSrv.$inject = ['$http', 'CONSTANTS'];

app.service('ProdutoSrv', produtoSrv);