import java.util.*;

class Question {
    String questionText;
    ArrayList<String> options;
    int correctOption;

    public Question(String questionText, ArrayList<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}

class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
}

public class OnlineExamSystem {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    private ArrayList<Question> questions;
    private User currentUser;

    public OnlineExamSystem() {
        this.questions = new ArrayList<>();
        this.currentUser = null;
    }

    public void addQuestion(String questionText, ArrayList<String> options, int correctOption) {
        Question question = new Question(questionText, options, correctOption);
        questions.add(question);
    }

    public boolean login(String username, String password) {
        User user = new User(USERNAME, PASSWORD);
        if (user.authenticate(username, password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public void startExam() {
        if (currentUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);

            System.out.println("Question " + (i + 1) + ": " + question.questionText);

            if (question.options.size() > 1) {
                for (int j = 0; j < question.options.size(); j++) {
                    System.out.println((j + 1) + ". " + question.options.get(j));
                }
                System.out.print("Your answer: ");
                int selectedOption = scanner.nextInt();

                if (question.isCorrect(selectedOption)) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer is: " + question.correctOption + "\n");
                }
            } else {
                System.out.println("True or False?");
                System.out.print("Your answer (1 for True, 2 for False): ");
                int selectedOption = scanner.nextInt();

                if (question.isCorrect(selectedOption)) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer is: " + (question.correctOption == 1 ? "True" : "False") + "\n");
                }
            }
        }

        System.out.println("Your final score is: " + score + " out of " + questions.size());
    }

    public static void main(String[] args) {
        OnlineExamSystem examSystem = new OnlineExamSystem();

        // Adding sample questions
        examSystem.addQuestion("What is the capital of France?", new ArrayList<>(List.of("Berlin", "Paris", "London")), 2);
        examSystem.addQuestion("What is 2 + 2?", new ArrayList<>(List.of("3", "4", "5")), 2);
        examSystem.addQuestion("Which planet is known as the Red Planet?", new ArrayList<>(List.of("Earth", "Mars", "Venus")), 2);
        examSystem.addQuestion("Java is a programming language. (True/False)", new ArrayList<>(List.of("True", "False")), 1);

        // Login
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();

        if (examSystem.login(enteredUsername, enteredPassword)) {
            System.out.println("Login successful. Starting the exam...\n");
            examSystem.startExam();
        } else {
            System.out.println("Invalid credentials. Exiting the program.");
        }
    }
}
