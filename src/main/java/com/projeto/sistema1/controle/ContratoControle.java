package com.projeto.sistema1.controle;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema1.modelos.Contrato;
import com.projeto.sistema1.modelos.Parceiro;
import com.projeto.sistema1.repositorios.ContratoRepositorio;
import com.projeto.sistema1.repositorios.ParceiroRepositorio;
import com.projeto.sistema1.repositorios.ProjectoRepositorio;


@Controller 
public class ContratoControle {
	@Autowired
	private ContratoRepositorio contratoRepositorio;
	@Autowired
	private ParceiroRepositorio parceiroRepositorio;
	@Autowired
	private ProjectoRepositorio projectoRepositorio;
	
		
	@GetMapping("/cadastroContrato") 
	public ModelAndView cadastrar(Contrato contrato) {
		ModelAndView mv= new ModelAndView("administrativo/contratos/cadastro");//esse é o caminho de pastas até chagar o nosso arquivo cadastro.html
		mv.addObject("contrato",contrato);
		mv.addObject("listaParceiros",parceiroRepositorio.findAll());
		mv.addObject("listaProjectos",projectoRepositorio.findAll());
		return  mv;
		
	}

	
	@GetMapping("/listarContrato")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("administrativo/contratos/lista");//criamos uma nova visualizaçao
		mv.addObject("listaContratos",contratoRepositorio.findAll());//listaContratos é oque está lá na tela de listagem graças ao thymleaf e vamos passar todos os registros que estao la no contrato repositoriios que gere o banco de dados e vamos pegar todos os registro do banco de dados
		return mv;
		
	}
	

	
	@GetMapping("/editarContrato/{id}")//para chamar a função editar contrato no front-end vamos isar o thymeleaf i vamos pasar o nome \editarContrato\{id} responsavel pelo método de editar contrato com o parametro id
	public ModelAndView editar(@PathVariable("id") Long id) {//(@PathVariable é para receber o nosso parámetro
		Optional<Contrato> contrato =contratoRepositorio.findById(id);//Option é para evitar questão de null quando nós caregamos as informações de contrato para edição
		return cadastrar(contrato.get());
	}
	
	@GetMapping("/removerContrato/{id}")
	public String remover(@PathVariable("id") Long id) {
	    Optional<Contrato> contrato = contratoRepositorio.findById(id);
	    if (contrato.isPresent()) {
	        contratoRepositorio.delete(contrato.get()); // Remove o contrato
	    }
	    return "redirect:/listarContrato"; // Redireciona para a lista de contratos
	}
	
	
	@PostMapping("/salvarContrato")//post oculta os dados
	public ModelAndView salvar(Contrato contrato,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return cadastrar(contrato);//se tiver um erro terá que chamar a função de cadastrar contrato
			
		}
		contratoRepositorio.saveAndFlush(contrato);//essa é uma função do jpa para salvar
		String mensagem = "Contrato '" + contrato.getNome() + "' salvo com sucesso!";
		model.addAttribute("message", mensagem);
		
		return cadastrar(new Contrato());
		
		
	}
	

	
}
