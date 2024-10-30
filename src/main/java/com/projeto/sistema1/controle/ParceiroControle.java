package com.projeto.sistema1.controle;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.sistema1.dto.UserDto;
import com.projeto.sistema1.modelos.Parceiro;
import com.projeto.sistema1.modelos.User;
import com.projeto.sistema1.repositorios.ParceiroRepositorio;
import com.projeto.sistema1.service.EmailService;


@Controller //para identificar que 'e um controle da arquitetura mvc
public class ParceiroControle {
	@Autowired// para fazer a conexão com aquele repositório de parceiro para nós salvarmos os dados
	private ParceiroRepositorio parceiroRepositorio;
	
    @Autowired
    private EmailService emailService;
	
		
	@GetMapping("/cadastroParceiro") //para nós obtemos o mateamento(Quando a pessoa executar isso nós vamos executar um método(chamada da função ou seja sempre que a pessoa chama cadastra estato ela quer cadastrar o  parceiro)
	public ModelAndView cadastrar(Parceiro parceiro) {//vai ser responsável por criar a nossa view ou seja a nossa visualização(uma vez que nós estamos utilizado o timelieaf
		ModelAndView mv= new ModelAndView("administrativo/parceiros/cadastro");//esse é o caminho de pastas até chagar o nosso arquivo cadastro.html
		mv.addObject("parceiro",parceiro);

		return  mv;
		
	}
	
	
	@GetMapping("/atualizarParceiro") //para nós obtemos o mateamento(Quando a pessoa executar isso nós vamos executar um método(chamada da função ou seja sempre que a pessoa chama cadastra estato ela quer cadastrar o  parceiro)
	public ModelAndView atualizar(Parceiro parceiro) {//vai ser responsável por criar a nossa view ou seja a nossa visualização(uma vez que nós estamos utilizado o timelieaf
		ModelAndView mv= new ModelAndView("administrativo/parceiros/atualizar");//esse é o caminho de pastas até chagar o nosso arquivo cadastro.html
		mv.addObject("parceiro",parceiro);
		
		return  mv;
		
	}

	
	
	@GetMapping("/listarParceiro")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("administrativo/parceiros/lista");//criamos uma nova visualizaçao
		mv.addObject("listaParceiros",parceiroRepositorio.findAll());//listaParceiros é oque está lá na tela de listagem graças ao thymleaf e vamos passar todos os registros que estao la no parceiro repositoriios que gere o banco de dados e vamos pegar todos os registro do banco de dados
		return mv;
		
	}
	

	
	@GetMapping("/editarParceiro/{id}")//para chamar a função editar parceiro no front-end vamos isar o thymeleaf i vamos pasar o nome \editarParceiro\{id} responsavel pelo método de editar parceiro com o parametro id
	public ModelAndView editar(@PathVariable("id") Long id) {//(@PathVariable é para receber o nosso parámetro
		ModelAndView mv= new ModelAndView("administrativo/parceiros/atualizar");//esse é o caminho de pastas até chagar o nosso arquivo cadastro.html
		
		Optional<Parceiro> parceiro =parceiroRepositorio.findById(id);//Option é para evitar questão de null quando nós caregamos as informações de parceiro para edição
		mv.addObject("parceiro",parceiro.get());
		return mv;
	}
	
	@GetMapping("/removerParceiro/{id}")
	public String remover(@PathVariable("id") Long id) {
	    Optional<Parceiro> parceiro = parceiroRepositorio.findById(id);
	    if (parceiro.isPresent()) {
	        parceiroRepositorio.delete(parceiro.get()); // Remove o parceiro
	    }
	    return "redirect:/listarParceiro"; // Redireciona para a lista de parceiros
	}
	
	
	@PostMapping("/salvarParceiro")//post oculta os dados
	public ModelAndView salvar(Parceiro parceiro,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return cadastrar(parceiro);//se tiver um erro terá que chamar a função de cadastrar parceiro
			
		}
		parceiroRepositorio.saveAndFlush(parceiro);//essa é uma função do jpa para salvar
		String mensagem = "Parceiro '" + parceiro.getNome() + "' salvo com sucesso!";
		
		model.addAttribute("message", mensagem);
		
        String email = parceiro.getEmail();
        String subject = "Nova parceria";
        String text = "Parabés "+parceiro.getNome()+", Passa a ser parceiro da empresa GPP";
        emailService.sendEmail(email, subject, text);
        
		
		
		
		return cadastrar(new Parceiro());
		
		
	}
	

	

	
	
}
