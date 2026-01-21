import java.util.ArrayList;
import java.util.Scanner;

// =======================
// Player Class
// =======================
class Player {
    private String name;
    private String position;
    private int number;

    public Player(String name, String position, int number) {
        this.name = name;
        this.position = position;
        this.number = number;
    }

    public String getName() { return name; }
    public String getPosition() { return position; }
    public int getNumber() { return number; }

    @Override
    public String toString() {
        return number + " – " + name + " (" + position + ")";
    }
}

// =======================
// Coach Class
// =======================
class Coach {
    private String name;
    private String role;

    public Coach(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() { return name; }
    public String getRole() { return role; }

    @Override
    public String toString() {
        return name + " – " + role;
    }
}

// =======================
// Trainer Class
// =======================
class Trainer {
    private String name;
    private String title;

    public Trainer(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() { return name; }
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return name + " – " + title;
    }
}

// =======================
// Executive Class
// =======================
class Executive {
    private String name;
    private String position;

    public Executive(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() { return name; }
    public String getPosition() { return position; }

    @Override
    public String toString() {
        return name + " – " + position;
    }
}

// =======================
// Raiders Database
// =======================
class RaidersDatabase {

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Coach> coaches = new ArrayList<>();
    private ArrayList<Trainer> trainers = new ArrayList<>();
    private ArrayList<Executive> executives = new ArrayList<>();

    public RaidersDatabase() {
        loadPlayers();
        loadCoaches();
        loadTrainers();
        loadExecutives();
    }

    private void loadPlayers() {
        players.add(new Player("Davante Adams", "WR", 17));
        players.add(new Player("Josh Jacobs", "RB", 28));
        players.add(new Player("Aidan O'Connell", "QB", 12));
        players.add(new Player("Michael Mayer", "TE", 87));
        players.add(new Player("Maxx Crosby", "DE", 98));
    }

    private void loadCoaches() {
        coaches.add(new Coach("Chris Beatty", "Wide Receivers Coach"));
        coaches.add(new Coach("Brennan Carroll", "Run Game Coordinator / Offensive Line"));
        coaches.add(new Coach("Nate Carroll", "Assistant QBs / Game Management"));
        coaches.add(new Coach("Deland McCullough", "Running Backs"));
        coaches.add(new Coach("Greg Olson", "Quarterbacks"));
        coaches.add(new Coach("Joe Philbin", "Senior Offensive Assistant"));
        coaches.add(new Coach("Luke Steckel", "Tight Ends"));
        coaches.add(new Coach("Patrick Graham", "Defensive Coordinator"));
        coaches.add(new Coach("John Glenn", "Linebackers"));
    }

    private void loadTrainers() {
        trainers.add(new Trainer("Chris Cortez", "Head Athletic Trainer"));
        trainers.add(new Trainer("Scott Touchet", "Director of Sports Medicine"));
        trainers.add(new Trainer("Drew Grant", "Physical Therapist / Athletic Trainer"));
        trainers.add(new Trainer("Timothy Harkins", "Physician Assistant / Athletic Trainer"));
        trainers.add(new Trainer("Lemar Mosley", "Athletic Trainer"));
        trainers.add(new Trainer("Rudy Rodriguez Jr.", "Athletic Trainer"));
        trainers.add(new Trainer("Rob Brennan", "Assistant Athletic Trainer"));
        trainers.add(new Trainer("Trevor Hunt", "Assistant Athletic Trainer"));
    }

    private void loadExecutives() {
        executives.add(new Executive("Kristen Banks", "SVP, Marketing"));
        executives.add(new Executive("Justin Carley", "SVP, Chief Legal Officer"));
        executives.add(new Executive("Michael Crome", "SVP, Chief Financial Officer"));
        executives.add(new Executive("Heather DeSanto", "SVP, Human Resources"));
        executives.add(new Executive("Qiava Martinez", "SVP, Chief Sales Officer"));
        executives.add(new Executive("Piper Overstreet‑White", "SVP, Government & Community Relations"));
        executives.add(new Executive("Brad Phinney", "SVP, Chief Experience Officer"));
        executives.add(new Executive("Mark Thewes", "SVP, Football Operations & Strategy"));
        executives.add(new Executive("Brian Stark", "Assistant General Manager"));
    }

    public ArrayList<Player> getPlayers() { return players; }
    public ArrayList<Coach> getCoaches() { return coaches; }
    public ArrayList<Trainer> getTrainers() { return trainers; }
    public ArrayList<Executive> getExecutives() { return executives; }
}

// =======================
// Main Program + Menu
// =======================
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        RaidersDatabase db = new RaidersDatabase();

        while (true) {
            System.out.println("\n=== Las Vegas Raiders Personnel Directory ===");
            System.out.println("1. View Players");
            System.out.println("2. View Coaches");
            System.out.println("3. View Athletic Trainers");
            System.out.println("4. View Executives");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();
            input.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.println("\n--- Players ---");
                    for (Player p : db.getPlayers()) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.println("\n--- Coaches ---");
                    for (Coach c : db.getCoaches()) {
                        System.out.println(c);
                    }
                    break;

                case 3:
                    System.out.println("\n--- Athletic Trainers ---");
                    for (Trainer t : db.getTrainers()) {
                        System.out.println(t);
                    }
                    break;

                case 4:
                    System.out.println("\n--- Executives ---");
                    for (Executive e : db.getExecutives()) {
                        System.out.println(e);
                    }
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
