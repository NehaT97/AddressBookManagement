package com.AddressBookManagement;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressBook {
    private Scanner sc;
    public static ArrayList<Person> addressbooklist = new ArrayList<Person>();
    ArrayList<com.AddressBookManagement.AddressBookList> stackofaddressbook = new ArrayList<>();
    static String[] HEADINGS = {"FirstName", "LastName", "ContactNo", "EmailId", "Address", "City", "State", "Zip"};
    static String filePath = "E:\\BRIDGE_FELLOWSHIP\\AddressBook\\MultipleAddressBook\\";
    static String filename;
    String bookname;

    /* Uc14 : Using OpenCSV Classes */
    public void createCSVFile(String filePath, String filename) throws IOException {
        try {
            File file = new File(filePath + filename + ".csv");

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());

                try {
                    FileWriter writer = new FileWriter(file);
                    CSVWriter csvWriter = new CSVWriter(writer);
                    csvWriter.writeAll(Collections.singletonList(HEADINGS));
                    //addPerson();
                    csvWriter.close();

                    System.out.println("Successfully wrote to the file.");
                } catch (Exception e) {
                    System.out.println("Failed to create address book");
                    ;
                }

            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void openCsvFilesList() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + "!!! You Can Opened Any Address Book !!!");
        System.out.println("\nAll AddressBook List");
        File file = new File("E:\\BRIDGE_FELLOWSHIP\\AddressBook\\MultipleAddressBook\\");
        File[] filelist = file.listFiles();
        for (File f : filelist) {
            if (f.getName().contains(".csv")) //To get only CSV files
                System.out.println(f.getName());
        }

        System.out.println("\nEnter the Addressbook to open And Write");
        String bookname = scanner.nextLine();
        boolean filefound = false;
        for (File f : filelist) {
            if (f.getName().equals(bookname)) {
                filefound = true;
                System.out.println("[" + f.getName() + " is Opened ]");
                List<List<String>> BookData = readCSVFile(bookname);
                System.out.println(BookData);
            }
        }
        if (filefound == false) {
            System.out.println("\n!! File Not found.First Create it !!");
        }

    }

    public void writeToCSVFile(String personDetails, String data1) {
        try {
            FileWriter myWriter = new FileWriter("E:\\BRIDGE_FELLOWSHIP\\AddressBook\\MultipleAddressBook\\" + data1 + ".csv", true);
            myWriter.write(personDetails);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    public void displayCSV() throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Which Book want to read");
            String read = sc.nextLine();
            List<String> lines = Files.readAllLines(Paths.get("E:\\BRIDGE_FELLOWSHIP\\AddressBook\\MultipleAddressBook\\" + read + ".csv"));
            for (String data : lines) {
                System.out.println(data);
            }
        } catch (Exception e) {
            System.out.println("No data found");
            e.printStackTrace();
        }
    }

    /*Reading Csv File*/
    private List<List<String>> readCSVFile(String bookname) {
        List<List<String>> data = new ArrayList<>();
        try {
            System.out.println("Yes....");
            FileReader filereader = new FileReader(filePath + bookname);
            CSVReader csvReader = new CSVReader(filereader);
            csvReader.forEach(strings -> data.add(Arrays.asList(strings)));
            System.out.println();
            return data;
        } catch (Exception ex) {
            System.out.println("Failed to open file : " + bookname);
        }
        return data;
    }

    /*UC2: Add Person Details In AddressBook*/
    public void addPerson() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Where u want to add data");
        String data1 = scanner.nextLine();
        System.out.println("WELCOME TO ADDPERSON_OPERATION");

        System.out.println("\nEnter the FirstName");
        String firstname = scanner.nextLine();
        duplicateNameCheck(firstname);

        System.out.println("Enter the LastName");
        String lastname = scanner.nextLine();

        System.out.println("Enter the Contact");
        String contactno = scanner.nextLine();

        System.out.println("Enter the Emailid");
        String emailid = scanner.nextLine();

        System.out.println("Enter the Address");
        String address = scanner.nextLine();

        System.out.println("Enter the city");
        String city = scanner.nextLine();

        System.out.println("Enter the state");
        String state = scanner.nextLine();

        System.out.println("Enter the zip");
        String zip = scanner.nextLine();

        //Person p = new Person(firstname, lastname, contactno, emailid, address, city, state, zip);
        //addressbooklist.add(p);
        //System.out.println("Here is ur data "+addressbooklist);
        String Result = personToString(firstname, lastname, contactno, emailid, address, city, state, zip);
        //writeToCSVFile(personToString(firstname,lastname,contactno,emailid,address,city,state,zip),data1);
        writeToCSVFile(Result, data1);
        //System.out.println("AddressBook Data:" + addressbooklist);
    }


    public String personToString(String firstname, String lastname, String contactno, String emailid, String address, String city, String state, String zip) {
        return "\n" + firstname + "," + lastname + "," + contactno + "," + emailid + "," + address + "," + city + "," + state + "," + zip;
    }


    private void createNewAddressBook() {
        sc = new Scanner(System.in);
        System.out.println("!!! Create Address Book Here !!!");
        System.out.println("\nEnter Address Book Name");
        String addressBookName = sc.nextLine();

        com.AddressBookManagement.AddressBookList bookList = new com.AddressBookManagement.AddressBookList(addressBookName);
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
        for (com.AddressBookManagement.AddressBookList list : stackofaddressbook) {
            if (bookname.equals(list.getAddressBookName())) {
                System.out.println("AddressBook " + list.getAddressBookName() + " Is Opened ");
            }
        }
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

    /* UC11: Sort Persons Details By Name*/
    public void sortPersonsDetailsByName() {
        addressbooklist.stream();
        addressbooklist.sort(Comparator.comparing(Person::getFirstname));
        addressbooklist.forEach(System.out::println);

    }

    /* UC12: Sort By City, State And Zip*/
    public void sortByCity_State_Zip() {
        addressbooklist.stream();

        Comparator<Person> CompareByCityStateZip = Comparator.comparing(Person::getCity)
                .thenComparing(Person::getState)
                .thenComparing(Person::getZip);

        List<Person> sortedPersonsData = addressbooklist.stream()
                .sorted(CompareByCityStateZip)
                .collect(Collectors.toList());

        System.out.println("Sorted Data :" + sortedPersonsData);

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
        // addressBook.readCSVFile("final.csv");
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
                    //addressBook.createNewAddressBook();
                    System.out.println("Enter the AddressBook Name To Create");
                    do {
                        filename = sc.nextLine();
                    } while (filename.isEmpty());
                    addressBook.createCSVFile(filePath, filename);
                    break;

                case 2:
                    // addressBook.openAddressBook();
                    Scanner scanner = new Scanner(System.in);
                    addressBook.openCsvFilesList();
                    int loop1 = 1;
                    while (loop1 == 1) {
                        System.out.println("\nADD PERSON DETAILS");
                        System.out.println("1].Add Person" +
                                "\n" + "2].Edit Person" +
                                "\n" + "3].Delete Person" +
                                "\n" + "4].Display" +
                                "\n" + "5].Search_Person_By_City_State" +
                                "\n" + "6].Count_Person_By_City_State" +
                                "\n" + "7].Sort_Person_Details_By_Name" +
                                "\n" + "8].Sort_Person_Details_By_CityStateZip" +
                                "\n" + "9].Quit");

                        System.out.println("\n" + "Enter the choice:");
                        int ch1 = scanner.nextInt();
                        switch (ch1) {
                            case 1:
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
                                addressBook.displayCSV();
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
                                System.out.println("WELCOME TO SORT BY NAME OPERATION");
                                addressBook.sortPersonsDetailsByName();
                                break;

                            case 8:
                                System.out.println("WELCOME TO SORT BY CITY_STatE_ZIP OPERATION");
                                addressBook.sortByCity_State_Zip();
                                break;

                            case 9:
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
