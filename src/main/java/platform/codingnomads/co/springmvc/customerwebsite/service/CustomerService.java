package platform.codingnomads.co.springmvc.customerwebsite.service;

import platform.codingnomads.co.springmvc.customerwebsite.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    Customer saveCustomer(Customer customer);

    Customer getCustomer(Long id);

    void deleteCustomer(Long id);

    List<Customer> saveAllCustomer(List<Customer> customerList);
}
