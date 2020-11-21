package com.AddressBookManagement;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {

    Scanner sc=new Scanner(System.in);
    public static ArrayList<Person> addressbooklist = new ArrayList<Person>();

    /*UC2: Add Person Details In AddressBook*/
    public void addPerson()
    {
        System.out.println("____Add Person Details____");
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

        Person p=new Person(firstname,lastname,contactno,emailid,address,city,state,zip);
        addressbooklist.add(p);
        System.out.println("AddressBook Data:" +addressbooklist);

    }

    /* UC3: Edit Person Details*/
    public void editPerson()
    {
        System.out.println("\n___Edit the Person Using Name___");
        System.out.println("\nEnter the FirstName And LastName To Find Records.\nFirstName");
        String firstName=sc.nextLine();
        System.out.println("\nLastName");
        String lastName = sc.nextLine();

        int loop=1;
        for (Person person : addressbooklist)
        {
            if (person.getFirstname().equals(firstName)  &&  person.getLastname().equals(lastName))
            {
                System.out.println("\nData Found as per match!!!");
                System.out.println( person.toString());
                while (loop==1)
                {
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

                        System.out.println("address Edited Successfully:" +toString());
                    }

                    else if (field.equals("contact")) {
                        System.out.print("\n Enter contact number : ");
                        String contact = sc.nextLine();
                        person.setContactno(contact);
                        System.out.println("\nContact Edited Successfully :" + person.toString());

                    }

                    else if (field.equals("emailid")){
                        System.out.println("Enter email id");
                        String emailid = sc.nextLine();
                        person.setEmailid(emailid);
                        System.out.println("\nContact Edited Successfuly :" + person.toString());

                    }

                    else if (field.equals("quit")) {
                        loop = 0;
                        System.out.println("\nExit From Edit Functionality");
                    }

                }

            }
            else
            {
                System.out.println("given Wrong Input : Unable to find data!!!!");

            }
        }
    }

    /*UC4 : Delete Person Using Name*/
    public void deletePerson()
    {
        System.out.println("Delete Person Using FirstName And LastName");
        String firstName=sc.nextLine();
        System.out.println("\nLastName");
        String lastName = sc.nextLine();

        for (int i=0;i<addressbooklist.size();i++)
        {
            Person person=(Person) addressbooklist.get(i);
            if ((person.getFirstname().equals(firstName))&& (person.getLastname().equals(lastName)));
            System.out.println("Details Found!!"+person.getFirstname() +person.getLastname());
            addressbooklist.remove(i);
            Display();
            System.out.println("Contact Deleted Successfully:");
        }
    }

    /*Display Function to Show AddressBook Data*/
    public void Display()
    {
        System.out.println("\nDISPLAYING ADDRESS BOOK DATA");
        for (int i=0; i<addressbooklist.size(); i++)
        {
            System.out.println(addressbooklist.get(i));
        }

    }
    @Override
    protected void finalize(){
        sc.close();
    }

    public static void main(String []args)
    {
        AddressBook addressBook=new AddressBook();
        System.out.println("WELCOME TO ADDRESS BOOK MANAGEMENT");
        addressBook.addPerson();
        addressBook.editPerson();
        addressBook.deletePerson();
        addressBook.Display();
    }


}
