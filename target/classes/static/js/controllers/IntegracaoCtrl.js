'use strict';
var integracaoCtrl = function(NgTableParams, integracaoSrv, toaster, CONSTANTS, $timeout) {
    var vm = this;

    var status = {
        AGUARDANDO: 'Aguardando',
        INTEGRANDO: 'Integrando...',
        CONCLUIDO: 'Concluído',
        ERRO: 'ERRO'
    }

    vm.receberDados = receberDados;
    vm.enviarDados = enviarDados;
    vm.envio = {
        percentualProgresso: 0,
        statusIntegracao: status.AGUARDANDO
    }
    vm.receb = {
        percentualProgresso: 0,
        statusIntegracao: status.AGUARDANDO
    }

    vm.tableParams = new NgTableParams({ count: 1 },
        {
            counts: [],
            getData: function(params) {
                var ngParams = {
                    page: params.page() - 1,
                    size: 8
                }

                angular.extend(ngParams, params.filter());
                
                return integracaoSrv.listar(ngParams).then(function(res) {
                    params.total(res.data.totalPages);
                    return res.data.content;
                });
            }
        }
    );

    function enviarDados() {
        var progressoAtual = getRandomInt(60, 20);
        atualizarStatusIntegracao(vm.envio, progressoAtual, status.INTEGRANDO);

        $timeout(function() {
            atualizarStatusIntegracao(vm.envio, getRandomInt(progressoAtual + 35, progressoAtual + 5));
        }, 3000);

        $timeout(function() {
            integracaoSrv.enviarDados().then(function() {
                atualizarStatusIntegracao(vm.envio, 100, status.CONCLUIDO);
                toaster.pop('success', 'Integração realizada com sucesso');
            },
            function(res) {
                atualizarStatusIntegracao(vm.envio, 0, status.ERRO);
                toaster.pop('error', 'ERRO', res.data.erro);
            });
        },5000);
    }

    function receberDados() {
        var progressoAtual = getRandomInt(60, 20);
        atualizarStatusIntegracao(vm.receb, progressoAtual, status.INTEGRANDO);

        $timeout(function() {
            atualizarStatusIntegracao(vm.receb, getRandomInt(progressoAtual + 35, progressoAtual + 5));
        }, 3000);

        $timeout(function() {
            integracaoSrv.receberDados().then(function() {
                atualizarStatusIntegracao(vm.receb, 100, status.CONCLUIDO);
                toaster.pop('success', 'Integração realizada com sucesso');
                recarregar();
            },
            function(res) {
                atualizarStatusIntegracao(vm.receb, 0, status.ERRO);
                toaster.pop('error', 'ERRO', res.data.erro);
            });
        },5000);
    }

    function recarregar() {
        vm.tableParams.page(1);
        vm.tableParams.reload();
    }

    function atualizarStatusIntegracao(obj, percentual, status) {
        obj.percentualProgresso = percentual;

        if (status) {
            obj.statusIntegracao = status;
        }
    }

    function getRandomInt(max, min) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
};

integracaoCtrl.$inject = ['NgTableParams', 'IntegracaoSrv', 'toaster', 'CONSTANTS', '$timeout'];

app.controller('IntegracaoCtrl', integracaoCtrl);