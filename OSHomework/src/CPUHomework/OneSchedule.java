package CPUHomework;

//represents a single task in the schedule
public class OneSchedule {
//required values in the assignment 
 private String name; // references T1, T2, etc.
 private int arrivalTime; // first number
 private int cpuBurst; // second number

 // constructor to initialize the required variables
 public OneSchedule(String name, int arrivalTime, int cpuBurst) {
     this.name = name;
     this.arrivalTime = arrivalTime;
     this.cpuBurst = cpuBurst;
 }

 // getters ARE NECESSARY to retrieve the variables from the schedule.txt
 public String getName() {
     return name;
 }
 public int getArrivalTime() {
     return arrivalTime;
 }
 public int getCpuBurst() {
     return cpuBurst;
 }
 @Override
 public String toString() {
	 return String.format("%s", name);
    // return String.format("%s (Arrival: %d, CPU Burst: %d)", name, arrivalTime, cpuBurst);
 }
}
