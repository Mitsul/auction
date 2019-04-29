package com.cygnet.Auction.config;

import static com.cygnet.Auction.config.SecurityConstants.HEADER_STRING;
import static com.cygnet.Auction.config.SecurityConstants.SECRET;
import static com.cygnet.Auction.config.SecurityConstants.SIGN_UP_URL;
import static com.cygnet.Auction.config.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.repository.UserRepository;

import io.jsonwebtoken.Jwts;



public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	@Autowired CustomUserDetailService customUserDetailService;
	@Autowired UserRepository userRepository; 
	@Autowired EmployeeRepository employeeRepository;

	public JWTAuthorizationFilter(AuthenticationManager authManager,CustomUserDetailService customUserDetailService) {
		super(authManager);
		this.customUserDetailService = customUserDetailService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);
		System.out.println("Header: "+header);
		if (header == null || header.startsWith(TOKEN_PREFIX)==false) {
			System.out.println("Header in IF: "+header);
			res.setStatus(401);	
			//chain.doFilter(req, res);
			return;
		}
		else
		{
			System.out.println("Header = " + header);
			UsernamePasswordAuthenticationToken authentication;
			try {
				authentication = getAuthentication(req);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				res.addHeader(header, SIGN_UP_URL);
				chain.doFilter(req, res);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}


	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws Exception {
		String token = request.getHeader(HEADER_STRING);
		System.out.println("getAuthenticationToken token: "+token);
		if (token == null) return null;
		try {
			String username = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();

			//String userRole = getUserRole(username.toString());
			UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
			System.out.println("getAuthenticationToken userDetails "+userDetails.toString()+ " userDetails.getAuthorities() "+userDetails.getAuthorities());
			return username != null ?
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) : null;
		}
		catch(Exception e)
		{
			throw new Exception("Invalid Token");
		}
	}

	public String getUserRole(String email)
	{
		return employeeRepository.findByEmail(email).getRoles(); 
	} 
}