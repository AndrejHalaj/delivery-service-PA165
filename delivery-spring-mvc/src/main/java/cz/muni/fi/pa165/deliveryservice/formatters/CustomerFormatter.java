package cz.muni.fi.pa165.deliveryservice.formatters;

import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDetailDTO;

import java.beans.PropertyEditorSupport;

/**
 * Created by Jamik on 18.12.2016.
 */
public class CustomerFormatter extends PropertyEditorSupport {

    private static final int ATTR_ID = 0;
    private static final int ATTR_FIRST_NAME = 1;
    private static final int ATTR_LAST_NAME = 2;
    private static final int ATTR_HOUSE_NUM = 3;
    private static final int ATTR_POSTAL_CODE = 4;
    private static final int ATTR_CITY = 5;
    private static final int ATTR_COUNTRY = 6;
    private static final int ATTR_PHONE_NUM = 7;
    private static final int ATTR_EMAIL = 8;

    private static final int IDX_START_ID = 21;
    private static final int IDX_START_FN = 11;
    private static final int IDX_START_LN = 10;
    private static final int IDX_START_HN = 13;
    private static final int IDX_START_PC = 12;
    private static final int IDX_START_CTY = 6;
    private static final int IDX_START_CRY = 9;
    private static final int IDX_START_PN = 13;
    private static final int IDX_START_E = 7;


    @Override
    public void setAsText(String text){
        if(text == null || text.isEmpty()) {
            System.out.println("CustomerFormatter::setAsText() text is null or empty");
            setValue(null);
            return;
        }

        CustomerDetailDTO customer = new CustomerDetailDTO();

        String[] attr = text.split(";");
        System.out.println("Array listed:" + text);
        System.out.println("start:" + IDX_START_ID + ",end:"+(attr[ATTR_ID].length()));
        customer.setId(Long.parseLong(attr[ATTR_ID].substring(IDX_START_ID, attr[ATTR_ID].length())));
        customer.setFirstName(attr[ATTR_FIRST_NAME].substring(IDX_START_FN, attr[ATTR_FIRST_NAME].length()));
        customer.setLastName(attr[ATTR_LAST_NAME].substring(IDX_START_LN, attr[ATTR_LAST_NAME].length()));
        customer.setHouseNumber(attr[ATTR_HOUSE_NUM].substring(IDX_START_HN, attr[ATTR_HOUSE_NUM].length()));
        customer.setPostalCode(attr[ATTR_POSTAL_CODE].substring(IDX_START_PC, attr[ATTR_POSTAL_CODE].length()));
        customer.setCity(attr[ATTR_CITY].substring(IDX_START_CTY, attr[ATTR_CITY].length()));
        customer.setCountry(attr[ATTR_COUNTRY].substring(IDX_START_CRY, attr[ATTR_COUNTRY].length()));
        customer.setPhoneNumber(attr[ATTR_PHONE_NUM].substring(IDX_START_PN, attr[ATTR_PHONE_NUM].length()));
        customer.setEmailAddress(attr[ATTR_EMAIL].substring(IDX_START_E, attr[ATTR_EMAIL].length()-1));

        System.out.println("CustomerFormatter::setAsText->" + customer.toString());

        setValue(customer);
    }
}
