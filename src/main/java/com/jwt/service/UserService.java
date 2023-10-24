package com.jwt.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.model.User;
import com.jwt.repository.UserRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository repository;

	// The phone number you want to use as the Caller ID
    String callerId = "your_purchased_phone_number"; // e.g., +1234567890

    // The recipient's phone number
    String toPhoneNumber = "recipient_phone_number"; // e.g., +9876543210

	
	public void sendOtp(String phoneNumber, String otp) {
        // Use Twilio to send SMS with OTP
        Message message = Message.creator(
//            new PhoneNumber(phoneNumber),
//            new PhoneNumber("(724) 398-1024"),
        		new PhoneNumber(toPhoneNumber),
                new PhoneNumber(callerId), // Set the Caller ID here
                
            "Your OTP is: " + otp
        ).create();
    }

//    public void registerUser(User user) {
//        // Generate a random OTP (e.g., a 6-digit code)
//        String otp = String.format("%06d", new Random().nextInt(1000000));
//        user.setPassword(otp);
//        repository.save(user);
//
//        // Send the OTP to the user
//        sendOtp(user.getPhoneNumber(), otp);
//    }
//    
	
	
	
	
	
	
	public List<User> getUsers() {
		return repository.findAll();
	}

	public User registerUser(User user) {
		// Check if the mobile number is already present
//		User existingUser = repository.findByMobileNo(user.getMobileNo());
		User existingUser = repository.findByPhoneNumber(user.getPhoneNumber());

		if (existingUser != null) {
			// Mobile number already exists, remove the password and regenerate a new random
			// number
			Random random = new Random();
			int newOtpValue = 100_000 + random.nextInt(900_000);
			String newOtp = String.valueOf(newOtpValue);
			System.out.println("--------\n" + newOtpValue + "\n----------");

			sendOtp(user.getPhoneNumber(), newOtp);
			
			// Update the existing user with a new random OTP as the password
			existingUser.setPassword(passwordEncoder.encode(newOtp));
			
			return repository.save(existingUser);
		}

		// Mobile number doesn't exist, proceed to generate a random OTP
//		user.setUserId(UUID.randomUUID().toString());

		// Generate a random OTP as the password
		Random random = new Random();
		int otpValue = 100_000 + random.nextInt(900_000);
		String otp = String.valueOf(otpValue);
		System.out.println("--------\n" + otpValue + "\n----------");

		// Save the OTP to the user entity and update it in the database
		user.setPassword(passwordEncoder.encode(otp));
		
		
		
		// Send the OTP to the user
        sendOtp(user.getPhoneNumber(), otp);
		
		return repository.save(user);
	}
	
	
}
