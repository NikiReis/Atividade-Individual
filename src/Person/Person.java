package Person;

public class Person extends Thread {
    String Name;
    String Age;

    public Person(String name, String age) {
        this.Name = name;
        this.Age = age;
    }

    public String getAge() {
        return Age;
    }

    public String getNamePerson() {
        return Name;
    }
}