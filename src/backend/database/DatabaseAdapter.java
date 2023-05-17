package backend.database;

import backend.config.ConfigFile;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class DatabaseAdapter implements AutoCloseable {
    private static final Logger logger = Logger.getLogger(DatabaseAdapter.class.getName());
    private final Connection databaseConnection;


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
     * @throws SQLException if there was a problem establishing a connection to the SQL database
     */
    public DatabaseAdapter(String configPath) throws FileNotFoundException, SQLException, RuntimeException {
        ConfigFile cf = new ConfigFile(configPath);
        String dbName = cf.getOption("database", "dbname");
        String dbPort = cf.getOption("database", "dbport");
        String uri = "jdbc:mysql://" + cf.getOption("database", "dbaddress") + ":" + dbPort + "/" + dbName;
        logger.log(Level.CONFIG, "URI: " + uri);

        // initialize credentials for database access
        Properties credentials = new Properties();
        credentials.put("user", cf.getOption("database", "dbuser"));
        credentials.put("password", cf.getOption("database", "dbpass"));

        databaseConnection = DriverManager.getConnection(uri, credentials);
    }

    /**
     * Retrieve all items and their quantities from this database and return as a list of <code>InventoryItems</code>.
     * If the inventory table in the database is empty, this method will return an empty ArrayList.
     *
     * @return an <code>ArrayList</code> of all inventory items stored on this database, or null if there was an error
     * with the query
     */
    public ArrayList<InventoryItem> getItems() throws SQLException {
        ArrayList<InventoryItem> items = new ArrayList<>();
        try (Statement s = databaseConnection.createStatement();
             ResultSet itemEntries = s.executeQuery("select * from inventory")) {

            while (itemEntries.next()) {
                String itemName = itemEntries.getString("ItemName");
                int itemCount = itemEntries.getInt("ItemCount");
                items.add(new InventoryItem(itemName, itemCount));
            }
        }
        return items;
    }

    /**
     * Retrieve inventory amount for a single item. Note that this method does not specify for individual units
     * or cases. <b>It is up to the caller to determine that and display to the user whether they want to store number
     * of cases or number of units in the datastore.</b>
     *
     * @param item the item name to query for
     * @return the amount of items last specified in the system stored in the database or -1 if there was an
     * error with the query
     */
    public int getInventoryAmount(String item) {
        try (Statement s = databaseConnection.createStatement();
             ResultSet itemResult = s.executeQuery(String.format("SELECT * FROM inventory WHERE `ItemName` = '%s';", item))) {
            if (!itemResult.next()) {
                return -1;
            } else {
                return itemResult.getInt("ItemCount");
            }

        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            return -1;
        }
    }

    /**
     * Add a new item and count to the database. Note that the
     * database is configured to reject the addition of new items
     * that already have entries in the inventory table.
     *
     * @param itemName the name of the item to add to the inventory
     * @param count    the initial count
     * @throws SQLException if there was a problem with the
     */
    public void addInventoryItem(String itemName, int count) throws SQLException {
        try (Statement s = databaseConnection.createStatement()) {
            s.executeUpdate(String.format("INSERT INTO inventory(ItemName, ItemCount) VALUES('%s', %d);", itemName, count));
        }
    }

    public void updateInventoryItem(String itemName, int newCount) {

    }

    /**
     * Delete a specific item from the inventory table.
     * @param itemName the name of the item to delete
     */
    public void deleteInventoryItem(String itemName) throws SQLException {
        try (Statement s = databaseConnection.createStatement()) {
            s.executeUpdate(String.format("delete from inventory where `ItemName` = '%s';", itemName));
        }
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
        try (Statement s = databaseConnection.createStatement();
             ResultSet reservationEntries = s.executeQuery("select * from reservations")) {

            while (reservationEntries.next()) {
                int reservationID = reservationEntries.getInt("reservationID");
                int partySize = reservationEntries.getInt("partySize");
                int tableNumber = reservationEntries.getInt("tableNumber");
                String customerName = reservationEntries.getString("customerName");
                String phoneNumber = reservationEntries.getString("phoneNumber");
            }
        }
        return reservations;
    }

    /**
     * Modify a selected reservation.
     *
     * @param r the reservation to modify
     */
    public void changeReservation(Reservation r) {

    }

    /**
     * Close any connections and other resources that have been opened during the duration of this object.
     */
    @Override
    public void close() throws Exception {
        databaseConnection.close();
    }
}