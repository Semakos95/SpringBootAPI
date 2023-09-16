package gr.cinema.api.controller;

import gr.cinema.api.dto.RoleDTO;
import gr.cinema.api.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/allRoles") //http://localhost:8080/role/allRoles
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoleDTOList();
    }

    @GetMapping("/{id}") //http://localhost:8080/role/1
    public RoleDTO getRole(@PathVariable("id") Long id) {
        return roleService.getRoleDTO(id);
    }

    @PostMapping //http://localhost:8080/role OK
    public RoleDTO insertRole(@RequestBody RoleDTO roleDTO) {
        return roleService.insertRoleDTO(roleDTO);
    }

    @PutMapping("/{id}") //http://localhost:8080/role/1 OK
    public RoleDTO updateRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO) throws Exception {
        if (!id.equals(roleDTO.getId())) {
            LOGGER.error("");
            throw new Exception("BadRequest");
        }
        roleDTO.setId(id);
        return roleService.updateRoleDTO(roleDTO);
    }

    @DeleteMapping("/{id}") //http://localhost:8080/role/2
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable("id") Long id) throws Exception {
        roleService.deleteRoleDTO(id);
    }
}
