package com.example.practice;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsurerController {
    
    @Autowired
    private InsurerService insurerService;

    @PostMapping("/getInsurer")
    public ResponseEntity<InsurerDTO> getInsurer(@RequestBody InsurerRequestDTO body) {
        
        InsurerDTO insurerResponse = this.insurerService.getInsurer(body).get();
        return ResponseEntity.ok(insurerResponse);
    }

}
