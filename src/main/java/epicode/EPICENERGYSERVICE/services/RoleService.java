package epicode.EPICENERGYSERVICE.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import epicode.EPICENERGYSERVICE.entities.Role;
import epicode.EPICENERGYSERVICE.exceptions.BadRequestException;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;

@Service
public class RoleService {
	private RoleService roleService;

	public Role create(Role r) {
		roleService.findByTipoRuolo(r.getTipo()).ifPresent(role -> {
			throw new BadRequestException("Tipo ruolo" + r.getTipo() + " already in exist!");
		});

		Role newRole = new Role(r.getTipo());

		return newRole;
	}

	public List<Role> find() {

		return roleRepo.findAll();
	}

	public Role findById(UUID id) {

		return roleRepo.findById(id).orElseThrow(() -> new NotFoundException("Ruolo con Id:" + id + "non trovato!!"));

	}

	public Role findByTipoRuolo(String tipo) {
		return roleRepo.findByTipo(tipo)
				.orElseThrow(() -> new NotFoundException("Ruolo di tipo:" + tipo + "non trovato!!"));

	}

	public Role findByIdAndUpdate(UUID id, Role r) throws NotFoundException {
		Role found = this.findById(id);

		found.setId(id);
		found.setTipo(r.getTipo());

		return roleRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Role found = this.findById(id);
		roleRepo.delete(found);
	}

}
