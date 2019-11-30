'use strict';
var comprasCtrl = function(NgTableParams, comprasSrv) {
    var vm = this;
    
    vm.tableParams = new NgTableParams({ count: 1 },
        {
            counts: [],
            getData: function(params) {
                var ngParams = {
                    page: params.page() - 1,
                    size: 20,
                    sort: 'id,ASC'
                }

                angular.extend(ngParams, params.filter());
                
                return comprasSrv.listar(ngParams).then(function(res) {
                    params.total(res.data.totalPages);
                    var compras = res.data.content;
                    compras.forEach(a => {
                        a.dtaPedido = a.dtaPedido != null ? dayjs(a.dtaPedido).format('DD/MM/YYYY') : '-'; 
                        a.dtaPagamento = a.dtaPagamento != null ? dayjs(a.dtaPagamento).format('DD/MM/YYYY') : '-';
                        a.dtaEnvio = a.dtaEnvio != null ? dayjs(a.dtaEnvio).format('DD/MM/YYYY') : '-';
                        a.nome = a.usuarios[0].nome;
                        a.codProduto = a.produtos[0].codProduto;
                     });
                    return compras;
                });
            }
        }
    );
    
    // function exportarCompras() {
    //     vm.tableParams.page(1);
    //     vm.tableParams.reload();
    // }
    // 
    // function exportarVendas() {
    //     vm.tableParams.page(1);
    //     vm.tableParams.reload();
    // }

};

comprasCtrl.$inject = ['NgTableParams', 'ComprasSrv'];

app.controller('ComprasCtrl', comprasCtrl);