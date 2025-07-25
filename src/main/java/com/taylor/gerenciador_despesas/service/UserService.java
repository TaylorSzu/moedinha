package com.taylor.gerenciador_despesas.service;

import com.taylor.gerenciador_despesas.domain.User;
import com.taylor.gerenciador_despesas.excption.NotFoundException;
import com.taylor.gerenciador_despesas.excption.NotPermissionException;
import com.taylor.gerenciador_despesas.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public User save(User user){
        user.setRole("USER");
        return userRepository.save(user);
    }

    public void update(User user){
        User foundUser = findById(user.getId());
        user.setRole(foundUser.getRole());
        userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void delete(Long id, String password){
        User user = findById(id);
        if(!user.getPassword().equals(password)){
            new NotPermissionException("User not permission delete account");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
