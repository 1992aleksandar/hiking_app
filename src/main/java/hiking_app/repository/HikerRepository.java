package hiking_app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hiking_app.entity.HikerEntity;

@Repository
public interface HikerRepository extends CrudRepository<HikerEntity, String> {
	public HikerEntity getHikerEntityByEmail(String email);

	public HikerEntity getHikerEntityByName(String name);

	public HikerEntity getHikerEntityByUserId(String id);
}