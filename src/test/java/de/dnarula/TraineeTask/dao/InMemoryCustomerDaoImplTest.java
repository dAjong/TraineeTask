package de.dnarula.TraineeTask.dao;

import static org.assertj.core.api.Assertions.assertThat;

import de.dnarula.TraineeTask.model.Address;
import de.dnarula.TraineeTask.model.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class InMemoryCustomerDaoImplTest
{
    private static InMemoryCustomerDaoImpl testDB;

    @BeforeAll
    public static void setUp()
    {
        testDB = new InMemoryCustomerDaoImpl();
    }

    @Test
    public void canPerformCrud()
    {
        //Create three customers
        Address addressOne = new Address("Musterstraße","12345","123","Musterstadt");
        UUID idOne = UUID.randomUUID();
        Customer customerOne = new Customer(idOne,"Max Mustermann",addressOne);

        UUID idTwo = UUID.randomUUID();
        Customer customerTwo = new Customer(idTwo,"Maxine Musterfrau",addressOne);

        Address addressTwo = new Address("Hoserkirchweg","41747","129","Viersen");
        UUID idThree = UUID.randomUUID();
        Customer customerThree = new Customer(idThree,"Dominic Narula",addressTwo);

        //Testing insert customer function
        testDB.insertCustomer(idOne,customerOne);
        testDB.insertCustomer(idTwo,customerTwo);
        testDB.insertCustomer(idThree,customerThree);

        assertThat(testDB.selectCustomerById(idOne))
                .isPresent()
                .hasValueSatisfying(customerFromDb -> assertThat(customerFromDb)
                                                        .isEqualToComparingFieldByField(customerOne));

        assertThat(testDB.selectCustomerById(idTwo))
                .isPresent()
                .hasValueSatisfying(customerFromDb -> assertThat(customerFromDb)
                                                        .isEqualToComparingFieldByField(customerTwo));

        //Testing get all customer function
        List<Customer> customers = testDB.selectAllCustomer();

        //Checking if the inserted Customers are in
        assertThat(customers)
                .hasSize(3)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(customerOne,customerTwo,customerThree);

        //Testing filtered List

        List<Customer> filteredCustomers = testDB.selectAllCustomerbyStreet("Hoserkirchweg");

        assertThat(filteredCustomers)
                .hasSize(1)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(customerThree);

        //Update the first customer
        Customer customerUpdate = new Customer(idOne,"Maximum Musterdiverse",addressOne);

        assertThat(testDB.updateCustomerById(idOne,customerUpdate)).isEqualTo(1);

        assertThat(testDB.selectCustomerById(idOne))
                .isPresent()
                .hasValueSatisfying(customerFromDb -> assertThat(customerFromDb)
                                                        .isEqualToComparingFieldByField(customerUpdate));


        //Testing delete function
        assertThat(testDB.deleteCustomerById(idOne)).isEqualTo(1);

        //Checking if the customer with idOne is really deleted
        assertThat(testDB.selectCustomerById(idOne)).isEmpty();

        //At the end the DB should contain only one customer
        assertThat(testDB.selectAllCustomer())
                .hasSize(2)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(customerTwo,customerThree);

    }

    @Test
    public void willReturn0IfNoCustomerFoundToDelete()
    {
        UUID id = UUID.randomUUID();

        int deleteResult = testDB.deleteCustomerById(id);

        assertThat(deleteResult).isEqualTo(0);
    }

    @Test
    public void willReturn0IfNoCustomerFoundToUpdate()
    {
        UUID id = UUID.randomUUID();
        Address address = new Address("Test","Test","Test","Test");
        Customer customer = new Customer(id,"Test",address);

        int updateResult = testDB.updateCustomerById(id,customer);

        assertThat(updateResult).isEqualTo(0);
    }
}
