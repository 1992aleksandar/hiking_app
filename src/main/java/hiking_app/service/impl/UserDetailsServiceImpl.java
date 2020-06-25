package hiking_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import hiking_app.entity.UserEntity;
import hiking_app.repository.UserRepository;
import hiking_app.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userRepository.getUserEntityByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find the user");
		}
		return new MyUserDetails(user);
	}
}
