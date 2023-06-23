package io.github.realyusufismail.learningspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//organise using NT articulate or others
@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepo customerRepo;

    public Main(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepo.save(customer);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
        //potentially catch the exception and return a 404
        Customer customer = customerRepo.findById(id).orElseThrow();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepo.save(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        //check if the customer exists
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
        } else {
            throw new IllegalStateException("Customer with id " + id + " does not exist");
        }
    }

    /**
     * nextval automatically increments the id
     * @return the terminal code that is used to insert data into the database
     */
    private String terminalCode() {
        return """
                customer=# INSERT INTO customer(id, name, email, age)\s
                customer-# VALUES (nextval('customer_id_sequence'), 'yusuf', 'yusuf@gamil.com', 16)
                customer-# ;
                INSERT 0 1
                customer=# INSERT INTO customer(id, name, email, age)\s
                VALUES (nextval('customer_id_sequence'), 'bob', 'bob@gmail.com', 21);
                INSERT 0 1
                customer=#\s
                                
                """;
    }
}
