package fr.eni.ludothque.security;

import fr.eni.ludothque.bo.User;
import fr.eni.ludothque.dal.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.Optional;

@Configuration
public class SecurityConfig {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;

	public SecurityConfig(JwtUtil jwtUtil, UserRepository userRepository) {
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("api/admin/**").hasRole("ADMIN")
						.requestMatchers("api/clients/**", "api/exemplaires/**", "api/genres/**","api/jeux/add", "api/locations/**").hasRole("EMPLOYEE")
						.requestMatchers("api/jeux/*").authenticated()
						.requestMatchers("api/auth/**").permitAll()
						.anyRequest().authenticated()
				)
				.addFilterBefore(new fr.eni.ludothque.security.JwtFilter(jwtUtil, userDetailsService()),
						UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			Optional<User> userOpt = userRepository.findByUsername(username);
			if (userOpt.isEmpty()) {
				throw new UsernameNotFoundException("User not found");
			}
			User user = userOpt.get();
			return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
					.password(user.getPassword())
					.roles(user.getRoles().toArray(new String[0]))
					.build();
		};
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return new ProviderManager(List.of(authProvider));
	}
}
