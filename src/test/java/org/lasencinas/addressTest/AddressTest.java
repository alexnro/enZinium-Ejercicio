package org.lasencinas.addressTest;

import org.junit.Before;
import org.junit.Test;
import org.lasencinas.address.Address;

import java.security.PublicKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AddressTest {

    Address address = null;

    @Before
    public void init() {
        address = new Address();
    }

    @Test
    public void generate_key_pair_test() {
        assertNotNull(address);
        address.generateKeyPair();
        assertNotNull(address.getPK());
        assertNotNull(address.getSK());
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
