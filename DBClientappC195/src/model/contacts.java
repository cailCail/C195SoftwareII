package model;
/** The class creates contacts. */
public class contacts {
    private int contact_ID;
    private String contact_name;
    private String Email;

    public contacts(int contact_ID, String contact_name, String email) {
        this.contact_ID = contact_ID;
        this.contact_name = contact_name;
        Email = email;
    }
    /** Get contact_Id.
     * @return contact_ID. */
    public int getContact_ID() {
        return contact_ID;
    }
    /** Get contact_name.
     * @return contact_name. */
    public String getContact_name() {
        return contact_name;
    }
    /** Get email.
     * @return Email. */
    public String getEmail() {
        return Email;
    }

    @Override
    public String toString(){
        return contact_ID+ " - " + contact_name ;
    }

}
