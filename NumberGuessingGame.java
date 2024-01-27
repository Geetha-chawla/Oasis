import java.util.Scanner;
public class NumberGuessingGame {
    public static void main(String[] args) {
        int MIN=1;
        int MAX=100;
        int ATTEMPTS=10;
        int totalScore=10;
        int attempts=0;
        Scanner inp=new Scanner(System.in);
        int guessno=(int)(Math.random()*(MAX));
        System.out.println("Number Guessing Game Started....");
        System.out.println("Instructions : ");
        System.out.println("You have only 10 Attempts...");
        System.out.println("When you successfull completed each round... You will be moved to next round...");
        System.out.println("Each Round You're Score Start with 10. You loss the each attempt, then the score is also minus...");
        System.out.println("-------------------------Start-------------------------");
        for(int i=1;i<=ATTEMPTS;i++){
            attempts+=1;
            System.out.print("You're " +i+"th Attempt Start : ");
            int userno= inp.nextInt();
            if(userno==guessno) {
                System.out.println("You're Successfully Completed this Round....");
                System.out.println("You're Score is : " + totalScore);
                MAX+=100;
                System.out.println("Congratulations!!! You're moving to next Round ");
                System.out.println("Range between "+MIN+" to "+MAX);
                i=0;
                attempts=10;
                totalScore=10;
                guessno=(int)(Math.random()*(MAX));
            } else if (userno>guessno) {
                System.out.println("Wrong Answer : Try Lower Number.....");
                System.out.println("Try Again!!!");
            } else if (userno<guessno) {
                System.out.println("Wrong Answer : Try Higher Number.....");
                System.out.println("Try Again!!!");
            }
            totalScore-=1;
        }
        if (ATTEMPTS==attempts) {
            System.out.println("The number is : "+guessno);
            System.out.println("Sorry!!! Game Over");
        }
    }
}