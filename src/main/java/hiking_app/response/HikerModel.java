package hiking_app.response;

import java.util.HashSet;
import java.util.Set;

public class HikerModel extends UserModel {

	Set<EventModel> events = new HashSet<>();

	public HikerModel() {
		super();
	}

	public Set<EventModel> getEvents() {
		return events;
	}

	public void setEvents(Set<EventModel> events) {
		this.events = events;
	}
}
