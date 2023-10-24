//package com.jwt.service;
//
//import org.springframework.stereotype.Service;
//
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//@Service
//public class OtpService {
//
//    public void sendOtp(String phoneNumber, String otp) {
//        // Use Twilio to send SMS with OTP
//        Message message = Message.creator(
//            new PhoneNumber(phoneNumber),
//            new PhoneNumber("(724) 398-1024"),
//            "Your OTP is: " + otp
//        ).create();
//    }
//}
