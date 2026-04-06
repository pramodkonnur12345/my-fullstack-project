package dem.example.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendScannerLink(String toEmail, String token) {

//        String link = "http://localhost:4200/scanner?token=" + token;

    	String link = "https://subalgebraic-diffident-tarsha.ngrok-free.dev/scanner?token=" + token;
    	
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pramodkonnur12345@gmail.com");
//        message.setTo(toEmail); // 👈 operator email from DB
        message.setTo("pramodreddykonnur98@gmail.com");
        message.setSubject("Scanner Access Link");
        message.setText("Click below link to scan:\n" + link);

//        mailSender.send(message);
        
        try {
            mailSender.send(message);
            System.out.println("✅ Email sent successfully to " + toEmail);
        } catch (Exception e) {
            System.out.println("❌ Email sending failed");
            e.printStackTrace();
        }
        
    }
}