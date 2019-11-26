'use strict';
var cursoCtrl = function(NgTableParams, cursoSrv, toaster, CONSTANTS) {
    var vm = this;
    limparCadastro();

    vm.salvar = salvar;
    vm.areasDeConhecimento = CONSTANTS.curso.areasDeConhecimento;
    vm.obterSegmento = obterSegmento;
    vm.limparCadastro = limparCadastro;
    vm.desabilitarBtnSalvar = desabilitarBtnSalvar;
    vm.editar = editar;
    vm.atualizarStatus = atualizarStatus;
    vm.recarregar = recarregar;

    vm.tableParams = new NgTableParams({ count: 1 },
        {
            counts: [],
            getData: function(params) {
                var ngParams = {
                    page: params.page() - 1,
                    size: 5,
                    sort: 'id,DESC'
                }

                angular.extend(ngParams, params.filter());
                
                return cursoSrv.listar(ngParams).then(function(res) {
                    params.total(res.data.totalPages);
                    return res.data.content;
                });
            }
        }
    );

    function salvar(cadastro, successCb) {
        configurarStatusPadrao(cadastro);

        var metodo = cadastro.id ? 'atualizar' : 'inserir';
        cursoSrv[metodo](cadastro).then(function(res) {
            if (successCb) {
                successCb();
            }
            toaster.pop('success', 'Registro salvo com sucesso');
        }, function(res) {
            toaster.pop('error', "Erro", res.data.erro);
        });
    }
    
    function configurarStatusPadrao(cadastro) {
        if (cadastro.ativo === undefined) {
            cadastro.ativo = true;
        }
    }
    
    function recarregar() {
        vm.tableParams.page(1);
        vm.tableParams.reload();
        limparCadastro();
    }

    function desabilitarBtnSalvar() {
        return !vm.cadastro.descricao ||
                !vm.cadastro.segmento ||
                !vm.cadastro.periodo;
        
    }

    function obterSegmento(codSegmento) {
        var segmento = vm.areasDeConhecimento.get(codSegmento);
        return segmento ? segmento.descricao : '-';
    }

    function limparCadastro() {
        vm.cadastro = {};
    }

    function editar(curso) {
        limparCadastro();
        vm.cadastro = angular.copy(curso);
    }

    function atualizarStatus(curso) {
        salvar(curso);
    }
};

cursoCtrl.$inject = ['NgTableParams', 'CursoSrv', 'toaster', 'CONSTANTS'];

app.controller('CursoCtrl', cursoCtrl);