package model;

import constant.RoomType;

public class Room {
    private final RoomType roomType;
    private final int cost;

    public Room(RoomType roomType, int cost) {
        this.roomType = roomType;
        this.cost = cost;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getCost() {
        return cost;
    }
}