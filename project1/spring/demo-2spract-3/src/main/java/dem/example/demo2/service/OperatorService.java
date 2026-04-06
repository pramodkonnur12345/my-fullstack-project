package dem.example.demo2.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dem.example.demo2.entity.Operator;
import dem.example.demo2.repo.OperatorRepository;
import lombok.RequiredArgsConstructor;


	@Service
	public class OperatorService {

	    @Autowired
	    private OperatorRepository repo;

	    public Operator login(String opname) {
	    	
	    	
	    	
	        return repo.findByOpname(opname)
	                .orElseThrow(() -> new RuntimeException("Operator not found"));
	    }
	    
	    public String generateToken() {
	        return UUID.randomUUID().toString();
	    }
	    
	    public String generateTokenAndSendEmail(Long operatorId, EmailService emailService) {

	        Operator op = repo.findById(operatorId)
	                .orElseThrow(() -> new RuntimeException("Operator not found"));

	        String token = UUID.randomUUID().toString();

	        op.setLoginToken(token);
	        repo.save(op);

	        // 👇 send email
	        emailService.sendScannerLink(op.getEmail(), token);

	        return token;
	    }
	    
	    
	    
	    
	   
	    
	    
	}
