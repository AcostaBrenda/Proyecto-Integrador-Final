package com.example.ProyectoIntegrador.security.service;

import com.example.ProyectoIntegrador.model.Rol;
import com.example.ProyectoIntegrador.model.Usuario;
import com.example.ProyectoIntegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UsuarioRepository usuarioRespository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // Debemos traer un Usuario basado en un string q recibe
        Usuario usuario = usuarioRespository.findByEmail(s);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuario o Paswword inválido");
        }

        // Antes le asignabamos manualmente un rol
        //List<GrantedAuthority> roles = new ArrayList<>();
        //roles.add(new SimpleGrantedAuthority("ADMIN"));

        // Ahora se lo asignamos
        // Armamos un User de spring secutiry con nuestros datos del modelo Usuario
        // username, password y roles del usuario que quiere iniciar sesión
        UserDetails userDetail = new User(usuario.getEmail(), usuario.getPassword(), getAuthorities(usuario));

        return userDetail;
    }

    private static Set<? extends GrantedAuthority> getAuthorities(Usuario retrievedUser) {
        Rol rol = retrievedUser.getRol();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        // Si tuvieramos un Usuario con muchos roles haríamos el forEach
        //roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
        return authorities;
    }
}
