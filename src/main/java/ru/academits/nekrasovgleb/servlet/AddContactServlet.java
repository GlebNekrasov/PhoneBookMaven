package ru.academits.nekrasovgleb.servlet;

import ru.academits.nekrasovgleb.PhoneBook;
import ru.academits.nekrasovgleb.converter.ContactConverter;
import ru.academits.nekrasovgleb.converter.ContactValidationConverter;
import ru.academits.nekrasovgleb.model.Contact;
import ru.academits.nekrasovgleb.service.ContactValidation;
import ru.academits.nekrasovgleb.service.ContactService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class AddContactServlet extends HttpServlet {

    private ContactService phoneBookService = PhoneBook.phoneBookService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;
    private ContactValidationConverter contactValidationConverter = PhoneBook.contactValidationConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (OutputStream responseStream = resp.getOutputStream()) {
            String contactJson = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            Contact contact = contactConverter.convertFromJson(contactJson);

            ContactValidation contactValidation = phoneBookService.addContact(contact);
            String contactValidationJson = contactValidationConverter.convertToJson(contactValidation);
            if (!contactValidation.isValid()) {
                resp.setStatus(500);
            }

            responseStream.write(contactValidationJson.getBytes(Charset.forName("UTF-8")));
        } catch (Exception e) {
            System.out.println("error in AddContactServlet POST: ");
            e.printStackTrace();
        }
    }
}