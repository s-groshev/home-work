package com.sbrf.reboot.atm.cassets;

import com.sbrf.reboot.atm.banknote.Banknote;

import java.util.ArrayList;
import java.util.List;

public class Cassette<T extends Banknote> {
    private List<T> cassette;

    public Cassette(List<T> cassette){
        this.cassette=cassette;
    }

    public int getCountBanknotes(){
        return cassette.size();
    }
    public void addBanknote(T banknote){cassette.add(banknote);}
}
