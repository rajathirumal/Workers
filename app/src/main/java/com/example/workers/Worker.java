package com.example.workers;

public class Worker {

    private String UID, name, bookingState, work_state, stars, type,createDate,createTime ;

    public Worker(String UID, String name, String bookingState, String work_state, String stars, String type, String createDate, String createTime) {
        this.UID = UID;
        this.name = name;
        this.bookingState = bookingState;
        this.work_state = work_state;
        this.stars = stars;
        this.type = type;
        this.createDate = createDate;
        this.createTime = createTime;
    }

    public String getUID() {
        return UID;
    }

    public String getName() {
        return name;
    }

    public String getBookingState() {
        return bookingState;
    }

    public String getWork_state() {
        return work_state;
    }

    public String getStars() {
        return stars;
    }

    public String getType() {
        return type;
    }
}
