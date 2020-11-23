package com.AddressBookManagement;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {

    static Scanner sc ;
    public static ArrayList<Person> addressbooklist = new ArrayList<Person>();

    /*UC2: Add Person Details In AddressBook*/
    public void addPerson() {
        sc=new Scanner(System.in);
        System.out.println("\nEnter the FirstName");
        String firstname = sc.nextLine();

        System.out.println("Enter the LastName");
        String lastname = sc.nextLine();

        System.out.println("Enter the Contact");
        String contactno = sc.nextLine();

        System.out.println("Enter the Emailid");
        String emailid = sc.nextLine();

        System.out.println("Enter the Address");
        String address = sc.nextLine();

        System.out.println("Enter the city");
        String city = sc.nextLine();

        System.out.println("Enter the state");
        String state = sc.nextLine();

        System.out.println("Enter the zip");
        String zip = sc.nextLine();

        Person p = new Person(firstname, lastname, contactno, emailid, address, city, state, zip);
        addressbooklist.add(p);
        System.out.println("AddressBook Data:" + addressbooklist);

    }

    /* UC3: Edit Person Details*/
    public void editPerson() {
        sc=new Scanner(System.in);
        System.out.println("\n___Edit the Person Using Name___");
        System.out.println("\nEnter the FirstName And LastName To Find Records.\nFirstName");
        String firstName = sc.nextLine();
        System.out.println("\nLastName");
        String lastName = sc.nextLine();

        int loop = 1;
        for (Person person : addressbooklist) {
            if (person.getFirstname().equals(firstName) && person.getLastname().equals(lastName)) {
                System.out.println("\nData Found as per match!!!");
                System.out.println(person.toString());
                while (loop == 1) {
                    System.out.println("\n\n Enter what field you want to edit(address/contact/emailid/quit) : ");
                    String field = sc.nextLine(); //taken input as address or contact

                    if (field.equals("address")) {
                        System.out.println("\n Enter Address : To Edit ");

                        System.out.print("\b\n City : ");
                        String city = sc.nextLine();

                        System.out.print("\b\n State : ");
                        String state = sc.nextLine();

                        System.out.print("\n ZipCode: ");
                        String zip = sc.nextLine();

                        //Adding Edited Address Related Data To Address arraylist
                        person.setCity(city);
                        person.setState(state);
                        person.setZip(zip);

                        System.out.println("address Edited Successfully:" + toString());
                    } else if (field.equals("contact")) {
                        System.out.print("\n Enter contact number : ");
                        String contact = sc.nextLine();
                        person.setContactno(contact);
                        System.out.println("\nContact Edited Successfully :" + person.toString());

                    } else if (field.equals("emailid")) {
                        System.out.println("Enter email id");
                        String emailid = sc.nextLine();
                        person.setEmailid(emailid);
                        System.out.println("\nContact Edited Successfuly :" + person.toString());

                    } else if (field.equals("quit")) {
                        loop = 0;
                        System.out.println("\nExit From Edit Functionality");
                    }

                }

            } else {
                System.out.println("given Wrong Input : Unable to find data!!!!");

            }
        }
    }

    /*UC4 : Delete Person Using Name*/
    public void deletePerson() {
        sc=new Scanner(System.in);
        System.out.println("Delete Person Using FirstName And LastName");
        String firstName = sc.nextLine();
        System.out.println("\nLastName");
        String lastName = sc.nextLine();

        for (int i = 0; i < addressbooklist.size(); i++) {
            Person person = (Person) addressbooklist.get(i);
            if ((person.getFirstname().equals(firstName)) && (person.getLastname().equals(lastName))) ;
            System.out.println("Details Found!!" + person.getFirstname() + person.getLastname());
            addressbooklist.remove(i);
            Display();
            System.out.println("Contact Deleted Successfully:");
        }
    }

    /*Display Function to Show AddressBook Data*/
    public void Display() {
        System.out.println("\nDISPLAYING ADDRESS BOOK DATA");
        for (int i = 0; i < addressbooklist.size(); i++) {
            System.out.println(addressbooklist.get(i));
        }

    }

    @Override
    protected void finalize() {
        sc.close();
    }

    public static void main(String[] args) {
        sc=new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        int loop = 1;
        while (loop == 1) {
            System.out.println("WELCOME TO ADDRESS BOOK MANAGEMENT");
            System.out.println("1].Add Person" +
                    "\n" + "2].Edit Person" +
                    "\n" + "3].Delete Person" +
                    "\n" + "4].Display" +
                    "\n" + "5].Quit");

            System.out.println("\n" + "Enter the choice:");
            int ch1 = sc.nextInt();
            switch (ch1) {
                case 1:
                    System.out.println("WELCOME TO ADDPERSON_OPERATION");
                    addressBook.addPerson();
                    break;

                case 2:
                    System.out.println("WELCOME TO EDIT_OPERATION");
                    addressBook.editPerson();
                    break;

                case 3:
                    System.out.println("WELCOME TO DELETE_OPERATION");
                    addressBook.deletePerson();
                    break;

                case 4:
                    System.out.println("WELCOME TO DISPLAY");
                    addressBook.Display();
                    break;

                case 5:
                    loop = 0;
                    break;

                default:
                    System.out.println(" Wrong Choice!!!");


            }

            
        }
    }
}
