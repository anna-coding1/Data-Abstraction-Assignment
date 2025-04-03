import java.util.Scanner; // imports the scanner class so that the user's input can be read
import java.util.Date; // imports the date class to handle the dates when the transactions were made

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer("Kayla Wilson", 84052, 5000.0, 400.0);
        Scanner scan = new Scanner(System.in); // creates a scan object to hold user input
        String choice; // this is the variable for the user's choice
        String accountType; // this is the variable for the account type


        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("d to deposit and see the deposits"); // this is the choice to deposit money and view deposits
            System.out.println("w to withdraw and see the withdrawals"); // this is the choice to withdraw money and view withdrawals
            System.out.println("q to quit");  // q to quit the program

            System.out.print("Enter your choice - d, w, or q? "); // asks the user to enter their choice
            choice = scan.next().toLowerCase();

            if (choice.equals("q")) { // checks if the user selected 'exit'
                System.out.println("You have now exited. Thank you!"); // this message comes when the user presses q
                break; // ends the program
            }

            switch (choice) {
                case "d":
                    accountType = getAccount(scan); // gets valid account type input
                    System.out.print("Enter amount to deposit: "); // asks the user to enter deposit amount
                    double depositAmount = scan.nextDouble(); // stores the deposit amount entered by the user
                    customer.deposit(depositAmount, new Date(), accountType); // deposits into the account
                    System.out.println("Here are all the deposits:\n");
                    customer.displayDeposits(); // displays all deposits into the checking and savings account
                    break;

              case "w":
                    accountType = getAccount(scan); // gets valid account type input
                    System.out.print("Enter amount to withdraw: "); // asks the user to enter withdrawal amount
                    double withdrawAmount = scan.nextDouble(); // stores the withdrawal amount entered by the user
                    customer.withdraw(withdrawAmount, new Date(), accountType); // when the customer attempts to withdraw, overdraft protection is enforced
                    System.out.println("Here are all the withdrawals:\n");
                    customer.displayWithdraws(); // displays all withdrawals from the checking and savings account
                    break;

                default:
                    System.out.println("Your input is invalid."); // this message will come up if the input is invalid
            }
        }

        scan.close(); // closes the scanner
    }

    private static String getAccount(Scanner scanner) {
        String accountType;
        while (true) {
            System.out.print("Enter account type (c for checking or s for saving): "); // asks the user to enter the account type
            accountType = scanner.next().toLowerCase();
            if (accountType.equals("c")) { // checks if the input is c
                return Customer.CHECKING;
            } else if (accountType.equals("s")) { // checks if the input is s
                return Customer.SAVING;
            } else {
                System.out.println("Your input is invalid. Please enter 'c' or 's'."); // this message will come up if the input is invalid
            }
        }
    }
}
