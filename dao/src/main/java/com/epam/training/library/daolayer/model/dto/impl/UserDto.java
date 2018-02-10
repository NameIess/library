package com.epam.training.library.daolayer.model.dto.impl;

import com.epam.training.library.daolayer.model.dto.Dto;

public class UserDto implements Dto {
    private Long userId;
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;

        UserDto userDto = (UserDto) o;

        if (userId != null ? !userId.equals(userDto.userId) : userDto.userId != null) return false;
        return roleId != null ? roleId.equals(userDto.roleId) : userDto.roleId == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDto{");
        sb.append("userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append('}');
        return sb.toString();
    }
}
