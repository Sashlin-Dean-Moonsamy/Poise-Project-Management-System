import java.sql.*;

// Create project class
public class Project {

    private String projectName;
    private final int projectNum;
    private final String buildType;
    private final String projectAddress;
    private final int erf;
    private final int totalFee;
    private int paid2Date;
    private String deadline;
    private String complete;

    /**
     * A constructor that saves information
     * <br>
     *
     * @param projectName    String value
     * @param projectNum     int value
     * @param buildType      String value
     * @param projectAddress String value
     * @param erf            int value
     * @param totalFee       int value
     * @param paid2Date      int value
     * @param deadline       String value
     * @param complete       String value
     */
    public Project(String projectName, int projectNum, String buildType, String projectAddress, int erf, int totalFee, int paid2Date, String deadline, String complete) {

        // initialise values
        this.projectName = projectName;
        this.projectNum = projectNum;
        this.buildType = buildType;
        this.projectAddress = projectAddress;
        this.erf = erf;
        this.totalFee = totalFee;
        setPaid2Date(paid2Date);
        setDeadline(deadline);
        setComplete(complete);

        // if no project name is entered create name out of buildType  and customer surname
        if (projectName.equals("")) {

            Connection connection = null;

            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/library_db?useSSL=false",
                        "versifire",
                        "RiddleMeThis?"
                );


                Statement statement = connection.createStatement();

                ResultSet results = statement.executeQuery("SELECT * FROM customer WHERE projectNum = " + projectNum);

                while (results.next()) {

                    setProjectName(getBuildType() + " " + results.getString("customerName"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A method that returns project name
     * <br>
     *
     * @return projectName int value
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * A method that returns a projects project number
     * <br>
     *
     * @return projectNum int value
     */
    public int getProjectNum() {
        return projectNum;
    }

    /**
     * A method that returns a project's build Type
     * <br>
     *
     * @return buildType String value
     */
    public String getBuildType() {
        return buildType;
    }

    /**
     * A method that returns a project's Address
     * <br>
     *
     * @return projectAddress String value
     */
    public String getProjectAddress() {
        return projectAddress;
    }

    /**
     * A method that returns a project's erf number
     * <br>
     *
     * @return erf int value
     */
    public int getErf() {
        return erf;
    }

    /**
     * A method that returns Deadline of a project
     * <br>
     *
     * @return deadline String
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * A method that returns The amount a customer paid on a project
     * <br>
     *
     * @return paid2Date int value
     */
    public int getPaid2Date() {
        return paid2Date;
    }

    /**
     * A method that returns the total cost of a project
     * <br>
     *
     * @return totalFee int
     */
    public int getTotalFee() {
        return totalFee;
    }

    // Create setters

    /**
     * A method that sets a project's name
     * <br>
     *
     * @param projectName String value
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;

    }

    /**
     * A method that set's the deadline of a project
     * <br>
     *
     * @param deadline String value
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * A method that set's the amount a customer has paid to current date
     *
     * @param paid2Date int value
     */
    public void setPaid2Date(int paid2Date) {
        this.paid2Date = paid2Date;
    }

    /**
     * A method that sets The information on weather a project has been completed as well as the date of completion if a project has been completed
     *
     * @param complete String value
     */
    public void setComplete(String complete) {
        this.complete = complete;
    }

    /**
     * A method that returns all information about a project object in an easy-to-read format
     * <br>
     *
     * @return String value
     */
    public String toString() {

        String output = "\nProject Name: " + getProjectName() + "\n";
        output += "Project Number: " + getProjectNum() + "\n";
        output += "Type of building: " + getBuildType() + "\n";
        output += "Project Address: " + getProjectAddress() + "\n";
        output += "erf Number: " + getErf() + "\n";
        output += "Total cost of project: " + getTotalFee() + "\n";
        output += "Amount paid to date: " + getPaid2Date() + "\n";
        output += "Deadline of Project: " + getDeadline() + "\n";

        // Return variable
        return output;
    }
}



