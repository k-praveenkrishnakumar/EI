class Database {
    private static Database instance;

    private Database() {
        // private constructor to prevent instantiation
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Connecting to the database...");
    }
}

public class Usecase3 {
    public static void main(String[] args) {
        Database db1 = Database.getInstance();
        Database db2 = Database.getInstance();

        System.out.println(db1 == db2);  // Output: true

        db1.connect();
        db2.connect();
    }
}