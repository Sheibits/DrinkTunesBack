package pe.edu.upc.tfversion1.dtos;

import java.sql.Timestamp;

public class MessagesDTO {
    private int messageId;
    private String messageText;
    private Timestamp sentAt;
    private int senderId; // Foreign Key to Users
    private int receiverId; // Foreign Key to Users
    private int restaurantAdsId; // Foreign Key to RestaurantAds

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getRestaurantAdsId() {
        return restaurantAdsId;
    }

    public void setRestaurantAdsId(int restaurantAdsId) {
        this.restaurantAdsId = restaurantAdsId;
    }
}
