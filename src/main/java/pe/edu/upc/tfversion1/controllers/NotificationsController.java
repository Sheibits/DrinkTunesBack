package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.NotificationsDTO;
import pe.edu.upc.tfversion1.entities.Notifications;
import pe.edu.upc.tfversion1.entities.RestaurantAds;
import pe.edu.upc.tfversion1.serviceinterfaces.INotificationsService;
import pe.edu.upc.tfversion1.serviceinterfaces.IRestaurantAdsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

    @Autowired
    private INotificationsService notificationsService;

    @Autowired
    private IRestaurantAdsService restaurantAdsService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')")
    public List<NotificationsDTO> listarNotificaciones() {
        return notificationsService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, NotificationsDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')")
    public ResponseEntity<NotificationsDTO> findById(@PathVariable("id") int id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Notifications> notification = notificationsService.findById(id);

        if (notification.isPresent()) {
            NotificationsDTO notificationsDTO = modelMapper.map(notification.get(), NotificationsDTO.class);
            return ResponseEntity.ok(notificationsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void registrar(@RequestBody NotificationsDTO notificationsDTO) {
        ModelMapper m = new ModelMapper();
        Notifications notification = m.map(notificationsDTO, Notifications.class);

        RestaurantAds restaurantAds = restaurantAdsService.findById(notificationsDTO.getRestaurantAdsId());
        notification.setRestaurantAds(restaurantAds);

        notificationsService.insert(notification);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> modificar(@PathVariable int id, @RequestBody NotificationsDTO notificationsDTO) {
        Optional<Notifications> existingNotification = notificationsService.findById(id);
        if (existingNotification.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            Notifications notification = modelMapper.map(notificationsDTO, Notifications.class);

            // Cargar el RestaurantAds desde la base de datos
            Optional<RestaurantAds> restaurantAds = restaurantAdsService.findAdsById(notificationsDTO.getRestaurantAdsId());

            if (restaurantAds.isPresent()) {
                notification.setRestaurantAds(restaurantAds.get());
                notification.setNotificationId(id); // Aseguramos que el ID sea correcto
                notificationsService.update(notification);
                return ResponseEntity.ok().build();
            } else {
                // Si RestaurantAds no se encuentra, devolver un error
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminar(@PathVariable("id") int id) {
        notificationsService.delete(id);
    }
}
