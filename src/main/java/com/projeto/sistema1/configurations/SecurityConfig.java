package com.projeto.sistema1.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.projeto.sistema1.service.CustomSuccessHandler;
import com.projeto.sistema1.service.CustomUserDetailsService;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	 @Bean
	 public static PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
		 
	 }
	 
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	     http.csrf(c -> c.disable())
//	         .authorizeRequests(requests -> requests
//	             // Acesso permitido a esses caminhos
//	             .requestMatchers("/registration", "/cssFile/**").permitAll()
//	             // Acesso à raiz "/" para qualquer pessoa
//	             .requestMatchers("/").permitAll()
//	             // Acesso "/administrativo" apenas para usuários com a autoridade "ADMIN"
//	             .requestMatchers("/administrativo").hasAuthority("ADMIN")
//	             // Todas as outras requisições precisam estar autenticadas
//	             .anyRequest().authenticated()
//	         )
//	         .formLogin(form -> form
//	             .loginPage("/login")
//	             .loginProcessingUrl("/login")
//	             .successHandler(customSuccessHandler)
//	             .permitAll()
//	         )
//	         .logout(logout -> logout
//	             .invalidateHttpSession(true)
//	             .clearAuthentication(true)
//	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	             .logoutSuccessUrl("/login?logout")
//	             .permitAll()
//	         );
//
//	     return http.build();
//	 }
	 
	 //acessoNegado.html
	 
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	     http.csrf(c -> c.disable())
//	         .authorizeRequests(requests -> requests
//	             // Acesso permitido a esses caminhos
//	             .requestMatchers("/registration", "/cssFile/**").permitAll()
//	             // Acesso à raiz "/" para qualquer usuário autenticado ou com a autoridade "ADMIN"
//	             .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
//	             // Acesso "/administrativo" para usuários com a autoridade "ADMIN" ou "USER"
//	             .requestMatchers("/administrativo").hasAnyAuthority("ADMIN", "USER")
//	             // Acesso negado aos caminhos para usuários com a autoridade "USER"
//	             .requestMatchers("/cadastroUsuario", "/cadastrarVaga").hasAuthority("ADMIN")
//	             // Todas as outras requisições precisam estar autenticadas
//	             .anyRequest().authenticated()
//	         )
//	         .formLogin(form -> form
//	             .loginPage("/login")
//	             .loginProcessingUrl("/login")
//	             .successHandler(customSuccessHandler)
//	             .permitAll()
//	         )
//	         .logout(logout -> logout
//	             .invalidateHttpSession(true)
//	             .clearAuthentication(true)
//	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	             .logoutSuccessUrl("/login?logout")
//	             .permitAll()
//	         )
//	         .exceptionHandling(exceptions -> exceptions
//	             .accessDeniedPage("/acessoNegado.html") // Redirecionamento para acesso negado
//	         );
//
//	     return http.build();
//	 }
	 
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	     http.csrf(c -> c.disable())
//	         .authorizeRequests(requests -> requests
//	             // Acesso permitido a esses caminhos
//	             .requestMatchers("/registration", "/cssFile/**").permitAll()
//	             // Acesso à raiz "/" para qualquer usuário autenticado ou com a autoridade "ADMIN"
//	             .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
//	             // Acesso "/administrativo" para usuários com a autoridade "ADMIN" ou "USER"
//	             .requestMatchers("/administrativo").hasAnyAuthority("ADMIN", "USER")
//	             // Acesso negado aos caminhos para usuários com a autoridade "USER"
//	             .requestMatchers("/cadastroUsuario", "/cadastrarVaga", "/detalheFuncionario/{id}","/removerUsuario/{id}","/removerVaga/{id}","/editarVaga/{id}","/detalheVaga/{id}").hasAuthority("ADMIN")
//	             // Todas as outras requisições precisam estar autenticadas
//	             .anyRequest().authenticated()
//	         )
//	         .formLogin(form -> form
//	             .loginPage("/login")
//	             .loginProcessingUrl("/login")
//	             .successHandler(customSuccessHandler)
//	             .permitAll()
//	         )
//	         .logout(logout -> logout
//	             .invalidateHttpSession(true)
//	             .clearAuthentication(true)
//	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	             .logoutSuccessUrl("/login?logout")
//	             .permitAll()
//	         )
//	         .exceptionHandling(exceptions -> exceptions
//	             .accessDeniedHandler((request, response, accessDeniedException) -> {
//	                 response.sendRedirect("/acessoNegado");
//	             })
//	         );
//
//	     return http.build();
//	 }
//	 
	 
