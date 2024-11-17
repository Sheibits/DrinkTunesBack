package pe.edu.upc.tfversion1.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tfversion1.entities.Restaurant;
import pe.edu.upc.tfversion1.repositories.IRestaurantRepository;
import pe.edu.upc.tfversion1.serviceinterfaces.IRestaurantService;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImplement implements IRestaurantService {

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @Override
    public void insert(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Optional<Restaurant> findById(int id) {
        return restaurantRepository.findById(id);
    }
}
