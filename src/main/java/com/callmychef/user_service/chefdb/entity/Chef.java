package com.callmychef.user_service.chefdb.entity;

import com.callmychef.user_service.userdb.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "chefs")
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "primary_cuisine")
    private String primaryCuisine;

    @Column(name = "experience_years")
    private Integer experienceYears;
    private String bio;
    private Double basePrice;

    @Column(name = "rating_average")
    private Double ratingAverage;

    @Column(name = "total_ratings")
    private Integer totalRatings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(Double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public Integer getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(Integer totalRatings) {
        this.totalRatings = totalRatings;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
