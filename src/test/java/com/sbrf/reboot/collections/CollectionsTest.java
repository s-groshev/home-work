package com.sbrf.reboot.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionsTest {


    /*
     * Задача.
     * Имеется список лучших студентов вуза.
     *
     * 1. Иванов
     * 2. Петров
     * 3. Сидоров
     *
     * В новом семестре по результатам подсчетов оценок в рейтинг на 1 место добавился новый студент - Козлов.
     * Теперь в рейтинге участвуют 4 студента.
     * (Предполагаем что в рейтинг можно попасть только получив достаточное количество балов, что бы занять 1 место).
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете для текущего хранения и использования списка студентов?
     *
     * Проинициализируйте students, добавьте в нее 4 фамилии студентов что бы тест завершился успешно.
     */
    @Test
    public void addStudentToRating() {

        List<String> students = null;

        students=new LinkedList<String>(Arrays.asList("Иванов","Петров","Сидоров"));
        System.out.println(students);
        ((LinkedList<String>)students).addFirst("Козлов");
        System.out.println(students);

        assertEquals(4, students.size());
    }

    /*
     * Задача.
     * Вы коллекционируете уникальные монеты.
     * У вас имеется специальный бокс с секциями, куда вы складываете монеты в хаотичном порядке.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для хранения монет в боксе.
     *
     * Проинициализируйте moneyBox, добавьте в нее 10 монет что бы тест завершился успешно.
     */
    @Test
    public void addMoneyToBox() {

        Set<Integer> moneyBox = null;

        moneyBox = new TreeSet<Integer>(Arrays.asList(10, 100, 5, 1000, 50, 2000, 1, 2, 500, 200));
        System.out.println(moneyBox);

        assertEquals(10, moneyBox.size());
    }

    /*
     * Задача.
     * Имеется книжная полка.
     * Периодически вы берете книгу для чтения, затем кладете ее на свое место.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для хранения книг.
     *
     * Проинициализируйте bookshelf, добавьте в нее 3 книги что бы тест завершился успешно.
     */
    @Test
    public void addBookToShelf() {
        class Book {
        }

        List<Book> bookshelf = null;

        bookshelf=new LinkedList<Book>(Arrays.asList(new Book(),new Book(),new Book(),new Book()));
        Book bookNumber2=bookshelf.remove(2);
        ((LinkedList<Book>)bookshelf).add(2,bookNumber2);
        bookNumber2=bookshelf.remove(2);

        assertEquals(3, bookshelf.size());
    }

    /*
    Моя задача: в детсаду на др именинница встает в круг а дети поют песню каравай каравай кого хочешь выбирай.

    Вопрос: какую коллекцию выбрать что-бы помочь имениннеце выбрать почти всех друзей в круг (обычно делают 5 итераций)

    Одно из решений: реализовать круговой связанный список на основе связанного списка и смоделировать выбор по кругу

     */

    @Test
    void name() {

        class CircleLinkedList<T> implements Iterable<T>{
            LinkedList<T> list=new LinkedList<>();
            int index;

            public CircleLinkedList(T... list){
                this.list.addAll(Arrays.asList(list));
                index=-1;
            }

            public void add(T item){list.add(item);}

            public void addAll(Collection<T> items){list.addAll(items);}

            public int size(){return list.size();}

            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    @Override
                    public boolean hasNext() {
                        return list.size()>0;
                    }

                    @Override
                    public T next() {
                        if(hasNext()){
                            if(index>=list.size()-1){index=-1;}
                            ++index;
                            return list.get(index);
                        } else throw new NoSuchElementException();
                    }

                    @Override
                    public void remove() {
                        list.remove(index);
                    }
                };
            }
        }

        CircleLinkedList<String> friends=new CircleLinkedList<>("Саша","Сережа","Кристина","Оксана","Влада",
                "Милана","Кирилл","Даниель");
        Iterator<String> iter=friends.iterator();
        Random rand=new Random();

        while (iter.hasNext()){
            int i=5+rand.nextInt(5);//именниница поет и мимо нее проходят
            for (int j = 0; j < i; j++)
                iter.next();
            System.out.println("Я люблю конечно всех, но вот эту больше всех: "+iter.next());
            iter.remove();
        }

        Assertions.assertEquals(friends.size(),0);

    }
}
