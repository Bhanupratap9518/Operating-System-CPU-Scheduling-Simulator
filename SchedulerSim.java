import java.io.IOException;
import java.util.Scanner;

public class SchedulerSim {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int repeat = 1;

        while (repeat == 1) {

            // Step 1: Read the file
            ProcessReader pr = new ProcessReader();
            Process[] pa = pr.readProcesses("Processes.txt");

            System.out.println("\n================ CPU Scheduling Simulator ================");
            System.out.println("Select the Scheduling Algorithm:");
            System.out.println("1. FCFS (First Come First Serve)");
            System.out.println("2. SRTF (Shortest Remaining Time First)");
            System.out.println("3. Priority Scheduling");
            System.out.println("4. Round Robin");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            Scheduler scheduler = null;

            switch (choice) {
                case 1:
                    scheduler = new FCFS(pa);
                    break;

                case 2:
                    scheduler = new SRTF(pa);
                    break;

                case 3:
                    scheduler = new Priority_Scheduling(pa);
                    break;

                case 4:
                    System.out.print("Enter Time Quantum: ");
                    int tq = sc.nextInt();
                    scheduler = new RR(pa, tq);
                    break;

                default:
                    System.out.println("Invalid choice! Defaulting to FCFS.");
                    scheduler = new FCFS(pa);
            }

            // Step 2: Run the scheduler
            System.out.println("\nRunning Scheduler...\n");
            scheduler.runScheduler();

            // Step 3: Print Results
            System.out.println("Average wait time = " + scheduler.calculateAverageWaitTime());
            System.out.println("Average turnaround time = " + scheduler.calculateAverageTurnaroundTime());
            System.out.println("Total Run time = " + scheduler.getRuntime());

            // Step 4: Ask user to run again
            System.out.println("\nDo you want to run again?");
            System.out.println("1. Yes");
            System.out.println("0. No (Exit)");
            System.out.print("Enter choice: ");

            repeat = sc.nextInt();
        }

        System.out.println("\nExiting CPU Scheduling Simulator. Goodbye!");
    }
}
