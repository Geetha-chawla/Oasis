import java.util.*;
public class ATMInterface {
    public static void main(String[] args) {
        System.out.println("Welcome to our ATM");
        Scanner inp=new Scanner(System.in);
        System.out.print("Enter the UserName : " );
        String name=inp.nextLine();
        System.out.print("Enter the Pin No : " );
        int n=inp.nextInt();
        int pin=123456;
        int balance=1000;
        int choose=0;
        if(n==pin){
            while(true){
                System.out.println("WELCOME " + name);
                System.out.println("1.Withdraw");
                System.out.println("2.Deposit");
                System.out.println("3.Transfer");
                System.out.println("4.Exit");
                choose=inp.nextInt();
                switch (choose){
                    case 1:
                        System.out.print("Enter your Withdrawal Amount : ");
                        int amount=inp.nextInt();
                        if(amount>=balance){
                            System.out.println("Insufficient Funds");
                            System.out.println("Balance : "+balance);
                        }else if (amount<=balance){
                            System.out.println("Balance: " + (balance-amount));
                            balance=balance-amount;
                        }
                        System.out.println("<--------------------------------------------------->");
                        break;
                    case 2:
                        System.out.print("How much amount you want to deposit : ");
                        int deposit=inp.nextInt();
                        System.out.println("your bank balance after deposit " + (deposit+balance) );
                        balance=deposit+balance;
                        System.out.println("<--------------------------------------------------->");
                        break;
                    case 3:
                        System.out.print("How much amount you want to Transfer : ");
                        int sendamount=inp.nextInt();
                        System.out.print("Enter the Pin no: ");
                        int sendpin=inp.nextInt();
                        if(sendpin==pin) {
                            if (sendamount >= balance) {
                                System.out.println("Insufficient Funds");
                            } else if (sendamount <= balance) {
                                System.out.println("After transaction you have : " + (balance - sendamount));
                                balance = balance - sendamount;
                            }
                        }
                        System.out.println("<--------------------------------------------------->");
                        break;
                    case 4:
                        System.out.println("THANK YOU");
                        System.exit(0);
                }
            }
        }else{
            System.out.println("Invalid Username or PinNo. Please try again");
        }
    }
}