package hiking_app.response.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import hiking_app.controller.UserController;
import hiking_app.entity.UserEntity;
import hiking_app.response.UserModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<UserEntity, UserModel> {

	public UserModelAssembler() {
		super(UserController.class, UserModel.class);
	}

	@Override
	public UserModel toModel(UserEntity entity) {
		UserModel userModel = instantiateModel(entity);

		try {
			userModel.add(linkTo(methodOn(UserController.class).getUser(entity.getEmail())).withSelfRel());
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}

		userModel.setName(entity.getName());
		userModel.setEmail(entity.getEmail());

		return userModel;

	}
}
