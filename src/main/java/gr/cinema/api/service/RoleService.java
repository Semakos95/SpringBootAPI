package gr.cinema.api.service;

import gr.cinema.api.dto.RoleDTO;
import gr.cinema.api.entity.Role;
import gr.cinema.api.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDTO> getAllRoleDTOList() {
        LOGGER.info("getAllRoles()");
        final List<Role> roleList = roleRepository.findAll();
        final List<RoleDTO> roleDTOList = new ArrayList<>();

        for (Role role : roleList) {
            final RoleDTO roleDTO = toRoleDTO(role);
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;
    }

    public Role getRole(Long id) {
        LOGGER.info("getRole() with id: {}", id);
        return roleRepository.findById(id).orElse(null);
    }

    public RoleDTO getRoleDTO(Long id) {
        LOGGER.info("getRoleDTO() with id: {}", id);
        final Role role = getRole(id);

        if (role == null) {
            //throw new NotFoundException();
        }
        return toRoleDTO(role);
    }

    public RoleDTO insertRoleDTO(RoleDTO roleDTO) {
        if (roleDTO.getId() != null) {
            LOGGER.error("insertRoleDTO(): there is a body 'id': {}", roleDTO.getId());
            //throw new BadRequestException();
        }
        Role role = new Role();
        toRole(roleDTO, role);

        role = roleRepository.save(role);
        LOGGER.info("insertRoleDTO: {}", roleDTO);
        return toRoleDTO(role);
    }

    public RoleDTO updateRoleDTO(RoleDTO roleDTO) throws Exception {
        Role role = roleRepository.findById(roleDTO.getId()).orElseThrow(Exception::new);

        toRole(roleDTO, role);
        role = roleRepository.save(role);
        LOGGER.info("updateRoleDTO() update to: {}", roleDTO);
        return toRoleDTO(role);
    }

    public void deleteRoleDTO(Long id) throws Exception {
        if (!roleRepository.existsById(id)) {
            LOGGER.error("deleteRole(): role with id {} does not exist.", id);
            throw new Exception("NotFound");
        }
        roleRepository.deleteById(id);
        LOGGER.info("deleteRole() with id: {}", id);
    }

    private RoleDTO toRoleDTO(Role role) {
        final RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    private void toRole(RoleDTO roleDTO, Role role) {
        role.setName(roleDTO.getName());
    }
}
