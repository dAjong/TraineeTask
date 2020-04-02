package de.dnarula.TraineeTask.dao;

import de.dnarula.TraineeTask.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("inMemoryDao")
public class InMemoryCustomerDaoImpl implements CustomerDao
{
    private static List<Customer> inMemoryDB = new ArrayList<>();


    @Override
    public int insertCustomer(UUID id, Customer customer)
    {
        inMemoryDB.add(new Customer(id,customer.getsName(),customer.getaAddress()));
        return 1;
    }

    @Override
    public List<Customer> selectAllCustomer() {
        return inMemoryDB;
    }

    @Override
    public int deleteCustomerById(UUID id) {
        Optional<Customer> customerMaybe = selectCustomerById(id);
        if(customerMaybe.isEmpty())
            return 0;
        inMemoryDB.remove(customerMaybe.get());
        return 1;
    }

    @Override
    public int updateCustomerById(UUID id, Customer newCustomer) {
        return selectCustomerById(id)
                .map(customer ->  {
                    int indexOfCustomerToUpdate = inMemoryDB.indexOf(customer);
                    if(indexOfCustomerToUpdate >= 0)
                    {
                        inMemoryDB.set(indexOfCustomerToUpdate,new Customer(id, newCustomer.getsName(),newCustomer.getaAddress()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public Optional<Customer> selectCustomerById(UUID id) {
        return inMemoryDB.stream()
                .filter(customer -> customer.getCustomernumber().equals(id))
                .findFirst();
    }

    @Override
    public List<Customer> selectAllCustomerbyStreet(String street) {
        List<Customer> filteredCustomerByStreet = new ArrayList<>();
        inMemoryDB.forEach(customer ->
                    {
                        if(customer.getaAddress().getsStreet().contains(street))
                        {
                            filteredCustomerByStreet.add(customer);
                        }
                    });


        return filteredCustomerByStreet;
    }
}
