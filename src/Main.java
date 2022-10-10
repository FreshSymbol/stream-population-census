import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //Колличесво несовершеннолетних.
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Несовершеннолетних: " + count + " человек");

        //Список фамилий мужчин призывного возраста.
        Stream<String> ofСonscripts = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 || person.getAge() <= 27)
                .map(Person::getSurname);
        System.out.println(ofСonscripts.collect(Collectors.toList()));

        //Отсортированый список работоспособных с высшим образщованием по фамилии.
        Stream<Person> ofOperable = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getSex() == Sex.WOMAN &&
                        person.getAge() >= 18 && person.getAge() <= 60 ||
                        person.getSex() == Sex.MAN &&
                                person.getAge() >= 18 && person.getAge() <= 65)
                .sorted(Comparator.comparing(Person::getSurname));
        System.out.println(ofOperable.collect(Collectors.toList()));

    }
}


