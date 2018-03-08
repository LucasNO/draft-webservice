package com.draft.back.javentus.service;


import com.draft.back.javentus.model.Usuario;
import com.draft.back.javentus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;


/**
 *
 * @author lucas
 */
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuario findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Usuario findUserByNome(String nome) {
        return userRepository.findByNome(nome);
    }

    public void saveUser(Usuario user) {
        user.setAtivo(1);
        userRepository.save(user);
    }

    public Usuario findOne(Integer id) {
        return userRepository.findOne(Long.valueOf(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario applicationUser = findUserByNome(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getNome(), applicationUser.getSenha(), emptyList());
    }
}
