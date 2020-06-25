package hiking_app.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import hiking_app.util.IDGenerator;

@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 883238860509357834L;

	@Id
	@GeneratedValue(generator = IDGenerator.generatorName)
	@GenericGenerator(name = IDGenerator.generatorName, strategy = "hiking_app.util.IDGenerator")
	private String roleId;

	private String name;

	@ManyToMany(mappedBy = "roles")
	Collection<UserEntity> users;

	@ManyToMany
	@JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId"), inverseJoinColumns = @JoinColumn(name = "privilegeId", referencedColumnName = "privilegeId"))
	private Collection<PrivilegeEntity> privileges;

	public RoleEntity() {
	}

	public RoleEntity(String name) {
		super();
		this.name = name;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String role_id) {
		this.roleId = role_id;
	}

	public Collection<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserEntity> users) {
		this.users = users;
	}

	public Collection<PrivilegeEntity> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<PrivilegeEntity> privileges) {
		this.privileges = privileges;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
