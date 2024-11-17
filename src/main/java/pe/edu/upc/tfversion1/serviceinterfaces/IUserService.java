package pe.edu.upc.tfversion1.serviceinterfaces;

import pe.edu.upc.tfversion1.entities.User;

import java.util.List;

public interface IUserService {

    public void insert(User user);
    public List<User> list();
    public void update(User user);
    public void delete(int id);
    public User findById(int id) throws RuntimeException;

}
