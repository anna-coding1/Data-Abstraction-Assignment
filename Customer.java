//import javax.security.sasl.SaslClient;
import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits;
    private ArrayList<Withdraw> withdraws;
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    Customer(){
        //create default constructor
        // Requires: nothing
        // Modifies: properties of the customer class (i.e. name, accountNumber)
        // Effects: initializes a customer object with start values for all the properties
        this.name = ""; // initializes the name to an empty string
        this.accountNumber = 0;// sets the account number to 0 initially
        this.checkBalance = 0.0; // initializes the check balance to 0
        this.savingBalance = 0.0; // initializes the saving balance to 0
        this.savingRate = 0.0; // sets the saving rate to 0
        this.deposits = new ArrayList<>(); // creates an empty array to store information on the deposit
        this.withdraws = new ArrayList<>(); // creates an empty array to store information on withdrawal
    }
    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit){
        //constructor code here
        // Requires: A name, an account number, and initial deposits for checking and saving accounts.
        // Modifies: The properties of the Customer class.
        // Effects: Initializes a Customer object with the given values and start values for other properties.
        this.name = name; // assigns the given name to the customer
        this.accountNumber = accountNumber; // assigns the account number to the customer
        this.checkBalance = checkDeposit; // sets the initial account balance for checking account which is equal to the deposit
        this.savingBalance = savingDeposit; // sets the initial account balance for saving account which is equal to the deposit
        this.savingRate = 0.0; // initializes the saving rate to 0
        this.deposits = new ArrayList<>(); // creates an empty array to store information on the deposit
        this.withdraws = new ArrayList<>(); // creates an empty array to store information on withdrawal
    }

    public double deposit(double amt, Date date, String account){
        //your code here
        // Requires: A deposit amount (must be positive), a valid account type ("checking" or "saving") and a date.
        // Modifies: Adds the deposit to the list and updates the account's balance.
        // Effects: Records a deposit and returns the new balance.
        if (amt <= 0) { // makes sure that the amount being deposited is not less than 0
            System.out.println("The deposit amount cannot be less than zero.");
            if (account.equals(CHECKING)) { // makes sure to return the current balance of the account
                return checkBalance;
            } else {
                return savingBalance;
            }
        }

        Deposit newDeposit = new Deposit(amt, date, account); // creates a new deposit transaction
        deposits.add(newDeposit); // adds the deposit to the other deposits

        if (account.equals(CHECKING)) { // checks if the deposit is for the checking account
            checkBalance += amt; // if it is, the balance is added to the checking account
            System.out.println("The deposit was successful. Your current balance is $" + checkBalance); // prints out the balance after the deposit
            return checkBalance;
        } else if (account.equals(SAVING)) { // checks if the deposit is for the savings account
            savingBalance += amt; // if it is, the balance is added to the savings account
            System.out.println("Your deposit was successful. Your current balance in saving is: $" + savingBalance); // prints out the balance after the deposit
            return savingBalance;
        } else {
            System.out.println("The account is invalid."); // if the account is invalid, then this is printed to the console
            if (account.equals(CHECKING)) { // returns the original checking account balance
                return checkBalance;
            } else {
                return savingBalance; // if it was a savings account, the balance is returned fot the savings account
            }
        }

    }
    public double withdraw(double amt, Date date, String account){
        //your code here
        // Requires: Requires: A withdrawal amount (must be positive), a valid account type ("checking" or "saving") and a date.
        // Modifies: Adds the withdrawal to the list and updates the account's balance, and makes sure it doesn't exceed the overdraft limit.
        // Effects: Records a withdrawal transaction and returns the updated balance of the specified account.
        if (amt <= 0) { // makes sure that the amount being withdrawn is not less than 0
            System.out.println("The withdrawal amount cannot be less than zero.");
            if (account.equals(CHECKING)) { // makes sure to return the current balance of the account
                return checkBalance;
            } else {
                return savingBalance;
            }
        }

        if (!checkOverdraft(amt, account)) { // checks if the amount being withdrawn is more than what is in the account
            System.out.println("Overdraft limit exceeded."); // lets the user know that there is an overdraft
            if (account.equals(CHECKING)) {
                System.out.println("Your current checking balance is $" + checkBalance + ". The overdraft limit: $" + (OVERDRAFT + checkBalance)); // Inform user of checking overdraft
                return checkBalance;

                //
            } else if (account.equals(SAVING)) { // checks if the amount being withdrawn is more than what is in the account
                System.out.println("Your current savings balance is $" + savingBalance + ". Savings overdraft limit: $" + (OVERDRAFT + savingBalance)); // lets the user know that there is an overdraft
                return savingBalance;
            }
        }

        Withdraw newWithdraw = new Withdraw(amt, date, account); // creates a new withdrawal transaction
        withdraws.add(newWithdraw); // adds the withdrawal to the other withdrawals

        if (account.equals(CHECKING)) { // checks if the withdrawal is from the checking account
            checkBalance -= amt; // if it is, the balance is subtracted from the total balance of the checking account
            System.out.println("Your withdrawal was successful. The current balance of your checking account is $" + checkBalance + " Overdraft limit is " + OVERDRAFT); // prints out the balance after the withdrawal
            return checkBalance;
        } else if (account.equals(SAVING)) { // checks if the withdrawal is from the saving account
            savingBalance -= amt; // if it is, the balance is subtracted from the total balance of the saving account
            System.out.println("Your withdrawal was successful. The current balance of your saving account is $" + savingBalance +" Overdraft limit is " + OVERDRAFT); // prints out the balance after the withdrawal
            return savingBalance;
        } else {
            System.out.println("The account is invalid"); // if the account is invalid, then this is printed to the console
            if (account.equals(CHECKING)) { // returns the original checking account balance
                return checkBalance;
            } else {
                return savingBalance; // if it was a savings account, the balance is returned fot the savings account
            }
        }

    }
    private boolean checkOverdraft(double amt, String account){
        //your code here
        // Requires: A withdrawal amount and a valid account type.
        // Modifies: nothing.
        // Effects: Checks if the withdrawal amount exceeds the withdrawal limit for the account and returns true if the transaction is valid.
        if(account.equals(CHECKING)) { // checks if the account is a checking account
            return(checkBalance-amt) >=OVERDRAFT; // makes sure the amount being withdrawn is not more than what is in the account
        } else if (account.equals(SAVING)) { // checks if the account is a savings account
            return(savingBalance-amt) >=OVERDRAFT; // makes sure the amount being withdrawn is not more than what is in the account
        } else {
            System.out.print("This account is invalid."); // if the account is invalid, this is printed to the console
            return false;
        }

    }
    //do not modify
    public void displayDeposits(){
        for(Deposit d : deposits){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(Withdraw w : withdraws){
            System.out.println(w);
        }
    }

}
