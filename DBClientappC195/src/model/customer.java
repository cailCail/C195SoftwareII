package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This creates the class of customer. */
public class customer {

    private int customer_ID;
    private String customer_name;
    private String address;
    private String postal_code;
    private String phone;
    private int division_ID;
    private int country_ID;

    public customer(int customer_ID, String customer_name, String address, String postal_code, String phone, int division_ID, int country_ID) {
        this.customer_ID = customer_ID;
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.division_ID = division_ID;
        this.country_ID = country_ID;
    }
    /** Get customer_ID.
     * @return customer_ID. */
    public int getCustomer_ID() {
        return customer_ID;
    }
    /** Get customer name.
     * @return customer name. */
    public String getCustomer_name() {
        return customer_name;
    }
    /** Get Address.
     * @return address.*/
    public String getAddress() {
        return address;
    }
    /** Get postal code.
     * @return postal code. */
    public String getPostal_code() {
        return postal_code;
    }
    /** Get Phone.
     * @return phone. */
    public String getPhone() {
        return phone;
    }
    /** Get division.
     * @return division. */
    public int getDivision_ID() {
        return division_ID;
    }
    /**Get country_ID.  */
    public int getCountry_ID() {
        return country_ID;
    }

    @Override
    public String toString(){
        return customer_ID+ " - " + customer_name ;
    }
}

