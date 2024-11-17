package pe.edu.upc.tfversion1.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tfversion1.entities.ArtistProfile;
import pe.edu.upc.tfversion1.entities.User;
import pe.edu.upc.tfversion1.repositories.IArtistProfileRepository;
import pe.edu.upc.tfversion1.repositories.IUserRepository;
import pe.edu.upc.tfversion1.serviceinterfaces.IArtistProfileService;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistProfileServiceImplement implements IArtistProfileService {

    @Autowired
    private IArtistProfileRepository artistProfileRepository;

    @Autowired
    private IUserRepository userRepository; // Añadido para acceder a User

    @Override
    public List<ArtistProfile> list() {
        return artistProfileRepository.findAll();
    }

    @Override
    public void insert(ArtistProfile artistProfile) {
        // Buscar el User asociado por userId
        User user = userRepository.findById(artistProfile.getUser().getUserId()).orElse(null);
        if (user != null) {
            artistProfile.setUser(user);
            artistProfileRepository.save(artistProfile);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void update(ArtistProfile artistProfile) {
        // Buscar el User asociado por userId
        User user = userRepository.findById(artistProfile.getUser().getUserId()).orElse(null);
        if (user != null) {
            artistProfile.setUser(user);
            artistProfileRepository.save(artistProfile);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void delete(int id) {
        artistProfileRepository.deleteById(id);
    }

    @Override
    public ArtistProfile findById(int id) throws RuntimeException {
        return artistProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ArtistProfile not found"));
    }

    @Override
    public Optional<ArtistProfile> findArtistById(int id) {
        return artistProfileRepository.findById(id);
    }
}
