import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * <h1>Update Project</h1>
 * UpdateProject class <b>used to update/ change information</b> for a project.
 *
 * @author Darren Noortman
 * @version 1.0
 */
public class UpdateProject {

    static Project proj;

    /**
     * Creating a method named 'createProject' that will be used to create new projects.
     *
     * @param projects An ArrayList of type Projects is needed here.
     */
    public static void createProject(ArrayList<Project> projects) {

        // Calling the 'connectionAttempt' method to connect to the 'ebookstore' database.
        Connection connection = Functions.connectionAttempt();

        Scanner input = new Scanner(System.in);  // Creating instance for Scanner package.

        String projectNum = null;
        String projectName = null;
        String typeOfBuilding = null;
        String physicalAddress = null;
        String erfNum = null;
        String totalFee = null;
        String totalPaid = null;
        String deadline = null;
        String finalised = null;

        String architectName = null;
        String architectTele = null;
        String architectEmail = null;
        String architectAddress = null;

        String contractorName = null;
        String contractorTele = null;
        String contractorEmail = null;
        String contractorAddress = null;

        String customerName = null;
        String customerTele = null;
        String customerEmail = null;
        String customerAddress = null;

        // Using a try-catch-finally block to get the new book information from the user.
        try {

            // Requesting each needed piece of information and storing it in the appropriate variable.
            System.out.print("\nInsert Project Number:\t");
            projectNum = input.nextLine();
            Functions.intTest(projectNum, " Project Number");  // Testing whether or not input is an integer.
            Functions.convertToInt(projectNum);

            System.out.print("\nInsert Project Name:\t");
            projectName = input.nextLine();

            System.out.print("\nInsert Building Type (Home, Office-Building, etc):\t");
            typeOfBuilding = input.nextLine();

            System.out.println("\nInsert Physical Address of Building:\t");
            physicalAddress = input.nextLine();

            System.out.print("\nInsert ERF Number:\t");
            erfNum = input.nextLine();
            Functions.intTest(erfNum, " ERF Number");  // Testing whether or not input is an integer.
            Functions.convertToInt(erfNum);

            System.out.print("\nInsert Total Fee for Project:\t");
            totalFee = input.nextLine();
            Functions.intTest(totalFee, " Total Fee");  // Testing whether or not input is an integer.
            Functions.convertToInt(totalFee);

            System.out.print("\nInsert Total Amount Already Paid:\t");
            totalPaid = input.nextLine();
            Functions.intTest(totalPaid, " Total Paid");  // Testing whether or not input is an integer.
            Functions.convertToInt(totalPaid);

            System.out.println("""
                    Insert the new due-date that you would like to have for the project: 
                    (Format: YYYY-MM-DD)(E.g: 2021-06-16)""");
            deadline = input.nextLine();

            finalised = "No";

            // Making a string array for each person working on the project with
            // the values gathered from the 'getDetails' method.

            // An instance of "Architect" is then created with the values gathered in getDetails. ('Details' used here)
            String[] details = getDetails("Architect");
            Persons architect = new Persons(details[0], details[1], details[2], details[3]);

            architectName = details[0];
            architectTele = details[1];
            architectEmail = details[2];
            architectAddress = details[3];

            // An instance of "Contractor" is then created with the values gathered in getDetails. ('Details1' used here)
            String[] details1 = getDetails("Contractor");
            Persons contractor = new Persons(details1[0], details1[1], details1[2], details1[3]);

            contractorName = details1[0];
            contractorTele = details1[1];
            contractorEmail = details1[2];
            contractorAddress = details1[3];

            // An instance of "Customer" is then created with the values gathered in getDetails. ('Details2' used here)
            String[] details2 = getDetails("Customer");
            Persons customer = new Persons(details2[0], details2[1], details2[2], details2[3]);

            customerName = details2[0];
            customerTele = details2[1];
            customerEmail = details2[2];
            customerAddress = details2[3];

            // Using an if-statement to determine whether or not the Project Name should be generated for the user.
            if (projectName.equals("")) {

                // If the Project Name variable is empty, then the Project Name will be equal to the
                // Customer's first name and the type of building for the project.
                String[] customerFullName = details2[3].split(" ");

                projectName = typeOfBuilding + " " + customerFullName[0];
            }

            // Creating an instance of "Project" with all the information gathered in this method.
            // The arguments given for the class are the attributes of this method
            Project proj = new Project(projectNum, projectName, typeOfBuilding, physicalAddress, erfNum, totalFee,
                    totalPaid, deadline, finalised, architect, contractor, customer);

            UpdateProject.proj = proj;

            //  Adding the created 'proj' instance to the 'projects' arraylist.
            projects.add(proj);

            // Printing success message and displaying added project to the user.
            System.out.println("\n\n------------------------------------------------------------------------------------");
            System.out.println("\nProject Added:\n");
            System.out.println(proj);

        } catch (Exception e) {

            // Error message
            System.out.println("Incorrect Input");
        }

        // Using a try-catch-finally block to insert the user's input into the 'books' table within the 'ebookstore' database.
        try {

            //
            String architectInsert = " insert into architect (name, telephone, email_address, physical_address)" +
                    " values (?, ?, ?, ?)";

            // Creating and using a PreparedStatement to insert the user's input into the database.
            PreparedStatement preparedStmtArchi = connection.prepareStatement(architectInsert);
            preparedStmtArchi.setString(1, architectName);
            preparedStmtArchi.setString(2, architectTele);
            preparedStmtArchi.setString(3, architectEmail);
            preparedStmtArchi.setString(4, architectAddress);

            // Executing the created statement
            preparedStmtArchi.execute();

            // ------------------------------- //

            //
            String contractorInsert = " insert into contractor (name, telephone, email_address, physical_address)" +
                    " values (?, ?, ?, ?)";

            // Creating and using a PreparedStatement to insert the user's input into the database.
            PreparedStatement preparedStmtContr = connection.prepareStatement(contractorInsert);
            preparedStmtContr.setString(1, contractorName);
            preparedStmtContr.setString(2, contractorTele);
            preparedStmtContr.setString(3, contractorEmail);
            preparedStmtContr.setString(4, contractorAddress);

            // Executing the created statement
            preparedStmtContr.execute();

            //
            String customerInsert = " insert into customer (name, telephone, email_address, physical_address)" +
                    " values (?, ?, ?, ?)";

            // Creating and using a PreparedStatement to insert the user's input into the database.
            PreparedStatement preparedStmtCust = connection.prepareStatement(customerInsert);
            preparedStmtCust.setString(1, customerName);
            preparedStmtCust.setString(2, customerTele);
            preparedStmtCust.setString(3, customerEmail);
            preparedStmtCust.setString(4, customerAddress);

            // Executing the created statement
            preparedStmtCust.execute();

            // ------------------------------- //

            // SQL insert statement
            String query = " insert into projects (project_number, project_name, deadline, finalised, " +
                    "physical_address, total_fee, total_paid, architect_name," +
                    "contractor_name, customer_name) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Creating and using a PreparedStatement to insert the user's input into the database.
            PreparedStatement preparedStmtProj = connection.prepareStatement(query);
            preparedStmtProj.setString(1, projectNum);
            preparedStmtProj.setString(2, projectName);
            preparedStmtProj.setString(3, deadline);
            preparedStmtProj.setString(4, finalised);
            preparedStmtProj.setString(5, physicalAddress);
            preparedStmtProj.setString(6, totalFee);
            preparedStmtProj.setString(7, totalPaid);
            preparedStmtProj.setString(8, architectName);
            preparedStmtProj.setString(9, contractorName);
            preparedStmtProj.setString(10, customerName);

            // Executing the created statement
            preparedStmtProj.execute();

            // ------------------------------- //

            String buildingInsert = " insert into building_info (erd_number, building_type, fk_project_number)" +
                    " values (?, ?, ?)";

            // Creating and using a PreparedStatement to insert the user's input into the database.
            PreparedStatement preparedStmtBuild = connection.prepareStatement(buildingInsert);
            preparedStmtBuild.setString(1, erfNum);
            preparedStmtBuild.setString(2, typeOfBuilding);
            preparedStmtBuild.setString(3, projectNum);

            // Executing the created statement
            preparedStmtBuild.execute();

            connection.close();
        } catch (Exception e) {

            // Error message
            System.err.println("\nGot an exception! Could not add project details to database.");
            System.err.println(e.getMessage());
        }

    }

