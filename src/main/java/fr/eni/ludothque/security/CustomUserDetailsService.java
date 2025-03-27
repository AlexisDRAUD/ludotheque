package fr.eni.ludothque.security;

import fr.eni.ludothque.bo.User;
import fr.eni.ludothque.dal.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByUsername(username);

		if (userOptional.isEmpty()) {
			throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
		}

		User user = userOptional.get();

		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRoles().toArray(new String[0]))
				.build();
	}
}
