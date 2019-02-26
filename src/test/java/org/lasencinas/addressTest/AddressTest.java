package org.lasencinas.addressTest;

import org.junit.Before;
import org.junit.Test;
import org.lasencinas.address.Address;

import java.security.PublicKey;

import static org.junit.Assert.*;

public class AddressTest {

    Address address = null;

    @Before
    public void init() {
        address = new Address();
        address.generateKeyPair();
    }

    @Test
    public void generate_key_pair_test() {
        assertNotNull(address);
        assertNotNull(address.getPK());
        assertNotNull(address.getSK());
    }

    @Test
    public void addEZITest() {
        address.addEZI(50);
        assertEquals(50, address.getBalance(), 0);

        address.addEZI(2);
        assertEquals(52, address.getBalance(), 0);
    }

//    @Test
//    public void transferEZI_test() {
//
//        Address rick = new Address();
//        rick.generateKeyPair();
//
//        rick.addEZI(20d);
//
//        rick.transferEZI(20d);
//
//        assertEquals(40d, rick.getBalance(), 0d);
//    }
//
}
