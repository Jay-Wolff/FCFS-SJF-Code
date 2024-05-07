package CPUHomework;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader; // needed to read the input of the file
import java.io.FileReader; // allows to read the content of the file
import java.io.IOException; //handles file read error
import java.util.Scanner;

// Driver class to execute the selected scheduling algorithm
public class Driver {
    public static void main(String[] args) {
    	 String algorithmName;
         String scheduleFile;

         // Create a Scanner object for user input
         Scanner scanner = new Scanner(System.in);
    	  
          
        if (args.length == 2) { // used to make sure it takes in the algo name and the sched file
             algorithmName = args[0]; // for the fcfs we write in terminal filling in the <algorithm>
             scheduleFile = args[1]; // used for the <sechdule.txt>
            
        }else {
                // Prompt the user for the algorithm name
                System.out.print("Enter the scheduling algorithm (FCFS/SJF): "); // java CPUHomework.Driver FCFS schedule.txt
                algorithmName = scanner.nextLine().trim().toLowerCase();
                System.out.print("name of the file: "); // java CPUHomework.Driver FCFS schedule.txt
                scheduleFile = scanner.nextLine().trim().toLowerCase();
                //scheduleFile = "schedule.txt";
          }
        	
       	scanner.close();

    	// List to hold the tasks        
        List<OneSchedule> tasks = new ArrayList<>(); //list to take in te parsed tasks

        // read tasks from schedule file using the buffer import
        try (BufferedReader reader = new BufferedReader(new FileReader(scheduleFile))) {
            String line;
            while ((line = reader.readLine()) != null) { //reading each line of the txt
                String[] parts = line.split(",\\s*"); //states it is split by commas 
                if (parts.length != 3) continue;
                //create object for the oneschedule which handles the line of code for fcfs or sjf
                tasks.add(new OneSchedule(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
            }
        } catch (IOException e) { //if there is a problem we catch and outprit the error
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // initializes the scheduling algorithm
        Algorithm scheduler;
        switch (algorithmName.toLowerCase()) {
            case "fcfs":
                scheduler = new FCFS(tasks);
                break;
            case "sjf":
                scheduler = new SJF(tasks);
                break;
            default:
                System.out.println("Unsupported algorithm: " + algorithmName);
                return;
        }

        // schedule tasks using the chosen algorithm with there predecer file
        scheduler.schedule();
    }
}
