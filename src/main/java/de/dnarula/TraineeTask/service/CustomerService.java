package de.dnarula.TraineeTask.service;

import de.dnarula.TraineeTask.dao.CustomerDao;
import de.dnarula.TraineeTask.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService
{
    private final CustomerDao customerDao;

    @Autowired
    public CustomerService(@Qualifier("inMemoryDao") CustomerDao customerDao)
    {
        this.customerDao = customerDao;
    }

    public int addCustomer(Customer customer)
    {
        return customerDao.insertCustomer(customer);
    }

    public List<Customer> getAllCustomer()
    {
        return customerDao.selectAllCustomer();
    }

    public Optional<Customer> getCustomerPersonById(UUID id)
    {
        return customerDao.selectCustomerById(id);
    }

    public int deleteCustomer(UUID id)
    {
       return customerDao.deleteCustomerById(id);
    }

    public int updateCustomer(UUID id, Customer updatedCustomer)
    {
        return customerDao.updateCustomerById(id,updatedCustomer);
    }

    public List<Customer> getAllCustomerFilteredByStreet(String street)
    {
        return customerDao.selectAllCustomerbyStreet(street);
    }
}
