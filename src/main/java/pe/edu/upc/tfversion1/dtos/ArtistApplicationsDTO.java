package pe.edu.upc.tfversion1.dtos;

import java.sql.Timestamp;

public class ArtistApplicationsDTO {
    private int applicationId;
    private String status;
    private Timestamp appliedAt;
    private int restaurantAdsId; // Foreign Key to RestaurantAds
    private int artistProfileId;  // Foreign Key to ArtistProfile

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(Timestamp appliedAt) {
        this.appliedAt = appliedAt;
    }

    public int getRestaurantAdsId() {
        return restaurantAdsId;
    }

    public void setRestaurantAdsId(int restaurantAdsId) {
        this.restaurantAdsId = restaurantAdsId;
    }

    public int getArtistProfileId() {
        return artistProfileId;
    }

    public void setArtistProfileId(int artistProfileId) {
        this.artistProfileId = artistProfileId;
    }
}
