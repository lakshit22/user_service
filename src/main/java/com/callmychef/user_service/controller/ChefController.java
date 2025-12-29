package com.callmychef.user_service.controller;

import com.callmychef.user_service.chefdb.entity.Chef;
import com.callmychef.user_service.service.ChefService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chefs")
public class ChefController {
    private final ChefService chefService;

    public ChefController(ChefService chefService){
        this.chefService = chefService;
    }

    @PostMapping("/internal")
    public void createChef(@RequestBody Chef chef){
        chefService.createChef(chef);
    }

}
