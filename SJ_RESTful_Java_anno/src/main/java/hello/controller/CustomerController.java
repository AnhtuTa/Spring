package hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hello.model.Customer;
import hello.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	public CustomerController() {
		System.out.println("This is constructor of CustomerController.java");
	}
	
	/* ---------------- GET ALL customers ------------------------ */
	@RequestMapping(value= {"/customers"}, method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<List<Customer>>(
				customerService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value= {"/customer/{id}"})
	public ResponseEntity<Object> getCustomerById(@PathVariable("id") int id) {
		Customer c = customerService.findById(id);
		System.out.println("c = " + c);
		if(c != null) return new ResponseEntity<Object> (c, HttpStatus.OK);
		else return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
	}
}