    /**
     * Creating a method named 'getDetails' that will be used to get
     * the details of each person involved in a project.
     *
     * @param role This parameter requires the role of the person.
     * @return String[] This will return a list of projects read from the chosen person.
     */
    public static String[] getDetails(String role) {

        Scanner input = new Scanner(System.in);  // Instance for Scanner package.

        // Requesting each needed piece of information and storing it in the appropriate variable.
        System.out.println("\nInsert Name of " + role + ":\t");
        String name = input.nextLine();

        System.out.println("\nInsert Telephone Number of " + role + ":\t");
        String telephone = input.nextLine();

        System.out.println("\nInsert Email-Address of " + role + ":\t");
        String emailAddress = input.nextLine();

        System.out.println("\nInsert Physical Home Address of " + role + ":\t");
        String PhysicalAddress = input.nextLine();

        // Returning a string array with the information gathered in this method.
        return new String[]{name, telephone, emailAddress, PhysicalAddress};
    }

    /**
     * Creating a method named 'updateDeadline' that will
     * be used to update the deadline for a chosen project.
     */
    public static void updateDeadline() {

        // Calling the 'connectionAttempt' method to connect to the 'ebookstore' database.
        Connection connection = Functions.connectionAttempt();

        Scanner in = new Scanner(System.in);

        PreparedStatement ps;
        int id = 0;
        String newDeadine = null;

        // Using a try-catch block to get the new quantity for a chosen book from the user.
        try {
            // New Date Message
            System.out.println("""
                                    
                    ----------------------------------------------------------------------------------------------                                
                                    
                    Insert the new due-date that you would like to have for the project:
                    (Format: YYYY-MM-DD)(E.g: 2021-06-16)                            
                                                   
                    """);

            newDeadine = in.nextLine();

            // Project Choice Message
            System.out.println("""
                                    
                    ----------------------------------------------------------------------------------------------                                
                                    
                    Insert The Number Or Name Of The Project That You Want To Edit:
                                                   
                    """);

            id = in.nextInt();

        } catch (Exception e) {

            // Error message
            System.out.println("\nIncorrect Input");
        }

        // Using a try-catch block to update the chosen record with the new quantity details for chosen book.
        try {
            // SQL update statement
            String query = "UPDATE projects SET deadline = ? WHERE project_number = ? ";

            // Using a PreparedStatement to change the details for the chosen record/ id.
            ps = connection.prepareStatement(query);
            ps.setString(1, newDeadine);
            ps.setInt(2, id);

            ps.executeUpdate();  // Executing statement

            System.out.println("\n------------------------\n" +
                    "Record updated successfully");

            connection.close();
        } catch (SQLException e) {

            // Error message
            e.printStackTrace();
        }

    }

