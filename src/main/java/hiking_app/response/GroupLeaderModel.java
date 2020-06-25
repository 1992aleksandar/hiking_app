package hiking_app.response;

import java.util.HashSet;
import java.util.Set;

public class GroupLeaderModel extends UserModel {

	Set<EventModel> events = new HashSet<>();

	public GroupLeaderModel() {
		super();
	}

	public Set<EventModel> getEvents() {
		return events;
	}

	public void setEvents(Set<EventModel> events) {
		this.events = events;
	}

	public GroupLeaderModel(Set<EventModel> events) {
		super();
		this.events = events;
	}
}
