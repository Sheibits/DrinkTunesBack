package pe.edu.upc.tfversion1.serviceinterfaces;

import pe.edu.upc.tfversion1.entities.ArtistApplications;

import java.util.List;
import java.util.Optional;

public interface IArtistApplicationsService {
    public List<ArtistApplications> list();
    public void insert(ArtistApplications artistApplications);
    public void update(ArtistApplications artistApplications);
    public void delete(int id);
    Optional<ArtistApplications> findById(int id);
}
