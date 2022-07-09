package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
/**  This class creates users. */
public class users {

    private int user_ID;
    private String user_name;
    private String password;


    public users(int user_ID, String user_name, String password) {
        this.user_ID = user_ID;
        this.user_name = user_name;
        this.password = password;

    }
    /**Get user_ID.
     * @return user_ID.  */
    public int getUser_ID() { return user_ID; }
    /** Get user name.
     * @return user name. */
    public String getUser_name() {
        return user_name;
    }
    /** Get password.
     * @return password. */
    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return user_ID + " - " + user_name ;
    }
}
