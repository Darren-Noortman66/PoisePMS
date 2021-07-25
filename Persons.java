// SE L2T07 - Capstone Project I - OOP

/**
 * <h1>Persons</h1>
 * Persons class used to <b>create new people instances</b> (Contractor, Architect, Customer).
 *
 * @author Darren Noortman
 * @version 1.0
 */
public class Persons {

    // Attributes
    private final String name;
    private final String telephone;
    private final String emailAddress;
    private final String physicalAddress;

    /**
     * Constructor method used to add a person based on the given data.
     *
     * @param name            Name
     * @param telephone       Telephone
     * @param emailAddress    Email Address
     * @param physicalAddress Physical Address
     */
    public Persons(String name, String telephone, String emailAddress, String physicalAddress) {
        this.name = name;
        this.telephone = telephone;
        this.emailAddress = emailAddress;
        this.physicalAddress = physicalAddress;
    }

    // toString method

    /**
     * This is the toString method used to create a string result of the project.
     *
     * @return String This will return a String of the project.
     */
    public String toString() {

        String output = "\nName: " + name;
        output += "\nTelephone: " + telephone;
        output += "\nEmail Address: " + emailAddress;
        output += "\nPhysical Address: " + physicalAddress;

        return output;
    }
}
