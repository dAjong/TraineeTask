package de.dnarula.TraineeTask.service;

import de.dnarula.TraineeTask.dao.CustomerDao;
import de.dnarula.TraineeTask.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Der Service für das Kunden DAO.
 * @author Dominic Narula
 * @version 1.0
 * @since 2020-04-01
 */
@Service
public class CustomerService
{
    private final CustomerDao customerDao;

    /**
     * Initialisierung des DAO's.
     * @param customerDao Enhält das DAO
     */
    @Autowired
    public CustomerService(@Qualifier("inMemoryDao") CustomerDao customerDao)
    {
        this.customerDao = customerDao;
    }

    /**
     * Erstellen eines Kunden
     * @param customer Enthält das Kunden Model
     * @return Integer (1=erfolgreich/0=fehler)
     */
    public int addCustomer(Customer customer)
    {
        return customerDao.insertCustomer(customer);
    }

    /**
     * Gibt eine Liste von allen Kunden zurück.
     * @return List<Customer>
     */
    public List<Customer> getAllCustomer()
    {
        return customerDao.selectAllCustomer();
    }

    /**
     * Gibt einen Kunden zurück auf Basis der Kundennummer.
     * @param customernumber Ist die eindeutige ID eines Kunden
     * @return Gibt einen Kunden zurück, falls keiner gefunden worden ist, wird ein leeres Objekt zurückgegeben
     */
    public Optional<Customer> getCustomerPersonByCustomernumber(UUID customernumber)
    {
        return customerDao.selectCustomerByCustomernumber(customernumber);
    }

    /**
     * Löscht einen Kunden
     * @param customernumber Ist die eindeutige ID eines Kunden
     * @return Integer (1=erfolgreich/0=fehler)
     */
    public int deleteCustomer(UUID customernumber)
    {
       return customerDao.deleteCustomerByCustomernumber(customernumber);
    }

    /**
     * Aktualisiert einen Kunden
     * @param customernumber Ist die eindeutige ID eines Kunden
     * @param updatedCustomer Enhält das neue Kunden Model
     * @return Integer (1=erfolgreich/0=fehler)
     */
    public int updateCustomer(UUID customernumber, Customer updatedCustomer)
    {
        return customerDao.updateCustomerByCustomernumber(customernumber,updatedCustomer);
    }

    /**
     * Gibt eine Liste von Kunden zurückt die die selbe Straße haben
     * @param street Enhölt den Straßennamen als String
     * @return Gibt eine Liste von Kunden zurück
     */
    public List<Customer> getAllCustomerFilteredByStreet(String street)
    {
        return customerDao.selectAllCustomerbyStreet(street);
    }
}
