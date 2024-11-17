package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.MessagesDTO;
import pe.edu.upc.tfversion1.entities.Messages;
import pe.edu.upc.tfversion1.entities.RestaurantAds;
import pe.edu.upc.tfversion1.entities.User;
import pe.edu.upc.tfversion1.serviceinterfaces.IMessagesService;
import pe.edu.upc.tfversion1.serviceinterfaces.IRestaurantAdsService;
import pe.edu.upc.tfversion1.serviceinterfaces.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private IMessagesService messagesService;

    @Autowired
    private IRestaurantAdsService restaurantAdsService;

    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')") // Todos los roles pueden listar mensajes
    public List<MessagesDTO> listarMensajes() {
        return messagesService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, MessagesDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')")
    public ResponseEntity<MessagesDTO> findById(@PathVariable("id") int id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Messages> message = messagesService.findById(id);

        if (message.isPresent()) {
            MessagesDTO messagesDTO = modelMapper.map(message.get(), MessagesDTO.class);
            return ResponseEntity.ok(messagesDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')") // Todos los roles pueden listar mensajes
    public void registrar(@RequestBody MessagesDTO messagesDTO) {
        ModelMapper m = new ModelMapper();
        Messages message = m.map(messagesDTO, Messages.class);

        // Buscar RestaurantAds existente en la base de datos
        RestaurantAds restaurantAds = restaurantAdsService.findById(messagesDTO.getRestaurantAdsId());

        // Buscar el sender existente en la base de datos
        User sender = userService.findById(messagesDTO.getSenderId());

        // Buscar el receiver existente en la base de datos
        User receiver = userService.findById(messagesDTO.getReceiverId());

        // Asignar las entidades persistidas a Messages
        message.setRestaurantAds(restaurantAds);
        message.setSender(sender);  // Asignar el sender
        message.setReceiver(receiver);  // Asignar el receiver

        // Guardar Messages
        messagesService.insert(message);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')") // Todos los roles pueden listar mensajes
    public ResponseEntity<Void> modificar(@PathVariable("id") int id, @RequestBody MessagesDTO messagesDTO) {
        ModelMapper m = new ModelMapper();
        Messages message = m.map(messagesDTO, Messages.class);

        // Buscar RestaurantAds existente en la base de datos
        RestaurantAds restaurantAds = restaurantAdsService.findById(messagesDTO.getRestaurantAdsId());

        // Buscar el sender existente en la base de datos
        User sender = userService.findById(messagesDTO.getSenderId());

        // Buscar el receiver existente en la base de datos
        User receiver = userService.findById(messagesDTO.getReceiverId());

        // Asignar las entidades persistidas a Messages
        message.setRestaurantAds(restaurantAds);
        message.setSender(sender);  // Asignar el sender
        message.setReceiver(receiver);  // Asignar el receiver
        message.setMessageId(id);  // Asegurar que el ID del mensaje se establezca correctamente

        // Actualizar Messages
        messagesService.update(message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')") // Todos los roles pueden listar mensajes
    public void eliminar(@PathVariable("id") int id) {
        messagesService.delete(id);
    }
}
