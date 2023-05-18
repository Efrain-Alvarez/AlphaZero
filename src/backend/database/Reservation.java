package backend.database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

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
        this.phoneNumber = "0000000000";
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
     * @param phoneNumber 10-digit phone number to contact customer with
     * @param date        date to attach to reservation
     * @param partySize   number of people included in this reservation
     */
    public Reservation(String name, String phoneNumber, LocalDateTime date, int partySize, int tableNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.partySize = partySize;
        this.tableNumber = tableNumber;

        // must be a 10 digit phone number
        if (phoneNumber.length() != 10) {
            throw new RuntimeException("Phone number " + phoneNumber + " rejected, invalid format");
        }

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
     * @return the phone number of this reservation but formatted as (xxx) xxx-xxxx
     */
    public String getPhoneNumberFormatted() {
        String block1 = phoneNumber.substring(0, 3);
        String block2 = phoneNumber.substring(3, 6);
        String block3 = phoneNumber.substring(6, 10);
        return String.format("(%s) %s-%s", block1, block2, block3);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return partySize == that.partySize && tableNumber == that.tableNumber && Objects.equals(preorderItems, that.preorderItems) && Objects.equals(specialRequests, that.specialRequests) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preorderItems, specialRequests, name, phoneNumber, date, partySize, tableNumber);
    }
}
