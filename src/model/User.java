package model;

import static constant.UserConstant.USER_DEFAULT_POINT;

public class User {
    private final String name;
    private final String phoneNumber;
    private int point = USER_DEFAULT_POINT;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
