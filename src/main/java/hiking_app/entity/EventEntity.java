package hiking_app.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hiking_app.util.IDGenerator;

@JsonIgnoreProperties({ "eventId", })
@Entity
@Table(name = "events")
public class EventEntity implements Serializable {

	private static final long serialVersionUID = 7748180921905223063L;

	@Id
	@GeneratedValue(generator = IDGenerator.generatorName)
	@GenericGenerator(name = IDGenerator.generatorName, strategy = "hiking_app.util.IDGenerator")
	private String eventId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String dateTime;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "events_group_leaders", joinColumns = @JoinColumn(name = "eventId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	Set<GroupLeaderEntity> group_leaders = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "events_hikers", joinColumns = @JoinColumn(name = "eventId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	private Set<HikerEntity> hikers = new HashSet<>();

	private String description;

	public EventEntity() {
	}

	public EventEntity(String name) {
		this.name = name;
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

	public Set<GroupLeaderEntity> getGroup_leaders() {
		return group_leaders;
	}

	public void setGroup_leaders(Set<GroupLeaderEntity> group_leaders) {
		this.group_leaders = group_leaders;
	}

	public Set<HikerEntity> getHikers() {
		return hikers;
	}

	public void setHikers(Set<HikerEntity> hikers) {
		this.hikers = hikers;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addGroupLeader(GroupLeaderEntity groupLeader) {
		this.getGroup_leaders().add(groupLeader);
		groupLeader.getEvents().add(this);
	}

	public void removeGroupLeader(GroupLeaderEntity groupLeader) {
		this.getGroup_leaders().remove(groupLeader);
		groupLeader.getEvents().remove(this);
	}

	public void addHiker(HikerEntity hiker) throws Exception {
		if ((this.getHikers().add(hiker)) && (hiker.getEvents().add(this)))
			return;
		else
			throw new Exception("Hiker failed to be added to the event");
	}

	public void removeHiker(HikerEntity hiker) {
		this.getHikers().remove(hiker);
		hiker.getEvents().remove(this);
	}

}
