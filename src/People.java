
// Create class
public class People{

    // Set private attributes
    private final Person architect;
    private final Person contractor;
    private final Person customer;

    // Create constructor
    public People(Person architect, Person contractor, Person customer) {

        this.architect = architect;
        this.contractor = contractor;
        this.customer = customer;

    }

    // Create getters
    public Person getArchitect() {
        return architect;
    }

    public Person getContractor() {
        return contractor;
    }

    public Person getCustomer() {
        return customer;
    }

    // Create toString
    public String toString() {

        // Create string variable that will contain all information
        String output = "\nArchitect:";
        output += getArchitect().toString();
        output += "\n\nContractor:";
        output += getContractor().toString();
        output += "\n\nCustomer:";
        output += getCustomer().toString();

        // Return variable
        return output;
    }
}