    /**
     * Creating a method named 'updateFee' that will
     * be used to update the amount of money paid by the customer to date.
     */
    public static void updateFee() {

        // Calling the 'connectionAttempt' method to connect to the 'ebookstore' database.
        Connection connection = Functions.connectionAttempt();

        Scanner in = new Scanner(System.in);

        PreparedStatement ps;
        int id = 0;
        int newTotalPaid = 0;

        // Using a try-catch block to get the new quantity for a chosen book from the user.
        try {
            // Project Choice Message
            System.out.println("""
                                    
                    ----------------------------------------------------------------------------------------------                                
                                    
                    Insert The Number Or Name Of The Project That You Want To Edit:
                                                   
                    """);

            id = in.nextInt();


            // Requesting the new amount paid by the customer to date.
            System.out.println("""
                                    
                    ----------------------------------------------------------------------------------------------                                
                                    
                    Input New Amount Paid for Project Fee:
                                                             
                    """);

            newTotalPaid = in.nextInt();

        } catch (Exception e) {

            // Error message
            System.out.println("\nIncorrect Input");
        }

        // Using a try-catch block to update the chosen record with the new quantity details for chosen book.
        try {
            // SQL update statement
            String query = "UPDATE projects SET total_paid = ? WHERE project_number = ? ";

            // Using a PreparedStatement to change the details for the chosen record/ id.
            ps = connection.prepareStatement(query);
            ps.setInt(1, newTotalPaid);
            ps.setInt(2, id);

            ps.executeUpdate();  // Executing statement

            System.out.println("\n------------------------\n" +
                    "Record updated successfully");

            connection.close();
        } catch (SQLException e) {

            // Error message
            e.printStackTrace();
        }

    }

