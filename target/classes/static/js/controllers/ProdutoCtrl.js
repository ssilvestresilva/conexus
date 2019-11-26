'use strict';
var produtoCtrl = function(NgTableParams, produtoSrv, cursoSrv, toaster) {
    var vm = this;
    vm.limparCadastro = limparCadastro;
    vm.recarregar = recarregar;
    vm.salvar = salvar;
    vm.editar = editar;
    vm.desabilitarBtnSalvar = desabilitarBtnSalvar;
    vm.atualizarStatus = atualizarStatus;

    limparCadastro();
    carregarCursos();

    vm.tableParams = new NgTableParams({ count: 1 },
        {
            counts: [],
            getData: function(params) {
                var ngParams = {
                    page: params.page() - 1,
                    size: 2,
                    sort: 'id,DESC'
                }

                angular.extend(ngParams, params.filter());
                
                return produtoSrv.listar(ngParams).then(function(res) {
                    params.total(res.data.totalPages);
                    var produtos = res.data.content;
                    produtos.forEach(a => a.dtaNascimento = dayjs(a.dtaNascimento).format('DD/MM/YYYY'));
                    return produtos;
                });
            }
        }
    );

    function salvar(cadastro, successCb) {
        configurarStatusPadrao(cadastro);

        cadastro.cursos.forEach((c, idx) => {
            if (typeof c === 'string' && !isNaN(c)) {
                cadastro.cursos[idx] = new Object({id: Number(c)});
            }
        });

        produtoSrv['atualizar'](cadastro).then(function() {
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

    function limparCadastro() {
        vm.cadastro = {
            cursos: []
        };
    }

    function recarregar() {
        vm.tableParams.page(1);
        vm.tableParams.reload();
        limparCadastro();
    }

    function desabilitarBtnSalvar() {
        return !vm.cadastro.nome ||
                !vm.cadastro.cpf ||
                !vm.cadastro.dtaNascimento ||
                !vm.cadastro.endereco ||
                !vm.cadastro.cursos ||
                !vm.cadastro.cursos.length;
        
    }

    function editar(curso) {
        limparCadastro();
        vm.cadastro = angular.copy(curso);
    }

    function carregarCursos() {
        cursoSrv.listarTodos().then(function(res) {
            vm.cursos = res.data;
        });
    }

    function atualizarStatus(curso) {
        salvar(curso);
    }
  
};

produtoCtrl.$inject = ['NgTableParams', 'ProdutoSrv', 'CursoSrv', 'toaster'];

app.controller('Produtotrl', produtoCtrl);