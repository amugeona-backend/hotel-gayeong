package service;

import model.ProductRoom;

public class ProductRoomService {
    public void changeReservationState(ProductRoom productRoom, boolean reservation) {
        productRoom.setReserved(reservation);
    }
}
