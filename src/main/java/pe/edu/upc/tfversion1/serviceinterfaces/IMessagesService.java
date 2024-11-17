package pe.edu.upc.tfversion1.serviceinterfaces;

import pe.edu.upc.tfversion1.entities.Messages;

import java.util.List;
import java.util.Optional;

public interface IMessagesService {
    public List<Messages> list();
    public void insert(Messages messages);
    public void update(Messages messages);
    public void delete(int id);
    Optional<Messages> findById(int id);
}
