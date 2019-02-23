package org.lasencinas.tokenContractTest;

import org.junit.Before;
import org.junit.Test;
import org.lasencinas.address.Address;
import org.lasencinas.tokenContract.TokenContract;

import static org.junit.Assert.*;

public class TokenContractTest {

    Address rick = null;
    Address morty = null;
    TokenContract token = null;

    @Before
    public void init() {
        rick = new Address();
        rick.generateKeyPair();
        morty = new Address();
        morty.generateKeyPair();
        token = new TokenContract(rick);
    }

    @Test
    public void TokenContractTest() {

        assertNotNull(token.getOwner());
        assertEquals(token.getOwner(), rick.getPK());
    }

    @Test
    public void gettersTest() {
        token.setName("Pepinillos");
        token.setSymbol("PNP");
        token.setTotalSupply(350);

        assertEquals("Pepinillos", token.getName());
        assertEquals("PNP", token.symbol());
        assertEquals(350, token.totalSupply(), 0);
    }

    @Test
    public void ownerTest() {
        morty.generateKeyPair();
        token.addOwner(rick.getPK(), token.totalSupply());
        token.addOwner(morty.getPK(), 500d);

        assertEquals(1, token.getBalances().size());
        assertEquals(token.getOwner(), rick.getPK());
        assertNotEquals(token.totalSupply(), 500d);
        assertEquals(token.numOwners(), 1);
    }

    @Test
    public void balanceOfTest() {
        token.addOwner(rick.getPK(), 100);
        token.addOwner(morty.getPK(), 200);

        assertEquals(100, token.balanceOf(rick.getPK()), 0);
        assertEquals(0, token.balanceOf(morty.getPK()), 0);
    }

//    @Test
//    public void payable_test() {
//
//        Address rick = new Address();
//        rick.generateKeyPair();
//        TokenContract ricknillos = new TokenContract(rick);
//        ricknillos.addOwner(rick.getPK(), 100d);
//        Address morty = new Address();
//        morty.generateKeyPair();
//
//        morty.addEZI(20d);
//
//        // verifico la transferencia de entradas
//        ricknillos.payable(morty.getPK(), morty.getBalance());
//        assertEquals(4d, ricknillos.balanceOf(morty.getPK()), 0d);
//
//        // verifico la trasnferencia de EZI
//        assertEquals(20d, ricknillos.owner().getBalance(), 0d);
//    }
}
