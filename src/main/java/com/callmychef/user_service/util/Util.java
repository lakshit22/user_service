package com.callmychef.user_service.util;

import com.callmychef.user_service.chefdb.entity.Chef;
import com.callmychef.user_service.dto.mapper.ChefDTO;
import com.callmychef.user_service.dto.mapper.UserDTO;
import com.callmychef.user_service.userdb.entity.User;

public class Util {
    public User setUserFromDto(UserDTO userDto){
        User persistedUser = new User();
        persistedUser.setEmail(userDto.getEmail());
        persistedUser.setStatus(userDto.getStatus());
        persistedUser.setPasswordHash(userDto.getPassword());
        persistedUser.setFirstName(userDto.getFirstName());
        persistedUser.setLastName(userDto.getLastName());
        persistedUser.setPhoneNo(userDto.getPhoneNo());

        return persistedUser;
    }

    public Chef setChefFromDto(ChefDTO chefDto){
        Chef persistedChef = new Chef();
        persistedChef.setPrimaryCuisine(chefDto.getPrimaryCuisine());
        persistedChef.setExperienceYears(chefDto.getExperienceYears());
        persistedChef.setBio(chefDto.getBio());
        persistedChef.setBasePrice(chefDto.getBasePrice());
        return persistedChef;
    }
}
