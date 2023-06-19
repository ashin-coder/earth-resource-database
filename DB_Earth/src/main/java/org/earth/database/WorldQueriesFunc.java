/*WorldQueries
 *Version 1.0
 *4-04-2023
 *Copyright notice
 */
package org.earth.database;
import lombok.extern.java.Log;
import org.json.JSONArray;
import org.testng.annotations.Test;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;


/**
 * This class represents WorldQueriesFunc for Earth Resource Database
 * The class provides methods for converting outputs received while running method from WorldDBUtils class so that they could
 * be displayed appropriately in the application GUI
 * the required methods from this class are called when the Earth_GUI Package is ran to perform various operations
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */


@Log
public class WorldQueriesFunc {

    static HashMap<String, Callable> queryIdTableMap = new HashMap<>();

    // Show All Tables Available in the Database
    public Object[] show_tables() throws SQLException, IOException {
        WorldDBUtils db_Utils = new WorldDBUtils();
        db_Utils.init();
        List<String> selects = db_Utils.get_db_tables();
        List<String> tables = getListofTables(selects.get(1));
        String[][] tableData = new String[tables.size()][1];
        for (int i = 0; i < tables.size(); i++) {
            tableData[i][0] = tables.get(i);
        }
        String[] columnNames = {"Tables"};
        Object[] result = new Object[2];
        result[0] = tableData;
        result[1] = columnNames;
        return result;
    }

    // getListofTables receives List of tables from database
    private List<String> getListofTables(String jsonString) {
        JSONArray rows = new JSONArray(jsonString);
        int row_size = rows.length();
        List<String> lst = new ArrayList();
        for (Object row : rows) {
            JSONArray each_row = (JSONArray) row;
            for (Object value : each_row) {
                String col_value = (String) value;
                lst.add(col_value);
            }
        }
        return lst;
    }

    //  show_table_data receives table data from database and displays them properly in the JTable in GUI
    public TableModel show_table_data(String table_name) throws Exception {
        Callable lambda = getTableMap(null).get(table_name);
        if (lambda == null) {
            log.info("Invalid table name: " + table_name);
            return null;
        }
        List<String> selects = (List<String>) lambda.call();
        JSONArray rows = new JSONArray(selects.get(1));
        Object[][] data = new Object[rows.length()][];
        for (int i = 0; i < rows.length(); i++) {
            JSONArray row = rows.getJSONArray(i);
            Object[] rowData = new Object[row.length()];
            for (int j = 0; j < row.length(); j++) {
                rowData[j] = row.get(j);
            }
            data[i] = rowData;
        }
        Object[] columnNames = getColumnNames(table_name); // Replace with actual column names
        TableModel model = new DefaultTableModel(data, columnNames);
        return model;
    }

    //  getColumnNames displays column names of the tables from the database and displays them properly in the JTable in GUI
    private Object[] getColumnNames(String table_name) {
        if (table_name.equals("Continents")) {
            return new Object[] { "Continent ID", "Continent Name" };
        } else if (table_name.equals("Countries")) {
            return new Object[] { "Country ID", "Country Name", "Continent ID" };
        } else if (table_name.equals("States")) {
            return new Object[] { "State ID", "State Name", "Country Code" };
        } else if (table_name.equals("Agricultureresources")) {
        return new Object[] { "Agriculture Resource ID", "Agriculture Resource Name", "Amount","Category","State ID" };
        }else if (table_name.equals("Mineralresources")) {
            return new Object[] { "Mineral Resource ID", "Mineral Resource Name", "Amount","Reserve","State ID" };
        }
        return new Object[] {};
        }

    @Test
    //  getTableMap is used check which table user has entered so that appropriate table from database can be displayed in the JTable in GUI
    private HashMap<String, Callable> getTableMap(String[] params) {
        if (queryIdTableMap.isEmpty()) {
            WorldDBUtils db_Utils = new WorldDBUtils();
            db_Utils.init();
            queryIdTableMap.put("Continents", () -> db_Utils.select_continents());
            queryIdTableMap.put("Countries", () -> db_Utils.select_countries());
            queryIdTableMap.put("States", () -> db_Utils.select_states());
            queryIdTableMap.put("Agricultureresources", () -> db_Utils.select_agriculture_resource());
            queryIdTableMap.put("Mineralresources", () -> db_Utils.select_mineral_resource());
        }
        return queryIdTableMap;
    }

    private Callable tableListCallable(String table_name, String[] input) {
        return getTableMap(input).get(table_name);
    }

}