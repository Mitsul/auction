package com.cygnet.Auction.config;

import static com.cygnet.Auction.config.SecurityConstants.EXPIRATION_TIME;
import static com.cygnet.Auction.config.SecurityConstants.HEADER_STRING;
import static com.cygnet.Auction.config.SecurityConstants.SECRET;
import static com.cygnet.Auction.config.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired EmployeeRepository employeeRepository;
	@Autowired CustomUserDetailService customUserDetailService;

	private AuthenticationManager authenticationManager;
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,CustomUserDetailService customUserDetailService) {
		this.authenticationManager = authenticationManager;
		this.customUserDetailService = customUserDetailService;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		System.out.println("In attemptAuthentication");
		try {
			Employee creds = new ObjectMapper().readValue(req.getInputStream(), Employee.class);
			System.out.println("Email: "+creds.getEmail() + " Password: "+creds.getPassword());
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getEmail(),
							creds.getPassword(),
							new ArrayList<>())
					);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		String token = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();

//		JSONObject jsonToken = new JSONObject();
//		try {
//			UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
//			jsonToken.put("Type", TOKEN_PREFIX);
//			jsonToken.put("Token", token);
//			jsonToken.put("ROLE", userDetails.getAuthorities().toString().replace("ROLE_", "").trim());
//			jsonToken.put("Expiry", new Date(System.currentTimeMillis() + EXPIRATION_TIME));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		if(jsonToken!=null)
//		{
//			System.out.println(jsonToken.toString());
//		}
		res.addHeader("Access-Control-Expose-Headers", "Authorization");
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token ); //+ userDetails.getAuthorities().toString().replace("ROLE_", "").trim()
		System.out.println("Token: "+TOKEN_PREFIX + token );
	}

}