    /**
     * Creating a method named 'updateContractor' that will be
     * used to update the contractor's contact details.
     */
    public static void updateContractor() {

        // Calling the 'connectionAttempt' method to connect to the 'ebookstore' database.
        Connection connection = Functions.connectionAttempt();

        Scanner in = new Scanner(System.in);

        PreparedStatement psContractor;
        PreparedStatement psProjects;
        PreparedStatement psClose = null;
        PreparedStatement psOpen = null;
        String contractor = null;
        String newName = null;
        String newNum = null;
        String newEmail = null;
        String newAddress = null;

        // Using a try-catch block to get the new quantity for a chosen book from the user.
        try {
            // Project Choice Message
            System.out.println("""
                                    
                    ----------------------------------------------------------------------------------------------                                
                                    
                    Insert The Name of the Contractor You Would Lie to Edit:
                                                   
                    """);

            contractor = in.nextLine();

            // Asking user for new Contractor Name
            System.out.println("""
                                    
                    Insert the new contact details for the contractor working on the project
                                             
                    ----------------------------------------------------------------------------------------------                                
                                    
                    Insert the name of the contractor working on the project:
                                                   
                    """);

            newName = in.nextLine();  // Requesting user's choice

            // Asking user for new Contractor TelephoneNumber
            System.out.println("""
                                    
                    ----------------------------------------------------------------------------------------------                                
                                    
                    Insert the telephone of the contractor working on the project:
                                                   
                    """);

            newNum = in.nextLine();  // Requesting user's choice

            // Asking user for new Contractor Email-Address
            System.out.println("""
                                    
                    ----------------------------------------------------------------------------------------------                                
                                    
                    Insert the email-address of the contractor working on the project:
                                                   
                    """);

            newEmail = in.nextLine();  // Requesting user's choice

            // Asking user for new Contractor Physical-Address
            System.out.println("""
                                    
                    ----------------------------------------------------------------------------------------------                                
                                    
                    Insert the physical address of the contractor working on the project:
                                                   
                    """);

            newAddress = in.nextLine();  // Requesting user's choice


        } catch (Exception e) {

            // Error message
            System.out.println("\nIncorrect Input");
        }

        // Your problem is below here!

        // Using a try-catch block to update the chosen record with the new quantity details for chosen book.
        try {
            // SQL update statement
            String closeForeignChecks = "SET FOREIGN_KEY_CHECKS=0 ";

            String openForeignChecks = "SET FOREIGN_KEY_CHECKS=0 ";

            // SQL update statement
            String updateContractor =
                    "UPDATE contractor SET name = ?, telephone = ?, email_address = ?, physical_address = ?\n" +
                            "WHERE contractor.name = ? ";

//
//            "SET FOREIGN_KEY_CHECKS=1"

            // SQL update statement
            String updateProjects = "UPDATE projects SET contractor_name = ?\n " +
                    "WHERE projects.contractor_name = ?";

            // Using a PreparedStatement to change the details for the chosen record/ id.
            psContractor = connection.prepareStatement(updateContractor);
            psContractor.setString(1, newName);
            psContractor.setString(2, newNum);
            psContractor.setString(3, newEmail);
            psContractor.setString(4, newAddress);
            psContractor.setString(5, contractor);

            // Using a PreparedStatement to change the details for the chosen record/ id.
            psProjects = connection.prepareStatement(updateProjects);
            psProjects.setString(1, newName);
            psProjects.setString(2, contractor);

            psClose = connection.prepareStatement(closeForeignChecks);
            psClose.executeUpdate();

            psContractor.executeUpdate();  // Executing statement
            psProjects.executeUpdate();  // Executing statement

            psOpen = connection.prepareStatement(openForeignChecks);
            psOpen.executeUpdate();

            System.out.println("\n------------------------\n" +
                    "Record updated successfully");

            connection.close();
        } catch (SQLException e) {

            // Error message
            e.printStackTrace();
        }
    }

