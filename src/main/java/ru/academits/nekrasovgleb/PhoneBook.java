package ru.academits.nekrasovgleb;

import ru.academits.nekrasovgleb.converter.ContactConverter;
import ru.academits.nekrasovgleb.converter.ContactValidationConverter;
import ru.academits.nekrasovgleb.converter.DeleteValidationConverter;
import ru.academits.nekrasovgleb.converter.IdsConverter;
import ru.academits.nekrasovgleb.dao.ContactDao;
import ru.academits.nekrasovgleb.service.ContactService;

/**
 * Created by Anna on 14.06.2017.
 */
public class PhoneBook {

    public static ContactDao contactDao = new ContactDao();

    public static ContactService phoneBookService = new ContactService();

    public static ContactConverter contactConverter = new ContactConverter();

    public static ContactValidationConverter contactValidationConverter = new ContactValidationConverter();

    public static IdsConverter idsConverter = new IdsConverter();

    public static DeleteValidationConverter deleteValidationConverter = new DeleteValidationConverter();
}
