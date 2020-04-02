package de.dnarula.TraineeTask.dao;

import de.dnarula.TraineeTask.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerDao
{
    int insertCustomer(UUID customernumber, Customer customer);

    default int insertCustomer(Customer customer)
    {
        UUID customernumber = UUID.randomUUID();
        return insertCustomer(customernumber, customer);
    }

    List<Customer> selectAllCustomer();

    Optional<Customer> selectCustomerByCustomernumber(UUID customernumber);

    int deleteCustomerByCustomernumber(UUID customernumber);

    int updateCustomerByCustomernumber(UUID customernumber, Customer newCustomer);

    List<Customer> selectAllCustomerbyStreet(String street);

}
