'use strict';
var produtoCtrl = function(NgTableParams, produtoSrv, toaster) {
    var vm = this;
    vm.limparCadastro = limparCadastro;
    vm.recarregar = recarregar;
    vm.salvar = salvar;
    vm.editar = editar;
    vm.desabilitarBtnSalvar = desabilitarBtnSalvar;
    vm.atualizarStatus = atualizarStatus;

    limparCadastro();

    vm.tableParams = new NgTableParams({ count: 1 },
        {
            counts: [],
            getData: function(params) {
                var ngParams = {
                    page: params.page() - 1,
                    size: 10,
                    sort: 'id,ASC'
                }

                angular.extend(ngParams, params.filter());
                
                return produtoSrv.listar(ngParams).then(function(res) {
                    params.total(res.data.totalPages);
                    var produtos = res.data.content;
                    produtos.forEach(a => a.dtaCriacao = dayjs(a.dtaCriacao).format('DD/MM/YYYY'));
                    produtos.forEach(a => a.dtaAtualizacao = dayjs(a.dtaAtualizacao).format('DD/MM/YYYY'));
                    return produtos;
                });
            }
        }
    );

    function salvar(cadastro, successCb) {
        if(!cadastro.id) {
            toaster.pop('error', 'Selecione um produto para edição');
            return;
        }

        produtoSrv['atualizar'](cadastro).then(function() {
            if (successCb) {
                successCb();
                limparCadastro();
            }
            toaster.pop('success', 'Registro salvo com sucesso');
        }, function(res) {
            toaster.pop('error', "Erro", res.data.erro);
        });
    }

    function recarregar() {
        vm.tableParams.page(1);
        vm.tableParams.reload();
    }

    function desabilitarBtnSalvar() {
        return !vm.cadastro.vlrAdmin;
        
    }

    function limparCadastro() {
        vm.cadastro = {};
    }

    function editar(produto) {
        vm.cadastro = angular.copy(produto);
    }

    function atualizarStatus(produto) {
        salvar(produto);
    }
  
};

produtoCtrl.$inject = ['NgTableParams', 'ProdutoSrv', 'toaster'];

app.controller('ProdutoCtrl', produtoCtrl);