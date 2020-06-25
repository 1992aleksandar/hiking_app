package hiking_app;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import hiking_app.entity.PrivilegeEntity;
import hiking_app.entity.RoleEntity;
import hiking_app.repository.PrivilegeRepository;
import hiking_app.repository.RoleRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	private boolean alreadySetup = false;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Transactional
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;

		PrivilegeEntity userPrivilege = createPrivilegeIfNotFound("USER_PRIVILEGE");
		PrivilegeEntity groupLeaderPrivilege = createPrivilegeIfNotFound("GROUP_LEADER_PRIVILEGE");
		List<PrivilegeEntity> adminPrivileges = Arrays.asList(userPrivilege, groupLeaderPrivilege);

		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_USER", Arrays.asList(userPrivilege));

		alreadySetup = true;
	}

	private PrivilegeEntity createPrivilegeIfNotFound(String name) {
		PrivilegeEntity privilege = privilegeRepository.findByName(name);

		if (privilege == null) {
			privilege = new PrivilegeEntity(name);
			privilegeRepository.save(privilege);
		}

		return privilege;
	}

	private RoleEntity createRoleIfNotFound(String name, Collection<PrivilegeEntity> privileges) {
		RoleEntity role = roleRepository.findByName(name);

		if (role == null) {
			role = new RoleEntity(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
