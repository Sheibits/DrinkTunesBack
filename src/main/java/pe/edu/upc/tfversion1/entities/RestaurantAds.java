package pe.edu.upc.tfversion1.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "restaurant_ads")
public class RestaurantAds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adId;

    @Column(name = "event_date", nullable = false)
    private Timestamp eventDate;

    @Column(name = "budget", nullable = false)
    private double budget;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant; // Foreign Key reference to Restaurant

    @OneToMany(mappedBy = "restaurantAds")
    private List<ArtistApplications> artistApplications;

    @OneToMany(mappedBy = "restaurantAds")
    private List<Notifications> notifications;

    // Getters y Setters

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<ArtistApplications> getArtistApplications() {
        return artistApplications;
    }

    public void setArtistApplications(List<ArtistApplications> artistApplications) {
        this.artistApplications = artistApplications;
    }

    public List<Notifications> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notifications> notifications) {
        this.notifications = notifications;
    }
}
