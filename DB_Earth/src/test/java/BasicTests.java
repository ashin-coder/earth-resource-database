/*BasicTests
 *Version 1.0
 *4-04-2023
 *Copyright notice
 */
import org.earth.database.WorldDBUtils;
import org.earth.database.WorldQueriesFunc;
import org.junit.Test;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This is a class that represents Basic Tests for DB_Earth package
 * can be used when required to run methods which are used to test whether the required functionalities
 * are working in this package before they can be implemented as a feature in the application
 * they tests implemented can confirm using assertion test as well as using a tool called DB Browser for SQLite
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */

public class BasicTests {

    @Test
    public void continentTest() throws SQLException, IOException {
        WorldDBUtils db = new WorldDBUtils();
        db.insertContinent01("SA","SouthAmerica");
    }

    @Test
    public void countryTest() throws SQLException, IOException {
        WorldDBUtils db = new WorldDBUtils();
        db.insertCountry01("AR","Argentina","SA");
    }

    @Test
    public void stateTest() throws SQLException, IOException {
        WorldDBUtils db = new WorldDBUtils();
        db.insertState01("AP" , "Andhra Pradesh", "IN" ,100000);

    }

    @Test
    public void showTableTest() throws SQLException, IOException {
        WorldQueriesFunc queryInstance = new WorldQueriesFunc();
        queryInstance.show_tables();

    }

    @Test
    public void insertTestData() {
        WorldDBUtils dbUtils = new WorldDBUtils();
        dbUtils.init();
        dbUtils.insertContinent01("AS" , "Asia");
        dbUtils.insertCountry01("IN" , "India" , "AS");
        dbUtils.insertState01( "GJ" , "Gujarat" , "IN",12929222);
        dbUtils.insertAgriculturalResource01( "Apple" , 250 , "Fruits","GJ");
        dbUtils.insertMineralResource01( "Iron" , 1000000 , 12334444,"GJ");
    }


}
