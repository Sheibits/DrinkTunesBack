package pe.edu.upc.tfversion1.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tfversion1.entities.Messages;
import pe.edu.upc.tfversion1.repositories.IMessagesRepository;
import pe.edu.upc.tfversion1.serviceinterfaces.IMessagesService;

import java.util.List;
import java.util.Optional;

@Service
public class MessagesServiceImplement implements IMessagesService {

    @Autowired
    private IMessagesRepository messagesRepository;

    @Override
    public List<Messages> list() {
        return messagesRepository.findAll();
    }

    @Override
    public void insert(Messages messages) {
        messagesRepository.save(messages);
    }

    @Override
    public void update(Messages messages) {
        messagesRepository.save(messages);
    }

    @Override
    public void delete(int id) {
        messagesRepository.deleteById(id);
    }

    @Override
    public Optional<Messages> findById(int id) {
        return messagesRepository.findById(id);
    }
}
