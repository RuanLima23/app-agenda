package br.com.agenda.controller;

import br.com.agenda.persistence.entity.ContactEntity;
import br.com.agenda.persistence.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app-agenda/api/contacts/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getAllContacts")
    public List<ContactEntity> getAllContacts() {
        List<ContactEntity> contactList = contactRepository.findAll();
        return contactList;
    }

    @PostMapping("/")
    public ContactEntity addContact(@RequestBody ContactEntity contact) {
        ContactEntity newContact = contactRepository.save(contact);
        return newContact;
    }
}
