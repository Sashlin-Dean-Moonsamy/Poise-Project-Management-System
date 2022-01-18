// Import modules

import java.sql.SQLException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Main {

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // create private final scanner variable
    private static final Scanner sc = new Scanner(System.in);

    // Create global variables
    static Project project;

    public static void main(String[] arrs) {

        try {

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "versifire",
                    "RiddleMeThis?"
            );

            Statement statement = connection.createStatement();

            ResultSet results;

            // Create label for switch method
            label:

            // Create main loop for project
            while (true) {

                // Create variable for options printed out to user
                String options1 = "\nCNP - Create New Project";
                options1 += "\nEEP - Edit Existing Project";
                options1 += "\nSTA - Statistics";
                options1 += "\nE   - End Program";
                options1 += "\n\nSelection: ";

                // Create variable for options printed out to user
                String options2 = "\nCDD - Change Project Deadline";
                options2 += "\nCTA - Change Total Amount Of Fees Paid To Date";
                options2 += "\nUCI - Update Contractor's Info (Contact information)";
                options2 += "\nFTP - Finalise This Project";
                options2 += "\nE   - Escape";
                options2 += "\n\nSelection: ";

                // Print options to user and receive input
                String selection = getString(options1).toLowerCase();

                // create switch statement
                switch (selection) {

                    // if input is "cnp
                    case "cnp":

                        // Request information from user to create objects
                        System.out.println("\n\n\t\tFill In Information About New Project!!");

                        String projectName = getString("\n\nProject Name: ");

                        int projectNum = getInt("Project Number: ");

                        String buildType = getString("Type Of Building: ");

                        String address = getString("Physical Address Of Project(E.g Street Name, Suburb, Town): ");

                        int erfNum = getInt("ERF Number: ");

                        int totalFee = getInt("Total Fee: R");

                        int paid2Date = getInt("Total Paid TO Date: R");

                        String deadline = getString("Deadline Of Project (DD/MM/YYYY): ");

                        String architectName = getString("Architect Full Name ( Name Surname): ");

                        long architectTelNum = getLong("Architect Telephone Number: ");

                        String architectEmail = getString("Architect Email Address: ");

                        String architectPhysicAdd = getString("Architect Physical Address(E.g Street Name, Suburb, Town): ");

                        String contractorName = getString("Contractor Full Name ( Name Surname): ");

                        long contractorTelNum = getLong("Contractor Telephone Number: ");

                        String contractorEmail = getString("Contractor Email Address: ");

                        String contractorPhysicAdd = getString("Contractor Physical Address(E.g Street Name, Suburb, Town): ");

                        String customerName = getString("Customer Full Name ( Name Surname): ");

                        long customerTelNum = getLong("Customer Telephone Number: ");

                        String customerEmail = getString("Customer Email Address: ");

                        String customerPhysicAdd = getString("Customer Physical Address(E.g Street Name, Suburb, Town): ");

                        // Add people to database
                        int architectUpdate = statement.executeUpdate("INSERT INTO architect VALUES( " + projectNum + ", '" + architectName + "', '" + architectTelNum + "', '" + architectEmail + "', '" + architectPhysicAdd + "' )");
                        int contractorUpdate = statement.executeUpdate("INSERT INTO contractor VALUES( " + projectNum + ", '" + contractorName + "', '" + contractorTelNum + "', '" + contractorEmail + "', '" + contractorPhysicAdd + "' )");
                        int customerUpdate = statement.executeUpdate("INSERT INTO customer VALUES( " + projectNum + ", '" + customerName + "', '" + customerTelNum + "', '" + customerEmail + "', '" + customerPhysicAdd + "' )");

                        // Create project object
                        int projectUpdate = statement.executeUpdate("INSERT INTO  poise_project VALUES('" + projectName + "', " + projectNum + ", '" + buildType + "', '" + address + "', " + erfNum + ", " + totalFee + ", " + paid2Date + ", '" + deadline + "', 'No' )");
                        System.out.println("Update successful !, " + architectUpdate + " Architect, " + contractorUpdate + " Contractor, " + customerUpdate + " Customer And " + projectUpdate + " Projects Updated!");
                        break;


                    // if user enters "eep"
                    case "eep":

                        // Create while loop
                        while (true) {

                            // Print out a list of projects
                            printProjects(statement, "SELECT * FROM poise_project");

                            // Print information to user and request input
                            System.out.println("\n\n\t\t(Type 'e' To Escape!");
                            String searchFilter = getString("\n\t\tChoose A Project Using One Of The Following Search Filters!\n\nPNA - Project Name\nPNU - Project Number\n\nSearch Filter: ").toLowerCase();

                            // if user enters "pna'
                            if (searchFilter.equals("pna")) {

                                // request project name from user
                                System.out.println("\n\t\t(Type 'e' To Escape!");
                                String searchProjectName = getString("\nSearch By Name: ");

                                // if user enters 'e' break loop
                                if (searchProjectName.equals("e")) {
                                    break;
                                }

                                // Execute sql query
                                results = statement.executeQuery("SELECT * FROM poise_project WHERE projectName = '" + searchProjectName + "'");

                                // Create a while loop to create a project value
                                while (results.next()) {

                                    project = new Project(
                                            results.getString("projectName"),
                                            results.getInt("projectNum"),
                                            results.getString("buildType"),
                                            results.getString("projectAddress"),
                                            results.getInt("erf"),
                                            results.getInt("totalFee"),
                                            results.getInt("paid2Date"),
                                            results.getString("Deadline"),
                                            results.getString("Completed_CompletionDate")
                                    );
                                }

                                // Once project is found print out toString
                                System.out.print(project.toString());

                                // Print out options to user and request input
                                String selection2 = getString(options2).toLowerCase();

                                // Create switch statement
                                switch (selection2) {

                                    // if user enters "cdd" print method
                                    case "cdd":

                                        System.out.print(ChangeDeadline(statement));
                                        break;

                                    // if user enters "cta" print method
                                    case "cta":

                                        System.out.print(changePaid2Date(statement));
                                        break;

                                    // if user enters "uci" print method
                                    case "uci":

                                        System.out.print(updateContractorInfo(statement));
                                        break;

                                    // if user enters "fap" print method
                                    case "ftp":

                                        finalise(statement);
                                        break;

                                    // if user enters "e" break
                                    case "e":

                                        break;

                                    default:

                                        // else print statement and break loop
                                        System.out.print("Incorrect Option Entered!");
                                        break;
                                }


                                // if user enters "pnu"
                            } else if (searchFilter.equals("pnu")) {

                                // Print information to user and request input
                                System.out.println("\n\t\t(Type 'e' To Escape!");
                                int searchProjectNum = getInt("\nSearch By Number: ");

                                // if user enters 'e' break loop
                                if (searchProjectNum == 'e') {
                                    break;
                                }

                                // Execute sql query
                                results = statement.executeQuery("SELECT * FROM poise_project WHERE projectNum = " + searchProjectNum);

                                // Create while loop to create project object
                                while (results.next()) {

                                    project = new Project(
                                            results.getString("projectName"),
                                            results.getInt("projectNum"),
                                            results.getString("buildType"),
                                            results.getString("projectAddress"),
                                            results.getInt("erf"),
                                            results.getInt("totalFee"),
                                            results.getInt("paid2Date"),
                                            results.getString("Deadline"),
                                            results.getString("Completed_CompletionDate")
                                    );
                                }

                                // if project found
                                if (project.getProjectNum() == searchProjectNum) {

                                    // Once project is found print out toString
                                    System.out.print(project.toString());

                                    // print information to user and request input
                                    String selection2 = getString(options2).toLowerCase();

                                    // Create switch statement for second list of objects
                                    switch (selection2) {

                                        // if user enters "cdd" print method
                                        case "cdd":

                                            System.out.print(ChangeDeadline(statement));
                                            break;

                                        // if user enters "cta" print method
                                        case "cta":

                                            System.out.print(changePaid2Date(statement));

                                            break;

                                        // if user enters "uci" print method
                                        case "uci":

                                            //Print method
                                            System.out.print(updateContractorInfo(statement));
                                            break;

                                        // if user enters "fap" print method
                                        case "ftp":

                                            System.out.print(finalise(statement));
                                            break;

                                        // if user enters "e" break
                                        case "e":

                                            break;

                                        default:

                                            // else print statement and break loop
                                            System.out.println("Incorrect Option entered. Try again!");
                                            break;
                                    }
                                }

                                // If user enters 'e' break loop
                            } else if (searchFilter.equals("e")) {
                                break;
                            }
                        }// Break case so program doesn't fall to next case
                        break;

                    case "sta":

                        // Call stats method
                        stats(statement);
                        break;

                    // If user enters 'e' break loop
                    case "e":
                        break label;

                    // Create default to print statement to user
                    default:
                        System.out.println("Incorrect option entered. Try again!");
                }
            }

        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * A method that allows user's to update the deadline of the project
     * <br>
     *
     * @param statement Creates a connection with database
     * @return Amount of deadlines added
     * @throws SQLException
     */
    private static String ChangeDeadline(Statement statement) throws SQLException {

        // Display olf information and request new information from user
        String newDeadline = getString("Old Deadline: " + project.getDeadline() + "\nNew Deadline: ");

        // Update deadline in database
        int rowsAffected = statement.executeUpdate("UPDATE poise_project SET deadline = '" + newDeadline + "' WHERE projectNum = " + project.getProjectNum());

        // Return statement to user
        return "\n\nNew DeadLine Successfully Set! " + rowsAffected + " Deadlines Updated!";
    }

    /**
     * A method that allows users to change the total amount paid by customer
     * <br>
     *
     * @param statement Creates connnection with database
     * @return amounted of values updated
     * @throws SQLException
     */
    private static String changePaid2Date(Statement statement) throws SQLException {

        // Print out instructions to user
        System.out.println("\n\t\tType '-1' to escape");

        // Display old information to user and request new information
        int newPaid2Date = getInt("\nPrevious Amount Paid To Date: R" + project.getPaid2Date() + "\nNew Amount Paid To Date: R");

        // Set new amount
        int rowsAffected = statement.executeUpdate("UPDATE poise_project SET paid2Date = " + newPaid2Date + " WHERE projectNum = " + project.getProjectNum());

        // Return statement to user
        return "\n\nAmount Of Fee's Paid To Date Successfully  Updated!!, " + rowsAffected + " Values Updated!";

    }

    /**
     * A method that allows a user to update a contractors information
     * <br>
     *
     * @param statement Creates a connection with database
     * @return amount of contractors updated
     * @throws SQLException
     */
    private static String updateContractorInfo(Statement statement) throws SQLException {

        Person contractor = Database.createContractor(statement, project.getProjectNum());

        // print old info to user
        System.out.print("\n\t\tOld Contractor Contact Info\nName: " + contractor.getName() + "\nTelephone Number: " + contractor.getTellNum() + "\nEmail Address: " + contractor.getEmail());

        // Display old information from user and request new information
        System.out.print("\n\n\t\tContractor New Contact Info:");

        // Set input as variable
        long newTellNum = getInt("\nTelephone Number: ");

        // Request that user enter information and save input in variable
        String newEmail = getString("\nEmail Address: ");

        int rowsAffected = statement.executeUpdate("UPDATE contractor SET contractorEmail = '" + newEmail + "', contractorTell = '" + newTellNum + "' WHERE projectNum = " + project.getProjectNum());

        //return statement to user
        return "\nContractor's Contact Info Successfully updated! " + rowsAffected + " Contractors Updated!";

    }

    /**
     * A method that allows user to finalize a project and generated a invoice if full cost is not paid
     * <br>
     *
     * @param statement Creates connection with database
     * @return amount of projects updated
     * @throws SQLException
     */
    private static String finalise(Statement statement) throws SQLException {

        Person customer = Database.createCustomer(statement, project.getProjectNum());

        // Set variable for outStanding fees
        int outstanding = project.getTotalFee() - project.getPaid2Date();

        // Create string for invoice
        String invoice = "\n\n\t\tCustomer Contact info: ";
        invoice += "\nTelephone Number: " + customer.getTellNum();
        invoice += "\nEmail Address: " + customer.getEmail();
        invoice += "\nPhysical Address: " + customer.getAddress();
        invoice += "\nOutstanding Amount: R" + outstanding;

        // If there is still an outstanding amount print invoice
        if (project.getPaid2Date() != project.getTotalFee()) {
            System.out.print(invoice);
        }

        // Request information from user and save input in variable
        String dateCompleted = getString("\n\nEnter Date of Completion Or 'No' If Project Was Not Complete: ");

        // if the user confirms change boolean value
        if (dateCompleted.equalsIgnoreCase("No")) {

            return "Project Not Finalised";

        } else {

            int rowsAffected = statement.executeUpdate("UPDATE poise_project SET Completed_CompletionDate = '" + dateCompleted + "' WHERE projectNum = " + project.getProjectNum());

            return "Update Completed!, " + rowsAffected + " projects updated";

        }
    }

    /**
     * A method that displays all projects, all completed projects and all overdue projects
     * <br>
     *
     * @param statement Creates a connection database
     * @throws SQLException
     * @throws ParseException
     */
    private static void stats(Statement statement) throws SQLException, ParseException {

        // Print out overdue projects
        System.out.println("\n\t\tAll Projects");

        printProjects(statement, "SELECT * FROM poise_project");

        // Print heading to user
        System.out.println("\n\t\tAll Finalized/ Completed Projects: ");

        // Call printProjects method
        printProjects(statement, "SELECT * FROM poise_project WHERE Completed_CompletionDate != 'No'");


        // Print heading to user
        System.out.println("\n\t\tOverDue Projects: ");

        overdue(statement);
    }

    /**
     * A private method that displays all overdue projects
     * <br>
     *
     * @param statement Creates a connection with database
     * @throws SQLException
     * @throws ParseException
     */
    private static void overdue(Statement statement) throws SQLException, ParseException {

        int num = 1;

        // Create a variable for current date
        Date date = new Date();

        ResultSet results = statement.executeQuery("SELECT * FROM poise_project");

        while (results.next()) {

            String deadline = results.getString("deadline");

            Date deadlineDB = sdf.parse(deadline);

            if (date.compareTo(deadlineDB) >= 0) {
                System.out.println(num + ") " + results.getString("projectName") + ", " + results.getInt("projectNum"));

            } else {
                System.out.println("There Are No Overdue Projects");
            }
        }
    }

    /**
     * A method that reads project name and project numbers from database based on an sql query and prints them out
     * <br>
     *
     * @param statement Creates a connection with database
     * @param sqlQuery
     * @throws SQLException
     */
    private static void printProjects(Statement statement, String sqlQuery) throws SQLException {

        int num = 1;
        ResultSet projects = statement.executeQuery(sqlQuery);

        System.out.print("\n\n");

        while (projects.next()) {

            System.out.println(num + ") " + projects.getString("projectName") + ", " + projects.getInt("projectNum"));
            num += 1;
        }
    }

    /**
     * A method that gets integers from user and enures that value in in correct format
     * <br>
     *
     * @param request String value that will be displayed to user
     * @return integer value that user enters
     */
    private static int getInt(final String request) {

        // Create variable
        int value;

        // Create loop
        while (true) {

            // Print out request to user
            System.out.print(request);

            // Try to save and convert variable to integer
            try {
                value = Integer.parseInt(sc.nextLine());

                // Catch number format exception should there be any and request that user try again
            } catch (NumberFormatException nfe) {
                System.out.println("\nYou have not entered a number. Please try again!");
                continue;

            }
            // Return value
            return value;

        }
    }

    /**
     * A method that requests a long value from user and ensures that it is in correct format
     * <br>
     *
     * @param request String value that will be displayed to user
     * @return Long value that user enters in
     */
    private static long getLong(final String request) {

        // Create variable
        long value;

        // Create loop
        while (true) {

            // Print out request to user
            System.out.print(request);

            // Try to save and convert variable to integer
            try {
                value = Long.parseLong(sc.nextLine());

                // Catch number format exception should there be any and request that user try again
            } catch (NumberFormatException nfe) {
                System.out.println("\nYou have not entered a number. Please try again!");
                continue;

            }
            // Return value
            return value;
        }
    }

    /**
     * A method that requests a String from user
     *
     * @param request String value that will be displayed to user
     * @return String value that user enters in
     */
    private static String getString(final String request) {

        // Print out request to user
        System.out.print(request);

        // Return user's input
        return sc.nextLine();
    }
}