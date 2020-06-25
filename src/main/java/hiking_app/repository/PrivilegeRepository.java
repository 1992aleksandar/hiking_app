package hiking_app.repository;

import org.springframework.data.repository.CrudRepository;

import hiking_app.entity.PrivilegeEntity;

public interface PrivilegeRepository extends CrudRepository<PrivilegeEntity, String> {
	PrivilegeEntity findByName(String name);
}
