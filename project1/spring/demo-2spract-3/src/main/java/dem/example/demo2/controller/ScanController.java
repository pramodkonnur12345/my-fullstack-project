package dem.example.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dem.example.demo2.dto.ScanRequest;
import dem.example.demo2.entity.Scan;
import dem.example.demo2.service.ScanService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employee2/scan")
public class ScanController {

    @Autowired
    private ScanService service;

    @PostMapping
    public Scan scan(@RequestBody ScanRequest req) {
        return service.scan(req);
    }
}