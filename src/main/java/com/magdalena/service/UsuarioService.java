package com.magdalena.service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.magdalena.entity.Rol;
import com.magdalena.entity.Usuario;
import com.magdalena.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;

	/* UserDetailsService Functions Begin*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));
		
		return new User(usuario.getUsername(), usuario.getPassword(), mapRoles(usuario.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRoles(Set<Rol> roles){
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}
	/* UserDetailsService Functions end*/
	
	public Boolean existsByUsername(String username) {
		return repository.existsByUsername(username);
	}
	
	public Usuario findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Optional<Usuario> findByUsername(String username){
		return repository.findByUsername(username);
	}
	
	public Usuario save(Usuario bean) {
		return repository.save(bean);
	}
	
}
