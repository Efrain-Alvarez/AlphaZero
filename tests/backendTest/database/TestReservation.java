package backendTest.database;

import backend.database.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestReservation {
    @Test
    void testDefaultConstructor() {
        Reservation r = new Reservation();
        Assertions.assertEquals("unknown", r.getName());
        Assertions.assertEquals("0000000000", r.getPhoneNumber());
        Assertions.assertEquals(0, r.getPartySize());
        Assertions.assertEquals(0, r.getPreorderItems().size());
        Assertions.assertEquals(0, r.getSpecialRequests().size());
    }

    @Test
    void testPreorderItems() {
        // Test that adding preorder items works correctly
        Reservation r = new Reservation();
        Assertions.assertEquals(0, r.getPreorderItems().size());
        r.addPreOrderItem("");
        Assertions.assertEquals(1, r.getPreorderItems().size());
        r.addPreOrderItem("");
        Assertions.assertEquals(2, r.getPreorderItems().size());
    }

    @Test
    void testSpecialRequests() {
        // Test that adding preorder items works correctly
        Reservation r = new Reservation();
        Assertions.assertEquals(0, r.getSpecialRequests().size());
        r.addSpecialRequest("");
        Assertions.assertEquals(1, r.getSpecialRequests().size());
        r.addSpecialRequest("");
        Assertions.assertEquals(2, r.getSpecialRequests().size());
    }

    @Test
    void testRejectsInvalidNumber() {
        Assertions.assertThrows(RuntimeException.class, () -> new Reservation("TestNumber", "hi", null, 0, 0));
    }

    @Test
    void testGetPhoneNumber() {
        String numberUnformatted = "1234567890";
        Reservation r = new Reservation("TestNumber", numberUnformatted, null, 0, 0);
        Assertions.assertEquals(numberUnformatted, r.getPhoneNumber());
    }

    @Test
    void testNumberFormatting() {
        String numberUnformatted = "1234567890", numberFormatted = "(123) 456-7890";
        Reservation r = new Reservation("TestNumber", numberUnformatted, null, 0, 0);
        Assertions.assertEquals(numberFormatted, r.getPhoneNumberFormatted());
    }
}