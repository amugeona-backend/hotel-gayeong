package controller;

import service.GuestService;
import service.HotelService;
import service.ManagerService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Scanner;
import static service.HotelService.getHotelService;

public class HotelController {

    HotelService hotelService = getHotelService();
    GuestService guestService = new GuestService(hotelService);
    ManagerService managerService = new ManagerService(hotelService);

    public void run() {
        hotelService.initRoom();
        displayMode();
    }

    public void displayMode() {
        System.out.println("환영합니다. JAVA 호텔 입니다.");
        modeInputHandling();
    }

    public void modeInputHandling() {
        System.out.println();
        System.out.println("Mode 를 선택해주세요.");
        System.out.println("1. Guest Mode");
        System.out.println("2. Manager Mode");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        switch (command) {
            case 1 -> {
                guestService.displayGuestMode();
                modeInputHandling();
            }
            case 2 -> {
                if (passwordCheck()) {
                    managerService.displayManagerMode();
                    modeInputHandling();
                } else {
                    passwordNotMatchMessage();
                    modeInputHandling();
                }
            }
            default -> {
                System.out.println("에러");
                modeInputHandling();
            }
        }
    }

    public boolean passwordCheck() {
        System.out.print("관리자 비밀번호를 입력해주세요: ");
        try {
            Scanner scanner = new Scanner(System.in);
            return encryption(String.valueOf(scanner.nextInt())).equals(hotelService.getHotelPassword());
        } catch (Exception e) {
            return false;
        }
    }

    public void passwordNotMatchMessage() {
        System.out.println("비밀번호가 일치하지 않습니다.");
        System.out.println("시스템이 돌아갑니다.");
    }

    private String encryption(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}