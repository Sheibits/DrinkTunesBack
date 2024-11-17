package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.ArtistApplicationsDTO;
import pe.edu.upc.tfversion1.dtos.ArtistProfileDTO;
import pe.edu.upc.tfversion1.entities.ArtistApplications;
import pe.edu.upc.tfversion1.entities.ArtistProfile;
import pe.edu.upc.tfversion1.entities.RestaurantAds;
import pe.edu.upc.tfversion1.serviceinterfaces.IArtistApplicationsService;
import pe.edu.upc.tfversion1.serviceinterfaces.IArtistProfileService;
import pe.edu.upc.tfversion1.serviceinterfaces.IRestaurantAdsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artist_applications")
public class ArtistApplicationsController {

    @Autowired
    private IArtistApplicationsService artistApplicationsService;
    @Autowired
    private IRestaurantAdsService restaurantAdsService;
    @Autowired
    private IArtistProfileService artistProfileService;

    // Listar solicitudes de artistas (acceso para todos los roles)
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')")
    public List<ArtistApplicationsDTO> listarSolicitudesDeArtistas() {
        return artistApplicationsService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ArtistApplicationsDTO.class);
        }).collect(Collectors.toList());
    }

    // Buscar por ID (acceso para todos los roles)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')")
    public ResponseEntity<ArtistApplicationsDTO> findById(@PathVariable("id") int id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<ArtistApplications> artistApplication = artistApplicationsService.findById(id);

        if (artistApplication.isPresent()) {
            ArtistApplicationsDTO artistApplicationsDTO = modelMapper.map(artistApplication.get(), ArtistApplicationsDTO.class);
            return ResponseEntity.ok(artistApplicationsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Registrar solicitudes (solo Admin y Artista)
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ARTISTA')")
    public void registrar(@RequestBody ArtistApplicationsDTO artistApplicationsDTO) {
        ModelMapper m = new ModelMapper();
        ArtistApplications aa = m.map(artistApplicationsDTO, ArtistApplications.class);

        // Validar entidades relacionadas
        RestaurantAds restaurantAds = restaurantAdsService.findById(artistApplicationsDTO.getRestaurantAdsId());
        ArtistProfile artistProfile = artistProfileService.findById(artistApplicationsDTO.getArtistProfileId());

        if (restaurantAds == null || artistProfile == null) {
            throw new IllegalArgumentException("Datos relacionados no encontrados");
        }

        aa.setRestaurantAds(restaurantAds);
        aa.setArtistProfile(artistProfile);

        artistApplicationsService.insert(aa);
    }

    // Modificar solicitudes (solo Admin y Artista)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ARTISTA')")
    public ResponseEntity<Void> modificar(@PathVariable("id") int id, @RequestBody ArtistApplicationsDTO artistApplicationsDTO) {
        Optional<ArtistApplications> existingApplication = artistApplicationsService.findById(id);

        if (existingApplication.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            ArtistApplications artistApplication = modelMapper.map(artistApplicationsDTO, ArtistApplications.class);

            // Actualizar datos relacionados
            artistApplication.setApplicationId(id);
            RestaurantAds restaurantAds = restaurantAdsService.findById(artistApplicationsDTO.getRestaurantAdsId());
            ArtistProfile artistProfile = artistProfileService.findById(artistApplicationsDTO.getArtistProfileId());

            if (restaurantAds == null || artistProfile == null) {
                throw new IllegalArgumentException("Datos relacionados no encontrados");
            }

            artistApplication.setRestaurantAds(restaurantAds);
            artistApplication.setArtistProfile(artistProfile);

            artistApplicationsService.update(artistApplication);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar solicitudes (solo Admin y Artista)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ARTISTA')")
    public void eliminar(@PathVariable("id") int id) {
        artistApplicationsService.delete(id);
    }
}
