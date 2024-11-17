package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.RestaurantAdsDTO;
import pe.edu.upc.tfversion1.entities.RestaurantAds;
import pe.edu.upc.tfversion1.serviceinterfaces.IRestaurantAdsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurant_ads")
public class RestaurantAdsController {

    @Autowired
    private IRestaurantAdsService restaurantAdsService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')") // Todos los roles pueden listar anuncios
    public List<RestaurantAdsDTO> listarAnunciosRestaurantes() {
        return restaurantAdsService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RestaurantAdsDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')") // Todos los roles pueden ver un anuncio específico
    public ResponseEntity<RestaurantAdsDTO> findById(@PathVariable("id") int id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<RestaurantAds> restaurantAds = restaurantAdsService.findAdsById(id);

        if (restaurantAds.isPresent()) {
            RestaurantAdsDTO restaurantAdsDTO = modelMapper.map(restaurantAds.get(), RestaurantAdsDTO.class);
            return ResponseEntity.ok(restaurantAdsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE')") // Solo Admin y Restaurante pueden registrar anuncios
    public void registrar(@RequestBody RestaurantAdsDTO restaurantAdsDTO) {
        ModelMapper m = new ModelMapper();
        RestaurantAds ra = m.map(restaurantAdsDTO, RestaurantAds.class);
        restaurantAdsService.insert(ra);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE')") // Solo Admin y Restaurante pueden modificar anuncios
    public ResponseEntity<Void> modificar(@PathVariable("id") int id, @RequestBody RestaurantAdsDTO restaurantAdsDTO) {
        Optional<RestaurantAds> existingAd = restaurantAdsService.findAdsById(id);

        if (existingAd.isPresent()) {
            ModelMapper m = new ModelMapper();
            RestaurantAds ra = m.map(restaurantAdsDTO, RestaurantAds.class);

            // Setear el ID y conservar el valor de createdAt
            ra.setAdId(id);
            ra.setCreatedAt(existingAd.get().getCreatedAt()); // Mantener el valor de createdAt

            restaurantAdsService.update(ra);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE')") // Solo Admin y Restaurante pueden modificar anuncios
    public void eliminar(@PathVariable("id") int id) {
        restaurantAdsService.delete(id);
    }
}
