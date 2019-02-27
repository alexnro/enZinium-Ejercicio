package org.lasencinas.address;

import org.lasencinas.genSig.GenSig;
import org.lasencinas.tokenContract.TokenContract;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {

    private PublicKey PK = null;
    private PrivateKey SK = null;
    private double balance = 0;
    private String symbol = "EZI";

    public PublicKey getPK() {
        return this.PK;
    }

    public PrivateKey getSK() {
        return this.SK;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getSymbol() {
        return symbol;
    }

    public void generateKeyPair() {
        setPK(GenSig.generateKeyPair().getPublic());
        setSK(GenSig.generateKeyPair().getPrivate());
    }

    public void setPK(PublicKey PK) {
        this.PK = PK;
    }

    public void setSK(PrivateKey SK) {
        this.SK = SK;
    }

    @Override
    public String toString() {
        String toString = "\nPK = " + getPK().hashCode() +
                          "\nBalance = " + getBalance() + " " + getSymbol();
        return toString;
    }

    public void addEZI(double EZI) {
        this.balance += EZI;
    }

    public void send(TokenContract contract, double EZI) {
        //TODO
    }

    public void transferEZI(PublicKey address, double EZI) {
        //TODO
    }
}
