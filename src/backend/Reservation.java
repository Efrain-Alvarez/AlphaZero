package backend;

import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * This class is a representation of a singular reservation on the system.
 * It is intended as a helper for the program frontend to store and retrieve reservation
 * information for display, as well as the program's backend for scheduling purposes.
 */
public class Reservation {
    private ArrayList<String> preorderItems, specialRequests;
    private String name, phoneNumber;
    private ZonedDateTime date;
    int partySize;

    /**
     * Create a single reservation entry for a single party.
     *
     * @param name        name of customer making reservation under
     * @param phoneNumber phone number to contact customer with
     * @param date        date to attach to reservation
     */
    public Reservation(String name, String phoneNumber, ZonedDateTime date, int partySize) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.partySize = partySize;
        this.date = date;

        preorderItems = new ArrayList<String>();
        specialRequests = new ArrayList<String>();
    }

    /**
     * Add a menu item to this reservation's preorder list.
     * This is a helper for the functionality for a customer preordering a menu item to aid with
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
     * @return date attached to this reservation
     */
    public ZonedDateTime getDate() {
        return date;
    }

    /**
     * @return <code>ArrayList</code> of preordered menu items
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
}
