package org.maveric.quarkus.jpa;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerRepositoryTest {

 @Inject CustomerRepository customerRepository;

 @Test
 @TestTransaction
 public void canCreateAndFindACustomer()
 {

     Customer customer= new Customer("name",
             "last name","email");
     customerRepository.persist(customer);
     assertNotNull(customer.getId());

     customer=customerRepository.findById(customer.getId());
     assertEquals("last name",customer.getLastName());

 }
}