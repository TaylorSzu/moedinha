package com.taylor.gerenciador_despesas.controller;

import com.taylor.gerenciador_despesas.domain.User;
import com.taylor.gerenciador_despesas.dto.user.CreateUserDTO;
import com.taylor.gerenciador_despesas.dto.user.DeleteAccountDTO;
import com.taylor.gerenciador_despesas.dto.user.UpdateUserDTO;
import com.taylor.gerenciador_despesas.dto.user.UserDTO;
import com.taylor.gerenciador_despesas.mapper.UserMapper;
import com.taylor.gerenciador_despesas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<CreateUserDTO> register(@RequestBody CreateUserDTO dto) {
        User user = userMapper.toUser(dto);
        CreateUserDTO response = userMapper.toCreateUserDTO(userService.save(user));
        return ResponseEntity.created(null).body(response);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateUserDTO dto){
        userService.update(userMapper.toUser(dto));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO response = userMapper.toUserDTO(userService.findById(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(@RequestBody DeleteAccountDTO dto){
        userService.delete(dto.id(), dto.password());
        return ResponseEntity.noContent().build();
    }

}