//	 
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	     http.csrf(c -> c.disable())
	         .authorizeRequests(requests -> requests
	             // Acesso permitido a esses caminhos
        		 .requestMatchers("/", "/listarVagas", "/detalheVagaa/{id}", "/salvarCandidato/{id}", "/mostarImagem/{imagem}", "/mostarImagemCV/{imagem}") .permitAll()
	             // Acesso à raiz "/" para qualquer usuário autenticado ou com a autoridade "ADMIN"
        		// .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
	             // Acesso "/administrativo" para usuários com a autoridade "ADMIN" ou "USER"
        		 .requestMatchers("/administrativo").hasAnyAuthority("ADMIN", "USER")
	             // Acesso negado aos caminhos para usuários com a autoridade "USER"
        		 .requestMatchers("/cadastroUsuario", "/cadastrarVaga", "/detalheFuncionario/{id}", "/removerUsuario/{id}", "/removerVaga/{id}", "/editarVaga/{id}", "/detalheVaga/{id}","/editarContrato/{id}","/cadastroContrato","/removerContrato/{id}","/salvarContrato","/salvarUsuario","/editarUsuario/{id}","/listarUsuarios","/removerUsuario/{id}","/listarContrato").hasAuthority("ADMIN")
	             // Todas as outras requisições precisam estar autenticadas
	             .anyRequest().authenticated()
	         )
	         .formLogin(form -> form
	             .loginPage("/login")
	             .loginProcessingUrl("/login")
	             .successHandler(customSuccessHandler)
	             .permitAll()
	         )
	         .logout(logout -> logout
	             .invalidateHttpSession(true)
	             .clearAuthentication(true)
	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	             .logoutSuccessUrl("/login?logout")
	             .permitAll()
	         )
	         .exceptionHandling(exceptions -> exceptions
	             .accessDeniedHandler((request, response, accessDeniedException) -> {
	                 response.sendRedirect("/acessoNegado");
	             })
	         );

	     return http.build();
	 }
