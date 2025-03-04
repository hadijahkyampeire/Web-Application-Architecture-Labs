package waa.labs.waalabs.service;

import waa.labs.waalabs.domain.Role;

import java.util.List;

public interface RoleService {
    Role createRole(String name);
    Role updateRole(long id, String name);
    void deleteRole(long id);
    List<Role> getAllRoles();
}
