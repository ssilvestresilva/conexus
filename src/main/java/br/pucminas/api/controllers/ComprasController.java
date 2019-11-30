package br.pucminas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.api.entities.Compras;
import br.pucminas.api.repositories.ComprasRepository;
import br.pucminas.api.utils.Utils;

@RestController
@RequestMapping("/api/compras")
public class ComprasController {
	
	@Autowired
	private ComprasRepository comprasRepository;
	
	@GetMapping
	public Page<Compras> listar(@RequestParam(required = false) Long id,
							  @RequestParam(required = false) String nome,
							  @RequestParam(required = false) Integer idProduto,
							  @RequestParam(required = false) Integer codCompra,
							  @RequestParam(required = false) Double vlrCompra,
							  @RequestParam(required = false) Integer quantidade,
							  @RequestParam(required = false) String dtaPedido,
							  @RequestParam(required = false) String formaPagamento,
							  @RequestParam(required = false) String dtaPagamento,
							  @RequestParam(required = false) String statusPagamento,
							  @RequestParam(required = false) String dtaEnvio,
							  @RequestParam(required = false) String statusEnvio,
							  Pageable pageable) throws Exception {
		
		Compras compras = new Compras()
				.comId(id)
				.comCodCompra(codCompra)
				.comVlrCompra(vlrCompra)
				.comQuantidade(quantidade)
				.comDtaPedido(dtaPedido)
				.comFormaPagamento(formaPagamento)
				.comDtaPagamento(dtaPagamento)
				.comStatusPagamento(statusPagamento)
				.comDtaEnvio(dtaEnvio)
				.comStatusEnvio(statusEnvio);

		return comprasRepository.findAll(Example.of(compras, Utils.MATCHER_CONTAINING), pageable);
	}
	
//	@PostMapping
//	public ResponseEntity<Response<Produto>> inserir(@RequestBody ProdutoDto produtoDto) {
//		Response<Produto> response = new Response<Produto>();
//		List<String> erros = Validador.validate(produtoDto);
//		
//		if (!erros.isEmpty()) {
//			response.setErro(erros.get(0));
//			return ResponseEntity.badRequest().body(response);
//		}
//		
//		try {
//			Produto produto = this.fromDto(produtoDto);
//			response.setData(produtoRepository.save(produto));
//		} catch (Exception e) {
//			response.setErro(Mensagem.ERRO.SALVAR);
//			return ResponseEntity.badRequest().body(response);
//		}
//		
//		return ResponseEntity.ok(response);
//	}
//	
//	@PutMapping
//	private ResponseEntity<Response<Produto>> atualizar(@RequestBody ProdutoDto produtoDto) {
//		Response<Produto> response = new Response<Produto>();
//		List<String> erros = Validador.validate(produtoDto);
//		
//		if (!erros.isEmpty()) {
//			response.setErro(erros.get(0));
//			return ResponseEntity.badRequest().body(response);
//		}
//		
//		try {
//			Produto produto = this.fromDto(produtoDto);
//			response.setData(produtoService.atualizar(produto));
//		} catch (Exception e) {
//			response.setErro(Mensagem.ERRO.SALVAR);
//			return ResponseEntity.badRequest().body(response);
//		}
//
//		return ResponseEntity.ok(response);
//	}
}
