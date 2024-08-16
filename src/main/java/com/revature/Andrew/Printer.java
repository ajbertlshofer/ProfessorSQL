package com.revature.Andrew;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Printer {

    /**
     * Harvests all the data from a ResultSet required to print as a table, with tab-aligned columns.
     * 
     * @param rs {@code ResultSet} to be printed
     * @throws SQLException if a database access error occurs or a closed {@code ResultSet} is provided
     * @see com.revature.Barbee.utils.UltimateStringTabber#tabColumn UltimateStringTabber tabColumn
     */
    public static void printResultSet(ResultSet rs) throws SQLException {

        // Get the column names (and an easier way to see how many columns there are)
        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            columnNames.add(rs.getMetaData().getColumnName(i+1));
        }

        // Prepare to store all the data in a column list
        List<List<String>> dataColumns = new ArrayList<>();
        for (int i = 0; i < columnNames.size(); i++) {
            dataColumns.add(new ArrayList<>());
            dataColumns.get(i).add(columnNames.get(i));
        }

        // While there is another row to read...
        while (rs.next()) {
            // Iterate over the columns of that row...
            for (int i = 0; i < columnNames.size(); i++) {
                // And add that row's value to the list of columns
                dataColumns.get(i).add(rs.getString(i+1));
            }
        }

        // Throw every column into the UltimateStringTabber
        List<List<String>> formattedColumns = new ArrayList<>();
        for (List<String> column : dataColumns) {
            formattedColumns.add(Tabber.tabColumn(column, true));
        }
        
        // Print to CLI
        for (int row = 0; row < formattedColumns.get(0).size(); row++) {
            for (int col = 0; col < formattedColumns.size(); col++) {
                System.out.print(formattedColumns.get(col).get(row));
            }
            System.out.println();
        }

    }
    
}
