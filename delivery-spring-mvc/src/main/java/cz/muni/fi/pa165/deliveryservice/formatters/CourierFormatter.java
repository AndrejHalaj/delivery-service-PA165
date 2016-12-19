package cz.muni.fi.pa165.deliveryservice.formatters;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;

import java.beans.PropertyEditorSupport;

/**
 * Created by Jamik on 18.12.2016.
 */
public class CourierFormatter extends PropertyEditorSupport {

    private static final int ATTR_ID = 0;
    private static final int ATTR_FIRST_NAME = 1;
    private static final int ATTR_LAST_NAME = 2;
    private static final int ATTR_EMAIL = 3;

    private static final int IDX_START_ID = 14;
    private static final int IDX_START_FN = 11;
    private static final int IDX_START_LN = 10;
    private static final int IDX_START_E = 7;

    @Override
    public void setAsText(String text) {

        if(text == null || text.isEmpty()) {
            System.out.println("CourierFormatter::setAsText null or empty text");
            setValue(null);
            return;
        }
        String[] attr = text.split(";");

        CourierDTO courier = new CourierDTO();

        courier.setId(Long.parseLong(attr[ATTR_ID].substring(IDX_START_ID, attr[ATTR_ID].length())));
        courier.setFirstName(attr[ATTR_FIRST_NAME].substring(IDX_START_FN, attr[ATTR_FIRST_NAME].length()));
        courier.setLastName(attr[ATTR_LAST_NAME].substring(IDX_START_LN, attr[ATTR_LAST_NAME].length()));
        courier.setEmail(attr[ATTR_EMAIL].substring(IDX_START_E, attr[ATTR_EMAIL].length()));

        setValue(courier);
    }
}
