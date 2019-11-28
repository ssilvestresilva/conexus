'use strict'
var app = angular.module('ngApp', [
    'ui.bootstrap',
    'ui.router',
    'ngTable',
    'toaster',
    'uiSwitch',
    'ngMask',
    'angularCharts'
]);

app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');

    $stateProvider
        .state('base', {
            abstract: true,
            url: '',
            templateUrl: 'views/base.html',
            controller: 'BaseCtrl',
        })
        .state('login', {
            url: '/login',
            templateUrl: 'views/pages/login.html',
            controller: 'LoginCtrl',
            controllerAs: 'vm'
        })
        .state('dashboard', {
            url: '/dashboard',
            parent: 'base',
            templateUrl: 'views/pages/dashboard.html',
            controller: 'DashboardCtrl',
            controllerAs: 'vm'
        })
        .state('curso', {
            url: '/curso',
            data : {
                role : 'ADMIN'
            },
            parent: 'base',
            templateUrl: 'views/pages/curso.html',
            controller: 'CursoCtrl',
            controllerAs: 'vm'
        })
        .state('aluno', {
            url: '/aluno',
            data : {
                role : 'ADMIN'
            },
            parent: 'base',
            templateUrl: 'views/pages/aluno.html',
            controller: 'AlunoCtrl',
            controllerAs: 'vm'
        })
        .state('integracao', {
            url: '/integracao',
            data : {
                role : 'ADMIN'
            },
            parent: 'base',
            templateUrl: 'views/pages/integracao.html',
            controller: 'IntegracaoCtrl',
            controllerAs: 'vm'
        })
        .state('produto', {
            url: '/produto',
            data : {
                role : 'ADMIN'
            },
            parent: 'base',
            templateUrl: 'views/pages/produto.html',
            controller: 'ProdutoCtrl',
            controllerAs: 'vm'
        })
        .state('compras', {
            url: '/compras',
            data : {
                role : 'ADMIN'
            },
            parent: 'base',
            templateUrl: 'views/pages/compras.html',
            controller: 'ComprasCtrl',
            controllerAs: 'vm'
        })
        .state('access-denied', {
            url: '/access-denied',
            templateUrl: '',
            controller: ''
        });
});

app.constant("CONSTANTS", {
    aluno: {
        baseUrl: "/api/aluno"
    },
    curso: {
        baseUrl: "/api/curso",
        areasDeConhecimento: {
            lista: [
                { cod: 'CIENCIAS_EXATAS_TERRA', descricao: 'Ciências Exatas e da Terra' },
                { cod: 'CIENCIAS_BIOLOGICAS', descricao: 'Ciências Biológicas' },
                { cod: 'ENGENHARIA_TECNOLOGIA', descricao: 'Engenharia / Tecnologia' },
                { cod: 'CIENCIAS_SAUDE', descricao: 'Ciências da Saúde' },
                { cod: 'CIENCIAS_AGRARIAS', descricao: 'Ciências Agrárias' },
                { cod: 'CIENCIAS_SOCIAIS', descricao: 'Ciências Sociais' },
                { cod: 'CIENCIAS_HUMANAS', descricao: 'Ciências Humanas' },
                { cod: 'CIENCIAS_LINGUISTICA', descricao: 'Lingüística' },
                { cod: 'LETRAS_ARTES', descricao: 'Letras e Artes' }
            ],
            get: function(cod) {
                return this.lista.filter(function(l) { return l.cod === cod })[0];
            }
        }
    },
    integracao: {
        baseUrl: "/api/integracao"
    },
    resultado: {
        baseUrl: "/api/resultado"
    },
    produto: {
        baseUrl: "api/produto"
    },
    compras: {
        baseUrl: "api/compras"
    }
});

app.run(function(AuthSrv, $rootScope, $state, toaster) {
    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
        if (!AuthSrv.user) {
            if (toState.name != 'login' && toState.name != 'register') {
                event.preventDefault();
                $state.go('login');
            }
        }
    });
});