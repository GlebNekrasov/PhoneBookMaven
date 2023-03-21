package ru.academits.nekrasovgleb.service;

import ru.academits.nekrasovgleb.PhoneBook;
import ru.academits.nekrasovgleb.dao.ContactDao;
import ru.academits.nekrasovgleb.model.Contact;

import java.util.List;


public class ContactService {
    private ContactDao contactDao = PhoneBook.contactDao;

    private boolean isExistContactWithPhone(String phone) {
        List<Contact> contactList = contactDao.getAllContacts();
        for (Contact contact : contactList) {
            if (contact.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public ContactValidation validateContact(Contact contact) {
        ContactValidation contactValidation = new ContactValidation();
        contactValidation.setValid(true);
        if (contact.getFirstName().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Имя должно быть заполнено.");
            return contactValidation;
        }

        if (contact.getLastName().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Фамилия должно быть заполнено.");
            return contactValidation;
        }

        if (contact.getPhone().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Телефон должно быть заполнено.");
            return contactValidation;
        }

        if (isExistContactWithPhone(contact.getPhone())) {
            contactValidation.setValid(false);
            contactValidation.setError("Номер телефона не должен дублировать другие номера в телефонной книге.");
            return contactValidation;
        }
        return contactValidation;
    }

    public ContactValidation addContact(Contact contact) {
        ContactValidation contactValidation = validateContact(contact);
        if (contactValidation.isValid()) {
            contactDao.add(contact);
        }
        return contactValidation;
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public DeleteValidation validateDelete(int[] ids) {
        DeleteValidation deleteValidation = new DeleteValidation();
        deleteValidation.setValid(true);

        if (ids.length == 0) {
            deleteValidation.setValid(false);
            deleteValidation.setError("Для удаления нужно передать хотя бы один id контакта.");
            return deleteValidation;
        }

        return deleteValidation;
    }

    public DeleteValidation deleteContacts(int[] idsToDelete) {
        DeleteValidation deleteValidation = validateDelete(idsToDelete);

        if (deleteValidation.isValid()) {
            contactDao.delete(idsToDelete);
        }

        return deleteValidation;
    }
}
