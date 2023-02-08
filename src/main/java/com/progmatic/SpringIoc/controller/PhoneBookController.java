package com.progmatic.SpringIoc.controller;

import com.progmatic.SpringIoc.model.Contact;
import com.progmatic.SpringIoc.model.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhoneBookController {

    private ContactRepository contactRepository;

    public PhoneBookController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional
    public Iterable<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    @Transactional
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void removeContact(Long idx) {
        contactRepository.deleteById(idx);
    }

    public List<Contact> searchContact(Optional<String> searchText) {
        List<Contact> result = new LinkedList<>();

        if (searchText.isPresent()) {
            result = contactRepository.findByAll(searchText.get());
        }

        return result.isEmpty()
                ? List.of()
                : List.copyOf(result);
    }
}
