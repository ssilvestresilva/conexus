'use strict';

var cursoSrv = function($http, CONSTANTS) {
    var CONSTANTE_CURSO = CONSTANTS.curso;

    return {
        listar: function(params) {
            return $http.get(CONSTANTE_CURSO.baseUrl, { params: params });
        },
        listarTodos: function() {
            return $http.get(CONSTANTE_CURSO.baseUrl + "/todos");
        },
        inserir: function(params) {
            return $http.post(CONSTANTE_CURSO.baseUrl, params);
        },
        atualizar: function(params) {
            return $http.put(CONSTANTE_CURSO.baseUrl, params);
        }
    }
};

cursoSrv.$inject = ['$http', 'CONSTANTS'];

app.service('CursoSrv', cursoSrv);