/*WorldDBUtils
 *Version 1.0
 *4-04-2023
 *Copyright notice
 */

package org.earth.database;
import com.jdbcservice.DatabaseTransactions;
import com.jdbcservice.dbexec.DefaultQueryExecutor;
import com.jdbcservice.transform.TransformToCSVData;
import com.jdbcservice.transform.TransformToDefaultListOfStrings;
import com.jdbcservice.transform.TransformToJSONObjectsList;
import lombok.extern.java.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents World DB utils for Earth Resource Database
 * The class provides methods for executing all required queries on the tables present in the database file "earth_1.db"
 * which is defined in the default.properties file
 * it executes queries using Query Executer from jdbc service framework from the "JDBCService" file
 * it is advised go through the "JDBCService" file to understand how database connections work with this project
 * all queries are externally are entered and written in a external text file "queries.txt
 * ,this file can referred for understanding the query"
 * queries are given code which is to be like here in this example "instance.genericQueryExecute("20100", Arrays.asList(params),....."
 * where "20100" represents a query
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 * the required methods from this class are called when the Earth_GUI Package is ran to perform various operations
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */


@Log
public class WorldDBUtils {
    static DatabaseTransactions instance = null;
    private long NumberOfRowsInsert = 10;

//    initialize the database
    public void init() {
        String jsonResp = "";
        if (instance != null) //Hacky, for some reason the database in memory vanishes.. for the latter couple of tests..
            jsonResp = instance.genericQueryExecute("100010", Arrays.asList("skrish01"), TransformToJSONObjectsList.class.getName()).get(1);
        if (instance == null || jsonResp.contains("ERROR")) {
            instance = new DatabaseTransactions(DefaultQueryExecutor.class.getName(), "default");
            settingUpDatabase();
        }
    }

//   settingUpDatabase() sets up  the database including its tables before any query can be performed
//  over the table data present in the database
    void settingUpDatabase() {
        log.info("*** Setting up the SQLite database in memory for all the tests");
        log.info("*** CREATE TABLES Continents if not Exist");
        instance.genericQueryExecute("10000", new ArrayList(), TransformToDefaultListOfStrings.class.getName());
        log.info("*** CREATE TABLES Countries if not Exist");
        instance.genericQueryExecute("10100", new ArrayList(), TransformToDefaultListOfStrings.class.getName());
        log.info("*** CREATE TABLES States if not Exist");
        instance.genericQueryExecute("10200", new ArrayList(), TransformToDefaultListOfStrings.class.getName());
        log.info("*** CREATE TABLES Agriculturalresources if not Exist");
        instance.genericQueryExecute("10300", new ArrayList(), TransformToDefaultListOfStrings.class.getName());
        log.info("*** CREATE TABLES Mineralresources if not Exist");
        instance.genericQueryExecute("10400", new ArrayList(), TransformToDefaultListOfStrings.class.getName());
        log.info("*** Database Setup Complete : Run All Tests ...");
    }



// insertion methods
    public List<String> insertContinent01(String continent_id,String continent_name) {
        log.info("*** insertContinent01 function is used ***********");
        log.info("*** INSERT rows into Continent table:");
        String[] params = new String[]{continent_id,continent_name};
        List<String> updates = instance.genericQueryExecute("20000", Arrays.asList(params), TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> insertCountry01(String country_id,String country_name,String continent_id) {
        log.info("*** insertCountry01 function is used ***********");
        log.info("*** INSERT rows into Countries table:");
        int random_suffix = new java.util.Random().nextInt(9999);
        String[] params = new String[]{country_id,country_name,continent_id};
        List<String> updates = instance.genericQueryExecute("20100", Arrays.asList(params), TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public void insertState01(String state_id,String state_name,String country_id,int population ) {
        log.info("*** insertState01 function is used ***********");
        log.info("*** INSERT rows into State table:");

        Object[] params = new Object[]{state_id,state_name,country_id,population};
        List<String> updates = instance.genericQueryExecute("20200", Arrays.asList(params), TransformToDefaultListOfStrings.class.getName());
    }


    public void insertAgriculturalResource01(String agri_resource_name,double amount,String agri_category,String state_id) {
        log.info("*** insertAgriculturalResource01 function is used ***********");
        log.info("*** INSERT rows into AgricultureResource table:");
        Object[] params = new Object[]{agri_resource_name,amount,agri_category,state_id};
        List<String> updates = instance.genericQueryExecute("20300", Arrays.asList(params), TransformToDefaultListOfStrings.class.getName());
    }


    public void insertMineralResource01(String mineral_resource_name,double amount,double reserve,String state_id) {
        log.info("*** insertMineralResource01  function is used ***********");
        log.info("*** INSERT rows into MineralResource table:");
        Object[] params = new Object[]{mineral_resource_name,amount,reserve,state_id};
        List<String> updates = instance.genericQueryExecute("20400", Arrays.asList(params), TransformToDefaultListOfStrings.class.getName());
    }

// selection methods
    public List<String> select_continents() {
        log.info("*** select_continents function  is used ***********");
        log.info("*** View rows from Continent table:");
        String[] params = new String[]{};
        List<String> selects = instance.genericQueryExecute("40110", Arrays.asList(params), TransformToJSONObjectsList.class.getName());
        return selects;
    }

    public List<String> select_countries() {
        log.info("*** select_countries function is used ***********");
        log.info("*** View rows from Countries table:");
        List<String> selects = instance.genericQueryExecute("40120", new ArrayList(), TransformToJSONObjectsList.class.getName());
        return selects;
    }

    public List<String> select_countries(String[] input) {
        log.info("*** select_countries with parameter function is used ***********");
        log.info( " Testing : " + String.join(": " ,input) );
        String[] params = input;
        List<String> selects = instance.genericQueryExecute("100020", Arrays.asList(params), TransformToCSVData.class.getName());
        return selects;
    }

    public List<String> select_states() {
        log.info("*** select_states function is used ***********");
        log.info("*** View rows from States table:");
        String[] params = new String[]{};
        List<String> selects = instance.genericQueryExecute("40130", Arrays.asList(params), TransformToJSONObjectsList.class.getName());
        return selects;
    }

    public List<String> select_agriculture_resource() {
        log.info("*** select_agriculture_resource function is used ***********");
        log.info("*** View rows from Agriculture Resource table:");
        String[] params = new String[]{};
        List<String> selects = instance.genericQueryExecute("40140", Arrays.asList(params), TransformToJSONObjectsList.class.getName());
        return selects;
    }

    public List<String> select_mineral_resource() {
        log.info("*** select_mineral_resource function is used ***********");
        log.info("*** View rows from Mineral Resource table:");
        String[] params = new String[]{};
        List<String> selects = instance.genericQueryExecute("40150", Arrays.asList(params), TransformToJSONObjectsList.class.getName());
        return selects;
    }


    public List<String> select_population(String country_name) {
        log.info("*** select_population function is used ***********");
        log.info("*** View Population of a Country based on States table:");
        String[] params = new String[]{country_name};
        List<String> updates = instance.genericQueryExecute( "40200" , Arrays.asList(params) , TransformToCSVData.class.getName());
        return updates;
    }


    public List<String> count_agri_resource( String agri_resource_name,String country_name) {
        log.info("*** count_agri_resource function is used ***********");
        String[] params = new String[]{agri_resource_name,country_name};
        List<String> selects = instance.genericQueryExecute("40300", Arrays.asList(params), TransformToCSVData.class.getName());
        return selects;

    }


    public List<String> count_mineral_resource(String mineral_resource_name,String country_name) {
        log.info("*** count_mineral_resource function is used ***********");
        String[] params = new String[]{mineral_resource_name,country_name};
        List<String> selects = instance.genericQueryExecute("40400", Arrays.asList(params), TransformToCSVData.class.getName());
        return selects;
    }


    public List<String> get_db_tables() {
        log.info("*** get_db_tables function is used ***********");
        String[] params = new String[]{};
        List<String> selects = instance.genericQueryExecute("100000", Arrays.asList(params), TransformToJSONObjectsList.class.getName());
        return selects;
    }

//update methods
    public List<String> update_continent_id(String new_continent_id,String continent_id) {
        log.info("*** update_continent_id function is used ***********");
        String[] params = new String[] {new_continent_id,continent_id};
        List<String> updates = instance.genericQueryExecute( "30100" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());

        return updates;
    }

    public List<String> update_continent_name(String new_continent_name,String continent_id) {
        log.info("*** update_continent_name function is used ***********");
        String[] params = new String[] {new_continent_name,continent_id};
        List<String> updates = instance.genericQueryExecute( "30101" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> update_country_id(String new_country_id,String country_id) {
        log.info("*** update_country_id function is used ***********");
        String[] params = new String[] {new_country_id,country_id};
        List<String> updates = instance.genericQueryExecute( "30200" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }

    public List<String> update_country_name(String new_country_name,String country_id) {
        log.info("*** update_country_name function is used ***********");
        String[] params = new String[] {new_country_name,country_id};
        List<String> updates = instance.genericQueryExecute( "30201" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }
    public List<String> update_country_continent_id(String country_continent_id,String country_id) {
        log.info("*** update_country_continent_id function is used ***********");
        String[] params = new String[] {country_continent_id,country_id};
        List<String> updates = instance.genericQueryExecute( "30202" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }



    public List<String> update_state_id(String new_state_id,String state_id) {
        log.info("*** update_state_id function is used ***********");
        String[] params = new String[] {new_state_id,state_id};
        List<String> updates = instance.genericQueryExecute( "30300" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }

    public List<String> update_state_name(String new_state_name,String state_id) {
        log.info("*** update_state_name function is used ***********");
        String[] params = new String[] {new_state_name,state_id};
        List<String> updates = instance.genericQueryExecute( "30301" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }

    public List<String> update_state_country_id(String new_state_country_id,String state_id) {
        log.info("*** update_state_country_id function is used ***********");
        String[] params = new String[] {new_state_country_id,state_id};
        List<String> updates = instance.genericQueryExecute( "30302" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }

    public List<String> update_state_population(int new_state_state_population,String state_id) {
            log.info("*** update_state_population function is used ***********");
            Object[] params = new Object[] {new_state_state_population,state_id};
        List<String> updates = instance.genericQueryExecute( "30303" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> update_agri_res_name(String new_agri_res_name,int agri_resource_id) {
        log.info("*** update_agri_res_name function is used ***********");
        Object[] params = new Object[]{new_agri_res_name,agri_resource_id};
        List<String> updates = instance.genericQueryExecute( "30400" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }



    public List<String> update_agri_res_amount(double new_agri_res_amount,int agri_resource_id) {
        log.info("*** update_agri_res_amount function is used ***********");
        Object[] params = new Object[]{new_agri_res_amount,agri_resource_id};
        List<String> updates = instance.genericQueryExecute( "30401" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> update_agri_res_category(String new_agri_res_category,int agri_resource_id) {
        log.info("*** update_agri_res_category function is used ***********");
        Object[] params = new Object[]{new_agri_res_category,agri_resource_id};
        List<String> updates = instance.genericQueryExecute( "30402" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> update_agri_res_state_id(String new_agri_res_state_id,int agri_resource_id) {
        log.info("*** update_agri_res_state_id function is used ***********");
        Object[] params = new Object[]{new_agri_res_state_id,agri_resource_id};
        List<String> updates = instance.genericQueryExecute( "30403" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> update_mineral_res_name(String new_mineral_res_name,int mineral_resource_id) {
        log.info("*** update_agri_res_state_id function is used ***********");
        Object[] params = new Object[] {new_mineral_res_name,mineral_resource_id};
        List<String> updates = instance.genericQueryExecute( "30500" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }



    public List<String> update_mineral_res_amount(double new_mineral_res_amount,int mineral_resource_id) {
        log.info("*** update_mineral_res_amount function is used ***********");
        Object[] params = new Object[] {new_mineral_res_amount,mineral_resource_id};
        List<String> updates = instance.genericQueryExecute( "30501" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> update_mineral_res_reserve(double new_mineral_res_reserve,int mineral_resource_id) {
        log.info("*** update_mineral_res_reserve function is used ***********");
        Object[] params = new Object[] {new_mineral_res_reserve,mineral_resource_id};
        List<String> updates = instance.genericQueryExecute( "30502" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> update_mineral_res_state_id(String new_mineral_res_state_id,int mineral_resource_id) {
        log.info("*** update_mineral_res_state_id function is used ***********");
        Object[] params = new Object[]{new_mineral_res_state_id,mineral_resource_id};
        List<String> updates = instance.genericQueryExecute( "30503" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> delete_continent_rows(String continent_id) {
        log.info("*** delete_continent_rows function is used ***********");
        Object[] params = new Object[]{continent_id};
        List<String> updates = instance.genericQueryExecute( "30000" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }

//    deletion methods

    public List<String> delete_countries_rows(String country_id) {
        log.info("*** delete_countries_rows function is used ***********");
        Object[] params = new Object[]{country_id};
        List<String> updates = instance.genericQueryExecute( "30001" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> delete_states_rows(String states_id) {
        log.info("*** delete_states_rows function is used ***********");
        Object[] params = new Object[]{states_id};
        List<String> updates = instance.genericQueryExecute( "30002" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> delete_agri_res_rows(int agri_resource_id) {
        log.info("*** delete_agri_res_rows function is used ***********");
        Object[] params = new Object[]{agri_resource_id};
        List<String> updates = instance.genericQueryExecute( "30003" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }


    public List<String> delete_mineral_res_rows(int mineral_resource_id) {
        log.info("*** delete_mineral_res_rows function is used ***********");
        Object[] params = new Object[]{mineral_resource_id};
        List<String> updates = instance.genericQueryExecute( "30004" , Arrays.asList(params) , TransformToDefaultListOfStrings.class.getName());
        return updates;
    }
}
