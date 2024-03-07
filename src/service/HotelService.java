package service;

import model.Hotel;
import model.Reservation;
import model.ProductRoom;
import constant.RoomType;
import model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HotelService {
    Hotel hotel = new Hotel();
    private static HotelService hotelService;

    public static HotelService getHotelService() {
        if (hotelService == null) {
            hotelService = new HotelService();
        }
        return hotelService;
    }

    public void initRoom() {
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 7; i++) {
            hotel.getProductRooms().add(new ProductRoom(RoomType.STANDARD, 30000, 101, localDate.plusDays(i)));
            hotel.getProductRooms().add(new ProductRoom(RoomType.SUPERIOR, 40000, 102, localDate.plusDays(i)));
            hotel.getProductRooms().add(new ProductRoom(RoomType.DELUXE, 50000, 103, localDate.plusDays(i)));
            hotel.getProductRooms().add(new ProductRoom(RoomType.SUITE, 60000, 104, localDate.plusDays(i)));
        }
    }

    public String getHotelPassword() {
        return hotel.getPassword();
    }

    public List<LocalDate> findAvailableDays() {
        return hotel.getProductRooms().stream()
                .map(ProductRoom::getReservedDate)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<ProductRoom> findProductRoomByDate(LocalDate date) {
        return hotel.getProductRooms().stream()
                .filter(room -> room.getReservedDate().equals(date))
                .toList();
    }

    public User findUserByPhoneNumber(String phoneNumber) {
        return hotel.getUsers().stream()
                .filter(u -> u.getPhoneNumber().equals(phoneNumber))
                .findFirst().orElse(null);
    }

    public List<Reservation> findReservationByDate(LocalDate date) {
        return hotel.getReservations().stream()
                .filter(reservation -> reservation.productRoom().getReservedDate().equals(date))
                .toList();
    }

    public List<Reservation> findReservationByName(String name) {
        return hotel.getReservations().stream()
                .filter(reservation -> reservation.userName().equals(name))
                .toList();
    }

    public List<Reservation> findReservationByPhoneNumber(String phoneNumber) {
        return hotel.getReservations().stream()
                .filter(reservation -> reservation.userPhoneNumber().equals(phoneNumber))
                .toList();
    }

    public void addReservation(Reservation reservation) {
        hotel.getReservations().add(reservation);

    }

    public void cancelReservation(Reservation reservation) {
        hotel.getReservations().remove(reservation);
    }

    public void addUser(User user) {
        hotel.getUsers().add(user);
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
        return Pattern.matches(pattern, phoneNumber);
    }

    public boolean existsPhoneNumber(String phoneNumber) {
        return hotel.getUsers().stream().anyMatch(u -> u.getPhoneNumber().equals(phoneNumber));
    }

    public void addAsset(int price) {
        hotel.setAsset(hotel.getAsset() + price);
    }

    public void deductAsset(int price) {
        hotel.setAsset(hotel.getAsset() - price);
    }

    public int getAsset() {
        return hotel.getAsset();
    }

    public List<ProductRoom> findEmptyProductRoomByDate(LocalDate date) {
        return hotel.getProductRooms().stream()
                .filter(room -> room.getReservedDate().equals(date))
                .filter(empty -> !empty.isReserved())
                .toList();
    }

    public boolean findReservationByExistingName(String name) {
        return hotel.getReservations().stream()
                .anyMatch(reservation -> reservation.userName().equals(name));
    }

    public boolean findReservationByExistingPhoneNumber(String phone) {
        return hotel.getReservations().stream()
                .anyMatch(reservation -> reservation.userPhoneNumber().equals(phone));
    }

    public boolean findReservationByExistingDate(LocalDate date) {
        return hotel.getReservations().stream()
                .anyMatch(reservation -> reservation.productRoom().getReservedDate().equals(date));
    }
}
