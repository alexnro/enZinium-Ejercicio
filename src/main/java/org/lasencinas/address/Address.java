package org.lasencinas.address;

import org.lasencinas.genSig.GenSig;
import org.lasencinas.tokenContract.TokenContract;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {

    /*-------------- Atributos --------------------*/

    private PublicKey PK = null;
    private PrivateKey SK = null;
    private double balance = 0;
    private String symbol = "EZI";

    /*-------------- Getters --------------------*/

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

    /*--------------- Setters -----------------*/

    public void setPK(PublicKey PK) {
        this.PK = PK;
    }

    public void setSK(PrivateKey SK) {
        this.SK = SK;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }

    /*----------------- Métodos ------------------*/

    public void generateKeyPair() {
        setPK(GenSig.generateKeyPair().getPublic());
        setSK(GenSig.generateKeyPair().getPrivate());
    }

    @Override
    public String toString() {
        return  "\nPK = " + getPK().hashCode() +
                          "\nBalance = " + getBalance() + " " + getSymbol();
    }

    public void addEZI(double EZI) {
        this.setBalance(EZI);
    }

    public void send(TokenContract contract, double EZI) {
        if (this.getBalance() > 0 && EZI > contract.getTokenCost()) {
            contract.payable(this.getPK(), EZI);
            this.addEZI(-EZI);
        } else {
            this.addEZI(EZI);
        }
    }

    public void transferEZI(double EZI) {
        this.addEZI(EZI);
    }
}