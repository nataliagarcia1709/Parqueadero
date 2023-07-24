package com.parqueadero.Parqueadero.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.parqueadero.Parqueadero.entity.Usuario;
import com.parqueadero.Parqueadero.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {
   
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @PostMapping("user")
    public Usuario login(@RequestParam("user") String email, @RequestParam("password") String pwd) {
		
		String token = getJWTToken(email);
		Usuario user = new Usuario();
		user.setEmail(email);
		user.setToken(token);		
		return user;
		
	}
	private String getJWTToken(String email) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_SOCIO");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(email)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
    
    @GetMapping("/usuarios/")
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/users/{email}")
	public Usuario getUsuario(@PathVariable String email) {
		return usuarioRepository.findByEmail(email);
	}
}