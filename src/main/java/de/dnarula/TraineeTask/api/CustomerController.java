package de.dnarula.TraineeTask.api;

import de.dnarula.TraineeTask.model.Customer;
import de.dnarula.TraineeTask.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customer")
@Api(value="Übungsaufgabe")
public class CustomerController
{
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @ApiOperation(value="Legt einen neuen Kunden an")
    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public void addCustomer(@Valid @NotNull @RequestBody Customer customer)
    {
        customerService.addCustomer(customer);
    }

    @ApiOperation(value="Gibt alle Kunden zurück",response = List.class)
    @RequestMapping(method = RequestMethod.GET,produces = "application/json")
    public List<Customer> getAllCustomer()
    {
        return customerService.getAllCustomer();
    }

    @ApiOperation(value="Gibt einen Kunden auf Basis der Kundennummer zurück", response = Customer.class)
    @RequestMapping(path="{customernumber}",method = RequestMethod.GET,produces = "application/json")
    public Customer getCustomerById(@PathVariable("customernumber") UUID customernumber)
    {
        return customerService.getCustomerPersonByCustomernumber(customernumber)
                .orElse(null);

    }

    @ApiOperation(value="Löscht einen Kunden")
    @RequestMapping(path="{customernumber}",method = RequestMethod.DELETE,consumes = "application/json", produces = "application/json")
    public void deleteCustomer(@PathVariable("customernumber") UUID customernumber)
    {
        customerService.deleteCustomer(customernumber);
    }

    @ApiOperation(value="Aktualisiert einen Kunden")
    @RequestMapping(path="{customernumber}",method = RequestMethod.PUT,consumes = "application/json",produces = "application/json")
    public void updateCustomer(@PathVariable("customernumber") UUID customernumber, @Valid @NotNull @RequestBody Customer updatedCustomer)
    {
        customerService.updateCustomer(customernumber,updatedCustomer);
    }

    @ApiOperation(value="Erhalte eine Liste von Kunden die diese Straße enthalten",response = List.class)
    @RequestMapping(path="street/{street}", method = RequestMethod.GET, produces = "application/json")
    public List<Customer> getAllCustomerFilteredByStreet(@PathVariable("street") String street)
    {
        return customerService.getAllCustomerFilteredByStreet(street);
    }
}
