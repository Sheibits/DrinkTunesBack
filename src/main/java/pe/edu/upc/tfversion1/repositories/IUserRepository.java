package pe.edu.upc.tfversion1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.tfversion1.dtos.ReportDataDTO;
import pe.edu.upc.tfversion1.entities.User;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    public User findOneByUsername(String username);

    //BUSCAR POR NOMBRE
    @Query("select count(u.username) from User u where u.username =:username")
    public int buscarUsername(@Param("username") String nombre);

    // Consulta para contar usuarios por rol desde la relación con roles
    @Query("SELECT new pe.edu.upc.tfversion1.dtos.ReportDataDTO(r.roleName, COUNT(u)) " +
            "FROM User u JOIN u.roles r GROUP BY r.roleName")
    List<ReportDataDTO> countUsersByRole();

    //INSERTAR ROLES
    @Transactional
    @Modifying
    @Query(value = "insert into roles (rol, user_id) VALUES (:rol, :user_id)", nativeQuery = true)
    public void insRol(@Param("rol") String authority, @Param("user_id") Long user_id);
}
