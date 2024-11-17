package pe.edu.upc.tfversion1.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "artist_applications")
public class ArtistApplications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "applied_at", nullable = false)
    private Timestamp appliedAt;

    @ManyToOne
    @JoinColumn(name = "restaurant_ads_ad_id", nullable = false)
    private RestaurantAds restaurantAds; // Foreign Key reference to RestaurantAds

    @ManyToOne
    @JoinColumn(name = "artist_profile_artist_id", nullable = false)
    private ArtistProfile artistProfile; // Foreign Key reference to ArtistProfile

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

    public RestaurantAds getRestaurantAds() {
        return restaurantAds;
    }

    public void setRestaurantAds(RestaurantAds restaurantAds) {
        this.restaurantAds = restaurantAds;
    }

    public ArtistProfile getArtistProfile() {
        return artistProfile;
    }

    public void setArtistProfile(ArtistProfile artistProfile) {
        this.artistProfile = artistProfile;
    }
}
