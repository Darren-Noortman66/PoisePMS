import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

/**
 * <h1>Functions</h1>
 * <b>Functions</b> class used to store different function methods that will be used to work with the program.
 * (Such as converting to integer)
 *
 * @author Darren Noortman
 * @version 1.0
 */
public class Functions {

    /**
     * <b>readProjectFile</b> method used to read the 'Projects' text-file and add each line to an object ArrayList.
     * This method will return an ArrayList value that can be worked with.
     *
     * @return ArrayList This will return a list of projects read from the Projects text-file.
     */
    public static ArrayList<String> readProjectFile() {

        Scanner s;

        ArrayList<String> list = new ArrayList<>();  // Declaring ArrayList to add each line of text-file to.

        try {
            s = new Scanner(new File("Projects.txt"));

            // Using while-loop to add each line of the text-file to the ArrayList.
            while (s.hasNextLine()) {
                list.add(s.nextLine());
            }

            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    // connectionAttempt method used for attempting to open a connection to the 'ebookstore' database
    public static Connection connectionAttempt() {

        Connection connection = null;

        try {
            // Connect to the library_db database, via the jdbc:mysql:channel on localhost (this PC)
            // Use username "clerk1", password "password".
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false",
                    "clerk1",
                    "password"
            );
        } catch (SQLException e) {

            // We only want to catch a SQLException
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * <b>convertToInt</b> method used to convert a string value into an integer.
     * This method will return an integer value that can be used for calculations.
     *
     * @param str String needed to convert
     * @return int This will return the converted String.
     */
    public static int convertToInt(String str) {

        int intConverter = 0;

        // Using a try-catch block to try and parse/ convert the given string value into an integer.
        try {
            intConverter = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        return intConverter; // Returning the integer value for calulating.
    }

    /**
     * <b>intTest</b> method used to determine whether or not a user's input is equal to an integer value.
     *
     * @param numToConvert The number needed to convert.
     * @param type         The type of input needed to print to user.
     */
    public static void intTest(String numToConvert, String type) {

        Scanner input = new Scanner(System.in);  // Creating instance for Scanner package.

        while (true) {  // While loop repeatedly re-prompts for input until correct.
            try {
                Integer.parseInt(numToConvert);
                break;

            } catch (NumberFormatException ex) {
                System.out.println("\nIncorrect entry. Please ensure you are inserting the correct format");  // Error message
                System.out.print("\nInsert" + type + ":\t");  // Prompting for input again
                numToConvert = input.nextLine();

            }
        }
    }

    /**
     * <b>viewAllProjects</b> method used to view every project in the 'Projects' text-file.
     */
    public static void viewAllProjects() {

        // Calling the 'connectionAttempt' method to connect to the 'ebookstore' database.
        Connection connection = connectionAttempt();

        // Using a try- catch block to print the data for a specific record in the 'books' table.
        try {
            // Creating and using a normal statement to select the specific record in the database.
            Statement statement = connection.createStatement();

            // SQL select statement
            String query =  "SELECT " +
                            "projects.project_number, projects.project_name, projects.deadline, projects.finalised, projects.physical_address, projects.architect_name, \n" +
                            "projects.contractor_name, projects.customer_name, projects.total_fee, projects.total_paid,\n" +
                            "architect.telephone, architect.email_address, architect.physical_address,\n" +
                            "contractor.telephone, contractor.email_address, contractor.physical_address,\n" +
                            "customer.telephone, customer.email_address, customer.physical_address,\n" +
                            "building_info.erd_number, building_info.building_type\n" +
                            "FROM projects\n" +
                            "JOIN architect ON projects.architect_name  = architect.name\n" +
                            "JOIN contractor ON projects.contractor_name  = contractor.name\n" +
                            "JOIN customer ON projects.customer_name  = customer.name\n" +
                            "JOIN building_info ON projects.project_number  = building_info.fk_project_number;";

            ResultSet rs_projects = statement.executeQuery(query); // Executing statement

            if (rs_projects.next()) {
                do {
                    String projectDetails = "\nProject Number: " + rs_projects.getString("projects.project_number") + "\n" +
                            "Project Name: " + rs_projects.getString("projects.project_name") + "\n" +
                            "Type of building: " + rs_projects.getString("building_info.building_type") + "\n" +
                            "ERF Number: " + rs_projects.getString("building_info.erd_number") + "\n" +
                            "Physical Address: " + rs_projects.getString("projects.physical_address") + "\n" +
                            "Total fee charged for project: R" + rs_projects.getString("total_fee") + "\n" +
                            "Total amount already paid: R" + rs_projects.getString("total_paid") + "\n" +
                            "Deadline of the project: " + rs_projects.getString("projects.deadline") + "\n" +
                            "Project Finalised: " + rs_projects.getString("projects.finalised") + "\n" +

                            "\nArchitect Name: " + rs_projects.getString("projects.architect_name") + "\n" +
                            "Architect Telephone Number: " + rs_projects.getString("architect.telephone") + "\n" +
                            "Architect Email-Address: " + rs_projects.getString("architect.email_address") + "\n" +
                            "Architect Physical Address: " + rs_projects.getString("architect.physical_address") + "\n" +

                            "\nContractor Name: " + rs_projects.getString("projects.contractor_name") + "\n" +
                            "Contractor Telephone Number: " + rs_projects.getString("contractor.telephone") + "\n" +
                            "Contractor Email-Address: " + rs_projects.getString("contractor.email_address") + "\n" +
                            "Contractor Physical Address: " + rs_projects.getString("contractor.physical_address") + "\n" +

                            "\nCustomer Name: " + rs_projects.getString("projects.customer_name") + "\n" +
                            "Customer Telephone Number: " + rs_projects.getString("customer.telephone") + "\n" +
                            "Customer Email-Address: " + rs_projects.getString("customer.email_address") + "\n" +
                            "Customer Physical Address: " + rs_projects.getString("customer.physical_address") + "\n" +
                            "\n----------------------------------\n";

                    System.out.println(projectDetails);
                } while (rs_projects.next());
            }

            //connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * <b>viewIncompleteProjects</b> method used to view every incomplete project in the 'Projects' text-file.
     */
    public static void viewIncompleteProjects() {

        // Calling the 'connectionAttempt' method to connect to the 'ebookstore' database.
        Connection connection = connectionAttempt();

        PreparedStatement ps;

        // Using a try- catch block to print the data for a specific record in the 'books' table.
        try {
            // Creating and using a normal statement to select the specific record in the database.
            Statement statement = connection.createStatement();

            // SQL select statement
            String query =  "SELECT " +
                    "projects.project_number, projects.project_name, projects.deadline, projects.finalised, projects.physical_address, projects.architect_name, \n" +
                    "projects.contractor_name, projects.customer_name, projects.total_fee, projects.total_paid,\n" +
                    "architect.telephone, architect.email_address, architect.physical_address,\n" +
                    "contractor.telephone, contractor.email_address, contractor.physical_address,\n" +
                    "customer.telephone, customer.email_address, customer.physical_address,\n" +
                    "building_info.erd_number, building_info.building_type\n" +
                    "FROM projects\n" +
                    "JOIN architect ON projects.architect_name  = architect.name\n" +
                    "JOIN contractor ON projects.contractor_name  = contractor.name\n" +
                    "JOIN customer ON projects.customer_name  = customer.name\n" +
                    "JOIN building_info ON projects.project_number  = building_info.fk_project_number\n" +
                    "WHERE finalised = 'No';";

            ResultSet rs_projects = statement.executeQuery(query); // Executing statement

            // Printing ResultSet
            while (rs_projects.next()) {
                String projectDetails = "\nProject Number: " + rs_projects.getString("projects.project_number") + "\n" +
                        "Project Name: " + rs_projects.getString("projects.project_name") + "\n" +
                        "Type of building: " + rs_projects.getString("building_info.building_type") + "\n" +
                        "ERF Number: " + rs_projects.getString("building_info.erd_number") + "\n" +
                        "Physical Address: " + rs_projects.getString("projects.physical_address") + "\n" +
                        "Total fee charged for project: R" + rs_projects.getString("total_fee") + "\n" +
                        "Total amount already paid: R" + rs_projects.getString("total_paid") + "\n" +
                        "Deadline of the project: " + rs_projects.getString("projects.deadline") + "\n" +
                        "Project Finalised: " + rs_projects.getString("projects.finalised") + "\n" +

                        "\nArchitect Name: " + rs_projects.getString("projects.architect_name") + "\n" +
                        "Architect Telephone Number: " + rs_projects.getString("architect.telephone") + "\n" +
                        "Architect Email-Address: " + rs_projects.getString("architect.email_address") + "\n" +
                        "Architect Physical Address: " + rs_projects.getString("architect.physical_address") + "\n" +

                        "\nContractor Name: " + rs_projects.getString("projects.contractor_name") + "\n" +
                        "Contractor Telephone Number: " + rs_projects.getString("contractor.telephone") + "\n" +
                        "Contractor Email-Address: " + rs_projects.getString("contractor.email_address") + "\n" +
                        "Contractor Physical Address: " + rs_projects.getString("contractor.physical_address") + "\n" +

                        "\nCustomer Name: " + rs_projects.getString("projects.customer_name") + "\n" +
                        "Customer Telephone Number: " + rs_projects.getString("customer.telephone") + "\n" +
                        "Customer Email-Address: " + rs_projects.getString("customer.email_address") + "\n" +
                        "Customer Physical Address: " + rs_projects.getString("customer.physical_address") + "\n" +
                        "\n----------------------------------\n";

                System.out.println(projectDetails);
            }
            //connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * <b>viewPastDue</b> method used to view every project past due-date in the 'Projects' text-file.
     */
    public static void viewPastDue() {

        // Calling the 'connectionAttempt' method to connect to the 'ebookstore' database.
        Connection connection = connectionAttempt();

        PreparedStatement ps;

        // Using a try- catch block to print the data for a specific record in the 'books' table.
        try {
            // Creating and using a normal statement to select the specific record in the database.
            Statement statement = connection.createStatement();

            // SQL select statement
            String query =  "SELECT " +
                    "projects.project_number, projects.project_name, projects.deadline, projects.finalised, projects.physical_address, projects.architect_name, \n" +
                    "projects.contractor_name, projects.customer_name, projects.total_fee, projects.total_paid,\n" +
                    "architect.telephone, architect.email_address, architect.physical_address,\n" +
                    "contractor.telephone, contractor.email_address, contractor.physical_address,\n" +
                    "customer.telephone, customer.email_address, customer.physical_address,\n" +
                    "building_info.erd_number, building_info.building_type\n" +
                    "FROM projects\n" +
                    "JOIN architect ON projects.architect_name  = architect.name\n" +
                    "JOIN contractor ON projects.contractor_name  = contractor.name\n" +
                    "JOIN customer ON projects.customer_name  = customer.name\n" +
                    "JOIN building_info ON projects.project_number  = building_info.fk_project_number\n" +
                    "WHERE projects.deadline < DATE(NOW()) AND projects.finalised = 'No';";

            ResultSet rs_projects = statement.executeQuery(query); // Executing statement

            // Printing ResultSet
            while (rs_projects.next()) {
                String projectDetails = "\nProject Number: " + rs_projects.getString("projects.project_number") + "\n" +
                        "Project Name: " + rs_projects.getString("projects.project_name") + "\n" +
                        "Type of building: " + rs_projects.getString("building_info.building_type") + "\n" +
                        "ERF Number: " + rs_projects.getString("building_info.erd_number") + "\n" +
                        "Physical Address: " + rs_projects.getString("projects.physical_address") + "\n" +
                        "Total fee charged for project: R" + rs_projects.getString("total_fee") + "\n" +
                        "Total amount already paid: R" + rs_projects.getString("total_paid") + "\n" +
                        "Deadline of the project: " + rs_projects.getString("projects.deadline") + "\n" +
                        "Project Finalised: " + rs_projects.getString("projects.finalised") + "\n" +

                        "\nArchitect Name: " + rs_projects.getString("projects.architect_name") + "\n" +
                        "Architect Telephone Number: " + rs_projects.getString("architect.telephone") + "\n" +
                        "Architect Email-Address: " + rs_projects.getString("architect.email_address") + "\n" +
                        "Architect Physical Address: " + rs_projects.getString("architect.physical_address") + "\n" +

                        "\nContractor Name: " + rs_projects.getString("projects.contractor_name") + "\n" +
                        "Contractor Telephone Number: " + rs_projects.getString("contractor.telephone") + "\n" +
                        "Contractor Email-Address: " + rs_projects.getString("contractor.email_address") + "\n" +
                        "Contractor Physical Address: " + rs_projects.getString("contractor.physical_address") + "\n" +

                        "\nCustomer Name: " + rs_projects.getString("projects.customer_name") + "\n" +
                        "Customer Telephone Number: " + rs_projects.getString("customer.telephone") + "\n" +
                        "Customer Email-Address: " + rs_projects.getString("customer.email_address") + "\n" +
                        "Customer Physical Address: " + rs_projects.getString("customer.physical_address") + "\n" +
                        "\n----------------------------------\n";

                System.out.println(projectDetails);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }




//            // Using a try-catch block to try and convert the String date into a Date variable.
//            try {
//                projectDueDate = dateFormatter.parse(projectDueDateString);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }

//            // Getting the current date variable.
//            Date currentDate = new Date();

            // Using an if-statement to determine whether or not the project should be displayed.
            // If the project is past due-date, then the project will be printed.
//            if (projectDueDate.compareTo(currentDate) < 0) {
//
//
//        }


    }

    /**
     * <b>overwriteProjFile</b> method used to write a string value over the 'Projects' text-file.
     *
     * @param listString This is a string needed to write to the <b>Projects</b> text-file.
     */
    public static void overwriteProjFile(String listString) {

        try (
                FileWriter f = new FileWriter("Projects.txt");
                        BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b)) {
            p.println(listString);

        } catch (
                IOException i) {

            i.printStackTrace();
        }
    }

    /**
     * <b>appendCompletedFile</b> method used to append a string value to the 'Projects' text-file.
     *
     * @param listString This is a string needed to write to the <b>Projects</b> text-file.
     */
    public static void appendCompletedFile(String listString) {

        try (
                FileWriter f = new FileWriter("Completed project.txt");
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b)) {
            p.println(listString);

        } catch (
                IOException i) {

            i.printStackTrace();
        }
    }
}
