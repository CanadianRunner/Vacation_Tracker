package com.example.demo.BootstrapData;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BootStrapData {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @PostConstruct
    public void loadInitialData() {

        if (customerRepository.count() == 1) {

            Customer user1 = new Customer();
            user1.setFirstName("Wayne");
            user1.setLastName("Gretzky");
            user1.setPostal_code("12345");
            user1.setAddress("99 Greatness Ave");
            user1.setPhone("(555)555-6789");
            user1.setDivision(divisionRepository.findAll().get(7));
            user1.setCreateDate(new Date());
            user1.setLastUpdate(new Date());
            customerRepository.save(user1);

            Customer user2 = new Customer();
            user2.setFirstName("Mario");
            user2.setLastName("Lemieux");
            user2.setPostal_code("12345");
            user2.setAddress("66 Super St");
            user2.setPhone("(555)555-5678");
            user2.setDivision(divisionRepository.findAll().get(7));
            user2.setCreateDate(new Date());
            user2.setLastUpdate(new Date());
            customerRepository.save(user2);

            Customer user3 = new Customer();
            user3.setFirstName("Elias");
            user3.setLastName("Pettersson");
            user3.setPostal_code("12345");
            user3.setAddress("40 Legend Ln");
            user3.setPhone("(604)555-9101");
            user3.setDivision(divisionRepository.findAll().get(5));
            user3.setCreateDate(new Date());
            user3.setLastUpdate(new Date());
            customerRepository.save(user3);

            Customer user4 = new Customer();
            user4.setFirstName("Quinn");
            user4.setLastName("Hughes");
            user4.setPostal_code("12345");
            user4.setPhone("(604)555-1121");
            user4.setAddress("43 Defense Dr");
            user4.setDivision(divisionRepository.findAll().get(2));
            user4.setCreateDate(new Date());
            user4.setLastUpdate(new Date());
            customerRepository.save(user4);

            Customer user5 = new Customer();
            user5.setFirstName("Thatcher");
            user5.setLastName("Demko");
            user5.setPostal_code("12345");
            user5.setAddress("35 Save St");
            user5.setPhone("(604)555-3141");
            user5.setDivision(divisionRepository.findAll().get(3));
            user5.setCreateDate(new Date());
            user5.setLastUpdate(new Date());
            customerRepository.save(user5);

            System.out.println("Starter customers added!");
        } else {
            System.out.println("Starter customers already exist!");
        }
    }
}