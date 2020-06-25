package hiking_app.response.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import hiking_app.controller.EventController;
import hiking_app.controller.UserController;
import hiking_app.entity.EventEntity;
import hiking_app.entity.GroupLeaderEntity;
import hiking_app.entity.HikerEntity;
import hiking_app.response.EventModel;
import hiking_app.response.GroupLeaderModel;
import hiking_app.response.HikerModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.HashSet;
import java.util.Set;

@Component
public class EventModelAssembler extends RepresentationModelAssemblerSupport<EventEntity, EventModel> {

	public EventModelAssembler() {
		super(UserController.class, EventModel.class);
	}

	@Override
	public EventModel toModel(EventEntity entity) {
		EventModel eventModel = instantiateModel(entity);

		try {
			eventModel.add(linkTo(methodOn(EventController.class).getEventByName(entity.getName())).withSelfRel());
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}

		eventModel.setName(entity.getName());
		eventModel.setDateTime(entity.getDateTime());
		eventModel.setDescription(entity.getDescription());
		eventModel.setGroup_leaders(toGroupLeaderModel(entity.getGroup_leaders()));
		eventModel.setHikers(toHikerModel(entity.getHikers()));

		return eventModel;
	}

	public Set<GroupLeaderModel> toGroupLeaderModel(Set<GroupLeaderEntity> entities) {

		Set<GroupLeaderModel> groupLeadersModel = new HashSet<GroupLeaderModel>();

		for (GroupLeaderEntity entity : entities) {
			GroupLeaderModel groupLeaderModel = new GroupLeaderModel();
			groupLeaderModel.add(linkTo(methodOn(UserController.class).getUser(entity.getEmail())).withSelfRel());
			groupLeaderModel.setName(entity.getName());
			groupLeaderModel.setEmail(entity.getEmail());
			groupLeadersModel.add(groupLeaderModel);
		}

		return groupLeadersModel;
	}

	public Set<HikerModel> toHikerModel(Set<HikerEntity> entities) {

		Set<HikerModel> hikersModel = new HashSet<HikerModel>();

		for (HikerEntity entity : entities) {
			HikerModel hikerModel = new HikerModel();
			hikerModel.add(linkTo(methodOn(UserController.class).getUser(entity.getEmail())).withSelfRel());
			hikerModel.setName(entity.getName());
			hikerModel.setEmail(entity.getEmail());
			hikersModel.add(hikerModel);
		}
		return hikersModel;
	}

}
