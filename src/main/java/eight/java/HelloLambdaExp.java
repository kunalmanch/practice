package eight.java;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by kmishra on 3/18/2016.
 */
public class HelloLambdaExp {

    static class Person {
        public enum Sex {
            MALE, FEMALE
        }

        String name;
        Sex gender;
        int age;

        Person(String name, Sex gender, int age) {
            this.name = name;
            this.gender = gender;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public static void printPerson(List<Person> personList, Predicate<Person> predicate) {
            for (Person person : personList) {
                if (predicate.test(person)) System.err.println(person.name);
            }
        }

        public static void printPerson2(List<Person> personList, Test test) {
            for (Person person : personList) {
                if (test.passed(person)) System.err.println(person.name);
            }
        }
    }

    public static void main(String[] args) {
        Person person1 = new Person("K", Person.Sex.MALE, 29);
        Person person2 = new Person("S", Person.Sex.FEMALE, 26);
        Person person3 = new Person("N", Person.Sex.FEMALE, 26);

        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        Person.printPerson(persons, (Person p) -> p.age>27 && p.age<=29);

        Test test = (p) -> {
           return p.age>27 && p.age<=29;
        };


        Person.printPerson2(persons, test);

        Person.printPerson2(persons, new HelloLambdaExp()::passedSub);

        List<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(15);
        integers.add(16);

        List<Integer> filterList = integers.stream()
                .filter((i) -> i <= 15)
                .collect(Collectors.toList());

        for (int i : filterList) System.err.println(i);
    }

    boolean passedSub(Person p) {
        return p.age>27 && p.age<=29;
    }

    interface Test {
        boolean passed(Person p);
    }
}
