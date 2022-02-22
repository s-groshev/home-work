package com.sbrf.reboot.equalshashcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EqualsHashCodeTest {

     class Car {
        String model;
        String color;
        Calendar releaseDate;
        int maxSpeed;

        @Override
        public boolean equals(Object o) {

            //Рефлексивность: объект должен равняться самому себе
            if (o == this)
                return true;

            //Принадлежность классу Car
            if(!(o instanceof Car))
                return false;

            //Преобразуем Object o к классу Car, чтобы получить поля
            Car otherCar=(Car)o;

            //Сравниваем поле модель
            if(model!=otherCar.model) return false;

            //Сравниваем поле цвет
            if(color!=otherCar.color) return false;

            //Сравниваем поле дата выпуска
            if(!releaseDate.equals(otherCar.releaseDate)) return false;

            //Сравниваем поле максимальная скорость
            if(maxSpeed!=otherCar.maxSpeed) return false;

            //если нигде не вернуло false значит нас устроит true
            return true;
        }

         @Override
         public int hashCode() {

             //Выберем любое не нулевое число
             int result= 77;

             //Будем комбинировать код по формуле result+=43*result+поле.hashCode();
             //для поля модель
             result+=43*result+model.hashCode();

             //для поля цвет
             result+=43*result+color.hashCode();

             //для поля дата выпуска
             result+=43*result+releaseDate.hashCode();

             //для поля максимальная скорость
             result+=43*result+Integer.hashCode(maxSpeed);

             //возвращаем результат
             return result;
         }
     }

    @Test
    public  void assertTrueEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;


        Assertions.assertTrue(car1.equals(car2));
    }

    @Test
    public void assertFalseEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertFalse(car1.equals(car2));
    }

    @Test
    public void successEqualsHashCode(){
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertEquals(car1.hashCode(),car2.hashCode());

    }

    @Test
    public void failEqualsHashCode(){
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertNotEquals(car1.hashCode(),car2.hashCode());

    }
// Мои тесты
    @Test
    public void failEqualsSpeed(){
        Car car1 = new Car();
        car1.model = "LamborginiMurcielago";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 360;

        Car car2 = new Car();
        car2.model = "FerrariEnzo";
        car2.color = "red";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 355;

        Assertions.assertNotEquals(car1.maxSpeed,car2.maxSpeed);

    }

    @Test
    public void successEqualsSpeed(){
        Car car1 = new Car();
        car1.model = "LamborginiMurcielago";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 360;

        Car car2 = new Car();
        car2.model = "FerrariEnzo";
        car2.color = "red";
        car2.releaseDate = new GregorianCalendar(2021, 0, 25);//новейшая модель
        car2.maxSpeed = 360;

        Assertions.assertEquals(car1.maxSpeed,car2.maxSpeed);

    }
}
