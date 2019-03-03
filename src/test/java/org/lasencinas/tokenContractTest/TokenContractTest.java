package org.lasencinas.tokenContractTest;

import org.junit.Before;
import org.junit.Test;
import org.lasencinas.address.Address;
import org.lasencinas.tokenContract.TokenContract;

import static org.junit.Assert.*;

public class TokenContractTest {

    Address rick = null;
    Address morty = null;
    Address jen = null;
    TokenContract token = null;

    @Before
    public void init() {
        rick = new Address();
        rick.generateKeyPair();
        morty = new Address();
        morty.generateKeyPair();
        jen = new Address();
        jen.generateKeyPair();
        token = new TokenContract(rick);

        token.addOwner(rick.getPK(), 100);
    }

    @Test
    public void TokenContractTest() {

        assertNotNull(token.getOwner());
        assertEquals(token.getOwner().getPK(), rick.getPK());
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
    public void addOwnerTest() {
        morty.generateKeyPair();
        token.addOwner(rick.getPK(), token.totalSupply());
        token.addOwner(morty.getPK(), 500d);

        assertEquals(1, token.getBalances().size());
        assertEquals(token.getOwner().getPK(), rick.getPK());
        assertNotEquals(token.totalSupply(), 500d);
        assertEquals(token.numOwners(), 1);
    }

    @Test
    public void balanceOfTest() {
        token.addOwner(morty.getPK(), 200);

        assertEquals(100, token.balanceOf(rick.getPK()), 0);
        assertEquals(0, token.balanceOf(morty.getPK()), 0);
    }

    @Test
    public void transferTest() {
        token.transfer(morty.getPK(), 2);

        assertEquals(98, token.balanceOf(rick.getPK()), 0);
        assertEquals(2, token.balanceOf(morty.getPK()), 0);
        //Tests sobrecarga en transfer
        token.transfer(morty.getPK(), jen.getPK(), 1);
        assertEquals(1, token.balanceOf(jen.getPK()), 0);
        assertEquals(1, token.balanceOf(jen.getPK()), 0);
    }

    @Test
    public void totalTokenSoldTest() {
        token.transfer(morty.getPK(), 30);
        assertEquals(30, token.totalTokensSold(), 0);

        token.transfer(jen.getPK(), 25);
        assertEquals(55, token.totalTokensSold(), 0);
    }

    @Test
    public void payable_test() {

        morty.addEZI(20d);
        assertEquals(20, morty.getBalance(), 0);

        // verifico la transferencia de entradas
        token.payable(morty.getPK(), morty.getBalance());
        assertEquals(4d, token.balanceOf(morty.getPK()), 0d);

        // verifico la trasnferencia de EZI
        assertEquals(20d, token.getOwner().getBalance(), 0d);
    }
}
