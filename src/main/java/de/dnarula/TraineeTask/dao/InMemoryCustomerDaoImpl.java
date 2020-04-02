package de.dnarula.TraineeTask.dao;

import de.dnarula.TraineeTask.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 *Ist die Implementierung für eine In Memory(cache) Datenbank.
 * Alle Datensätze werden beim Neustart/Herunterfahren gelöscht!
 *
 * @author Dominic Narula
 * @version  1.0
 * @since 2020-04-01
 *
 */
@Repository("inMemoryDao")
public class InMemoryCustomerDaoImpl implements CustomerDao
{
    private static List<Customer> inMemoryDB = new ArrayList<>();

    /**
     * Erstellt einen Kunden.
     * @param customernumber Ist die eindeutige ID eines Kunden
     * @param customer Enhält das Kunden Model
     * @return Integer (1=erfolgreich,0=fehler)
     */
    @Override
    public int insertCustomer(UUID customernumber, Customer customer)
    {
        inMemoryDB.add(new Customer(customernumber,customer.getName(),customer.getAddress()));
        return 1;
    }

    /**
     * Übergibt alle Kunden als List zurück.
     * @return Gibt eine List<Customer> zurück.
     */
    @Override
    public List<Customer> selectAllCustomer() {
        return inMemoryDB;
    }

    /**
     * Löscht einen Kunden auf Basis der Kundennummer
     * @param customernumber Ist die eindeutige ID eines Kunden
     * @return Integer (1=erfolgreich,0=fehler)
     */
    @Override
    public int deleteCustomerByCustomernumber(UUID customernumber) {
        Optional<Customer> customerMaybe = selectCustomerByCustomernumber(customernumber);
        if(customerMaybe.isEmpty())
            return 0;
        inMemoryDB.remove(customerMaybe.get());
        return 1;
    }

    /**
     * Aktualisiert einen existierenden Kunden
     * @param customernumber Ist die eindeutige ID eines Kunden
     * @param newCustomer Ist das neue Kunden Model was mit dem alten ausgetauscht wird.
     * @return Integer (1=erfolgreich,0=fehler)
     */
    @Override
    public int updateCustomerByCustomernumber(UUID customernumber, Customer newCustomer) {
        return selectCustomerByCustomernumber(customernumber)
                .map(customer ->  {
                    int indexOfCustomerToUpdate = inMemoryDB.indexOf(customer);
                    if(indexOfCustomerToUpdate >= 0)
                    {
                        inMemoryDB.set(indexOfCustomerToUpdate,new Customer(customernumber, newCustomer.getName(),newCustomer.getAddress()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    /**
     * Gibt einen Kunden zurück auf Basis der Kundennummer.
     * @param customernumber Ist die eindeutige ID eines Kunden
     * @return Das Kunden Model falls vorhanden, ansonsten ein leeres Objekt.
     */
    @Override
    public Optional<Customer> selectCustomerByCustomernumber(UUID customernumber) {
        return inMemoryDB.stream()
                .filter(customer -> customer.getCustomernumber().equals(customernumber))
                .findFirst();
    }

    /**
     * Gibt eine Liste von Kunden zurück, die die selbe Straße im Adressen Model beeinhalten
     * @param street Ist ein String der den Straßennamen beinhaltet
     * @return Gibt eine Liste von Kunden zurück.
     */
    @Override
    public List<Customer> selectAllCustomerbyStreet(String street) {
        List<Customer> filteredCustomerByStreet = new ArrayList<>();
        inMemoryDB.forEach(customer ->
                    {
                        if(customer.getAddress().getStreet().contains(street))
                        {
                            filteredCustomerByStreet.add(customer);
                        }
                    });


        return filteredCustomerByStreet;
    }
}
