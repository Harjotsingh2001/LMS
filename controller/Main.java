import java.sql.Date;
import java.text.DateFormat;

import DAO.LibrarySQL;

public class Main {
    public static void main(String[] args) {
      new MainController(new LibraryManagementSystem(), new LibrarySQL());
    }
   }
    
