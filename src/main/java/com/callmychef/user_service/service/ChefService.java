package com.callmychef.user_service.service;

import com.callmychef.user_service.chefdb.entity.Chef;
import org.springframework.stereotype.Service;

public interface ChefService {
    public void createChef(Chef chef);
}
