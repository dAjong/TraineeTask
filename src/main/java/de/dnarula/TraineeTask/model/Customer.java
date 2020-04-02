package de.dnarula.TraineeTask.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Das Model für Kunden
 * @author Dominic Narula
 * @version 1.0
 * @since 2020-04-01
 */
public class Customer
{
    @ApiModelProperty(notes="eindeutige Kundennummer",position = 1)
    private final UUID customernumber;
    @ApiModelProperty(notes="Kundennamen", position = 2)
    @NotNull
    private String name;
    @ApiModelProperty(notes="Adressenmodel vom Kunden", position = 3)
    @NotNull
    private Address address;

    //Constructor
    public Customer(@JsonProperty("id") UUID customernumber,
                    @JsonProperty("name") String name,
                    @JsonProperty("address") Address address)
    {
        this.customernumber = customernumber;
        this.name = name;
        this.address = address;
    }

    //Getter and Setters for the variables in the class customer
    public UUID getCustomernumber()
    {
        return customernumber;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }
}
