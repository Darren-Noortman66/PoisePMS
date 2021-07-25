// Poised Management System

// Importing the needed packages

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * <h1>Poised</h1>
 * <p>This is the <b>'main'</b> task file. All code will be executed in the main-driver within the <b>'Poised'</b> class.</p>
 *
 * @author Darren Noortman
 * @version 1.0
 */
public class Poised {

    /**
     * This is the main method which makes use of addNum method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {

        // Creating an array to store the user's input.
        // An 'ArrayList' is used because the size of the list is not known and an ArrayList is resizable.
        ArrayList<Project> projects = new ArrayList<>();

        Scanner input = new Scanner(System.in);  // Creating instance for Scanner package.

        // Printing option list
        System.out.println("""
                                
                ----------------------------------------------------------------------------------------------                                
                                
                Insert your Choice:
                1. Add a Project
                2. Update Deadline
                3. Update Fees
                4. Update Contractor details
                5. Display All Projects
                6. Finalise a Project
                7. View List of Incomplete Projects
                8. View List of Projects Past Due-Date
                9. Exit
                                                
                """);

        int choice = input.nextInt();  // Requesting user's choice

        // Using a while-loop to keep the program running until the user is finished/ inserts 9.
        while (choice != 9) {

            // Each user-defined method is passed the ArrayList value, 'projects'
            // which will be used to work with our created data.
            if (choice == 1) {
                UpdateProject.createProject(projects);
            } else if (choice == 2) {
                UpdateProject.updateDeadline();
            } else if (choice == 3) {
                UpdateProject.updateFee();
            } else if (choice == 4) {
                UpdateProject.updateContractor();
            } else if (choice == 5) {
                Functions.viewAllProjects();
            } else if (choice == 6) {
                UpdateProject.finaliseProject();
            } else if (choice == 7) {
                Functions.viewIncompleteProjects();
            } else if (choice == 8) {
                Functions.viewPastDue();
            } else {
                System.out.println("Invalid Input");
            }

            System.out.println("""
                                            
                    ----------------------------------------------------------------------------------------------                                
                                                        
                    Insert your Choice:
                    1. Add a Project
                    2. Update Deadline
                    3. Update Fees
                    4. Update Contractor details
                    5. Display All Projects
                    6. Finalise a Project
                    7. View List of Incomplete Projects
                    8. View List of Projects Past Due-Date
                    9. Exit

                    """);

            choice = input.nextInt();  // Requesting user's choice
        }

    }
}
/*

References:

- HyperionDev (2021). SE L2T21 - Capstone II - Refactoring - Task 21. Retrieved 8 June 2021,
  from Dropbox/ Darren Noortman/ Task 21/ SE L2T21 - Capstone II - Refactoring.pdf

- Previous tasks on level 2 of my course.


https://towardsdatascience.com/data-analysis-in-mysql-operators-joins-and-more-in-relational-databases-26c0a968e61e

http://www-db.deis.unibo.it/courses/TW/DOCS/w3schools/sql/sql_func_now.asp.html

https://www.edureka.co/blog/prime-number-program-in-java/
*/