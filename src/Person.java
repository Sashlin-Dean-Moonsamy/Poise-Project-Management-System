/**
 * This code creates a person class that makes information about people more accessible and easy to read
 *
 * @author Sashlin Dean Moonsamy
 * @version 1.00
 */

public class Person {

    /**
     * String value for name
     */
    private final String name;

    /**
     * String value for surname
     */
    private final String surname;

    /**
     * Long value for telephone number
     */
    private long tellNum;

    /**
     * String value for email
     */
    private String email;

    /**
     * String value for address
     */
    private final String address;

    /**
     * A constructor that saves information
     * <br>
     * This helps keep information safe and makes it easier to access
     *
     * @param name    first name value
     * @param surname second name value
     * @param tellNum telephone number value
     * @param email   email address value
     * @param address physical address value
     */
    public Person(String name, String surname, long tellNum, String email, String address) {

        // initialise statement
        this.name = name;
        this.surname = surname;
        this.setTellNum(tellNum);
        this.setEmail(email);
        this.address = address;
    }

    /**
     * A method to get person's name
     *
     * @return name String value
     */
    public String getName() {
        return name;
    }

    /**
     * A method to get person's telephone number
     * <br>
     *
     * @return telephone long value
     */
    public long getTellNum() {
        return tellNum;
    }

    /**
     * A method to get person's email
     * <br>
     *
     * @return email String value
     */
    public String getEmail() {
        return email;
    }

    /**
     * A method to get person's physical address
     * <br>
     *
     * @return physical address String value
     */
    public String getAddress() {
        return address;
    }

    /**
     * A method to change person's telephone number
     * <br>
     *
     * @param tellNum telephone number value
     */
    public void setTellNum(long tellNum) {
        this.tellNum = tellNum;
    }

    /**
     * A method to change a person's email
     *
     * @param email email value
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * A method that returns all a person's information in an easy-to-read format
     *
     * @return string value
     */
    public String toString() {
        String output = "\nName: " + name + " " + surname;
        output += "\nTelephone Number: " + tellNum;
        output += "\nEmail Address: " + email;
        output += "\nPhysical Address " + address;

        return output;
    }
}
