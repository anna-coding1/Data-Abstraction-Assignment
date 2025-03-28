import java.util.Date; // the Date class is imported to handle the dates when the transactions were done

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer("Kayla Wilson", 84052, 5000.0, 400.0); // creates a customer object with information about the customer and their transactions


        System.out.println("Depositing in the checking account.\n");
        customer.deposit(100.0, new Date(), Customer.CHECKING); // deposits $100 into checking account
        customer.deposit(-75.0, new Date(), Customer.CHECKING); // this is a test to see whether negative deposits can be handled properly


        System.out.println("Depositing in the savings account.\n");
        customer.deposit(200.0, new Date(), Customer.SAVING);// deposits $200 into saving account
        customer.deposit(0.0, new Date(), Customer.SAVING); // this is a test to see whether a deposit of 0 can be handled properly



        System.out.println("Withdrawing from the checking account.\n");
        customer.withdraw(200.0, new Date(), Customer.CHECKING); // withdraws $200 from checking account
        customer.withdraw(1500.0, new Date(), Customer.CHECKING); // this is a test to see whether overdrafts can be handled properly



        System.out.println("Withdrawing from the savings account.\n");
        customer.withdraw(200.0, new Date(), Customer.SAVING); // withdraws $200 from saving account
        customer.withdraw(750.0, new Date(), Customer.SAVING); // this is a test to see whether overdrafts can be handled properly


        System.out.println("Here are all the deposits:\n");
        customer.displayDeposits(); // displays all deposits into checking and savings account

        System.out.println("Here are all the withdrawals:\n");
        customer.displayWithdraws(); // displays all withdrawals from checking and savings account
    }
}