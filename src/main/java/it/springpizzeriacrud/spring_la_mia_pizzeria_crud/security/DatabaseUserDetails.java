package it.springpizzeriacrud.spring_la_mia_pizzeria_crud.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model.Role;
import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model.User;

public class DatabaseUserDetails implements UserDetails{

    private String username;

    private String password;

    private Set<GrantedAuthority> authorities;

    // Costruttore
    public DatabaseUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new HashSet<>();

        for(Role role : user.getRoles()) {
            SimpleGrantedAuthority SGA = new SimpleGrantedAuthority(role.getName());
            this.authorities.add(SGA);
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    

}
