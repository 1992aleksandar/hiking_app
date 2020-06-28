package hiking_app.response.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import hiking_app.controller.UserController;
import hiking_app.entity.EventEntity;
import hiking_app.entity.GroupLeaderEntity;
import hiking_app.entity.HikerEntity;
import hiking_app.entity.UserEntity;
import hiking_app.response.EventModel;
import hiking_app.response.GroupLeaderModel;
import hiking_app.response.HikerModel;
import hiking_app.response.UserModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.HashSet;
import java.util.Set;

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

		try {
			if (entity.getClass() == HikerEntity.class) {
				if (((HikerEntity) entity).getEvents() != null)
					userModel.setEvents(toEventModel(((HikerEntity) entity).getEvents()));
			}			
			if (entity.getClass() == GroupLeaderEntity.class) {
				if (((GroupLeaderEntity) entity).getEvents() != null)
					userModel.setEvents(toEventModel(((GroupLeaderEntity) entity).getEvents()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userModel;
	}

	public Set<EventModel> toEventModel(Set<EventEntity> entities) throws Exception {
		Set<EventModel> eventsModel = new HashSet<EventModel>();

		for (EventEntity entity : entities) {
			EventModel eventModel = new EventModel();
			eventModel.setName(entity.getName());
			eventModel.setDateTime(entity.getDateTime());
			eventModel.setDescription(entity.getDescription());
			eventModel.setGroup_leaders(toGroupLeaderModel(entity.getGroup_leaders()));
			eventModel.setHikers(toHikerModel(entity.getHikers()));
			eventsModel.add(eventModel);

		}

		return eventsModel;
	}

	public Set<GroupLeaderModel> toGroupLeaderModel(Set<GroupLeaderEntity> entities) throws Exception {

		Set<GroupLeaderModel> groupLeadersModel = new HashSet<GroupLeaderModel>();

		for (GroupLeaderEntity entity : entities) {
			GroupLeaderModel groupLeaderModel = new GroupLeaderModel();
			groupLeaderModel.setName(entity.getName());
			groupLeaderModel.setEmail(entity.getEmail());
			groupLeadersModel.add(groupLeaderModel);
		}

		return groupLeadersModel;
	}

	public Set<HikerModel> toHikerModel(Set<HikerEntity> entities) throws Exception {

		Set<HikerModel> hikersModel = new HashSet<HikerModel>();

		for (HikerEntity entity : entities) {
			HikerModel hikerModel = new HikerModel();
			hikerModel.setName(entity.getName());
			hikerModel.setEmail(entity.getEmail());
			hikersModel.add(hikerModel);
		}
		return hikersModel;
	}

}
