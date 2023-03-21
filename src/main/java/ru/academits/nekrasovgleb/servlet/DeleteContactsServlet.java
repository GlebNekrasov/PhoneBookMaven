package ru.academits.nekrasovgleb.servlet;

import ru.academits.nekrasovgleb.PhoneBook;
import ru.academits.nekrasovgleb.converter.DeleteValidationConverter;
import ru.academits.nekrasovgleb.converter.IdsConverter;
import ru.academits.nekrasovgleb.service.ContactService;
import ru.academits.nekrasovgleb.service.DeleteValidation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class DeleteContactsServlet extends HttpServlet {
    private ContactService phoneBookService = PhoneBook.phoneBookService;

    private IdsConverter idsConverter = PhoneBook.idsConverter;

    private DeleteValidationConverter deleteValidationConverter = PhoneBook.deleteValidationConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (OutputStream responseStream = resp.getOutputStream()) {
            String idsJson = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            int[] idsToDelete = idsConverter.convertFromJson(idsJson);

            DeleteValidation deleteValidation = phoneBookService.deleteContacts(idsToDelete);
            String deleteValidationJson = deleteValidationConverter.convertToJson(deleteValidation);

            if (!deleteValidation.isValid()) {
                resp.setStatus(500);
            }

            responseStream.write(deleteValidationJson.getBytes(Charset.forName("UTF-8")));
        } catch (Exception e) {
            System.out.println("error in DeleteContactsServlet POST: ");
            e.printStackTrace();
        }
    }
}
