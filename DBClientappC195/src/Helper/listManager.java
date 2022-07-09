package Helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This class manages the hard coded combo box lists.  */
public class listManager {
   public static ObservableList<String> meetingType = FXCollections.observableArrayList("De-Briefing", "Planning Session");
    public static ObservableList <String> locOptions = FXCollections.observableArrayList("location 1", "location 2", "location 3");
    public static ObservableList<String> monthName = FXCollections.observableArrayList("January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December");
}
