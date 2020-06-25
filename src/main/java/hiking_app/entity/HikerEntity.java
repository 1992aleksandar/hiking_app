package hiking_app.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "password", "enabled", "roles", "events", "userId", })
@Entity
public class HikerEntity extends UserEntity {

	private static final long serialVersionUID = 6511200457025744593L;

	@ManyToMany(mappedBy = "hikers", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	Set<EventEntity> events;

	public HikerEntity() {
		super();
	}

	public HikerEntity(String email, String password, Set<RoleEntity> roles, String name) {
		super(email, password, roles, name);
	}

	public Set<EventEntity> getEvents() {
		return events;
	}

	public void setEvents(Set<EventEntity> events) {
		this.events = events;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		return result;
	}
}
