package epicode.EPICENERGYSERVICE.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epicode.EPICENERGYSERVICE.auth.payloads.AuthenticationSuccessfullPayload;
import epicode.EPICENERGYSERVICE.entities.Role;
import epicode.EPICENERGYSERVICE.entities.User;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.exceptions.UnauthorizedException;
import epicode.EPICENERGYSERVICE.payloads.UserCreatePayload;
import epicode.EPICENERGYSERVICE.payloads.UserLoginPayload;
import epicode.EPICENERGYSERVICE.repositories.RoleRepository;
import epicode.EPICENERGYSERVICE.services.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UsersService usersService;
	@Autowired
	private PasswordEncoder bcrypt;
	@Autowired
	RoleRepository roleRepo;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Validated UserCreatePayload body) {

		body.setPassword(bcrypt.encode(body.getPassword()));

		User createdUser = usersService.create(body);

		System.out.println(createdUser);

		Role ruoloDefault = roleRepo.findByTipo("USER").orElseThrow(() -> new NotFoundException("Ruolo USER non esiste!!"));
		System.out.println(ruoloDefault);

		//		Role userRole = new Role();
		//		userRole.setTipo("USER");
		//		createdUser.getRoles().add(userRole);

		//ruoloDefault.getUsers().add(createdUser);

		roleRepo.save(ruoloDefault);

		createdUser.setRole(ruoloDefault);

		System.out.println(createdUser);
		//createdUser.getRoles().add(ruoloDefault);

		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body)
			throws NotFoundException {

		User user = usersService.findByUsername(body.getUsername());

		String plainPW = body.getPassword();
		String hashedPW = user.getPassword();

		if (!bcrypt.matches(plainPW, hashedPW))
			throw new UnauthorizedException("Credenziali non valide");

		String token = JWTTools.createToken(user);
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}
