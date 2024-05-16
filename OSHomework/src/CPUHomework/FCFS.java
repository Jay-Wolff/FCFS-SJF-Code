package CPUHomework;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class FCFS implements Algorithm {
	private List<OneSchedule> tasks;

	// constructor used to accept a list of tasks
	public FCFS(List<OneSchedule> tasks) {
		this.tasks = tasks;
	}

	@Override
	public void schedule() {
		// sort tasks based on their Arrival Time (ascending order)
		Collections.sort(tasks, Comparator.comparingInt(OneSchedule::getArrivalTime));

		// add caluculations:
		int currentTime = 0;
		int WT; // waiting time
		int RT; // response time
		int TT; // turn around time
		int CT; // completion time
		int numTasks = tasks.size(); // used to calculate the averages
		// total
		double totalWT = 0.0;
		double totalRT = 0.0;
		double totalTT = 0.0;

		for (OneSchedule task : tasks) {
			// Update the current time to reflect the task completion
			currentTime = Math.max(currentTime, task.getArrivalTime()) + task.getCpuBurst();

			// Turn around time = Completed time - Arrival time (can also be waiting time +
			// the burst)
			TT = Math.max(0, (currentTime - task.getArrivalTime()));
			// System.out.println("\nct = " + currentTime);
			// System.out.println("at = " + task.getArrivalTime());
			// System.out.println("diff = " + (currentTime - task.getArrivalTime()) + '\n');

			// waiting time = Completed time - arrival time
			WT = Math.max(0, TT - task.getCpuBurst());

			// Response Time is the same as Waiting Time in FCFS
			RT = Math.max(0, ((currentTime - task.getCpuBurst()) - task.getArrivalTime()));

			// calculate the total times
			totalWT += WT;
			totalRT += RT;
			totalTT += TT;

			// Print results for this task
			System.out.printf(
					"Task: %s, Arrival Time: %d, Burst Time: %d, Waiting Time: %d, Response Time: %d, Turnaround Time: %d, Completion Time: %d\n",
					task.getName(), task.getArrivalTime(), task.getCpuBurst(), WT, RT, TT, currentTime);
		}

		double avgWT = totalWT / numTasks;
		double avgRT = totalRT / numTasks;
		double avgTT = totalTT / numTasks;

		System.out.printf("Average Waiting Time: %.2f\n", avgWT);
		System.out.printf("Average Response Time: %.2f\n", avgRT);
		System.out.printf("Average Turnaround Time: %.2f\n", avgTT);

		System.out.println("List of the tasks in the order that they come in: ");
		for (OneSchedule task : tasks) {
			System.out.print(task + ",");
		}

	}
}
