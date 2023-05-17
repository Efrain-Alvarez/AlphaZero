package backendTest.database;

import backend.database.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestReservation {
    @Test
    void testDefaultConstructor() {
        Reservation r = new Reservation();
        Assertions.assertEquals("", r.getName());
        Assertions.assertEquals("", r.getPhoneNumber());
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
}