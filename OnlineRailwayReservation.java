import java.util.*;


class Train {
    int trainNumber;
    String trainName;
    int totalSeats;
    int availableSeats;

    public Train(int trainNumber, String trainName, int totalSeats) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public void displayTrainInfo() {
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Train Name: " + trainName);
        System.out.println("Available Seats: " + availableSeats + "/" + totalSeats);
    }

    public boolean bookSeats(int numSeats) {
        if (numSeats <= availableSeats) {
            availableSeats -= numSeats;
            return true;
        } else {
            System.out.println("Not enough seats available.");
            return false;
        }
    }

    public void cancelSeats(int numSeats) {
        availableSeats += numSeats;
        System.out.println("Cancellation successful. " + numSeats + " seats added back.");
    }
}

class USer {
    String username;
    String password;
    Map<Integer, Integer> reservations; // Key: Train Number, Value: Number of Seats

    public USer(String username, String password) {
        this.username = username;
        this.password = password;
        this.reservations = new HashMap<>();
    }

    public void displayUserInfo() {
        System.out.println("USer: " + username);
        System.out.println("Reservations: " + reservations);
    }
}

class ReservationSystem {
    ArrayList<Train> trains;
    ArrayList<USer> users;
    USer currentUser;

    public ReservationSystem() {
        this.trains = new ArrayList<>();
        this.users = new ArrayList<>();
        this.currentUser = null;
    }

    public void addTrain(Train train) {
        trains.add(train);
    }

    public void addUser(USer user) {
        users.add(user);
    }

    public USer login(String username, String password) {
        for (USer user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                currentUser = user;
                return user;
            }
        }
        return null;
    }

    public void displayAllTrains() {
        System.out.println("Available Trains:");
        for (Train train : trains) {
            train.displayTrainInfo();
            System.out.println("------------------------------");
        }
    }

    public void makeReservation() {
        if (currentUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        displayAllTrains();

        System.out.print("Enter the train number you want to book: ");
        int selectedTrainNumber = scanner.nextInt();

        System.out.print("Enter the number of seats you want to book: ");
        int numSeats = scanner.nextInt();

        for (Train train : trains) {
            if (train.trainNumber == selectedTrainNumber) {
                if (train.bookSeats(numSeats)) {
                    currentUser.reservations.put(selectedTrainNumber, numSeats);
                    System.out.println("Reservation successful!");
                    currentUser.displayUserInfo();
                } else {
                    System.out.println("Reservation failed.");
                }
                return;
            }
        }

        System.out.println("Invalid train number. Reservation failed.");
    }

    public void cancelReservation() {
        if (currentUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        currentUser.displayUserInfo();

        System.out.print("Enter the train number you want to cancel reservation for: ");
        int trainNumberToCancel = scanner.nextInt();

        if (currentUser.reservations.containsKey(trainNumberToCancel)) {
            int numSeatsToCancel = currentUser.reservations.get(trainNumberToCancel);
            for (Train train : trains) {
                if (train.trainNumber == trainNumberToCancel) {
                    train.cancelSeats(numSeatsToCancel);
                    currentUser.reservations.remove(trainNumberToCancel);
                    System.out.println("Cancellation successful!");
                    currentUser.displayUserInfo();
                    return;
                }
            }
        } else {
            System.out.println("No reservation found for the given train number.");
        }
    }
}

public class OnlineRailwayReservation {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();

        // Adding sample trains
        reservationSystem.addTrain(new Train(101, "Express", 50));
        reservationSystem.addTrain(new Train(102, "Local", 30));

        // Adding sample users
        reservationSystem.addUser(new USer("user1", "password1"));
        reservationSystem.addUser(new USer("user2", "password2"));

        // Login
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();

        USer loggedInUser = reservationSystem.login(enteredUsername, enteredPassword);

        if (loggedInUser != null) {
            System.out.println("Login successful.");

            // Making a reservation
            reservationSystem.makeReservation();

            // Cancelling a reservation
            reservationSystem.cancelReservation();
        } else {
            System.out.println("Invalid credentials. Exiting the program.");
        }
    }
}
