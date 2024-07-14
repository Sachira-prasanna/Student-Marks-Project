import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Simple program to compute statistics of 'students' marks in an assignment.
 *
 * @author (Sachira Prasanna Marasinghe)
 * @version (Version 01.0 07/12/2024)
 */
public class StudentMarks
{
    // instance variables 
    private double highestMark;
    private double lowestMark;
    private String assignmentName;
    ArrayList<Double> marks = new ArrayList<Double>();
    private Double mean;
    Scanner inputScanner = new Scanner(System.in); // Create a Scanner
    
    private static final double NO_OF_MARKS = 30; // Number of marks need to take as the input 
    private static final double MIN_MARK = 0.0; // Minimum mark that can be taken as the input 
    private static final double MAX_MARK = 30.0; // Maximum mark that can be taken as the input 

    /**
     * Constructor for objects of class StudentMarks
     * 
     * When it creates an object with this constructor, it executes methods call in this constructor
     */
    public StudentMarks()
    {
        getAssignmentName(); // Receive assignment name as the input
        getAssignmentMarks(); // Receive assignment marks as the input
       
        if (marks.size() > 0 ) { // Check whether there are marks entered as input
            printAssignmentMarks(); // Print all the assignment marks
            printLowest(); // Print the lowest student mark
            printHighest(); // Print the highest student mark
            calculateAndPrintMean(); // Calculate and print the mean of the given marks
            calculateAndPrintStandardDeviation(); // Calculate and print the standard deviation of the marks given   
        }   
    }

    /**
     * The getAssignmentName method will receive the name of the assignment as an input 
     *  
     */
    public void getAssignmentName() {    
        while (true) {
            System.out.print("Enter Assignment Name: ");
            this.assignmentName = inputScanner.nextLine(); // Read user input
    
            if (!this.assignmentName.equals("")) {
                // If the assignment name is not empty, break out of the loop
                break;
            } else {
                System.out.println("Invalid input. Assignment name cannot be empty. Please enter the assignment name.");
            }
        }
    }
    
     /**
     * The getAssignmentMarks method will receive 30 student marks as an input
     *  
     */
    public void getAssignmentMarks() {
        
            for (int i=0; i<NO_OF_MARKS; i++) {
                
                try {
                    System.out.print("Enter mark " + (i+1) + ": ");
                    double mark = inputScanner.nextDouble(); // Read user input
                    
                    if (mark <= MIN_MARK || mark >= MAX_MARK) {
                        System.out.println("You have entered (" + mark + ") an invalid mark. Mark should be greater than 0 and less than 30.");
                        i--;
                    } else if (mark > MIN_MARK && mark < MAX_MARK) { // Check whether the marks entered are in the range of 0-30.
                        marks.add(mark);
                    } else {
                        System.out.println("The mark you have entered is invalid.");
                        i--;
                    }
                } catch (InputMismatchException ime) { // Handle error occurred when user enters data type other than double
                    System.out.println("The mark you have entered is invalid. You should enter a number." );
                    inputScanner.nextLine(); // Consume the invalid input
                    i--; // Retry the current iteration
                } catch (Exception ex) {
                    System.out.println("There is an error when getting input " + ex);
                }
            }
        
        inputScanner.close(); 
    }
    
     /**
     * The printHighest method will show the highest mark out of 30 marks which were given as input
     *  
     */
    public void printHighest() {
        
        highestMark = marks.get(0); // Assign the first entered value as the highest mark.
        
        for (int i=0; i<marks.size()-1; i++) {
            if (highestMark < marks.get(i+1)) {
                highestMark = marks.get(i+1);
            } 
        }
        System.out.println("The highest mark is: " + highestMark + ".");
    }

    /**
     * The printLowest method will show the lowest mark out of 30 marks which were given as input
     *  
     */
    public void printLowest() {
        
        lowestMark = marks.get(0); // Assign the first entered value as the lowest mark.
        
        for (int i=0; i<marks.size()-1; i++) {
            
            if (lowestMark > marks.get(i+1)) {
                lowestMark = marks.get(i+1);
            }  
        }
        System.out.println("The lowest mark is: " + lowestMark + ".");
    }
    
    /**
     * The printAssignmentMarks method will show the all the marks which were given as input
     *  
     */
    public void printAssignmentMarks() {
        
        System.out.print(assignmentName + ": " );
        
        for (int i=0; i< marks.size(); i++) {
            System.out.print(" " + marks.get(i));
        }
        System.out.println("");
    }
    
    /**
     * The calculateAndPrintMean method will show the mean value of the given marks
     *  
     */
    public void calculateAndPrintMean() {
        
        double sum = 0.0;
        
        for (int i=0; i<marks.size(); i++) {
            sum = sum + marks.get(i);
        }
        
        mean = sum/marks.size();
        System.out.printf("The mean value is: %.2f.%n", mean);
    }
    
    /**
     * The calculateAndPrintStandardDeviation method will show the standard deviation of the marks given as the input
     *  
     */
    public void calculateAndPrintStandardDeviation() {
        
        Double deviationSum = 0.0;
        
        for (int i=0; i<marks.size(); i++ ) {
            deviationSum = deviationSum + Math.pow((marks.get(i) - mean), 2);
        }
        
        Double sd = Math.sqrt(deviationSum/marks.size());
        System.out.printf("The standard deviation is: %.2f.%n", sd);
        
    }
}
