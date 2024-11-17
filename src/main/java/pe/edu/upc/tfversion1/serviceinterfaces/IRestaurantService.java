package pe.edu.upc.tfversion1.serviceinterfaces;

import pe.edu.upc.tfversion1.entities.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantService {
    List<Restaurant> list();
    void insert(Restaurant restaurant);
    void update(Restaurant restaurant);
    void delete(int id);
    Optional<Restaurant> findById(int id);
}
