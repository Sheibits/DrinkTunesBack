package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.ArtistProfileDTO;
import pe.edu.upc.tfversion1.entities.ArtistProfile;
import pe.edu.upc.tfversion1.serviceinterfaces.IArtistProfileService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artist_profiles")
public class ArtistProfileController {

    @Autowired
    private IArtistProfileService artistProfileService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')") // Todos los roles pueden listar perfiles
    public List<ArtistProfileDTO> listarPerfilesDeArtistas() {
        return artistProfileService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ArtistProfileDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')")
    public ResponseEntity<ArtistProfileDTO> findArtistById(@PathVariable("id") int id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<ArtistProfile> artistProfile = artistProfileService.findArtistById(id);

        if (artistProfile.isPresent()) {
            ArtistProfileDTO artistProfileDTO = modelMapper.map(artistProfile.get(), ArtistProfileDTO.class);
            return ResponseEntity.ok(artistProfileDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ARTISTA')") // Solo Admin y Artista pueden registrar perfiles
    public void registrar(@RequestBody ArtistProfileDTO artistProfileDTO) {
        ModelMapper m = new ModelMapper();
        ArtistProfile ap = m.map(artistProfileDTO, ArtistProfile.class);
        artistProfileService.insert(ap);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ARTISTA')") // Solo Admin y Artista pueden modificar perfiles
    public void modificar(@PathVariable("id") int id, @RequestBody ArtistProfileDTO artistProfileDTO) {
        ModelMapper m = new ModelMapper();
        ArtistProfile ap = m.map(artistProfileDTO, ArtistProfile.class);
        ap.setArtistId(id); // Asegura que el ID esté asignado en caso de que el DTO no lo tenga
        artistProfileService.update(ap);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ARTISTA')") // Solo Admin y Artista pueden modificar perfiles
    public void eliminar(@PathVariable("id") int id) {
        artistProfileService.delete(id);
    }
}
