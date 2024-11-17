package pe.edu.upc.tfversion1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.tfversion1.entities.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

}
