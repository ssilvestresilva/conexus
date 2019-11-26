'use strict';
var alunoCtrl = function(NgTableParams, alunoSrv, cursoSrv, toaster) {
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
                
                return alunoSrv.listar(ngParams).then(function(res) {
                    params.total(res.data.totalPages);
                    var alunos = res.data.content;
                    alunos.forEach(a => a.dtaNascimento = dayjs(a.dtaNascimento).format('DD/MM/YYYY'));
                    return alunos;
                });
            }
        }
    );

    function salvar(cadastro, successCb) {
        configurarStatusPadrao(cadastro);

        var metodo = !cadastro.id ? 'inserir' : 'atualizar';

        cadastro.cursos.forEach((c, idx) => {
            if (typeof c === 'string' && !isNaN(c)) {
                cadastro.cursos[idx] = new Object({id: Number(c)});
            }
        });

        alunoSrv[metodo](cadastro).then(function() {
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

alunoCtrl.$inject = ['NgTableParams', 'AlunoSrv', 'CursoSrv', 'toaster'];

app.controller('AlunoCtrl', alunoCtrl);