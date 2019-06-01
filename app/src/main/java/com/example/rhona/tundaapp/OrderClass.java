package com.example.rhona.tundaapp;

/**
 * Created by Rhona on 5/31/2019.
 */
public class OrderClass {

    private int id;
    private String prodname, prodqty, prodprice, phone;

    public OrderClass(int id, String prodname, String prodqty, String prodprice, String phone) {
        this.id = id;
        this.prodname = prodname;
        this.prodqty = prodqty;
        this.prodprice = prodprice;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProdqty() {
        return prodqty;
    }

    public void setProdqty(String prodqty) {
        this.prodqty = prodqty;
    }

    public String getProdprice() {
        return prodprice;
    }

    public void setProdprice(String prodprice) {
        this.prodprice = prodprice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
