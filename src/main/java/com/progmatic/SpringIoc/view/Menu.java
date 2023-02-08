package com.progmatic.SpringIoc.view;

import com.progmatic.SpringIoc.controller.PhoneBookController;
import com.progmatic.SpringIoc.model.Contact;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class Menu {

    private final Scanner sc;
    private final PhoneBookController controller;

    public Menu(PhoneBookController controller) {
        this.controller = controller;
        this.sc = new Scanner(System.in);
    }

    private void printMenu() {
        System.out.println("u - Uj kapcsolat");
        System.out.println("s - Kereses");
        System.out.println("l - Kapcsolatok listaja");
        System.out.println("k - Kilepes");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void mainMenu() {
        try (sc) {
            System.out.println("*".repeat(30));
            System.out.println("*" + StringUtils.center("PhoneBook Prog", 28) + "*");
            System.out.println("*".repeat(30) + "\n");

            String s;
            this.printMenu();
            while (!(s = sc.nextLine()).equalsIgnoreCase("k")) {
                switch (s.toLowerCase()) {
                    case "u" -> addContact();
                    case "s" -> searchContacts();
                    case "l" -> listContacts();
                    default -> System.out.println("Ilyen menuelem nincs, kerem valasszon ujra.\n");
                }
                this.printMenu();
            }
        }
    }

    private void addContact() {
        Contact c = new Contact();
        System.out.println("\n\tÚj kapcsolat felvétele:");
        System.out.println("Nev: ");
        c.setName(sc.nextLine());
        System.out.println("Tel: ");
        c.setTel(sc.nextLine());
        System.out.println("Email: ");
        c.setEmail(sc.nextLine());
        System.out.println("Megjegyzes: ");
        c.setMisc(sc.nextLine());

        controller.addContact(c);
    }

    private void listContacts() {
        Iterable<Contact> allC = controller.getAllContact();

        printContacts(allC);
    }

    private static void printContacts(Iterable<Contact> allC) {
        int hasItems = 0;
        Contact first = null;

        for (Contact c : allC) {
            if (hasItems == 0) {
                first = c;
            }

            System.out.println(c);
            System.out.println();

            hasItems += 1;
        }

        if (hasItems == 0) {
            System.out.println("** Nincsenek kapcsolatok még.");
        }
//        if (hasItems == 1) {
//            printModifyMenu(first);
//        }
        System.out.println();
    }

    private void searchContacts() {
        System.out.println("\n\tMit keresunk? ");
        String text = sc.nextLine();
        Optional<String> st = Optional.empty();
        if (!text.isEmpty()) {
            st = Optional.of(text);
        }

        printContacts(controller.searchContact(st));
    }
}
