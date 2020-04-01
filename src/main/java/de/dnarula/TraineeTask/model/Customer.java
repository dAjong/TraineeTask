package de.dnarula.TraineeTask.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;


public class Customer
{
    private final UUID customernumber;
    @NotNull
    private String sName;
    @NotNull
    private Address aAddress;
    public Customer(@JsonProperty("id") UUID customernumber,
                    @JsonProperty("name") String name,
                    @JsonProperty("address") Address address)
    {
        this.customernumber = customernumber;
        this.sName = name;
        this.aAddress = address;
    }

    public UUID getCustomernumber()
    {
        return customernumber;
    }

    public String getsName()
    {
        return sName;
    }

    public void setsName(String sName)
    {
        this.sName = sName;
    }

    public Address getaAddress()
    {
        return aAddress;
    }

    public void setaAddress(Address aAddress)
    {
        this.aAddress = aAddress;
    }
}
