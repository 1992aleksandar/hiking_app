package hiking_app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hiking_app.entity.EventEntity;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, String> {
	public EventEntity getEventEntityByName(String name);

	public EventEntity getEventEntityByEventId(String eventId);

	public Long deleteEventEntityByName(String name);
}