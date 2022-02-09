package com.sbrf.reboot.cocktail.drinks.alcohol;

import com.sbrf.reboot.cocktail.drinks.Drinks;
/*
 Внутренними классами можно делать готовые напитки или наследоваться от этого
 */
public class Alcohol implements Drinks {
    String name;
    double volume;
    int alcohol;

    public Alcohol(String name,int volume,int alcohol){
        this.name=name;
        this.volume=volume;
        this.alcohol=alcohol;
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