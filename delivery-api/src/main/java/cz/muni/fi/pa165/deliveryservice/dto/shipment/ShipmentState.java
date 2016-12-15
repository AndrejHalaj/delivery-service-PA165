package cz.muni.fi.pa165.deliveryservice.dto.shipment;

/**
 * @author Viktor Bako
 */
public enum ShipmentState {
	NEW("NEW"), TRANSFERED("TRANSFERED"), DELIVERED("DELIVERED"), CANCELED("CANCELED");

	public String stringVal;

	ShipmentState(String str) {
		stringVal = str;
	}

	@Override
	public String toString() {
		return stringVal;
	}
}
