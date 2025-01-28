package annotation.test;

public class User {

    @Masking
    private String name;
    @Masking
    private String phoneNumber;
    private int age; // 마스킹되지 않을 필드

    public User(String name, String phoneNumber, int age) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                '}';
    }
}

