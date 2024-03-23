// package com.customer360.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
// import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
// import org.springframework.security.access.vote.RoleHierarchyVoter;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import org.springframework.security.web.SecurityFilterChain;

// import com.customer360.models.Users;

// import static org.springframework.security.config.Customizer.withDefaults;
// import com.customer360.repository.UsersRepository;

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig {

// 	@Autowired
// 	private UsersRepository usersRepository;

// 	@Bean
// 	public RoleHierarchyVoter roleHierarchyVoter() {
// 		RoleHierarchy roleHierarchy = new RoleHierarchyImpl();
// 		return new RoleHierarchyVoter(roleHierarchy);
// 	}

// 	@Bean
// 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//                 .authorizeHttpRequests((requests) -> requests
//                                 .requestMatchers("/admin/**")
//                                 .hasRole("ADMIN")
//                                 .requestMatchers("/", "/getComplaintById", "/public/**", "/css/**")
//                                 .permitAll()
//                                 .anyRequest().authenticated()
//                                 .and()
//                 );


// 		return http.build();
// 	}

// 	@Bean
// 	public UserDetailsService userDetailsService() {
// 		return username -> {
// 			Users user = usersRepository.findByUsername(username);
// 			if (user != null) {
// 				String[] roles = user.getRole().split(",");
// 				UserDetails userDetails = User.builder()
// 						.username(user.getUsername())
// 						.password(passwordEncoder().encode(user.getPassword()))
// 						.roles(roles)
// 						.build();
// 				return userDetails;
// 			}
// 			throw new UsernameNotFoundException("User not found");
// 		};
// 	}

// 	@Bean
// 	public static BCryptPasswordEncoder passwordEncoder() {
// 		return new BCryptPasswordEncoder();
// 	}

// }











// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig {

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http
// .authorizeHttpRequests((requests) -> requests
// .requestMatchers("/", "/home").permitAll()
// .anyRequest().authenticated()
// )
// .formLogin();
// // .formLogin((form) -> form
// // .loginPage("/login")
// // .permitAll()
// // )
// // .logout((logout) -> logout.permitAll());

// return http.build();
// }

// @Bean
// public UserDetailsService userDetailsService( ) {
// UserDetails user =
// User.withDefaultPasswordEncoder()
// .username("user")
// .password("password")
// .roles("USER")
// .build();

// return new InMemoryUserDetailsManager(user);
// }
// }