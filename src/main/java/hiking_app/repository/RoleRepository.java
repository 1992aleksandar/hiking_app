package hiking_app.repository;

import org.springframework.data.repository.CrudRepository;

import hiking_app.entity.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, String> {
	RoleEntity findByName(String name);
}
