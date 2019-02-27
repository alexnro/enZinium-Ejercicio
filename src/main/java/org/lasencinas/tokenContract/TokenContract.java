package org.lasencinas.tokenContract;

import org.lasencinas.address.Address;

import java.lang.reflect.Executable;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    private String name = null;
    private String symbol = null;
    private double TotalSupply = 0;
    private Address owner = null;
    private Map<PublicKey, Double> balances = new HashMap<>();
    private int tokenCost = 0;

    public TokenContract(Address address) {
        this.owner = address;
    }

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

    public int getTokenCost() {
        return this.tokenCost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTotalSupply(double totalSupply) {
        TotalSupply = totalSupply;
    }

    @Override
    public String toString() {
        String toString = "\nname = " + getName() +
                          "\nsymbol = " + symbol() +
                          "\ntotalSupply = " + totalSupply() +
                          "\nowner PK = " + getOwner().hashCode();
        return toString;
    }

    public void addOwner(PublicKey PK, double units) {
        //getBalances().putIfAbsent(PK, units);
        if (getBalances().isEmpty()) {
            getBalances().put(PK, units);
        }
    }

    public double totalSupply() {
        return this.TotalSupply;
    }

    public int numOwners() {
        return getBalances().size();
    }

    public double balanceOf(PublicKey owner) {
        //return getBalance().get(owner);
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
           getBalances().put(recipient, balanceOf(recipient) + units);
           getBalances().replace(getOwner().getPK(), balanceOf(getOwner().getPK()) - units);
       } catch (Exception sinEntradas){

       }
    }

    public void transfer(PublicKey sender, PublicKey recipient, double units) {

        try {
            require(units < getBalances().get(sender));
            getBalances().put(recipient, balanceOf(recipient) + units);
            getBalances().replace(sender, balanceOf(sender) - units);
        } catch (Exception sinEntradas) {

        }
    }

    public void require(Boolean condicion) throws Exception{

        if(!condicion){
            Exception sinEntradas = new Exception();
            throw sinEntradas;
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
        //TODO
    }
}