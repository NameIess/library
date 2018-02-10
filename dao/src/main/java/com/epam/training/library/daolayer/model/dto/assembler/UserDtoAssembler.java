package com.epam.training.library.daolayer.model.dto.assembler;

import com.epam.training.library.daolayer.model.Role;
import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.model.dto.impl.UserDto;

public class UserDtoAssembler {

    public UserDto convertToTransferResource(User entity) {
        UserDto transferObject = new UserDto();
        Long userId = entity.getId();
        transferObject.setUserId(userId);
        Role entityRole = entity.getRole();
        Long roleId = entityRole.getId();
        transferObject.setRoleId(roleId);

        return transferObject;
    }
}
