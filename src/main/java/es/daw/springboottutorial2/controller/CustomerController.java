package es.daw.springboottutorial2.controller;

import es.daw.springboottutorial2.dto.CustomerDTO;
import es.daw.springboottutorial2.dto.ErrorDTO;
import es.daw.springboottutorial2.entity.Customer;
import es.daw.springboottutorial2.repository.CustomerRepository;
import es.daw.springboottutorial2.service.CustomerMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapperService customerMapperService;
    @PostMapping("/add")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {


        customerRepository.save(customerMapperService.toEntity(customerDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);

    }

    @GetMapping("/list")
    //public Iterable<Customer> getCustomers() {
    public ResponseEntity<List<CustomerDTO>> getCustomers() {

    List<Customer> customers = customerRepository.findAll();


        return ResponseEntity.ok(customerMapperService.toDTO(customers)); //200 y en el body un json que es un array de objetos dto
    }

    @GetMapping("/find/{id}")
    //public Customer findCustomerById(@PathVariable Integer id) {
    //public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable Integer id) {
    public ResponseEntity<?> findCustomerById(@PathVariable Integer id) {

        //return customerRepository.findCustomerById(id);
        //return customerRepository.findById(id).get();
        //return customerRepository.findCustomersById(id);


        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {

            return  ResponseEntity.ok(customerMapperService.toDTO(customer.get()));
        }
        // Si pasa por aquí es que no lo ha encontrado!!!
        //return ResponseEntity.notFound().build();
        // 404
        //return ResponseEntity.ok(null);
        ErrorDTO errorDTO = new ErrorDTO("Que no existe torpedo!","666");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);


        //return ResponseEntity.notFound().build();

        //return ResponseEntity.badRequest().body(errorDTO);

        //return ResponseEntity.notFound(). //?????




    }
}

