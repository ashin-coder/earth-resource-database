/*WorldDBUtilsTests
 *Version 1.0
 *4-04-2023
 *Copyright notice
 */
import lombok.extern.java.Log;
import org.earth.database.WorldDBUtils;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * This is a class that represents Basic Tests for DB_Earth package it is similar to BasicTests class
 * can be used when required to run methods which are used to test whether the required functionalities
 * are working in this package before they can be implemented as a feature in the application
 * they tests implemented can confirm using assertion test as well as using a tool called DB Browser for SQLite
 * here I have done few assertion test to confirm data is correctly entered in the database
 * as well-used DB Browser for SQLite for faster testing SQLite Database Table and viewing all the data
 * test cases can be expanded as required
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */


@Log
public class WorldDBUtilsTests {
    WorldDBUtils db_Utils;
    @Before
    public void setup(){
        db_Utils = new WorldDBUtils();
        db_Utils.init();
    }

    @Test
    public void insert_continent_test(){
        List<String> selects = db_Utils.select_continents();
//      print_jsonArray(selects.get(1));
//      Below assert check whether the second column in first row is "Antarctica" on the Continents Tables
//      Note : columns index starts with 0
//      All similarly others test below are performed
        Assert.assertEquals(true,selects.get(1).contains("Antarctica"));
    }

    @Test
    public void insert_country_test(){
//      new data like below can be entered and tested
//      db_Utils.insertCountry01("ML","Malayasia","AS");
        List<String> selects = db_Utils.select_countries();
        print_jsonArray(selects.get(1));
        Assert.assertEquals(true,selects.get(1).contains("Algeria"));
        log.info(selects.get(1));

    }

    @Test
    public void insert_states_test(){
//      db_Utils.insertState01("MP","Madhya Pradesh","IN",302120);
        List<String> selects = db_Utils.select_states();
        log.info(selects.get(1));
        Assert.assertEquals(true,selects.get(1).contains("Adrar"));
    }

    @Test
    public void insert_agriculture_test(){
//        db_Utils.insertAgriculturalResource01("Wheat",250,"Grains","MP");
          List<String> selects = db_Utils.select_agriculture_resource();
          Assert.assertEquals(true,selects.get(1).contains("Wheat"));
    }

    @Test
    public void insert_mineral_test(){
//      db_Utils.insertMineralResource01("Iron",1000,100000,"MP");
        List<String> selects = db_Utils.select_mineral_resource();
        Assert.assertEquals(true,selects.get(1).contains("Coal"));
    }

    private void print_jsonArray(String result){
        JSONArray rows = new JSONArray(result);
        int row_size = rows.length();
        for (Object row : rows) {
            JSONArray each_row = (JSONArray) row;
            for (Object value: each_row){
                String col_value = (String) value;
                log.info("column value ="+ col_value);
            }
        }
    }


    @Test
    public void sum_count_test(){
        List<String>   sum = db_Utils.select_population("India");
        log.info("sum of population based on no of states entered : "+sum.get(1));

        List<String>   agri_count = db_Utils.count_agri_resource("Wheat","India");
        log.info("count of states with a given agriculture resource in a country : "+agri_count.get(1));

        List<String>  mineral_count = db_Utils.count_mineral_resource("Lithium","India");
        log.info("count of states with a given mineral resource in a country : "+mineral_count.get(1));
    }

@Test
    public void update_agri_res_state_id_test() {
        db_Utils.update_agri_res_state_id("AM",28);
    }

}
