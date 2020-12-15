package com.Addressbook.GsonLibrary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Application {
    public static void main(String args[]) throws IOException {

        Application application = new Application();
        try {
            PersonDetails personDetails = new PersonDetails();
            personDetails.setName("Neha");
            personDetails.setAge(23);
            application.WriteJSON(personDetails);

            PersonDetails personDetails1 = application.readJSON();
            System.out.println(personDetails1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PersonDetails readJSON() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("E:\\BRIDGE_FELLOWSHIP\\AddressBook\\MultipleAddressBook\\student.JSON"));

        PersonDetails personDetails = gson.fromJson(bufferedReader, PersonDetails.class);
        return personDetails;

    }

    private void WriteJSON(PersonDetails personDetails) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        FileWriter writer = new FileWriter("E:\\BRIDGE_FELLOWSHIP\\AddressBook\\MultipleAddressBook\\student.JSON");
        writer.write(gson.toJson(personDetails));
        writer.close();

    }


}