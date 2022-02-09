package com.sbrf.reboot.cocktail.drinks.ingredients;

public class Ingredient {
    String name;
    public Ingredient(String name){
        this.name=name;
    }
    @Override
    public String toString() {
        return name;
    }
}
