package p2.ts;

import p2.interfaces.SimulatorInterface;
import java.util.Scanner;

public class TextUI {
    private SimulatorInterface simulator;

    public TextUI(SimulatorInterface simulator) {
        this.simulator = simulator;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nTrain System Simulator");
            System.out.println("1. Show Events");
            System.out.println("2. Validate Logs");
            System.out.println("3. Start Simulation");
            System.out.println("4. Stop Simulation");
            System.out.println("5. Add Train");
            System.out.println("6. Remove Train");
            System.out.println("7. Display System Status");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        
                        break;
                    case 2:
                  
                        break;
                    case 3:
                        simulator.simulate();
                        System.out.println("Simulation started.");
                        break;
                    case 4:
                        simulator.isFinished();
                        System.out.println("Simulation stopped.");
                        break;
                    case 5:
                       
                        break;
                    case 6:
                   
                        break;
                    case 7:
                        System.out.println("Current System Status: " + simulator.validate());
                        break;
                    case 0:
                        System.out.println("Exiting system...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 0 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        scanner.close();
    }
}
