package com.sbrf.reboot.cocktail;

import com.sbrf.reboot.cocktail.drinks.Drinks;
import com.sbrf.reboot.cocktail.drinks.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;

public class Cocktail<T extends Drinks> {
    String name;
    ArrayList<T> drinks =new ArrayList<T>(); //Делаем коктейль на основе
    ArrayList<Ingredient> ingredients =new ArrayList<Ingredient>();
    double alcoholVolume=0;
    double volume=0;
    public Cocktail(String name,T... drinks){
        this.name=name;
        this.drinks.addAll(Arrays.asList(drinks));
        for (T drink: this.drinks) {
            volume+=drink.getVolume();
            alcoholVolume+=drink.getVolume()*drink.getDegrees()*0.01;
        }
    }
    public void addIndigrients(Ingredient... ingredients){
        this.ingredients.addAll(Arrays.asList(ingredients));
    }

    public double getDegrees(){
        //в процентах до 1 знака после запятой, такой костыль
        return (Math.round((alcoholVolume/volume) * 1000.0) / 1000.0)*100;
    }

    @Override
    public String toString() {
        String cocktail="Состав коктейля "+name;
        if(getDegrees()<0.001)
            cocktail+=" безалкогольный "+volume+" мл:\n";
        else cocktail+=" алкогольный "+getDegrees()+ "% "+volume+" мл:\n";
        cocktail+=drinks;
        if(ingredients.size()>0)
            cocktail+="\nДобавляют "+ingredients+" по вкусу";
        return  cocktail;
    }

    public static void main(String[] args) {
    }
}
