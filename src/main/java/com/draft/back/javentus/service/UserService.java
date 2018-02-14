package com.draft.back.javentus.service;


import com.draft.back.javentus.model.Usuario;
import com.draft.back.javentus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author lucas
 */
@Service()
public class UserService {

    @Autowired
    private UserRepository userRepository;
    

    public Usuario findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(Usuario user) {
        user.setSenha(user.getSenha());
        user.setAtivo(1);
        userRepository.save(user);
    }

    public Usuario findOne(Integer id) {
        return userRepository.findOne(Long.valueOf(id));
    }
}
