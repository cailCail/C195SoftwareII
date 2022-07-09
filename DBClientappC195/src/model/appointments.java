package model;

import java.time.LocalDateTime;
/** This class sets the appointments. */
public class appointments {


    private int appointment_ID;
    private String title;
    private String description;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customer_ID;
    private int user_ID;
    private int contact_ID;
    private String location;

    public appointments(int appointID, String appointTitle, String appointDesc, int appointContact, String appointType, LocalDateTime appointStart, LocalDateTime appointEnd, int appointUID, int appointCID, String location) {

    this.appointment_ID = appointID;
    this.title = appointTitle;
    this.description = appointDesc;
    this.type = appointType;
    this.start = appointStart;
    this.end = appointEnd;
    this.customer_ID = appointCID;
    this.user_ID = appointUID;
    this.contact_ID = appointContact;
    this.location = location;
    }
    /** Get appointment_Id
     * @return appointment_ID*/
    public int getAppointment_ID() {
        return appointment_ID;
    }
    /** Get title.
     * @return title
     */
    public String getTitle() {
        return title;
    }
    /** Get description.
     * @return desctiption*/
    public String getDescription() {
        return description;
    }
    /**Get type.
     * @return type. */
    public String getType() {
        return type;
    }
    /** Get start.
     * @return start. */
    public LocalDateTime getStart() {
        return start;
    }
    /** Get end.
     * @return end. */
    public LocalDateTime getEnd() {
        return end;
    }
    /** Get customer_ID.
     * @return customer_ID. */
    public int getCustomer_ID() {
        return customer_ID;
    }
    /** Get user_ID.
     * @return user_ID. */
    public int getUser_ID() { return user_ID; }
    /** Get contact_ID.
     * @return contact_ID. */
    public int getContact_ID() {
        return contact_ID;
    }
    /** Get location.
     * @return Location. */
    public String getLocation(){
        return location;
    }

    @Override
    public String toString(){ return start.toString() + " - " ; }



}
