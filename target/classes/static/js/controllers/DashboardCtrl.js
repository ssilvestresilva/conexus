'use strict';
var dashboardCtrl = function(dashboardSrv, toaster, CONSTANTS) {
    var vm = this;
    vm.fonteAprovacoes = {};
    vm.fonteFaixaEtaria = {};
    vm.fonteMediaNotas = {};
    vm.fontePredominancia = {};
    
    dashboardSrv.fonteAprovacoes().then(function(res) {
        var fonte = res.data;
        carregarFonteAprovacoes(fonte.qtdAprovados, fonte.qtdReprovados);
    });

    dashboardSrv.fonteFaixaEtaria().then(function(res) {
        var fonte = res.data;
        carregarFonteFaixaEtaria(fonte);
    });

    dashboardSrv.fonteMediaNotas().then(function(res) {
        var fonte = res.data;
        carregarFonteMediaNotas(fonte);
    });

    dashboardSrv.fontePredominancia().then(function(res) {
        var fonte = res.data;
        carregarFontePredominancia(fonte);
    });

    function carregarFontePredominancia(fonte) {
        vm.fontePredominancia.data = {
            series: ['Predominância'],
            data : []
        };
        fonte.forEach(function(f) {
            vm.fontePredominancia.data.data.push({x: obterSegmento(f.segmento), y: [f.count]})
        });
    }

    function carregarFonteMediaNotas(fonte) {
        vm.fonteMediaNotas.data = {
            series: ['Média'],
            data : []
        };
        fonte.forEach(function(f, idx) {
            vm.fonteMediaNotas.data.data.push({x: idx+1+'/18', y: [f]})
        });
    }

    function obterSegmento(codSegmento) {
        var segmento = CONSTANTS.curso.areasDeConhecimento.get(codSegmento);
        return segmento ? segmento.descricao : '-';
    }

    vm.fontePredominancia.config = {
        title: 'Predominância',
        tooltips: true,
        labels: true,
        legend: {
          display: true,
          position: 'right'
        },
        innerRadius: 0,
        lineLegend: 'lineEnd' // can be also 'traditional'
    }

    vm.fonteMediaNotas.config = {
        title: 'Media de Notas',
        tooltips: true,
        labels: false,
        legend: {
          display: true,
          position: 'right'
        },
        innerRadius: 0,
        colors: ['rgb(78, 174, 236)'],
        lineLegend: 'lineEnd' // can be also 'traditional'
    }

    vm.fonteFaixaEtaria.config = {
        title: 'Faixa Etária',
        tooltips: true,
        labels: true,
        legend: {
          display: true,
          position: 'right'
        },
        innerRadius: 0,
        colors: ['#e29931'],
        lineLegend: 'lineEnd' // can be also 'traditional'
    }

    function carregarFonteFaixaEtaria(fonte) {
        vm.fonteFaixaEtaria.data = {
            series: ['Quantidade Alunos'],
            data : []
        };
        fonte.forEach(function(f) {
            vm.fonteFaixaEtaria.data.data.push({x: f[0], y: [f[1]]})
        });
    }

    
    vm.fonteAprovacoes.config = {
        title: 'Índice de Aprovação',
        tooltips: true,
        labels: true,
        legend: {
          display: true,
          position: 'right'
        },
        innerRadius: 0,
        colors: ['#5cb85c', '#d9534f'],
        lineLegend: 'lineEnd' // can be also 'traditional'
    }

    function carregarFonteAprovacoes(qtdAprovados, qtdReprovados) {
        vm.fonteAprovacoes.data = {
            "data": [
              {
                "x": "  Aprovados",
                "y": [5 * qtdAprovados],
                "tooltip": "Qtd. Aprovados"
              },
              {
                "x": "  Reprovados",
                "y": [5 * qtdReprovados],
                "tooltip": "Qtd. Reprovados"
              }
            ]
        };
    }

};

dashboardCtrl.$inject = ['DashboardSrv', 'toaster', 'CONSTANTS'];

app.controller('DashboardCtrl', dashboardCtrl);