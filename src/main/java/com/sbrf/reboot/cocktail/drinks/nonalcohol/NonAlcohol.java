package com.sbrf.reboot.cocktail.drinks.nonalcohol;

import com.sbrf.reboot.cocktail.drinks.Drinks;

public class NonAlcohol implements Drinks {
    String name;
    double volume;
    int alcohol=0;

    public NonAlcohol(String name,int volume){
        this.name=name;
        this.volume=volume;
    }

    @Override
    public void setVolume(double volume) {
        this.volume=volume;
    }
    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public int getDegrees() {
        return alcohol;
    }

    @Override
    public String toString() {
        return volume+" мл "+name;
    }
}
