package de.dnarula.TraineeTask.api;

import de.dnarula.TraineeTask.model.Customer;
import de.dnarula.TraineeTask.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController
{
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public void addCustomer(@Valid @NotNull @RequestBody Customer customer)
    {
        customerService.addCustomer(customer);
    }

    @RequestMapping(method = RequestMethod.GET,produces = "application/json")
    public List<Customer> getAllCustomer()
    {
        return customerService.getAllCustomer();
    }

    @RequestMapping(path="{id}",method = RequestMethod.GET,produces = "application/json")
    public Customer getCustomerById(@PathVariable("id") UUID id)
    {
        return customerService.getCustomerPersonById(id)
                .orElse(null);

    }

    @RequestMapping(path="{id}",method = RequestMethod.DELETE,consumes = "application/json", produces = "application/json")
    public void deleteCustomer(@PathVariable("id") UUID id)
    {
        customerService.deleteCustomer(id);
    }

    @RequestMapping(path="{id}",method = RequestMethod.PUT,consumes = "application/json",produces = "application/json")
    public void updateCustomer(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Customer updatedCustomer)
    {
        customerService.updateCustomer(id,updatedCustomer);
    }

    @RequestMapping(path="street/{street}", method = RequestMethod.GET, produces = "application/json")
    public List<Customer> getAllCustomerFilteredByStreet(@PathVariable("street") String street)
    {
        return customerService.getAllCustomerFilteredByStreet(street);
    }
}
