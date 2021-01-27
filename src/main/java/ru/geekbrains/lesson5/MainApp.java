package ru.geekbrains.lesson5;

public class MainApp {

    public static void main(String[] args) {
        Person[] persArray = new Person[5];

        persArray[0] = new Person("Roselyn Robinson", "Engineer", "rrobinson@mailbox.com", "89026390248", 30000, 30);
        persArray[1] = new Person("Celine Howard", "Developer", "choward@mailbox.com", "89036520248", 35000, 40);
        persArray[2] = new Person("Ysabella Robinson", "Manager", "yrobinson@mailbox.com", "89027436248", 20000, 45);
        persArray[3] = new Person("Isabel Roberts", "Manager", "iroberts@mailbox.com", "89026398569", 18000, 30);
        persArray[4] = new Person("Lily Ward", "Manager", "lward@mailbox.com", "89026398569", 18000, 50);

        for (Person p : persArray) {
            if (p != null && p.getAge() > 40) {
                System.out.println(p);
            }
        }
    }
}
