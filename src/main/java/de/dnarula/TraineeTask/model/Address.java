package de.dnarula.TraineeTask.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Address
{
    @NotNull
    private String sStreet;
    @NotNull
    private String sZip;
    @NotNull
    private String sHouseNumber;
    @NotNull
    private String sCity;

    public Address(@JsonProperty("street") String sStreet,
                   @JsonProperty("zip") String sZip,
                   @JsonProperty("housenumber") String sHouseNumber,
                   @JsonProperty("city") String sCity)
    {
        this.sStreet = sStreet;
        this.sZip = sZip;
        this.sHouseNumber = sHouseNumber;
        this.sCity = sCity;
    }

    public String getsStreet()
    {
        return sStreet;
    }

    public void setsStreet(String sStreet)
    {
        this.sStreet = sStreet;
    }

    public String getsZip()
    {
        return sZip;
    }

    public void setsZip(String sZip)
    {
        this.sZip = sZip;
    }

    public String getsHouseNumber()
    {
        return sHouseNumber;
    }

    public void setsHouseNumber(String sHouseNumber)
    {
        this.sHouseNumber = sHouseNumber;
    }

    public void setsCity(String sCity)
    {
        this.sCity = sCity;
    }

    public String getsCity()
    {
        return sCity;
    }
}
