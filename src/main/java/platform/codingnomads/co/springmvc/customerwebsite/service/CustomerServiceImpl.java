package platform.codingnomads.co.springmvc.customerwebsite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import platform.codingnomads.co.springmvc.customerwebsite.domain.Customer;
import platform.codingnomads.co.springmvc.customerwebsite.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    final CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) throws IllegalArgumentException {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Customer> saveAllCustomer(List<Customer> customerList) {
        return customerRepository.saveAll(customerList);
    }
}
