// package com.customer360.repository.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.customer360.models.Users;
// import com.customer360.repository.UsersRepository;

// @Service
// public class UserService {
//     private final UsersRepository userRepository;
//     private final BCryptPasswordEncoder passwordEncoder;

//     @Autowired
//     public UserService(UsersRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     public void createUser(Users user) {
//         String encodedPassword = passwordEncoder.encode(user.getPassword());
//         user.setPassword(encodedPassword);
//         userRepository.save(user);
//     }
// }

