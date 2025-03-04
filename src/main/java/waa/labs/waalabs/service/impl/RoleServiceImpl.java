package waa.labs.waalabs.service.impl;

import org.springframework.stereotype.Service;
import waa.labs.waalabs.domain.Role;
import waa.labs.waalabs.repo.RoleRepo;
import waa.labs.waalabs.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role createRole(String name) {
        if(roleRepo.findRoleByName(name) != null) {
            throw new RuntimeException("Role already exists");
        }
        Role role = Role.builder().role(name).build();
        return roleRepo.save(role);
    }

    @Override
    public Role updateRole(long id, String name) {
        Role role = roleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        role.setRole(name);
        return roleRepo.save(role);
    }

    @Override
    public void deleteRole(long id) {
        Role role = roleRepo.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        roleRepo.delete(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }
}
