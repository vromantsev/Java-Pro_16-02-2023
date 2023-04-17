package ua.hillel.hw.task14.phonebook;

public class PhoneBookDemo {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        for (int i = 0; i < 5; i++) {
            phoneBook.add(new Record("Vlad", "123" + i));
        }

        System.out.println(phoneBook.findAll("Vlad"));
    }
}
