package com.callmychef.user_service.client;

import com.callmychef.user_service.chefdb.entity.Chef;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "chef-service",
        url = "http://localhost:8080"
)
public interface ChefClient {
    @PostMapping("/chefs/internal")
    void createChef(@RequestBody Chef chef);
}
