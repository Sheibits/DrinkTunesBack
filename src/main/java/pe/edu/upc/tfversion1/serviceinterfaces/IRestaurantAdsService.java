package pe.edu.upc.tfversion1.serviceinterfaces;

import pe.edu.upc.tfversion1.entities.Restaurant;
import pe.edu.upc.tfversion1.entities.RestaurantAds;

import java.util.List;
import java.util.Optional;

public interface IRestaurantAdsService {
    public List<RestaurantAds> list();
    public void insert(RestaurantAds restaurantAds);
    public void update(RestaurantAds restaurantAds);
    public void delete(int id);
    RestaurantAds findById(int id) throws RuntimeException;
    Optional<RestaurantAds> findAdsById(int id);
}
