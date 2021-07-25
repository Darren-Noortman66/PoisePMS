// SE L2T07 - Capstone Project I - OOP

/**
 * <h1>Project</h1>
 * Project class used to <b>create new project instances</b> and allow data to be changed.
 *
 * @author Darren Noortman
 * @version 1.0
 */

public class Project {

    // Attributes
    private final String projectNum;
    private final String projectName;
    private final String buildingType;
    private final String physicalAddress;
    private final String erfNum;
    private final String totalFee;
    private final String totalPaid;
    private final String deadline;
    private final String finalised;

    private final Persons architect;
    private final Persons contractor;
    private final Persons customer;

    /**
     * This is a constructor method used to create a new project based on the given parameters.
     *
     * @param projectNum      Project Number
     * @param projectName     Project Name
     * @param buildingType    Building Type
     * @param physicalAddress Physical Address
     * @param erfNum          ERF Number
     * @param totalFee        Total Fee
     * @param totalPaid       Total Paid
     * @param deadline        Deadline
     * @param finalised       Finalised
     * @param architect       Architect
     * @param contractor      Contractor
     * @param customer        Customer
     */
    public Project(String projectNum, String projectName, String buildingType, String physicalAddress, String erfNum,
                   String totalFee, String totalPaid, String deadline, String finalised, Persons architect, Persons contractor,
                   Persons customer) {
        this.projectNum = projectNum;
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.physicalAddress = physicalAddress;
        this.erfNum = erfNum;
        this.totalFee = totalFee;
        this.totalPaid = totalPaid;
        this.deadline = deadline;
        this.finalised = finalised;
        this.architect = architect;
        this.contractor = contractor;
        this.customer = customer;
    }

    /**
     * This is the toString method used to create a string result of the project.
     *
     * @return String This will return a String of the project.
     */
    public String toString() {
        return "\nProject Number: " + projectNum + "\n" +
                "Project Name: " + projectName + "\n" +
                "Type of building: " + buildingType + "\n" +
                "ERF Number: " + erfNum + "\n" +
                "Physical Address: " + physicalAddress + "\n" +
                "Total fee charged for project: " + totalFee + "\n" +
                "Total amount already paid: " + totalPaid + "\n" +
                "Deadline of the project: " + deadline + "\n" +
                "Project Finalised: " + finalised + "\n" +
                "\nArchitect details: \n" + architect + "\n" + "\n" +
                "Contractor details: \n" + contractor + "\n" + "\n" +
                "Customer details: \n" + customer;
    }
}