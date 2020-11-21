package com.AddressBookManagement;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    Scanner sc=new Scanner(System.in);
    public static ArrayList<Person> addressbooklist = new ArrayList<Person>();

    /*UC1: Create Contact In AddressBook*/
    private void createAddressBook()
    {
        System.out.println("____Fill The Details____");
        System.out.println("Enter the FirstName");
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

        Person p=new Person(firstname,lastname,contactno,emailid,address,city,state,zip);
        addressbooklist.add(p);
        System.out.println("AddressBook Data:" +addressbooklist);

    }

    public static void main(String []args)
    {
        AddressBook addressBook=new AddressBook();
        System.out.println("WELCOME TO ADDRESS BOOK MANAGEMENT");
        addressBook.createAddressBook();

    }


}
