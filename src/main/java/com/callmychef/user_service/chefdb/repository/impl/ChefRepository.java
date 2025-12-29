package com.callmychef.user_service.chefdb.repository.impl;

import com.callmychef.user_service.chefdb.entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepository extends JpaRepository<Chef,Long> {
}
