package hiking_app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hiking_app.entity.GroupLeaderEntity;

@Repository
public interface GroupLeaderRepository extends CrudRepository<GroupLeaderEntity, String> {
	public GroupLeaderEntity getGroupLeaderEntityByUserId(Long id);

	public GroupLeaderEntity getGroupLeaderEntityByEmail(String email);

	public GroupLeaderEntity getGroupLeaderEntityByName(String name);
}