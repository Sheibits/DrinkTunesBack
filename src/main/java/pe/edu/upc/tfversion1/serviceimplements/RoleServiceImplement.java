package pe.edu.upc.tfversion1.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tfversion1.entities.Role;
import pe.edu.upc.tfversion1.repositories.IRoleRepository;
import pe.edu.upc.tfversion1.serviceinterfaces.IRoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplement implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> list() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(int id) {
        Optional<Role> optional = roleRepository.findById(id);
        return optional.orElse(null);  // Manejar excepción si es necesario
    }

    @Override
    public void insert(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void update(Role role) {
        roleRepository.save(role);  // Save sirve tanto para insertar como para actualizar
    }

    @Override
    public void delete(int id) {
        roleRepository.deleteById(id);
    }
}
