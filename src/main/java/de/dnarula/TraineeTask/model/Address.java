package de.dnarula.TraineeTask.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Das Model für Adressen von Kunden
 * @author Dominic Narula
 * @version 1.0
 * @since 2020-04-01
 */
public class Address
{
    @NotNull
    private String street;
    @NotNull
    private String zip;
    @NotNull
    private String housenumber;
    @NotNull
    private String city;

    //Constructor
    public Address(@JsonProperty("street") String sStreet,
                   @JsonProperty("zip") String sZip,
                   @JsonProperty("housenumber") String sHouseNumber,
                   @JsonProperty("city") String sCity)
    {
        this.street = sStreet;
        this.zip = sZip;
        this.housenumber = sHouseNumber;
        this.city = sCity;
    }

    //Getter and Setter
    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getHousenumber()
    {
        return housenumber;
    }

    public void setHousenumber(String housenumber)
    {
        this.housenumber = housenumber;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
}
