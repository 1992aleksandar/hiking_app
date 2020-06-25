package hiking_app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hiking_app.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
	public UserEntity getUserEntityByUserId(String userId);

	public UserEntity getUserEntityByEmail(String email);

	public String deleteUserEntityByUserId(String userId);
}
