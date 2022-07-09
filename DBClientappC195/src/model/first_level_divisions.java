package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
/**  This class creates the first_level_divisions.  */
public class first_level_divisions {

    private int division_ID;
    private String division;
    private int country_ID;

    public first_level_divisions(int division_ID, String division, int country_ID) {
        this.division_ID = division_ID;
        this.division = division;
        this.country_ID = country_ID;
    }
    /**Get division_ID
     * @return division_ID */
    public int getDivision_ID() {
        return division_ID;
    }
    /** Get division.
     * @return division*/
    public String getDivision() {
        return division;
    }
    /** Get country_ID.
     * @return country_ID. */
    public int getCountry_ID() {
        return country_ID;
    }

    @Override
    public String toString(){
        return division_ID + "-" + division;
    }

}
