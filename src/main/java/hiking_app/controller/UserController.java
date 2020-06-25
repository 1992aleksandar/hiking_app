package hiking_app.controller;

import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hiking_app.entity.EventEntity;
import hiking_app.entity.GroupLeaderEntity;
import hiking_app.entity.HikerEntity;
import hiking_app.entity.RoleEntity;
import hiking_app.entity.UserEntity;
import hiking_app.repository.EventRepository;
import hiking_app.repository.RoleRepository;
import hiking_app.repository.UserRepository;
import hiking_app.request.RegisterHikerModelMapping;
import hiking_app.response.UserModel;
import hiking_app.response.assembler.UserModelAssembler;

@RestController
public class UserController {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserModelAssembler userModelAssembler;

	@GetMapping(path = "users/{email}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserEntity getUser(@PathVariable String email) {
		UserEntity returnValue = new UserEntity();
		returnValue = userRepository.getUserEntityByEmail(email);

		return returnValue;
	}

	@PostMapping(path = "users/registration", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserModel> createHiker(@Valid @RequestBody RegisterHikerModelMapping userDetails)
			throws Exception {
		HikerEntity repositoryUser = new HikerEntity();

		if (userRepository.getUserEntityByEmail(userDetails.getEmail()) != null)
			throw new Exception("User already exist");

		userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		HikerEntity createdUser = new HikerEntity();
		RoleEntity userRole = roleRepository.findByName("ROLE_USER");
		createdUser.getRoles().add(userRole);
		BeanUtils.copyProperties(userDetails, createdUser);
		createdUser.setEnabled(true);

		repositoryUser = userRepository.save(createdUser);
		ResponseEntity<UserModel> userModel = new ResponseEntity<>(userModelAssembler.toModel(repositoryUser),
				HttpStatus.OK);
		return userModel;
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, path = "/users/{userEmail}/{eventName}")
	@Transactional
	public ResponseEntity<UserModel> addUserToEvent(@PathVariable String userEmail, @PathVariable String eventName,
			@AuthenticationPrincipal String currentPrincipal) throws Exception {
		EventEntity event = eventRepository.getEventEntityByName(eventName);
		UserEntity user = userRepository.getUserEntityByEmail(userEmail);

		if (event == null || user == null)
			throw new Exception("User or event wasnt found.");

		// principal class is String
		if (!currentPrincipal.equals(userEmail))
			if (!(userRepository.getUserEntityByEmail(currentPrincipal) instanceof GroupLeaderEntity))
				throw new Exception("You have no authorities to do that.");

		if (user instanceof HikerEntity) {
			event.addHiker((HikerEntity) user);
		}

		if (user instanceof GroupLeaderEntity) {
			event.addGroupLeader((GroupLeaderEntity) user);
		}

		eventRepository.save(event);
		ResponseEntity<UserModel> userModel = new ResponseEntity<>(userModelAssembler.toModel(user), HttpStatus.OK);

		return userModel;
	}

	@DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, path = "/users/{userEmail}/{eventName}")
	public void deleteUserFromEvent(@PathVariable String userEmail, @PathVariable String eventName) throws Exception {
		EventEntity event = eventRepository.getEventEntityByName(eventName);
		UserEntity user = userRepository.getUserEntityByEmail(userEmail);

		if (event == null || user == null)
			throw new Exception("User or event wasnt found");
		else {
			if (user instanceof HikerEntity)
				event.removeHiker((HikerEntity) user);

			if (user instanceof GroupLeaderEntity)
				event.removeGroupLeader((GroupLeaderEntity) user);
		}
		event.getHikers().remove(user);
		userRepository.save(user);
	}
}
