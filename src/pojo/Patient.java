package pojo;

public class Patient {

    private int id;
    private String name;
    private int age;
    private boolean hivInfected;

    public Patient() {
        id = 0;
        name = "";
        age = 0;
        hivInfected = false;
    }

    public Patient(String name, int age, boolean hivInfected) {
        this.name = name;
        this.age = age;
        this.hivInfected = hivInfected;
    }

    public Patient(int id, String name, int age, boolean hivInfected) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hivInfected = hivInfected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isHivInfected() {
        return hivInfected;
    }

    public void setHivInfected(boolean hivInfected) {
        this.hivInfected = hivInfected;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", age = " + age +
                ", hivInfected = " + hivInfected +
                '}';
    }
}
