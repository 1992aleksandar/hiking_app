package hiking_app.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import hiking_app.util.IDGenerator;

@Entity
@Table(name = "privileges")
public class PrivilegeEntity implements Serializable {

	private static final long serialVersionUID = -5678509386272175208L;
	private String name;

	@ManyToMany(mappedBy = "privileges")
	private Collection<RoleEntity> roles;

	@Id
	@GeneratedValue(generator = IDGenerator.generatorName)
	@GenericGenerator(name = IDGenerator.generatorName, strategy = "hiking_app.util.IDGenerator")
	private String privilegeId;

	public PrivilegeEntity() {
	}

	public void setPrivilege_id(String privilege_id) {
		this.privilegeId = privilege_id;
	}

	public PrivilegeEntity(String name) {
		super();
		this.name = name;
	}

	public PrivilegeEntity(String name, Collection<RoleEntity> roles) {
		super();
		this.name = name;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEntity> roles) {
		this.roles = roles;
	}
}
