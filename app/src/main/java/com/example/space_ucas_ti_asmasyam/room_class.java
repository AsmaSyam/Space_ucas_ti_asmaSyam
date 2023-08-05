package com.example.space_ucas_ti_asmasyam;

public class room_class {

    String name ;
    String code ;
    String capacity ;
    String width ;
    String length ;
    String bookable ;
    String base_cost ;
    String unit_cost ;
    String rates ;
    String minimum_term ;
    String maximum_term;
    String image_bath ;
    String rating ;
    String total_reviews ;
    String status ;
    String cancel_until ;
    String location ;
    String type ;

    String documentId ;


    public room_class() {

    }

    public room_class(String name, String code, String capacity, String width,
                      String length, String bookable, String base_cost,
                      String unit_cost, String rates, String minimum_term, String maximum_term,
                      String image_bath, String rating,
                      String total_reviews, String status, String cancel_until, String location, String type) {
        this.name = name;
        this.code = code;
        this.capacity = capacity;
        this.width = width;
        this.length = length;
        this.bookable = bookable;
        this.base_cost = base_cost;
        this.unit_cost = unit_cost;
        this.rates = rates;
        this.minimum_term = minimum_term;
        this.maximum_term = maximum_term;
        this.image_bath = image_bath;
        this.rating = rating;
        this.total_reviews = total_reviews;
        this.status = status;
        this.cancel_until = cancel_until;
        this.location = location;
        this.type = type;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getBookable() {
        return bookable;
    }

    public void setBookable(String bookable) {
        this.bookable = bookable;
    }

    public String getBase_cost() {
        return base_cost;
    }

    public void setBase_cost(String base_cost) {
        this.base_cost = base_cost;
    }

    public String getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(String unit_cost) {
        this.unit_cost = unit_cost;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getMinimum_term() {
        return minimum_term;
    }

    public void setMinimum_term(String minimum_term) {
        this.minimum_term = minimum_term;
    }

    public String getMaximum_term() {
        return maximum_term;
    }

    public void setMaximum_term(String maximum_term) {
        this.maximum_term = maximum_term;
    }

    public String getImage_bath() {
        return image_bath;
    }

    public void setImage_bath(String image_bath) {
        this.image_bath = image_bath;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTotal_reviews() {
        return total_reviews;
    }

    public void setTotal_reviews(String total_reviews) {
        this.total_reviews = total_reviews;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCancel_until() {
        return cancel_until;
    }

    public void setCancel_until(String cancel_until) {
        this.cancel_until = cancel_until;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
