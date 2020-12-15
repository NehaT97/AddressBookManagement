package com.AddressBookManagement;

public class AddressBookList {
    private String AddressBookName;

    public AddressBookList(String addressBookName) {
        AddressBookName = addressBookName;
    }

    public String getAddressBookName() {
        return AddressBookName;
    }

    public void setAddressBookName(String addressBookName) {
        AddressBookName = addressBookName;
    }

    @Override
    public String toString() {
        return AddressBookName ;
    }
}
