package com.example.space_ucas_ti_asmasyam;

public class Bookable_class {
    String first_name ;
    String last_name ;
    String email ;
    String phone_number ;
    String start_time ;
    String end_time ;
    String duration ;
    String timezone ;
    String rrule ;
    String until ;
    String people ;
    String fees ;
    String currency ;
    String status ;
    String review_sent_at ;
    String reviewed_at ;
    String payment_status ;
    String title ;


    public Bookable_class(){

    }

    public Bookable_class(String first_name, String last_name, String email, String phone_number,
                          String start_time, String end_time, String duration, String timezone,
                          String rrule, String until, String people, String fees, String currency,
                          String status, String review_sent_at,
                          String reviewed_at, String payment_status, String title) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.start_time = start_time;
        this.end_time = end_time;
        this.duration = duration;
        this.timezone = timezone;
        this.rrule = rrule;
        this.until = until;
        this.people = people;
        this.fees = fees;
        this.currency = currency;
        this.status = status;
        this.review_sent_at = review_sent_at;
        this.reviewed_at = reviewed_at;
        this.payment_status = payment_status;
        this.title = title;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getRrule() {
        return rrule;
    }

    public void setRrule(String rrule) {
        this.rrule = rrule;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReview_sent_at() {
        return review_sent_at;
    }

    public void setReview_sent_at(String review_sent_at) {
        this.review_sent_at = review_sent_at;
    }

    public String getReviewed_at() {
        return reviewed_at;
    }

    public void setReviewed_at(String reviewed_at) {
        this.reviewed_at = reviewed_at;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
