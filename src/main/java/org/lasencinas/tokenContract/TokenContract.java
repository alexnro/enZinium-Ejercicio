package org.lasencinas.tokenContract;

import org.lasencinas.address.Address;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    /*-------------- Atributos --------------------*/

    private String name = null;
    private String symbol = null;
    private double TotalSupply = 0;
    private Address owner = null;
    private Map<PublicKey, Double> balances = new HashMap<>();
    private double tokenCost = 5;

    /*------------- Constructor -------------------*/

    public TokenContract(Address address) {
        this.owner = address;
    }

    /*-------------- Getters --------------------*/

    public String getName() {
        return this.name;
    }

    public String symbol() {
        return this.symbol;
    }

    public Address getOwner() {
        return owner;
    }

    public Map<PublicKey, Double> getBalances() {
        return this.balances;
    }

    public double totalSupply() {
        return this.TotalSupply;
    }

    public double getTokenCost() {
        return tokenCost;
    }

    /*--------------- Setters -----------------*/

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTotalSupply(double totalSupply) {
        TotalSupply = totalSupply;
    }

    /*----------------- Métodos ------------------*/

    @Override
    public String toString() {
        return  "\nname = " + getName() +
                          "\nsymbol = " + symbol() +
                          "\ntotalSupply = " + totalSupply() +
                          "\nowner PK = " + getOwner().hashCode();
    }

    public void addOwner(PublicKey PK, double units) {
        if (getBalances().isEmpty()) {
            getBalances().put(PK, units);
        }
    }

    public int numOwners() {
        return getBalances().size();
    }

    public double balanceOf(PublicKey owner) {
        double balanceOf = 0;
        for (Map.Entry<PublicKey, Double> ownerSupply : getBalances().entrySet()) {
            if (ownerSupply.getKey() == owner) {
                balanceOf = ownerSupply.getValue();
            }
        }
        return balanceOf;
    }

    public void transfer(PublicKey recipient, double units) {

       try {
           require(units < getBalances().get(getOwner().getPK()));
           this.getBalances().compute(this.getOwner().getPK(), (pk, tokens) -> tokens - units);
           getBalances().put(recipient, balanceOf(recipient) + units);
       } catch (Exception sinEntradas){

       }
    }

    public void transfer(PublicKey sender, PublicKey recipient, double units) {

        try {
            require(units < getBalances().get(sender));
            getBalances().put(recipient, balanceOf(recipient) + units);
            getBalances().replace(sender, balanceOf(sender) - units);
        } catch (Exception noEntries) {

        }
    }

    public void require(Boolean condition) throws Exception{

        if(!condition){
            throw new Exception();
        }else {

        }
    }

    public void owners() {
        for (Map.Entry<PublicKey, Double> tokenOwner : getBalances().entrySet()) {
            if (getOwner().getPK().equals(tokenOwner.getKey())) {

            } else {
                System.out.println("Owner: " + tokenOwner.getKey().hashCode() + " " +
                        tokenOwner.getValue() + " " + symbol());
            }
        }
    }

    public double totalTokensSold() {
        double totalTokens = 0;
        for (Map.Entry<PublicKey, Double> numberTokens : getBalances().entrySet()) {
            if (getOwner().getPK().equals(numberTokens.getKey())) {

            } else {
                totalTokens += numberTokens.getValue();
            }
        }
        return totalTokens;
    }

    public void payable(PublicKey recipient, double EZI) {
        try {
            require(EZI >= this.getTokenCost());
            double tokens = EZI / this.getTokenCost();
            Math.floor(tokens);
            transfer(recipient, tokens);
            this.getOwner().transferEZI(EZI);
        } catch (Exception noEZI) {

        }
    }
}