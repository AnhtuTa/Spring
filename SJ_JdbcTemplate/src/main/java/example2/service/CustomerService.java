package example2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example2.dao.CustomerDAO;
import example2.entities.Customer;

/*
 * File Service:
Thường thì controller sẽ không gọi trực tiếp đến DAO 
mà nó sẽ thông qua 1 tầng service
Tầng service sẽ thực hiện validate bussiness, kiểm tra, tổng hợp.
1 Method trong service có thể gọi tới nhiều method trong DAO 
còn ở tầng DAO người ta sẽ chia nhỏ các method thành 
từng đơn vị nhỏ nhất để sử dụng lại trong service.
 */

@Service
public class CustomerService {
	@Autowired
	CustomerDAO customerDAO;

	public CustomerService() {
		System.out.println("This is constructor of CustomerService.java");
	}

	public List<Customer> findAll() {
		return customerDAO.findAll();
	}

	public Customer findById(int id) {
		return customerDAO.findById(id);
	}

	public void save(Customer customer) {
		// validate business
		// .......
		
		// save to database:
		customerDAO.save(customer);
	}

	public void update(Customer customer) {
		// validate business
		// .......
		
		// update to database:
		customerDAO.update(customer);
	}

	public void delete(int id) {
		// validate business
		// .......
		
		// delete from database:
		customerDAO.delete(id);
	}
}
