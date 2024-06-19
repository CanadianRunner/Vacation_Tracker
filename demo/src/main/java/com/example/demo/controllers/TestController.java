//package com.example.demo.controllers;
//
//import com.example.demo.dao.CustomerRepository;
//import com.example.demo.entities.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/test")
//@CrossOrigin(origins = "http://localhost:4200")
//public class TestController {
//
//    private final CustomerRepository customerRepository;
//
//    @Autowired
//    public TestController(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//    @GetMapping("/customers")
//    public ResponseEntity<?> getCustomers() {
//        try {
//            Iterable<Customer> customers = customerRepository.findAll();
//            return new ResponseEntity<>(customers, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Inline error response
//            return new ResponseEntity<>(new ErrorResponse("Failed to fetch customers: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Inline ErrorResponse class
//    private static class ErrorResponse {
//        private String message;
//
//        public ErrorResponse(String message) {
//            this.message = message;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//    }
//}
