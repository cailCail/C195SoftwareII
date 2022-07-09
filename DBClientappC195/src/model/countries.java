package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
/** The class creates countries. */
public class countries {

    private int country_ID;
    private String country;


    public countries(int country_ID, String country) {
        this.country_ID = country_ID;
        this.country = country;
    }
    /** Get country_ID.
     * @return country.ID. */
    public int getCountry_ID() { return country_ID;
    }
    /** Set Country_ID.
     * @param country_ID */
    public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
    }
    /** Set Country.
     * @param country . */
    public void setCountry(String country) {
        this.country = country;
    }
    /** Get Country.
     * @return country. */
    public String getCountry() {
        return country;
    }

    @Override
    public String toString(){
        return country_ID + "-" + country;
    }

}
