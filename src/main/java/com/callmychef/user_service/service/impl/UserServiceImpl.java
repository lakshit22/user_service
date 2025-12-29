package com.callmychef.user_service.service.impl;

import com.callmychef.user_service.chefdb.entity.Chef;
import com.callmychef.user_service.client.ChefClient;
import com.callmychef.user_service.dto.mapper.ChefProfileDTO;
import com.callmychef.user_service.userdb.entity.User;
import com.callmychef.user_service.userdb.repository.UserRepository;
import com.callmychef.user_service.service.UserService;
import com.callmychef.user_service.util.Util;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ChefClient chefClient;
    private Util util = new Util();

    public UserServiceImpl(UserRepository userRepository, ChefClient chefClient){
        this.userRepository = userRepository;
        this.chefClient = chefClient;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void createChef(ChefProfileDTO chefProfileDto) {
            try{
                User persistedUser = util.setUserFromDto(chefProfileDto.getUserDTO());
                userRepository.save(persistedUser);
                if(persistedUser.getId() != null){
                    Chef persistedChef = util.setChefFromDto(chefProfileDto.getChefDTO());
                    persistedChef.setUserId(persistedUser.getId());

                    chefClient.createChef(persistedChef);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
    }
}
