package connect;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Database {
    // This is a singleton object that represents the Database connection
    private static Database database;
    private FileOutputStream dbConnection;
    
    private Database(String filename) throws FileNotFoundException{
        this.dbConnection = new FileOutputStream(filename);
    }

    public static FileOutputStream connectDB(String filename) throws FileNotFoundException{
        if(database==null){
            database = new Database(filename);
        }

        return database.dbConnection;
    }
}
