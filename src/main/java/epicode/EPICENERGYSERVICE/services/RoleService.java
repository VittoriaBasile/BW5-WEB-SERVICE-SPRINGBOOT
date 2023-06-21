package epicode.EPICENERGYSERVICE.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import epicode.EPICENERGYSERVICE.entities.Role;
import epicode.EPICENERGYSERVICE.repositories.RoleRepository;

@Service
public class RoleService {
	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role findById(UUID id) {
		Optional<Role> optionalRole = roleRepository.findById(id);
		return optionalRole.orElse(null);
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	//	public Optional<Role> findByUser(String user) {
	//		return roleRepository.findByUser(user);
	//	}
	//
	//	public Optional<Role> findByAdmin(String admin) {
	//		return roleRepository.findByAdmin(admin);
	//	}

	public Role create(Role role) {
		return roleRepository.save(role);
	}

	public void deleteById(UUID id) {
		roleRepository.deleteById(id);
	}
}
