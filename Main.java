import java.util.*;

class Contact {
    private int number;
    private String name;
    private String email;
    private int age;

    public Contact(int number, String name, String email, int age) {
        this.number = number;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Contact [number=" + number +
                ", name=" + name +
                ", email=" + email +
                ", age=" + age + "]";
    }
}

class AgeInvalidException extends Exception {
    public AgeInvalidException(String message) {
        super(message);
    }
}

class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

class ContactManager {
    public void addContact(HashMap<Integer, Contact> contacts,int number,String name,String email,int age)
            throws AgeInvalidException, InvalidEmailException {
        if (contacts.containsKey(number)) {
            System.out.println(
                    "Contact number already exists. Skipping contact.");
            return;
        }
        if (age < 0) {
            throw new AgeInvalidException("Invalid Age");
        }
        if (!(email.contains("@") && email.endsWith(".com"))) {
            throw new InvalidEmailException("Invalid Email");
        }
        Contact contact = new Contact(number, name, email, age);
        contacts.put(number, contact);
        System.out.println("Contact added successfully");
    }
    public void removeContactsByName(HashMap<Integer, Contact> contacts,String name) {
        boolean removed = false;
        Iterator<Map.Entry<Integer, Contact>> iterator = contacts.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Contact> entry = iterator.next();
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                iterator.remove();
                removed = true;
            }
        }
        if (removed) {
            System.out.println("Contacts removed successfully for name: "+ name);
        } else {
            System.out.println("No contacts found with name: "+ name);
        }
    }
    public void editContact(HashMap<Integer, Contact> contacts,int number,Contact contact) {
        if (contacts.containsKey(number)) {
            contacts.put(number, contact);
            System.out.println("Contact updated successfully");
        } else {
            System.out.println("Contact number not found. Update failed.");
        }
    }
    public void displayContactThreshold(
            HashMap<Integer, Contact> contacts,int startAge,int endAge) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available");
            return;
        }
        boolean found = false;
        System.out.println("\nContacts with age between "+ startAge + " and "+ endAge + ":");
        for (Contact c : contacts.values()) {
            if (c.getAge() >= startAge && c.getAge() <= endAge) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contacts found in the age range "+ startAge + " to "+ endAge);
        }
    }
    public void displayContactThreshold(HashMap<Integer, Contact> contacts,String nameFilter) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available");
            return;
        }
        boolean found = false;
        System.out.println("\nContacts starting with '"+ nameFilter + "':");
        for (Contact c : contacts.values()) {
            if (c.getName().toLowerCase().startsWith(nameFilter.toLowerCase())) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contacts found starting with '"+ nameFilter + "'");
        }
    }
    public void displayAllContacts( HashMap<Integer, Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println( "No contacts available");
            return;
        }
        System.out.println("\nAll Contacts:");
        for (Contact c : contacts.values()) {
            System.out.println(c);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Contact> contacts =new HashMap<>();
        ContactManager manager =new ContactManager();
        int choice;
        do {
            System.out.println("\n===== CONTACT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact By Name");
            System.out.println("3. Edit Contact");
            System.out.println("4. Display Contacts By Age Range");
            System.out.println("5. Display Contacts By Name Prefix");
            System.out.println("6. View All Contacts");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("How many contacts do you want to add? ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    for (int i = 1; i <= n; i++) {
                    try {
                        System.out.println("\nEnter details for Contact " + i);
                        System.out.print("Enter Contact Number: ");
                        int number = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        manager.addContact(contacts,number,name,email,age);
                    } catch (AgeInvalidException | InvalidEmailException e) {
                            System.out.println(e.getMessage());
                    } catch (Exception e) {
                            System.out.println("Invalid Input");
                            sc.nextLine();
                    }
                    }
                        break;
                case 2:

                    System.out.print("Enter Name To Remove: ");
                    String removeName =sc.nextLine();
                    manager.removeContactsByName(contacts,removeName);
                    break;
                case 3:
                    System.out.print("Enter Contact Number To Edit: ");
                    int number = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Email: ");
                    String newEmail =
                            sc.nextLine();

                    System.out.print(
                            "Enter New Age: ");
                    int newAge =
                            sc.nextInt();

                    Contact updated =
                            new Contact( number,newName,newEmail,newAge);
                    manager.editContact(contacts,number,updated);
                    break;
                case 4:
                    System.out.print( "Enter Start Age: ");
                    int startAge =sc.nextInt();
                    System.out.print("Enter End Age: ");
                    int endAge =sc.nextInt();
                    manager.displayContactThreshold(contacts,startAge,endAge);
                    break;
                case 5:
                    sc.nextLine();
                    System.out.print("Enter Name Prefix: ");
                    String prefix = sc.nextLine(); manager.displayContactThreshold(contacts,prefix);
                    break;
                case 6:
                    manager.displayAllContacts(contacts);
                    break;
                case 7:
                    System.out.println("Exiting Contact Management System...");
                    break;
                default:
                    System.out.println( "Invalid Choice!");
            }
        } while (choice != 7);
        sc.close();
    }
}