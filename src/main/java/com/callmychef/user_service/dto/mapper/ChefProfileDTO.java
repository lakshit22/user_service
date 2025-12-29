package com.callmychef.user_service.dto.mapper;

public class ChefProfileDTO {
    private UserDTO userDTO;
    private ChefDTO chefDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ChefDTO getChefDTO() {
        return chefDTO;
    }

    public void setChefDTO(ChefDTO chefDTO) {
        this.chefDTO = chefDTO;
    }
}
