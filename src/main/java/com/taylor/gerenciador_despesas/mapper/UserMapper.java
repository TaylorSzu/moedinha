package com.taylor.gerenciador_despesas.mapper;

import com.taylor.gerenciador_despesas.annotation.EncoderMapping;
import com.taylor.gerenciador_despesas.domain.User;
import com.taylor.gerenciador_despesas.dto.user.CreateUserDTO;
import com.taylor.gerenciador_despesas.dto.user.UpdateUserDTO;
import com.taylor.gerenciador_despesas.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING, uses = PasswordEncoderMapping.class)
public interface UserMapper {
    @Mapping(target = "password", qualifiedBy = EncoderMapping.class)
    User toUser(CreateUserDTO dto);
    @Mapping(target = "password", qualifiedBy = EncoderMapping.class)
    User toUser(UpdateUserDTO dto);
    CreateUserDTO toCreateUserDTO(User user);
    UserDTO toUserDTO(User user);
}
