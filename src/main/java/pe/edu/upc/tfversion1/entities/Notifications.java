package pe.edu.upc.tfversion1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "notifications")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;

    @Column(name = "notification_text", nullable = false, columnDefinition = "TEXT")
    private String notificationText;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @Column(name = "notified_at", nullable = false)
    private Timestamp notifiedAt;

    @ManyToOne
    @JoinColumn(name = "users_user_id", nullable = false)
    @JsonBackReference
    private User user; // Foreign Key reference to Users

    @ManyToOne
    @JoinColumn(name = "restaurant_ads_ad_id", nullable = false)
    private RestaurantAds restaurantAds; // Foreign Key reference to RestaurantAds

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Timestamp getNotifiedAt() {
        return notifiedAt;
    }

    public void setNotifiedAt(Timestamp notifiedAt) {
        this.notifiedAt = notifiedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RestaurantAds getRestaurantAds() {
        return restaurantAds;
    }

    public void setRestaurantAds(RestaurantAds restaurantAds) {
        this.restaurantAds = restaurantAds;
    }
}
