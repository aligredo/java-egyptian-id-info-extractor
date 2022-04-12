package com.example.javaegypitanidinfoextractor.restservice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EgyptianIDInfoController {

	@GetMapping("/api/extract-info-from-id/")
	public ResponseEntity<?> extractInfoFromID(@RequestParam(value = "egyptianIDNumber", defaultValue = "100") String egyptianIDNumber) {
		
        EgyptianIDInfo egyptianIDInfo = new EgyptianIDInfo(egyptianIDNumber);
        if(egyptianIDInfo.isValidEgyptianIDNumber()){
            return ResponseEntity.ok(egyptianIDInfo.getInfo());
        }
        else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid Egyptian ID Number");
        }
	}
}
