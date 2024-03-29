/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Config class for WebConfig
 */

package com.cygnet.Auction.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer,Filter {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("/**")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD","OPTIONS")
		.allowedHeaders("Authorization", "Content-Type", "Date", "Total-Count", "loginInfo","jwt_token","EMPID")
		.exposedHeaders("Authorization", "Content-Type", "Date", "Total-Count", "loginInfo", "jwt_token", "EMPID")
		.maxAge(3600);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		System.out.println("WebConfig; "+request.getRequestURI());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Access-Control-Expose-Headers", "EMPID");
		response.addHeader("Access-Control-Expose-Headers", "ROLE");
		response.addHeader("Access-Control-Expose-Headers", "responseType");
		System.out.println("Request Method: "+request.getMethod());
		if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
			try {
				chain.doFilter(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Pre-flight");
			response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers"+"Authorization, content-type," +
				"EMPID"+"ROLE"+
				"access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType");
			response.setStatus(HttpServletResponse.SC_OK);
		}

	}

}