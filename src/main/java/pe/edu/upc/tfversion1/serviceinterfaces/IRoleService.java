package pe.edu.upc.tfversion1.serviceinterfaces;

import pe.edu.upc.tfversion1.entities.Role;

import java.util.List;

public interface IRoleService {
    List<Role> list();
    Role findById(int id);
    void insert(Role role);
    void update(Role role);
    void delete(int id);
}
