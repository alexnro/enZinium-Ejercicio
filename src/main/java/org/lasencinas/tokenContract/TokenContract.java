package org.lasencinas.tokenContract;

import org.lasencinas.address.Address;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    private String name = null;
    private String symbol = null;
    private double TotalSupply = 0;
    private PublicKey owner = null;
    private Map<PublicKey, Double> balances = new HashMap<>();
    private double balanceOf = 0;

    public TokenContract(Address address) {
        this.owner = address.getPK();
    }

    public String getName() {
        return this.name;
    }

    public String symbol() {
        return this.symbol;
    }

    public PublicKey getOwner() {
        return owner;
    }

    public Map<PublicKey, Double> getBalances() {
        return this.balances;
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
        if (balances.isEmpty()) {
            balances.put(PK, units);
        }
    }

    public double totalSupply() {
        return this.TotalSupply;
    }

    public int numOwners() {
        int numOwners = 0;
        for (PublicKey key : balances.keySet()) {
            numOwners++;
        }
        return numOwners;
    }

    public double balanceOf(PublicKey owner) {
        double balanceOf = 0;
        for (Map.Entry<PublicKey, Double> ownerSupply : balances.entrySet()) {
            if (ownerSupply.getKey() == owner) {
                balanceOf = ownerSupply.getValue();
            }
        }
        return balanceOf;
    }

    public void transfer(PublicKey recipient, double units) {
        if (units <= TotalSupply) {
            for (Map.Entry<PublicKey, Double> ownerSupply : balances.entrySet()) {
                if (ownerSupply.getKey() == getOwner()) {
                    ownerSupply.setValue(ownerSupply.getValue() - units);
                }
                if (ownerSupply.getKey() == recipient) {
                    ownerSupply.setValue(ownerSupply.getValue() + units);
                } else {
                    balances.put(recipient, units);
                }
            }
        }
    }

    public void transfer(PublicKey sender, PublicKey recipient, double units) {
        /*Exception in thread "main" java.util.ConcurrentModificationException
        for (Map.Entry<PublicKey, Double> ownerSupply : balances.entrySet()) {
            if (ownerSupply.getKey() == sender) {
                ownerSupply.setValue(ownerSupply.getValue() - units);
            }
            if (ownerSupply.getKey() == recipient) {
                ownerSupply.setValue(ownerSupply.getValue() + units);
            } else {
                balances.put(recipient, units);
            }
        }*/
    }

    public void require(Boolean holds) throws Exception{
        //TODO
    }
}