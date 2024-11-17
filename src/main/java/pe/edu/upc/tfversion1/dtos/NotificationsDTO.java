package pe.edu.upc.tfversion1.dtos;

import java.sql.Timestamp;

public class NotificationsDTO {
    private int notificationId;
    private String notificationText;
    private boolean isRead;
    private Timestamp notifiedAt;
    private int userId; // Foreign Key to Users
    private int restaurantAdsId; // Foreign Key to RestaurantAds

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

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Timestamp getNotifiedAt() {
        return notifiedAt;
    }

    public void setNotifiedAt(Timestamp notifiedAt) {
        this.notifiedAt = notifiedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantAdsId() {
        return restaurantAdsId;
    }

    public void setRestaurantAdsId(int restaurantAdsId) {
        this.restaurantAdsId = restaurantAdsId;
    }
}
