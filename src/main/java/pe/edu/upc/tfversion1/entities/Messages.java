package pe.edu.upc.tfversion1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @Column(name = "message_text", nullable = false, columnDefinition = "TEXT")
    private String messageText;

    @Column(name = "sent_at", nullable = false)
    private Timestamp sentAt;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    @JsonBackReference
    private User sender; // Foreign Key reference to Users (sender)

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    @JsonBackReference
    private User receiver; // Foreign Key reference to Users (receiver)

    @ManyToOne
    @JoinColumn(name = "restaurant_ads_ad_id", nullable = false)
    private RestaurantAds restaurantAds; // Foreign Key reference to RestaurantAds

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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public RestaurantAds getRestaurantAds() {
        return restaurantAds;
    }

    public void setRestaurantAds(RestaurantAds restaurantAds) {
        this.restaurantAds = restaurantAds;
    }
}
