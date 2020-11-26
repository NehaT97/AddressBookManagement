package com.AddressBookManagement;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {

    private Scanner sc;
    static String bookname;
    public static ArrayList<Person> addressbooklist = new ArrayList();
    final String CSV_HEADER = "FirstName,LastName,ContactNumber,City,State,Zip";

    private void CreateNewAddressBook() {
        sc=new Scanner(System.in);
        System.out.println("!!! Create Address Book Here !!!");
        System.out.println("\nEnter Address Book Name");
        String filename = sc.nextLine();
        try {

            File file = new File("E:\\BRIDGE_FELLOWSHIP\\AddressBook\\MultipleAddressBook\\" + filename + ".csv");
            if (file.createNewFile()) {
                System.out.println("\nAddress Book is created : " + file.getName());
            } else {
                System.out.println("\nAddress Book Already Exist");
            }

        } catch (IOException e) {
            System.out.println("\nError Occurred During Creation");
            e.printStackTrace();
        }
    }

    public void OpenAddressBook() {
        sc=new Scanner(System.in);
        System.out.println("\n" + "!!! You Can Opened Any Address Book !!!");
        System.out.println("\nAll AddressBook List");
        File file = new File("E:\\BRIDGE_FELLOWSHIP\\AddressBook\\MultipleAddressBook\\");
        File[] filelist = file.listFiles();
        for (File f : filelist) {
            if (f.getName().contains(".csv"))
                System.out.println(f.getName());
        }

        System.out.println("\nEnter the Addressbook To Open");
        String bookname = sc.nextLine();
        boolean filefound = false;
        for (File f : filelist) {
            if (f.getName().equals(bookname)) {
                filefound = true;
                System.out.println("[" + f.getName() + " is Opened ]");

            }
        }
        if (filefound == false) {
            System.out.println("\n!! AddressBook Not found.First Create it !!");
        }

    }

    public void SaveAddressBook ()  {
       System.out.println("Here you can Save the data in Address book");
        FileWriter writer = null;
        try {
            writer = new FileWriter(bookname);
            writer.append(CSV_HEADER);
            writer.append("\n");
            for (Person p : AddressBook.addressbooklist)
            {
                writer.append(p.getFirstname());
                writer.append(",");
                writer.append(p.getLastname());
                writer.append(",");
                writer.append(p.getContactno());
                writer.append(",");
                writer.append(p.getEmailid());
                writer.append(",");
                writer.append(p.getCity());
                writer.append(",");
                writer.append(p.getState());
                writer.append(",");
                writer.append(p.getZip());
                writer.append("\n");
            }
            System.out.println("Data Saved....");

        } catch (IOException e) {
            System.out.println("!! Writing CSV Error !!");
            e.printStackTrace();
        }finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Flushing/closing error!");
                e.printStackTrace();
            }
        }
    }

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
        sc = new Scanner(System.in);
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
        sc = new Scanner(System.in);
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
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        int loop = 1;
        while (loop == 1) {
            System.out.println("\n-----WELCOME TO ADDRESS BOOK MANAGEMENT-----");
            System.out.println("\n" + "ADDRESS BOOK MENU" + "\n"
                    + "1].Create address Book" + "\n"
                    + "2].Open Existing address book" + "\n"
                    + "3].Save Address Book" + "\n"
                    + "4].Quit");
            System.out.println("\nSelect any one choice");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addressBook.CreateNewAddressBook();
                    break;

                case 2:
                    addressBook.OpenAddressBook();
                    int loop1=1;
                    while (loop1==1) {
                        System.out.println("\nADD PERSON DETAILS");
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
                                loop1 = 0;


                            default:
                                System.out.println(" Back To Main Menu!!!");
                        }
                    }
                    break;

                case 3:
                    addressBook.SaveAddressBook();
                    break;

                case 4:
                    loop = 0;

                default:
                    System.out.println("Back To Main Menu : Wrong Choice!!!");

            }
        }
    }
}
