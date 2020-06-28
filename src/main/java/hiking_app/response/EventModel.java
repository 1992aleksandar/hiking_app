package hiking_app.response;

import java.util.HashSet;
import java.util.Set;
import org.springframework.hateoas.RepresentationModel;

public class EventModel extends RepresentationModel<EventModel> {

	private String name;
	private String dateTime;
	private String description;
	private Set<GroupLeaderModel> group_leaders = new HashSet<>();
	private Set<HikerModel> hikers;

	@Override
	public String toString() {
		return "EventModel [name=" + name + "]";
	}

	public EventModel() {
		super();
	}

	public Set<HikerModel> getHikers() {
		return hikers;
	}

	public void setHikers(Set<HikerModel> hikers) {
		this.hikers = hikers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<GroupLeaderModel> getGroup_leaders() {
		return group_leaders;
	}

	public void setGroup_leaders(Set<GroupLeaderModel> group_leaders) {
		this.group_leaders = group_leaders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventModel other = (EventModel) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
