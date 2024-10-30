package com.projeto.sistema1.controle;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;





@Controller
public class AcessoNegadoControle {

	
	@GetMapping("/acessoNegado") //para nós obtemos o mateamento(Quando a pessoa executar isso nós vamos executar um método(chamada da função ou seja sempre que a pessoa chama cadastra estato ela quer cadastrar o  funcionario)
	public ModelAndView aceder() {//vai ser responsável por criar a nossa view ou seja a nossa visualização(uma vez que nós estamos utilizado o timelieaf
		ModelAndView mv= new ModelAndView("administrativo/acessoNegado");//esse é o caminho de pastas até chagar o nosso arquivo cadastro.html
		return  mv;
		
	}
	

}
