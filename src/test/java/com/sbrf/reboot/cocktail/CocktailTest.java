package com.sbrf.reboot.cocktail;

import com.sbrf.reboot.cocktail.drinks.Drinks;
import com.sbrf.reboot.cocktail.drinks.alcohol.Alcohol;
import com.sbrf.reboot.cocktail.drinks.ingredients.Ingredient;
import com.sbrf.reboot.cocktail.drinks.nonalcohol.NonAlcohol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CocktailTest {
    //готовые алкогольные коктейли
    Cocktail<Drinks> mojito=new Cocktail<Drinks>("Мохито",new Alcohol("Белый ром",45,37)
            ,new NonAlcohol("Лимонный сок",20),new NonAlcohol("Газированная вода",80))
    {{addIndigrients(new Ingredient("6 листков мяты"),new Ingredient("2 ложки сахара"));}};

    Cocktail<Drinks> ginTonic=new Cocktail<Drinks>("Джин-тоник",new Alcohol("Джин",50,38)
            ,new NonAlcohol("Тоник",100))
    {{addIndigrients(new Ingredient("2 дольки лимона"),new Ingredient("1-2 кубика льда"));}};

    Cocktail<Drinks> pinaColada=new Cocktail<Drinks>("Пина колада",new Alcohol("Белый ром",50,37)
            ,new NonAlcohol("Кокосовое молоко",30),new NonAlcohol("Аннанасовый сок",50))
    {{addIndigrients(new Ingredient("Долька ананаса"));}};

    //готовые безалкогольные коктейли
    Cocktail<NonAlcohol> milkshake=new Cocktail<NonAlcohol>("Милкшейк",new NonAlcohol("Молоко",200),
            new NonAlcohol("Сливки",50),new NonAlcohol("Мороженное",250))
    {{addIndigrients(new Ingredient("Сахар"));}};


    Cocktail<NonAlcohol> bumblebee =new Cocktail<NonAlcohol>("Шмель",new NonAlcohol("Кофе эспессо",50),
            new NonAlcohol("Карамельный сироп",15),new NonAlcohol("Апельсиновый сок",100))
    {{addIndigrients(new Ingredient("Долька апельсина"),new Ingredient("1-2 кубика льда"));}};

    //коктели только на основе алкогольных напитков
    Cocktail<Alcohol> negroni=new Cocktail<Alcohol>("Негрони",new Alcohol("Джин",30,38)
            ,new Alcohol(" Красный вермут",30,25),new Alcohol("Красный биттер Campari",30,28))
    {{addIndigrients(new Ingredient("Апельсиновая цедра"),new Ingredient("Кубик льда"));}};


    @Test
    void getDegrees() {
        Assertions.assertEquals(milkshake.getDegrees()==0,true);
        Assertions.assertEquals(mojito.getDegrees()<13,true);
        Assertions.assertEquals(negroni.getDegrees()>30,true);
    }

    @Test
    void orderCocktails() {
        ArrayList<Cocktail<NonAlcohol>> cocktails=new ArrayList<Cocktail<NonAlcohol>>(){{
            add(milkshake);
            add(bumblebee);
//            add(pinaColada); //нельзя
//            add(ginTonic);
        }};
        Assertions.assertEquals(2,cocktails.size());

        ArrayList<Cocktail<Alcohol>> cocktails2=new ArrayList<Cocktail<Alcohol>>(){{
            //только для крепких напитков состоящий из микса алкогольных напитков
            add(negroni);
//            add(milkshake);//нельзя
//            add(bumblebee);
//            add(pinaColada); //нельзя
        }};
        Assertions.assertEquals(1,cocktails2.size());

        ArrayList<Cocktail<Drinks>> cocktails3=new ArrayList<Cocktail<Drinks>>(){{
            add(mojito);
            add(pinaColada);
            add(ginTonic);
//            add(milkshake); //нельзя молочному коктейлю
//            add(negroni); //нельзя тк крепкий
        }};
        Assertions.assertEquals(3,cocktails3.size());
    }
}