    /**
     * finaliseProject method used to finalise a chosen project, print it's invoice,
     * and append the project to the 'Completed Projects' text-file.
     */
    public static void finaliseProject() {

        // Calling the 'connectionAttempt' method to connect to the 'PoisePMS' database.
        Connection connection = Functions.connectionAttempt();

        Scanner in = new Scanner(System.in);

        // Variables
        int id = 0;
        PreparedStatement ps_projects;
        ResultSet rs = null;

        int projectNumber = 0;
        String projectName = null;
        String deadline = null;
        String finalised = null;
        String physicalAddress = null;
        int erd = 0;
        String buildingType = null;
        int totalFee = 0;
        int totalPaid = 0;

        String architectName = null;
        String architectTele = null;
        String architectEmail = null;
        String architectAddress = null;

        String contractorName = null;
        String contractorTele = null;
        String contractorEmail = null;
        String contractorAddress = null;

        String customerName = null;
        String customerTele = null;
        String customerEmail = null;
        String customerAddress = null;

        // Creating a string variable for the current date.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = null;
        currentDate = format1.format(date);

        // Using a try-catch block to get the new quantity for a chosen book from the user.
        try {
            // Project Choice Message
            System.out.println("""
                                    
                    ----------------------------------------------------------------------------------------------                                
                                    
                    Insert The Number Or Name Of The Project That You Want To Finalise:
                                                   
                    """);

            id = in.nextInt();

        } catch (Exception e) {

            // Error message
            System.out.println("\nIncorrect Input");
        }

        // Using a try-catch block to update the chosen record with the new quantity details for chosen book.
        try {
            // SQL update statement
            String query = "UPDATE projects SET finalised = 'Yes' WHERE project_number = ? ";

            // Using a PreparedStatement to change the details for the chosen record/ id.
            ps_projects = connection.prepareStatement(query);
            ps_projects.setInt(1, id);

            ps_projects.executeUpdate();  // Executing statement

            System.out.println("\n------------------------\n" +
                    "Record updated successfully");

        } catch (SQLException e) {

            // Error message
            e.printStackTrace();
        }

        // SQL select statement
        String query = "SELECT " +
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
                "WHERE project_number = ?";

        try {
            // Creating and using a PreparedStatement to insert the user's input into the database.
            ps_projects = connection.prepareStatement(query);

            ps_projects.setInt(1, projectNumber);

            // Executing the created statement
            rs = ps_projects.executeQuery();

            while (rs.next()) {    // Position the cursor

                projectNumber = rs.getInt("project_number");
                projectName = rs.getString("project_name");
                deadline = rs.getString("deadline");
                finalised = rs.getString("finalised");
                physicalAddress = rs.getString("physical_address");
                erd = rs.getInt("erd_number");
                buildingType = rs.getString("building_type");
                totalFee = rs.getInt("total_fee");
                totalPaid = rs.getInt("total_paid");

                architectName = rs.getString("architect_name");
                architectTele = rs.getString("architect_telephone");
                architectEmail = rs.getString("architect_email_address");
                architectAddress = rs.getString("architect_address");

                contractorName = rs.getString("contractor_name");
                contractorTele = rs.getString("contractor_telephone");
                contractorEmail = rs.getString("contractor_email_address");
                contractorAddress = rs.getString("contractor_address");

                customerName = rs.getString("customer_name");
                customerTele = rs.getString("customer_telephone");
                customerEmail = rs.getString("customer_email_address");
                customerAddress = rs.getString("customer_address");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to select from database.");
        }
        // ------------------------------- //

        // SQL insert statement
        query = " insert into finalised_projects " +
                "(project_number, project_name, deadline, date_finalised, finalised, " +
                "physical_address, erd_number, building_type, total_fee, total_paid, " +
                "architect_name, architect_telephone, architect_email_address, architect_physical_address, " +
                "contractor_name, contractor_telephone, contractor_email_address, contractor_physical_address, " +
                "customer_name, customer_telephone, customer_email_address, customer_physical_address) " +
                "values (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?,  ?, ?, ?, ?,  ?, ?, ?, ?);";

        try {

            // Creating and using a PreparedStatement to insert the project's data into the database.
            ps_projects = connection.prepareStatement(query);

            ps_projects.setInt(1, projectNumber);
            ps_projects.setString(2, projectName);
            ps_projects.setString(3, deadline);
            ps_projects.setString(4, currentDate);
            ps_projects.setString(5, finalised);
            ps_projects.setString(6, physicalAddress);
            ps_projects.setInt(7, erd);
            ps_projects.setString(8, buildingType);
            ps_projects.setInt(9, totalFee);
            ps_projects.setInt(10, totalPaid);

            ps_projects.setString(11, architectName);
            ps_projects.setString(12, architectTele);
            ps_projects.setString(13, architectEmail);
            ps_projects.setString(14, architectAddress);

            ps_projects.setString(15, contractorName);
            ps_projects.setString(16, contractorTele);
            ps_projects.setString(17, contractorEmail);
            ps_projects.setString(18, contractorAddress);

            ps_projects.setString(19, customerName);
            ps_projects.setString(20, customerTele);
            ps_projects.setString(21, customerEmail);
            ps_projects.setString(22, customerAddress);

            // Executing the created statement
            ps_projects.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to select from database.");
        }
        // If 'projectNumber' is equal to 'choice', then the specified project will be finalised.
        // This means the project will be marked as complete, given a completion date, and will be written to
        // a database table containing completed projects.

//        int amountToPay = 0;
//
//        // If 'totalFee' more than 'totalPaid' (There is money still outstanding), then an invoice will be
//        // printed for the user.
//        if (totalFee > totalPaid) {
//
//            amountToPay = totalFee - totalPaid;  // Calculation
//
//            System.out.println(" ----------------------- " +
//
//                    "\nInvoice Details:" +
//
//                    "\nCustomer Name:\t" + details[17] +
//                    "\nCustomer Telephone Number:\t" + details[18] +
//                    "\nCustomer Email-Address\t" + details[19] +
//                    "\nCustomer Physical-Address\t" + details[20] +
//
//                    "\n\nAmount Still to Pay:\tR" + amountToPay);
//
//
//        }
//
//        // Creating a Date variable to store the current date to mark the date of completion for the project.
//        Date completionDate = new Date();

    }
}

