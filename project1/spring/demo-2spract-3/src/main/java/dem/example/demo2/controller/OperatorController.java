package dem.example.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dem.example.demo2.dto.LoginRequest;
import dem.example.demo2.entity.Operator;
import dem.example.demo2.service.EmailService;
import dem.example.demo2.service.OperatorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employee2/operators")
public class OperatorController {

    @Autowired
    private OperatorService service;
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-link/{id}")
    public String sendLink(@PathVariable Long id) {
        service.generateTokenAndSendEmail(id, emailService);
        return "Email sent successfully";
    }

    @PostMapping("/login")
    public Operator login(@RequestBody LoginRequest req) {
        return service.login(req.opname);
    }
}