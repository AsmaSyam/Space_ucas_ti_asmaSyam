package com.example.space_ucas_ti_asmasyam;

public class Status_class {

    String booking_id ;
    String status ;
    String notes ;

    public  Status_class(){

    }

    public Status_class(String booking_id, String status, String notes) {
        this.booking_id = booking_id;
        this.status = status;
        this.notes = notes;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
