'use strict';

var alunoSrv = function($http, CONSTANTS) {
    var CONSTANTE_ALUNO = CONSTANTS.aluno;

    return {
        listar: function(params) {
            return $http.get(CONSTANTE_ALUNO.baseUrl, { params: params });
        },
        inserir: function(params) {
            return $http.post(CONSTANTE_ALUNO.baseUrl, params);
        },
        atualizar: function(params) {
            return $http.put(CONSTANTE_ALUNO.baseUrl, params);
        }
    }
};

alunoSrv.$inject = ['$http', 'CONSTANTS'];

app.service('AlunoSrv', alunoSrv);