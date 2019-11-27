package br.pucminas.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.api.constants.Mensagem;
import br.pucminas.api.constants.Validador;
import br.pucminas.api.dtos.ProdutoDto;
import br.pucminas.api.entities.Produto;
import br.pucminas.api.repositories.ProdutoRepository;
import br.pucminas.api.response.Response;
import br.pucminas.api.services.ProdutoService;
import br.pucminas.api.utils.Utils;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public Page<Produto> listar(@RequestParam(required = false) Long id,
							  @RequestParam(required = false) Integer codProduto,
							  @RequestParam(required = false) String descricao,
							  @RequestParam(required = false) String dtaCriacao,
							  @RequestParam(required = false) String dtaAtualizacao,
							  @RequestParam(required = false) Integer quantidade,
							  @RequestParam(required = false) Double vlrDistribuidor,
							  @RequestParam(required = false) Double vlrAdmin,
							  Pageable pageable) throws Exception {
		
		Produto produto = new Produto()
				.comId(id)
				.comCodProduto(codProduto)
				.comDescricao(descricao)
				.comDtaCriacao(dtaCriacao)
				.comDtaAtualizacao(dtaAtualizacao)
				.comQuantidade(quantidade)
				.comVlrDistribuidor(vlrDistribuidor)
				.comVlrAdmin(vlrAdmin);

		return produtoRepository.findAll(Example.of(produto, Utils.MATCHER_CONTAINING), pageable);
	}
	
	@PostMapping
	public ResponseEntity<Response<Produto>> inserir(@RequestBody ProdutoDto produtoDto) {
		Response<Produto> response = new Response<Produto>();
		List<String> erros = Validador.validate(produtoDto);
		
		if (!erros.isEmpty()) {
			response.setErro(erros.get(0));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			Produto produto = this.fromDto(produtoDto);
			response.setData(produtoRepository.save(produto));
		} catch (Exception e) {
			response.setErro(Mensagem.ERRO.SALVAR);
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	private ResponseEntity<Response<Produto>> atualizar(@RequestBody ProdutoDto produtoDto) {
		Response<Produto> response = new Response<Produto>();
		List<String> erros = Validador.validate(produtoDto);
		
		if (!erros.isEmpty()) {
			response.setErro(erros.get(0));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			Produto produto = this.fromDto(produtoDto);
			response.setData(produtoService.atualizar(produto));
		} catch (Exception e) {
			response.setErro(Mensagem.ERRO.SALVAR);
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	private Produto fromDto(ProdutoDto produtoDto) throws Exception {
		Produto produto = new Produto();
		produtoDto.getId().ifPresent(id -> produto.setId(id));
		produto.setCodProduto(produtoDto.getCodProduto());
		produto.setDescricao(produtoDto.getDescricao());
		produto.setVlrDistribuidor(produtoDto.getVlrDistribuidor());
		produto.setVlrAdmin(produtoDto.getVlrAdmin());
		produto.setQuantidade(produtoDto.getQuantidade());
		produto.setDtaCriacao(Utils.converterDataLocal(produtoDto.getDtaCriacao()));
		produto.setDtaAtualizacao(Utils.converterDataLocal(produtoDto.getDtaAtualizacao()));
		produto.setAtivo(produtoDto.getAtivo());
		
		return produto;
	}
}
