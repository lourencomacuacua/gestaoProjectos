package com.projeto.sistema1.controle;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.projeto.sistema1.dto.UserDto;

import com.projeto.sistema1.modelos.User;

import com.projeto.sistema1.repositorios.UserRepository;
import com.projeto.sistema1.service.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	

	@GetMapping("/cadastroUsuario")
	public ModelAndView cadastrar(@RequestParam(value = "id", required = false) Long id) {
	    ModelAndView mv = new ModelAndView("administrativo/usuarios/cadastro");
	    User user = new User();
	    if (id != null) {
	        Optional<User> existingUser = userRepository.findById(id);
	        if (existingUser.isPresent()) {
	            user = existingUser.get();
	        }
	    }
	    mv.addObject("user", user);

	    return mv;
	}
	

	@PostMapping("/salvarUsuario")
	public ModelAndView salvarUser(@ModelAttribute("user") UserDto userDto, Model model) {
	    try {
	        User user = userService.save(userDto);
	        String mensagem = "Usuário " + user.getFullname() + " salvo com sucesso!";
	        model.addAttribute("message", mensagem);
	    } catch (RuntimeException e) {
	        model.addAttribute("message", "Erro: " + e.getMessage());
	    }
	    
	    return cadastrar(null); // Retorna para o formulário vazio para novo cadastro
	}
	
	@GetMapping("/editarUsuario/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
	    Optional<User> user = userRepository.findById(id);
	    if (user.isPresent()) {
	        return cadastrar(id);
	    } else {
	        return new ModelAndView("redirect:/usuarios");
	    }
	}

	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("user-page")
	public String userPage( Principal principal,Model model) {
		UserDetails userDetails= userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user",userDetails);
		return "administrativo/home";
	}
	
	@GetMapping("admin-page")
	public String adminPage(Model model, Principal principal) {
		UserDetails userDetails= userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user",userDetails);
		return "administrativo/home";
	}
	
	@GetMapping("/listarUsuarios")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("administrativo/usuarios/lista");
		mv.addObject("listaUsuarios",userRepository.findAll());
		return mv;
		
	}
	
	@GetMapping("/removerUsuario/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<User> user =userRepository.findById(id);
		userRepository.delete(user.get());
		return listar();
	}
	
}
