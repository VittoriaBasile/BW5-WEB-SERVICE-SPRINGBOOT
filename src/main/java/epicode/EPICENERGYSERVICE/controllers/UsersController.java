package epicode.EPICENERGYSERVICE.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epicode.EPICENERGYSERVICE.entities.User;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.payloads.UserCreatePayload;
import epicode.EPICENERGYSERVICE.payloads.UserUpdatePayload;
import epicode.EPICENERGYSERVICE.services.UsersService;

@RestController
@RequestMapping("/users")
//@PreAuthorize("hasRole('USER')")
public class UsersController {
	@Autowired
	private UsersService usersService;

	@GetMapping("")
	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return usersService.find(page, size, sortBy);
	}

	//	@PostMapping("")
	//	@ResponseStatus(HttpStatus.CREATED)
	//	@PostAuthorize("hasRole('ADMIN')")
	//	public User saveUser(@RequestBody @Validated UserCreatePayload body) {
	//		return usersService.create(body);
	//	}
	@PostMapping("")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody @Validated UserCreatePayload body) {
		return usersService.create(body);
	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable UUID userId) throws Exception {
		return usersService.findById(userId);
	}

	@PutMapping("/{userId}")
	// @PostAuthorize("hasRole('ADMIN')")
	public User updateUser(@PathVariable UUID userId, @RequestBody UserUpdatePayload body) throws Exception {
		return usersService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteUser(@PathVariable UUID userId) throws NotFoundException {
		usersService.findByIdAndDelete(userId);
	}

}
