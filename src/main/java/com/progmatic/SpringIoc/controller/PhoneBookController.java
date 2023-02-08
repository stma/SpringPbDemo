package com.progmatic.SpringIoc.controller;

import com.progmatic.SpringIoc.model.Contact;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhoneBookController {

    private final List<Contact> contacts = new LinkedList<>();

    public Iterable<Contact> getAllContact() {
        return List.copyOf(contacts);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(int idx) {
        contacts.remove(idx);
    }

    public List<Contact> searchContact(Optional<String> searchText) {
        List<Contact> result = new LinkedList<>();
        if (searchText.isPresent()) {
            for (var c: contacts) {
                if (c.match(searchText.get())) {
                    result.add(c);
                }
            }
        }
        return contacts.isEmpty()
                ? List.of()
                : List.copyOf(result);
    }
}
