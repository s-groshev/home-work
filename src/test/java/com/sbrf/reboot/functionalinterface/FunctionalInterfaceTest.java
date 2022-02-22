package com.sbrf.reboot.functionalinterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class FunctionalInterfaceTest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class SomeObject {
        private String objectName;
    }

    @FunctionalInterface
    interface ObjectToJsonFunction<T> {
        String applyAsJson(T t) throws JsonProcessingException;
    }

    static class ListConverter<T> {
        @SneakyThrows
        public List<String> toJsonsList(@NonNull List<T> someObjects, ObjectToJsonFunction<T> objectToJsonFunction) {
            List<String> result = new ArrayList<>();
            if (someObjects.isEmpty())
                throw new IllegalArgumentException("The list is empty");

            //add code here...
            for (T object :someObjects)
                result.add(objectToJsonFunction.applyAsJson(object));

            return result;
        }
    }

    @Test
    public void successCallFunctionalInterface() {
        ListConverter<SomeObject> ListConverter = new ListConverter<>();

        ObjectToJsonFunction<SomeObject> objectToJsonFunction = (new ObjectMapper())::writeValueAsString;

        List<String> strings = ListConverter.toJsonsList(
                Arrays.asList(
                        new SomeObject("Object-1"),
                        new SomeObject("Object-2")
                ),
                objectToJsonFunction
        );

        Assertions.assertTrue(strings.contains("{\"objectName\":\"Object-1\"}"));
        Assertions.assertTrue(strings.contains("{\"objectName\":\"Object-2\"}"));
    }

    //Мои примеры
    //реализуем компараторы для фунцкции Collection.sort(Collection<SomeObject> list)
    List<SomeObject> someObjectList = new ArrayList<>(
            Arrays.asList(
                    new SomeObject("Kuyt"),
                    new SomeObject("van Nistelrooy"),
                    new SomeObject("van der Sar")
            ));

    @Test
    public void successCallMyFunctionalInterface1() {
        //вариант 1-го сравнения поля objectName методом compareTo
        Comparator<SomeObject> comparator1= Comparator.comparing(SomeObject::getObjectName);

        someObjectList.sort(comparator1);

        Assertions.assertEquals(someObjectList.get(0).getObjectName(), "Kuyt");
    }

    @Test
    public void successCallMyFunctionalInterface2() {
        //вариант 2-го сравнения поля objectName по длине строки, по убыванию
        Comparator<SomeObject> comparator2=(a, b)->b.getObjectName().length()-a.getObjectName().length();

        someObjectList.sort(comparator2);

        Assertions.assertEquals(someObjectList.get(0).getObjectName(), "van Nistelrooy");
    }

    @Test
    public void successCallMyFunctionalInterface3() {
        //вариант 3-го сравнения поля objectName по колличеству слов, по убыванию
        Comparator<SomeObject> comparator3=(a, b)->
                b.getObjectName().split(" ").length-a.getObjectName().split(" ").length;

        someObjectList.sort(comparator3);

        Assertions.assertEquals(someObjectList.get(0).getObjectName(), "van der Sar");
    }
}
