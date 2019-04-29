package com.cygnet.Auction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{

	@Autowired private CustomUserDetailService customUserDetailService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("Default: "+auth.getDefaultUserDetailsService());
		auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	}
//	
//	@Autowired
//	private JwtAuthenticationEntryPoint unauthorizedHandler;
//	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/employee/login/")
		.antMatchers("http://192.168.100.36:4200/**")
		.antMatchers(HttpMethod.OPTIONS,"/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.csrf().disable()
		.authorizeRequests()
//		.antMatchers("/**").permitAll()
		.antMatchers(HttpMethod.POST,"/employee/login/").permitAll()
		.antMatchers("http://192.168.100.36:8088/**").permitAll()
		.antMatchers("http://localhost:4200/login").permitAll()
		.antMatchers("/localhost:4200/**").permitAll()
		.antMatchers(HttpMethod.POST,"/employee/login/").permitAll()
		.antMatchers(HttpMethod.OPTIONS,"/employee/login/").permitAll()

		.antMatchers(HttpMethod.GET,"/actuator/**").permitAll() 
		.antMatchers(HttpMethod.POST,"/actuator/**").permitAll()
		
		.antMatchers(HttpMethod.POST,"/admin/**").hasRole("ADMIN") 
		.antMatchers(HttpMethod.PUT,"/admin/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/admin/**").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.POST,"/employee/**").hasRole("EMPLOYEE") 
		.antMatchers(HttpMethod.PUT,"/employee/**").hasRole("EMPLOYEE")
		.antMatchers(HttpMethod.GET,"/employee/**").hasRole("EMPLOYEE")
		.antMatchers(HttpMethod.DELETE,"/employee/**").hasRole("EMPLOYEE")
		
		.anyRequest().authenticated()
		.and()
		.formLogin()
//		.loginPage("/employee/login/")
		.loginProcessingUrl("/employee/login/")
		.loginProcessingUrl("/admin/login")
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager(), customUserDetailService))
		.addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
		//.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
	}

}