package waa.labs.waalabs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.labs.waalabs.domain.Role;
import waa.labs.waalabs.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestParam String roleName) {
        return ResponseEntity.ok(roleService.createRole(roleName));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable long id, @RequestParam String roleName) {
        return ResponseEntity.ok(roleService.updateRole(id, roleName));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
