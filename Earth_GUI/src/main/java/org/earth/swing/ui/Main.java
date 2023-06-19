/*Main(GUI)
 *Version 1.0
 *4-04-2023
 *Copyright notice
 */

package org.earth.swing.ui;
import lombok.extern.java.Log;

/**
 * This is a class that represents Main for Earth_GUI
 * GUI provides users with options to perform various function on the Earth Resource Data stored in the database
 * The class provides methods for to run GUI for Earth Resource Database project
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 */

@Log
public class Main {
    public static void main(String[] args) {
      //calls instance of Home Class to run the application
     Home  h1 = new Home();
     h1.gui_func();
     log.info("User Started the Earth Resource Database Application using GUI");
    }
}