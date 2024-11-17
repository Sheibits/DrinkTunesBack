package pe.edu.upc.tfversion1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tfversion1.dtos.ReportDataDTO;
import pe.edu.upc.tfversion1.entities.RestaurantAds;

import java.util.List;

@Repository
public interface IRestaurantAdsRepository extends JpaRepository<RestaurantAds, Integer> {
    @Query("SELECT new pe.edu.upc.tfversion1.dtos.ReportDataDTO(r.restaurantName, COUNT(a)) " +
            "FROM RestaurantAds a JOIN a.restaurant r " +
            "GROUP BY r.restaurantName " +
            "ORDER BY COUNT(a) DESC")
    List<ReportDataDTO> countAdsByRestaurant();
}
