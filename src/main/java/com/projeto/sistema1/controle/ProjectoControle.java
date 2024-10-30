package com.projeto.sistema1.controle;


import java.util.Optional;

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
import com.projeto.sistema1.modelos.Projecto;
import com.projeto.sistema1.modelos.User;
import com.projeto.sistema1.repositorios.ProjectoRepositorio;


@Controller //para identificar que 'e um controle da arquitetura mvc
public class ProjectoControle {
	@Autowired// para fazer a conexão com aquele repositório de projecto para nós salvarmos os dados
	private ProjectoRepositorio projectoRepositorio;
	
		
	@GetMapping("/cadastroProjecto") //para nós obtemos o mateamento(Quando a pessoa executar isso nós vamos executar um método(chamada da função ou seja sempre que a pessoa chama cadastra estato ela quer cadastrar o  projecto)
	public ModelAndView cadastrar(Projecto projecto) {//vai ser responsável por criar a nossa view ou seja a nossa visualização(uma vez que nós estamos utilizado o timelieaf
		ModelAndView mv= new ModelAndView("administrativo/projectos/cadastro");//esse é o caminho de pastas até chagar o nosso arquivo cadastro.html
		mv.addObject("projecto",projecto);

		return  mv;
		
	}

	
	@GetMapping("/listarProjecto")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("administrativo/projectos/lista");//criamos uma nova visualizaçao
		mv.addObject("listaProjectos",projectoRepositorio.findAll());//listaProjectos é oque está lá na tela de listagem graças ao thymleaf e vamos passar todos os registros que estao la no projecto repositoriios que gere o banco de dados e vamos pegar todos os registro do banco de dados
		return mv;
		
	}
	

	

	
	@GetMapping("/editarProjecto/{id}")//para chamar a função editar parceiro no front-end vamos isar o thymeleaf i vamos pasar o nome \editarParceiro\{id} responsavel pelo método de editar parceiro com o parametro id
	public ModelAndView editar(@PathVariable("id") Long id) {//(@PathVariable é para receber o nosso parámetro
		ModelAndView mv= new ModelAndView("administrativo/projectos/atualizar");//esse é o caminho de pastas até chagar o nosso arquivo cadastro.html
		
		Optional<Projecto> projecto =projectoRepositorio.findById(id);//Option é para evitar questão de null quando nós caregamos as informações de parceiro para edição
		mv.addObject("projecto",projecto.get());
		return mv;
	}
	
	@GetMapping("/removerProjecto/{id}")
	public String remover(@PathVariable("id") Long id) {
	    Optional<Projecto> projecto = projectoRepositorio.findById(id);
	    if (projecto.isPresent()) {
	        projectoRepositorio.delete(projecto.get()); // Remove o projecto
	    }
	    return "redirect:/listarProjecto"; // Redireciona para a lista de projectos
	}
	
	
	@PostMapping("/salvarProjecto")//post oculta os dados
	public ModelAndView salvar(Projecto projecto,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return cadastrar(projecto);//se tiver um erro terá que chamar a função de cadastrar projecto
			
		}
		projectoRepositorio.saveAndFlush(projecto);//essa é uma função do jpa para salvar
		String mensagem = "Projecto '" + projecto.getNome() + "' salvo com sucesso!";
		model.addAttribute("message", mensagem);
		return cadastrar(new Projecto());
		
		
	}
	

	
	
}
