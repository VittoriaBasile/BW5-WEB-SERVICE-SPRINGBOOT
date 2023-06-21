package epicode.EPICENERGYSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import epicode.EPICENERGYSERVICE.entities.Role;
import epicode.EPICENERGYSERVICE.repositories.UsersRepository;
import epicode.EPICENERGYSERVICE.services.RoleService;

@Component
@Order(0)
public class RoleRunner implements CommandLineRunner {

	@Autowired
	// RoleRepository roleRepo;
	RoleService roleService;

	@Autowired
	UsersRepository usersRepo;

	@Override
	public void run(String... args) throws Exception {

		if (roleService.find().size() == 0) {
			String[] tipiDefault = new String[] { "USER", "ADMIN" };

			for (String tipo : tipiDefault) {
				Role newRole = new Role(tipo);
				roleService.create(newRole);
			}
		}

	}

}