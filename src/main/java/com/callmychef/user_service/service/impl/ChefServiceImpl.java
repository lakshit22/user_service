package com.callmychef.user_service.service.impl;

import com.callmychef.user_service.chefdb.entity.Chef;
import com.callmychef.user_service.chefdb.repository.impl.ChefRepository;
import com.callmychef.user_service.client.ChefClient;
import com.callmychef.user_service.service.ChefService;
import org.springframework.stereotype.Service;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;

    public ChefServiceImpl(ChefRepository chefRepository){
        this.chefRepository = chefRepository;
    }

    @Override
    public void createChef(Chef chef) {
        chefRepository.save(chef);
    }
}
