package com.cygnet.Auction.config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Employee;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//import static com.devglan.model.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
//import static com.devglan.model.Constants.SIGNING_KEY;

@Component
public class JwtTokenUtil implements Serializable {

public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
public static final String SIGNING_KEY = "Mitsul";
public static final String TOKEN_PREFIX = "Bearer ";
public static final String HEADER_STRING = "Authorization";

public String getUsernameFromToken(String token) {
return getClaimFromToken(token, Claims::getSubject);
}

public Date getExpirationDateFromToken(String token) {
return getClaimFromToken(token, Claims::getExpiration);
}

public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
final Claims claims = getAllClaimsFromToken(token);
return claimsResolver.apply(claims);
}

private Claims getAllClaimsFromToken(String token) {
return Jwts.parser()
.setSigningKey(SIGNING_KEY)
.parseClaimsJws(token)
.getBody();
}

private Boolean isTokenExpired(String token) {
final Date expiration = getExpirationDateFromToken(token);
return expiration.before(new Date());
}

public String generateToken(Employee emp) {
return doGenerateToken(emp.getEmail() , emp.getRoles());
}

private String doGenerateToken(String subject, String role) {

Claims claims = Jwts.claims().setSubject(subject);
claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority(role)));



String token = Jwts.builder()
.setClaims(claims)
.setIssuer("Romil")
.setIssuedAt(new Date(System.currentTimeMillis()))
.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
.signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
.compact();

System.out.println("Entry Point: "+token);
return token;
}

public Boolean validateToken(String token, UserDetails userDetails) {
final String username = getUsernameFromToken(token);
return (
username.equals(userDetails.getUsername())
&& !isTokenExpired(token));
}

}