package pe.edu.upc.tfversion1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tfversion1.dtos.ReportDataDTO;
import pe.edu.upc.tfversion1.entities.Messages;

import java.util.List;

@Repository
public interface IMessagesRepository extends JpaRepository<Messages, Integer> {
    @Query("SELECT new pe.edu.upc.tfversion1.dtos.ReportDataDTO(u.username, COUNT(m)) " +
            "FROM Messages m JOIN m.sender u " +
            "GROUP BY u.username " +
            "ORDER BY COUNT(m) DESC")
    List<ReportDataDTO> countMessagesByUser();
}
