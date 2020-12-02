package com.AddressBookManagement;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressBook {
    private Scanner sc;
    public static ArrayList<Person> addressbooklist = new ArrayList<Person>();
    ArrayList<AddressBookList> stackofaddressbook = new ArrayList<>();

    /* UC6: Add Multiple AddressBook*/
    private void createNewAddressBook() {
        sc = new Scanner(System.in);
        System.out.println("!!! Create Address Book Here !!!");
        System.out.println("\nEnter Address Book Name");
        String addressBookName = sc.nextLine();

        AddressBookList bookList = new AddressBookList(addressBookName);
        stackofaddressbook.add(bookList);
        System.out.println("Address Book Is Created :" + stackofaddressbook.toString());

    }

    public void displayAddressBookList() {
        System.out.println("\nAll AddressBook List");
        for (int i = 0; i < stackofaddressbook.size(); i++) {
            System.out.println(stackofaddressbook.get(i));
        }
    }

    public void openAddressBook() throws IOException {
        sc = new Scanner(System.in);
        displayAddressBookList();
        System.out.println("Enter The Name to Open AddressBook:");
        String bookname = sc.nextLine();
        for (AddressBookList list : stackofaddressbook) {
            if (bookname.equals(list.getAddressBookName())) {
                System.out.println("AddressBook " + list.getAddressBookName() + " Is Opened ");
            }
        }
    }

    /*UC2: Add Person Details In AddressBook*/
    public void addPerson() {
        sc = new Scanner(System.in);
        System.out.println("\nEnter the FirstName");
        String firstname = sc.nextLine();
        duplicateNameCheck(firstname);

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

    /* UC7: To Check Duplicate Name Is Present */
    public void duplicateNameCheck(String firstname) {
        for (Person person : addressbooklist) {
            if (person.getFirstname().equals(firstname)) {
                System.out.println("Person " + firstname + "is present , add another Person");
                addPerson();
            } else {
                System.out.println("Person is Added");
            }
        }
    }

    /* UC8: Search Person By City_State And View Data */
    public void searchPerson_By_City_State() {
        sc = new Scanner(System.in);
        Stream<ArrayList> stream = Stream.of(addressbooklist);
        stream.forEach(System.out::println);

        System.out.println("Enter City To Search Person");
        String city = sc.nextLine();
        System.out.println("Enter State To Search Person");
        String state = sc.nextLine();
        addressbooklist.stream()
                .filter(addressbooklist -> addressbooklist.getCity().equals(city))
                .filter(addressbooklist -> addressbooklist.getState().equals(state))
                .forEach(addressBookList1 -> System.out.println(addressBookList1));

        addressbooklist.stream()
                .filter(addressbooklist -> !addressbooklist.getCity().equals(city))
                .filter(addressbooklist -> !addressbooklist.getState().equals(state));

        System.out.println("Sorry Data Not Found Related to City or State");
    }

    /* UC10: Count By City And State */
    public void countByCity_State() {
        System.out.println(addressbooklist.stream().collect(Collectors.groupingBy((Person p) -> p.getCity(), Collectors.counting())));
        System.out.println(addressbooklist.stream().collect(Collectors.groupingBy((Person p) -> p.getState(), Collectors.counting())));
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
            //Display();
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

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String addressBookName;
        AddressBook addressBook = new AddressBook();
        //AddressBookManagement addressBookManagement = new AddressBookManagement();
        int loop = 1;
        while (loop == 1) {
            System.out.println("\n-----WELCOME TO ADDRESS BOOK MANAGEMENT-----");
            System.out.println("\n" + "ADDRESS BOOK MENU" + "\n"
                    + "1].Create address Book" + "\n"
                    + "2].Open Existing address book" + "\n"
                    + "3].Quit");
            System.out.println("\nSelect any one choice");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addressBook.createNewAddressBook();
                    break;

                case 2:
                    addressBook.openAddressBook();
                    int loop1 = 1;
                    while (loop1 == 1) {
                        System.out.println("\nADD PERSON DETAILS");
                        System.out.println("1].Add Person" +
                                "\n" + "2].Edit Person" +
                                "\n" + "3].Delete Person" +
                                "\n" + "4].Display" +
                                "\n" + "5].Search_Person_By_City_State" +
                                "\n" + "6].Count_Person_By_City_State" +
                                "\n" + "7].Quit");

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
                                System.out.println("WELCOME TO SEARCH OPERATION");
                                addressBook.searchPerson_By_City_State();
                                break;

                            case 6:
                                System.out.println("WELCOME TO COUNT OPERATION");
                                addressBook.countByCity_State();
                                break;

                            case 7:
                                loop1 = 0;

                            default:
                                System.out.println(" Back To Main Menu!!!");
                        }
                    }
                    break;

                case 3:
                    loop = 0;

                default:
                    System.out.println("Back To Main Menu : Wrong Choice!!!");
            }

        }

    }


}
