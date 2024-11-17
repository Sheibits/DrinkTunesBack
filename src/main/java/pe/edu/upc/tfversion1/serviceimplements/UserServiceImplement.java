package pe.edu.upc.tfversion1.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tfversion1.entities.Role;
import pe.edu.upc.tfversion1.entities.User;
import pe.edu.upc.tfversion1.repositories.IRoleRepository;
import pe.edu.upc.tfversion1.repositories.IUserRepository;
import pe.edu.upc.tfversion1.serviceinterfaces.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements IUserService {

    @Autowired
    private IUserRepository userRepository;


    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(int id) throws RuntimeException {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
