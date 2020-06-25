package hiking_app.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "password", "enabled", "roles", "events", "userId", })
@Entity
public class GroupLeaderEntity extends UserEntity {

	private static final long serialVersionUID = -7303127633277060850L;

	@ManyToMany(mappedBy = "group_leaders", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	Set<EventEntity> events = new HashSet<EventEntity>();

	public GroupLeaderEntity() {
		super();
	}

	public GroupLeaderEntity(String email, String password, Set<RoleEntity> roles, String name) {
		super(email, password, roles, name);
	}

	public Set<EventEntity> getEvents() {
		return events;
	}

	public void setEvents(Set<EventEntity> events) {
		this.events = events;
	}
}
