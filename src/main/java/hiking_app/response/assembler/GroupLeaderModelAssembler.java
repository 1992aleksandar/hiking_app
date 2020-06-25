package hiking_app.response.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import hiking_app.controller.UserController;
import hiking_app.entity.GroupLeaderEntity;
import hiking_app.response.GroupLeaderModel;

public class GroupLeaderModelAssembler
		extends RepresentationModelAssemblerSupport<GroupLeaderEntity, GroupLeaderModel> {

	public GroupLeaderModelAssembler(Class<?> controllerClass, Class<GroupLeaderModel> resourceType) {
		super(UserController.class, GroupLeaderModel.class);
	}

	@Override
	public GroupLeaderModel toModel(GroupLeaderEntity entity) {
		GroupLeaderModel groupLeaderModel = instantiateModel(entity);

		groupLeaderModel.add(linkTo(methodOn(UserController.class).getUser(entity.getEmail())).withSelfRel());

		groupLeaderModel.setName(entity.getName());
		groupLeaderModel.setEmail(entity.getEmail());

		return groupLeaderModel;
	}
}
