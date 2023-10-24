//package com.jwt.model;
//
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.jwt.repository.UserRepository;
//
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService otpService;
//
//    public void registerUser(User user) {
//        // Generate a random OTP (e.g., a 6-digit code)
//        String otp = String.format("%06d", new Random().nextInt(1000000));
//        user.setPassword(otp);
//        userRepository.save(user);
//
//        // Send the OTP to the user
//        otpService.sendOtp(user.getPhoneNumber(), otp);
//    }
//}
