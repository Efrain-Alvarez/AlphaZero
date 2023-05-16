package backend.database;

import backend.config.ConfigFile;
import jdk.jshell.spi.ExecutionControl;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * A class intended to interact with JDBC. This acts as a
 * shim layer that sits in between JDBC and exposes an
 * interface that the GUI frontend is intended to call to
 * perform specified tasks on the database. These operations
 * can be split into two types of operations:
 * <ul>
 * <li>query</li>
 * <li>set</li>
 * </ul>
 * The set operations can be further divided into two different sub-operations:
 * <ul>
 *  <li>add</li>
 *  <li>change</li>
 *  <li>delete</li>
 * </ul>
 * <p>
 * The naming conventions of the operations exposed in this
 * class will follow the above divisions for the sake of consistency.
 * Furthermore, this shim layer will obey the following principles of a REST API:
 * <ul>
 *  <li>Client-server communication</li>
 *  <li>Stateless communication</li>
 *  <li>Uniform interface</li>
 * </ul>
 * Following these 3 principles are intended to again, increase consistency with the behavior of this shim layer.<br>
 * Please note that each callable method that interacts with the database can possibly throw <code>SQLException</code>
 * so the caller must keep this in mind when instantiating this class.
 */
public class DatabaseAdapter {
    private String uri, dbUser, dbPass;
    private static final String dbClassname = "com.mysql.cj.jdbc.Driver";
    private static final Logger logger = Logger.getLogger(DatabaseAdapter.class.getName());
    private final ConfigFile cf;
    private final Properties credentials;

    /**
     * Initialize the shim layer between the frontend and the database.
     * This will read the database credentials from the config file
     * and establish a connection to the database referenced by the URI.
     * <p>
     * The config file read by this class must have the following section in the file:
     * <p>
     * <code>
     * [database]<br>
     * dbName = <i>name of database/table on SQL instance</i><br>
     * dbUser = <i>root user for database</i><br>
     * dbPass = <i>root password for database</i><br>
     * dbAddress = <i>address or hostname of MySQL instance</i><br>
     * dbPort = <i><b>(optional) </b>port number of instance (default 3306)</i><br>
     * </code>
     *
     * @param configPath the path to the program that has the database credentials
     * @throws FileNotFoundException if ConfigPath references a non-existent location for a config file
     * @throws RuntimeException      if an option on the config file couldn't be read
     */
    public DatabaseAdapter(String configPath) throws FileNotFoundException {
        cf = new ConfigFile(configPath);
        String dbName = cf.getOption("database", "dbName");
        uri = "jdbc:mysql://" + cf.getOption("database", "dbaddress") + "/" + dbName;
        logger.log(Level.CONFIG, "URI: " + uri);

        // initialize credentials for database access
        credentials = new Properties();
        credentials.put("user", cf.getOption("database", "dbuser"));
    }

    /**
     * Retrieve inventory amount for a single item. Note that this method does not specify for individual units
     * or cases. <b>It is up to the caller to determine that and display to the user whether they want to store number
     * of cases or number of units in the datastore.</b>
     *
     * @param item the item name to query for
     * @return the amount of items last specified in the system stored in the database
     * @throws SQLException if there was a problem with the database results
     */
    public int getInventoryAmount(String item) throws SQLException {
        return 0;
    }

    /**
     * Retrieve all items and their quantities from this database and return as a map.
     *
     * @return an <code>ArrayList</code> of all inventory items stored on this database
     * @throws ExecutionControl.NotImplementedException
     */
    public ArrayList<InventoryItem> getItems() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Query for inventory items not yet implemented");
    }

    /**
     * Add a reservation to this database. Before adding a reservation, the following must be true:<br>
     * <ol>
     *     <li>There must be no conflicting reservations time-wise</li>
     *     <li>The date must not be before the time of entry into the system</li>
     *     <li>There must be open spaces on the seating arrangement with respect to party size</li>
     * </ol>
     *
     * @param r the reservation containing customer information
     * @return false if any of the requirements above are not met, false otherwise
     * @throws SQLException if there was a problem updating the database
     */
    public boolean addReservation(Reservation r) throws SQLException {
        return false;
    }

    /**
     * Query the database and retrieve a list of any reservations that are found.<br>
     * Please refer to <code>Reservation</code> to learn more about what is return by this method.
     *
     * @return a list of <code>Reservation</code>s that were queried from the database
     * @throws SQLException if the database throws an error while running a query
     */
    public ArrayList<Reservation> getReservations() throws SQLException {
        ArrayList<Reservation> reservations = new ArrayList<>();
        return reservations;
    }
}