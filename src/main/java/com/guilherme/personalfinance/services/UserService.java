package com.guilherme.personalfinance.services;

import com.guilherme.personalfinance.models.User;
import com.guilherme.personalfinance.models.enums.ProfileEnum;
import com.guilherme.personalfinance.repositories.UserRepository;
import com.guilherme.personalfinance.security.UserSpringSecurity;
import com.guilherme.personalfinance.services.exeptions.AuthorizationException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired                              //@Autowired: "Constructor" for when we want to instantiate an object, but
    private UserRepository userRepository;  //the interface can't to instantiate object, we use the notes of springboot
                                            //for to instantiate the objects

    public User findByID(Long id){
        UserSpringSecurity userSpringSecurity = authenticated();
        if(!Objects.nonNull(userSpringSecurity))
            ||!userSpringSecurity.hasRole(ProfileEnum.ADMIN) && !id.equals(UserSpringSecurity.getId())
            throw new AuthorizationException("Acesso negado");

        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException())


        }
    }







}
