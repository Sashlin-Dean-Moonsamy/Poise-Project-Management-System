import java.sql.*;

/**
 * This code Creates a database class that creates Person objects from database
 *
 * @author Sashlin Dean Moonsamy
 * @version 1.0
 */
public class Database {

    /**
     * @param statement  Statement value that creates a connect
     * @param projectNum integer value
     * @return contractor person object
     * @throws SQLException to catch all sql errors
     */
    public static Person createContractor(Statement statement, int projectNum) throws SQLException {

        Person contractor = null;

        ResultSet contractorDB = statement.executeQuery("SELECT * FROM contractor WHERE projectNum = " + projectNum);

        while (contractorDB.next()) {

            String contractorName = contractorDB.getString("contractorName");
            String[] contractorFullName = contractorName.split(" ");

            contractor = new Person(
                    contractorFullName[0],
                    contractorFullName[1],
                    Long.parseLong(contractorDB.getString("contractorTell")),
                    contractorDB.getString("contractorEmail"),
                    contractorDB.getString("contractorAddress")
            );
        }
        return contractor;
    }

    /**
     * @param statement  Statement value that creates a connect
     * @param projectNum integer value that
     * @return customer Person object
     * @throws SQLException to catch all sql errors
     */
    public static Person createCustomer(Statement statement, int projectNum) throws SQLException {

        Person customer = null;

        ResultSet customerDB = statement.executeQuery("SELECT * FROM customer WHERE projectNum = " + projectNum);

        while (customerDB.next()) {

            String customerName = customerDB.getString("customerName");
            String[] customerFullName = customerName.split(" ");

            customer = new Person(
                    customerFullName[0],
                    customerFullName[1],
                    Long.parseLong(customerDB.getString("customerTell")),
                    customerDB.getString("customerEmail"),
                    customerDB.getString("customerAddress")
            );
        }
        return customer;
    }


}
