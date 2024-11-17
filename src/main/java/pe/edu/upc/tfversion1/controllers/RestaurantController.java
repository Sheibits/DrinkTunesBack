package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.RestaurantDTO;
import pe.edu.upc.tfversion1.entities.Restaurant;
import pe.edu.upc.tfversion1.serviceinterfaces.IRestaurantService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')") // Todos los roles pueden listar restaurantes
    public List<RestaurantDTO> listarRestaurantes() {
        return restaurantService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RestaurantDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')")
    public ResponseEntity<RestaurantDTO> findById(@PathVariable("id") int id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Restaurant> restaurant = restaurantService.findById(id);

        if (restaurant.isPresent()) {
            RestaurantDTO restaurantDTO = modelMapper.map(restaurant.get(), RestaurantDTO.class);
            return ResponseEntity.ok(restaurantDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE')") // Solo Admin y Restaurante pueden modificar anuncios
    public void registrar(@RequestBody RestaurantDTO restaurantDTO) {
        ModelMapper m = new ModelMapper();
        Restaurant r = m.map(restaurantDTO, Restaurant.class);
        restaurantService.insert(r);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE')") // Solo Admin y Restaurante pueden modificar anuncios
    public ResponseEntity<Void> modificar(@PathVariable("id") int id, @RequestBody RestaurantDTO restaurantDTO) {
        ModelMapper m = new ModelMapper();
        Restaurant r = m.map(restaurantDTO, Restaurant.class);
        r.setRestaurantId(id); // Asegúrate de setear el ID en la entidad
        restaurantService.update(r);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content para éxito sin cuerpo
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE')") // Solo Admin y Restaurante pueden modificar anuncios
    public void eliminar(@PathVariable("id") int id) {
        restaurantService.delete(id);
    }
}
