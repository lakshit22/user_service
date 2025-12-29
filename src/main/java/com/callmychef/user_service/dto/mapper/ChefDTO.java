package com.callmychef.user_service.dto.mapper;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ChefDTO {
    private String primaryCuisine;
    private Integer experienceYears;
    private String bio;
    private Double basePrice;

    public String getPrimaryCuisine() {
        return primaryCuisine;
    }

    public void setPrimaryCuisine(String primaryCuisine) {
        this.primaryCuisine = primaryCuisine;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }
}
