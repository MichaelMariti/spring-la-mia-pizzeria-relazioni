package it.springpizzeriacrud.spring_la_mia_pizzeria_crud.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model.User;
import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.repository.UserRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{


    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepo.findByUsername(username);
        if(userOpt.isPresent()){
            return new DatabaseUserDetails(userOpt.get());
        }else {
            throw new UsernameNotFoundException("Username not found");
        }
    }
    
}