//	 
//	 
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	     http.csrf(c -> c.disable())
//	         .authorizeRequests(requests -> requests
//	             // Acesso permitido a esses caminhos
//	        		 .requestMatchers("/", "/listarVagas", "/detalheVagaa/{id}", "/salvarCandidato/{id}").permitAll()
//	             // Acesso "/administrativo" para usuários com a autoridade "ADMIN" ou "USER"
//	        		 .requestMatchers("/administrativo").hasAnyAuthority("ADMIN", "USER")
//	             // Acesso negado aos caminhos para usuários com a autoridade "USER"
//	        		 .requestMatchers("/cadastroUsuario", "/cadastrarVaga", "/detalheFuncionario/{id}", "/removerUsuario/{id}", "/removerVaga/{id}", "/editarVaga/{id}", "/detalheVaga/{id}").hasAuthority("ADMIN")
//	             // Todas as outras requisições precisam estar autenticadas
//	             .anyRequest().authenticated()
//	         )
//	         .formLogin(form -> form
//	             .loginPage("/login")
//	             .loginProcessingUrl("/login")
//	             .successHandler(customSuccessHandler)
//	             .permitAll()
//	         )
//	         .logout(logout -> logout
//	             .invalidateHttpSession(true)
//	             .clearAuthentication(true)
//	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	             .logoutSuccessUrl("/")
//	             .permitAll()
//	         )
//	         .exceptionHandling(exceptions -> exceptions
//	             .accessDeniedHandler((request, response, accessDeniedException) -> {
//	                 response.sendRedirect("/acessoNegado");
//	             })
//	         );
//
//	     return http.build();
//	 }
//	 
	 
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	     http.csrf(c -> c.disable())
//	         .authorizeRequests(requests -> requests
//	             // Acesso permitido a esses caminhos
//	             .requestMatchers("/registration", "/cssFile/**").permitAll()
//	             // Acesso à raiz "/" para qualquer usuário autenticado ou com a autoridade "ADMIN"
//	             .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
//	             // Acesso "/administrativo" para usuários com a autoridade "ADMIN" ou "USER"
//	             .requestMatchers("/administrativo").hasAnyAuthority("ADMIN", "USER")
//	             // Acesso negado aos caminhos para usuários com a autoridade "USER"
//	             .requestMatchers("/cadastroUsuario", "/cadastrarVaga").hasAuthority("ADMIN")
//	             // Todas as outras requisições precisam estar autenticadas
//	             .anyRequest().authenticated()
//	         )
//	         .formLogin(form -> form
//	             .loginPage("/login")
//	             .loginProcessingUrl("/login")
//	             .successHandler(customSuccessHandler)
//	             .permitAll()
//	         )
//	         .logout(logout -> logout
//	             .invalidateHttpSession(true)
//	             .clearAuthentication(true)
//	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	             .logoutSuccessUrl("/login?logout")
//	             .permitAll()
//	         );
//
//	     return http.build();
//	 }
	 
	 
//	 
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	     http.csrf(c -> c.disable())
//	         .authorizeRequests(requests -> requests
//	             // Acesso permitido a esses caminhos
//	             .requestMatchers("/registration", "/cssFile/**").permitAll()
//	             // Acesso à raiz "/" para qualquer usuário autenticado ou com a autoridade "ADMIN"
//	             .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
//	             // Acesso "/administrativo" apenas para usuários com a autoridade "ADMIN"
//	             .requestMatchers("/administrativo").hasAuthority("ADMIN")
//	             // Acesso negado aos caminhos para usuários com a autoridade "USER"
//	             .requestMatchers("/cadastroUsuario", "/cadastrarVaga").hasAuthority("ADMIN")
//	             // Todas as outras requisições precisam estar autenticadas
//	             .anyRequest().authenticated()
//	         )
//	         .formLogin(form -> form
//	             .loginPage("/login")
//	             .loginProcessingUrl("/login")
//	             .successHandler(customSuccessHandler)
//	             .permitAll()
//	         )
//	         .logout(logout -> logout
//	             .invalidateHttpSession(true)
//	             .clearAuthentication(true)
//	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	             .logoutSuccessUrl("/login?logout")
//	             .permitAll()
//	         );
//
//	     return http.build();
//	 }
	 
	 
	 
	 
	 
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	     http.csrf(c -> c.disable())
//	         .authorizeRequests(requests -> requests
//	             // Acesso permitido a esses caminhos
//	             .requestMatchers("/registration", "/cssFile/**").permitAll()
//	             // Acesso à raiz "/" para qualquer usuário autenticado ou com a autoridade "ADMIN"
//	             .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
//	             // Acesso "/administrativo" apenas para usuários com a autoridade "ADMIN"
//	             .requestMatchers("/administrativo").hasAuthority("ADMIN")
//	             // Todas as outras requisições precisam estar autenticadas
//	             .anyRequest().authenticated()
//	         )
//	         .formLogin(form -> form
//	             .loginPage("/login")
//	             .loginProcessingUrl("/login")
//	             .successHandler(customSuccessHandler)
//	             .permitAll()
//	         )
//	         .logout(logout -> logout
//	             .invalidateHttpSession(true)
//	             .clearAuthentication(true)
//	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	             .logoutSuccessUrl("/login?logout")
//	             .permitAll()
//	         );
//
//	     return http.build();
//	 }
	 
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
//		 http.csrf( c -> c.disable())
//		 .authorizeHttpRequests(request -> request.requestMatchers("/administrativo")
//				 .hasAuthority("ADMIN").requestMatchers("/").hasAuthority("USER")
//				 .requestMatchers("/registration","/cssFile/**").permitAll()
//				 .anyRequest().authenticated())
//		 .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
//				 .successHandler(customSuccessHandler).permitAll())
//		 .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
//				 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				 .logoutSuccessUrl("/login?logout").permitAll());
//		 
//		 return http.build();
//		 	 
//	 }
	//	 @Bean
	//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	//	     http.csrf(csrf -> csrf.disable())
	//	         .authorizeHttpRequests(requests -> requests
	//	             .requestMatchers("/administrativo").hasAuthority("ADMIN")
	//	             .requestMatchers("/finalizar").authenticated()
	//	             .requestMatchers("/registration", "/cssFile/**", "/").permitAll()
	//	             .anyRequest().authenticated())
	//	         .formLogin(form -> form
	//	             .loginPage("/login")
	//	             .loginProcessingUrl("/login")
	//	             .successHandler(customSuccessHandler)
	//	             .permitAll())
	//	         .logout(logout -> logout
	//	             .invalidateHttpSession(true)
	//	             .clearAuthentication(true)
	//	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	//	             .logoutSuccessUrl("/login?logout")
	//	             .permitAll());
	//
	//	     return http.build();
	//	 }
