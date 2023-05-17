package backend.database;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * This class is a representation of a singular reservation on the system.
 * It is intended as a helper for the program frontend to store and retrieve reservation
 * information for display, as well as the program's backend for scheduling purposes.
 */
public class Reservation {
    private final ArrayList<String> preorderItems, specialRequests;
    private final String name, phoneNumber;
    private final LocalDateTime date;
    private final int partySize;
    private final int tableNumber;

    /**
     * Default constructor for <code>Reservation</code>.
     */
    public Reservation() {
        this.name = "unknown";
        this.phoneNumber = "unknown";
        this.date = null;
        this.partySize = 0;
        this.tableNumber = -1;

        preorderItems = new ArrayList<>();
        specialRequests = new ArrayList<>();
    }

    /**
     * Create a single reservation entry for a single party.
     *
     * @param name        name of customer making reservation under
     * @param phoneNumber phone number to contact customer with
     * @param date        date to attach to reservation
     * @param partySize   number of people included in this reservation
     */
    public Reservation(String name, String phoneNumber, LocalDateTime date, int partySize, int tableNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.partySize = partySize;
        this.tableNumber = tableNumber;

        preorderItems = new ArrayList<>();
        specialRequests = new ArrayList<>();
    }

    /**
     * @return the customer's name attached to this reservation
     */
    public String getName() {
        return name;
    }

    /**
     * @return the phone number attached to this reservation
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return string representation of date attached to this reservation
     */
    public String getDateString() {
        return date.format(ISO_LOCAL_DATE_TIME);
    }

    /**
     * @return this <code>Reservation</code>'s date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @return <code>ArrayList</code> of pre-ordered menu items
     */
    public ArrayList<String> getPreorderItems() {
        return preorderItems;
    }

    /**
     * @return <code>ArrayList</code> of special requests for this reservation
     */
    public ArrayList<String> getSpecialRequests() {
        return specialRequests;
    }

    /**
     * @return the size of the party referenced by this reservation
     */
    public int getPartySize() {
        return partySize;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * Add a menu item to this reservation's pre-order list.
     * This is a helper for the functionality for a customer pre-ordering a menu item to aid with
     * inventory purposes before the actual reservation date.
     *
     * @param item the menu item to order
     */
    public void addPreOrderItem(String item) {
        preorderItems.add(item);
    }

    /**
     * Add a special request such as an allergy, menu item request, etc.
     * This method does not specify any formatting, so that is left up to the
     * caller i.e. the GUI.
     *
     * @param requestMessage the request message to add to this reservation
     */
    public void addSpecialRequest(String requestMessage) {
        specialRequests.add(requestMessage);
    }
}
