package pe.edu.upc.tfversion1.dtos;

import jakarta.persistence.*;
import pe.edu.upc.tfversion1.entities.User;

public class RoleDTO {


    private int roleId;


    private String roleName;


    private User user;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
