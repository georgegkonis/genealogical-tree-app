import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author George Gkonis
 * @version 1.0
 * <p>
 * This class represents the application that manages the genealogical tree. It contains the main method of the
 * application and the methods that handle the interface for the user to interact with the application.
 */
public class GenealogicalTreeApp {

    /**
     * The main method of the application. Creates an instance of the {@link GenealogicalTreeApp} class and calls the method
     * {@link GenealogicalTreeApp#showOptions()} to print the options to the user.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        GenealogicalTreeApp app = new GenealogicalTreeApp();
        app.showOptions();
    }

    /**
     * The genealogical tree.
     */
    private final GenealogicalTree tree = new GenealogicalTree();

    /**
     * The scanner used to read input from the user.
     */
    private final Scanner inputScanner = new Scanner(System.in);

    /**
     * Prints the options to the user and calls the appropriate method based on the user's selection.
     */
    private void showOptions() {
        System.out.println("""
                [1] Read the file and save the data to the tree.
                [2] Save the people in the tree to a file, in alphabetical order.
                [3] Find the relation one person has to another.
                [0] Exit the program.""");

        while (true) {
            try {
                System.out.print("\nSelect an option: ");
                int selection = Integer.parseInt(inputScanner.nextLine());
                switch (selection) {
                    case 1 -> loadTreeFromFile();
                    case 2 -> savePeopleInOrderToFile();
                    case 3 -> findRelation();
                    case 0 -> exitTheProgram();
                    default -> throw new InvalidInputException();
                }
            } catch (NumberFormatException | InvalidInputException e) {
                System.out.println("\nInvalid input. Please enter a number between 0 and 3.");
            }
        }
    }

    /**
     * Reads the data from the file and saves it to the tree.
     */
    private void loadTreeFromFile() {
        try {
            System.out.print("\nPlease enter the path of the file: ");
            String filepath = inputScanner.nextLine();
            File file = new File(filepath);
            Scanner fileScanner = new Scanner(file);
            List<String[]> peopleToAdd = new ArrayList<>();
            List<String[]> relationsToAdd = new ArrayList<>();

            while (fileScanner.hasNext()) {
                String[] row = fileScanner.nextLine().split(",");
                switch (row.length) {
                    case 2 -> peopleToAdd.add(row);
                    case 3 -> relationsToAdd.add(row);
                    default -> throw new RuntimeException("Invalid row length");
                }
            }
            for (String[] row : peopleToAdd) {
                String name = row[0];
                Gender gender = Gender.valueOf(row[1].toUpperCase());
                Person person = new Person(name, gender);
                tree.addPerson(person);
            }
            for (String[] row : relationsToAdd) {
                String name1 = row[0];
                String name2 = row[2];
                Relation relation = Relation.valueOf(row[1].toUpperCase());
                tree.addRelation(name1, name2, relation);
            }
            System.out.println("\nGenealogical tree successfully loaded from file.");
        } catch (FileNotFoundException | PersonNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Saves the people in the tree to a file, in alphabetical order.
     */
    private void savePeopleInOrderToFile() {
        System.out.print("\nPlease enter the name of the file: ");
        String filename = inputScanner.nextLine();
        File file = new File(filename);
        
        List<Person> peopleInOrder = tree.getPeopleInOrder();
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Person person : peopleInOrder) fileWriter.write(person + "\n");
            fileWriter.close();
            System.out.println("\nPeople successfully saved to file in alphabetical order.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Finds the relation one person has with another person.
     */
    private void findRelation() {
        System.out.print("\nEnter 2 names separated by comma: ");
        String[] names = inputScanner.nextLine().split(",");
        try {
            if (names.length != 2) throw new InvalidInputException("Please enter 2 names separated by comma.");
            String name1 = names[0];
            String name2 = names[1];
            Relation relation = tree.findRelation(name1, name2);
            System.out.println("\n" + name1 + " is " + relation + " to " + name2);
        } catch (PersonNotFoundException | InvalidInputException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    /**
     * Exits the program.
     */
    private void exitTheProgram() {
        System.out.println("\nExiting the program...");
        System.exit(0);
    }
}