//	 
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	     http.csrf(csrf -> csrf.disable())
//	         .authorizeHttpRequests(requests -> requests
//	             .requestMatchers("/administrativo").hasAuthority("ADMIN")
//	             .requestMatchers("/finalizar").authenticated()
//	             .requestMatchers("/registration", "/").permitAll() // Permitindo acesso a "/registration" e "/" sem autenticação
//	             .requestMatchers("/static/**").permitAll() // Permitindo acesso ao diretório "/static/**" sem autenticação
//	             .anyRequest().authenticated())
//	         .formLogin(form -> form
//	             .loginPage("/login")
//	             .loginProcessingUrl("/login")
//	             .successHandler(customSuccessHandler)
//	             .permitAll())
//	         .logout(logout -> logout
//	             .invalidateHttpSession(true)
//	             .clearAuthentication(true)
//	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	             .logoutSuccessUrl("/login?logout")
//	             .permitAll());
//
//	     return http.build();
//	 }
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	     http.csrf(csrf -> csrf.disable())
//	         .authorizeHttpRequests(requests -> requests
//	             .requestMatchers("/administrativo").hasAuthority("ADMIN")
//	             .requestMatchers("/finalizar").authenticated() // Requer autenticação para "/finalizar"
//	             .requestMatchers("/", "/finalizar/**").permitAll() // Permite acesso a "/" e "/finalizar/**" sem autenticação
//	             .requestMatchers("/static/**").permitAll() // Permite acesso ao diretório "/static/**" sem autenticação
//	             .anyRequest().authenticated())
//	         .formLogin(form -> form
//	             .loginPage("/login")
//	             .loginProcessingUrl("/login")
//	             .successHandler(customSuccessHandler)
//	             .permitAll())
//	         .logout(logout -> logout
//	             .invalidateHttpSession(true)
//	             .clearAuthentication(true)
//	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	             .logoutSuccessUrl("/login?logout")
//	             .permitAll());
//
//	     return http.build();
//	 }
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	     http.csrf(csrf -> csrf.disable())
//	         .authorizeHttpRequests(requests -> requests
//	             .requestMatchers("/administrativo").hasAuthority("ADMIN")
//	             .requestMatchers("/finalizar").authenticated() // Requer autenticação para "/finalizar"
//	             .requestMatchers("/", "/finalizar/**").permitAll() // Permite acesso a "/" e "/finalizar/**" sem autenticação
//	             .requestMatchers("/static/**").permitAll() // Permite acesso ao diretório "/static/**" sem autenticação
//	             .anyRequest().authenticated())
//	         .formLogin(form -> form
//	             .loginPage("/login")
//	             .loginProcessingUrl("/login")
//	             .successHandler(customSuccessHandler)
//	             .permitAll())
//	         .logout(logout -> logout
//	             .invalidateHttpSession(true)
//	             .clearAuthentication(true)
//	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	             .logoutSuccessUrl("/login?logout")
//	             .permitAll());
//
//	     return http.build();
//	 }
	 
	 @Autowired
	 public void configure(AuthenticationManagerBuilder auth)throws Exception{
		 auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		 
	 }
	 


}
