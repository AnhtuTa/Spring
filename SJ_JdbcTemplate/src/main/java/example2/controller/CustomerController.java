package example2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import example2.entities.Customer;
import example2.service.CustomerService;

/*
 * Ngay khi ứng dụng web đc deploy thì 1 instance (object) của class này sẽ đc khởi tạo
 * Do có @Autowired nên thuộc tính customerService cũng sẽ đc khởi tạo
 * Và trong class CustomerService cũng có @Autowired nên thuộc tính
 * customerDAO cũng sẽ đc khởi tạo
 */

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	public CustomerController() {
		System.out.println("This is constructor of CustomerController.java");
	}
	
	@RequestMapping(value= {"/", "customer-list"})
	public String customerList(Model model) {
		//customerService = new CustomerService();
		model.addAttribute("customerList", customerService.findAll());
		return "customer_list";
	}
	
	@RequestMapping(value="/add-customer", method = RequestMethod.GET)
	public String addCustomer() {
		return "add_customer";
	}
	
	@RequestMapping(value="/add-customer", method = RequestMethod.POST)
	public String doAddCustomer(@RequestParam("name") String name,
			@RequestParam("address") String address) {
		customerService.save(new Customer(name, address));
		return "redirect:customer-list";
	}
	
	@RequestMapping(value="/update-customer/{id}/{name}/{address}", method = RequestMethod.GET)
	public String updateCustomer(@PathVariable("id") int id,
			@PathVariable("name") String name,
			@PathVariable("address") String address, Model model) {
		model.addAttribute("customerId", id);
		model.addAttribute("customerName", name);
		model.addAttribute("customerAddress", address);
		
		return "update_customer";
	}
	
	@RequestMapping(value="/update-customer/{id}", method = RequestMethod.POST)
	public String doUpdateCustomer(@PathVariable("id") int id,
			@RequestParam("name") String name,
			@RequestParam("address") String address, Model model) {
		customerService.update(new Customer(id, name, address));
		//model.addAttribute("infoString", "Update success!");
		return "redirect:../customer-list";
	}
	
}
