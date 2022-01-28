package com.sbrf.reboot.concurrentmodify;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveElementWithoutErrorsTest {

    @Test
    public void successConcurrentModificationException() {

        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        assertThrows(ConcurrentModificationException.class, () -> {
            
            for (Integer integer : list) {
                list.remove(1);
            }

        });

    }

    @Test
    public void successRemoveElementWithoutErrorsVer1() {
        List<Integer> list = new ArrayList<>(Arrays.asList(-1, 2, 3, 4, 5));

        //перебор и удаление по индексу
        assertDoesNotThrow(() -> {

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > 0) {
                    list.remove(i);
                    i--;//нужно следить что при каждом удалении правая часть массива сдвигается влево
                }
            }

        });
    }

    @Test
    public void successRemoveElementWithoutErrorsVer2() {
        List<Integer> list = new ArrayList<>(Arrays.asList(-1, 2, 3, 4, 5));

        //перебор и удаление с помощью итератора
        assertDoesNotThrow(() -> {

            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() > 0)
                    iterator.remove();
            }

        });
    }

    @Test
    public void successRemoveElementWithoutErrorsVer3() {
        List<Integer> list = new ArrayList<>(Arrays.asList(-1, 2, 3, 4, 5));

        //перебор и удаление методом removeif
        assertDoesNotThrow(()->{

            list.removeIf(i->i>0);

        });
    }
}
