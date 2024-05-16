package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tech.csm.model.Customer;

public interface CustomerService {

	List<Customer> getALlCustomers();

}
