package cz.muni.fi.pa165.deliveryservice.formatters;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;

import java.beans.PropertyEditorSupport;

/**
 * Created by Jamik on 19.12.2016.
 */
public class ProductFormatter extends PropertyEditorSupport{

    private static final int ATTR_ID = 0;
    private static final int ATTR_NAME = 1;
    private static final int ATTR_DESC = 2;
    private static final int ATTR_PROD = 3;
    private static final int ATTR_WEIGHT = 4;
    private static final int ATTR_SHIP_ID = 5;

    private static final int IDX_START_ID = 14;
    private static final int IDX_START_NAME = 6;
    private static final int IDX_START_DESC = 13;
    private static final int IDX_START_PROD = 10;
    private static final int IDX_START_WEIGHT = 8;
    private static final int IDX_START_SHIP_ID = 12;


    @Override
    public void setAsText(String text){
        if(text == null || text.isEmpty()){
            System.out.println("ProductFormater::setAsText() text is null or empty");
            setValue(null);
            return;
        }

        String[] attrs = text.split(";");

        ProductDTO product = new ProductDTO();
        product.setId(Long.parseLong(attrs[ATTR_ID].substring(IDX_START_ID, attrs[ATTR_ID].length())));
        product.setName(attrs[ATTR_NAME].substring(IDX_START_NAME, attrs[ATTR_NAME].length()));
        product.setDescription(attrs[ATTR_DESC].substring(IDX_START_DESC, attrs[ATTR_DESC].length()));
        product.setProducer(attrs[ATTR_PROD].substring(IDX_START_PROD, attrs[ATTR_PROD].length()));
        product.setWeight(Double.parseDouble(attrs[ATTR_WEIGHT].substring(IDX_START_WEIGHT, attrs[ATTR_WEIGHT].length())));
        String shipmentId = attrs[ATTR_SHIP_ID].substring(IDX_START_SHIP_ID, attrs[ATTR_SHIP_ID].length()-1);
        product.setShipmentId(shipmentId.compareTo("null") == 0 ? null : Long.parseLong(shipmentId));

        System.out.println("ProductFormatter::setAsText()-->" + product.toString());
        setValue(product);

    }
}
