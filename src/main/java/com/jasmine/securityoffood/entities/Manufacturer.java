package com.jasmine.securityoffood.entities;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName("ManufacturerView")
public class Manufacturer {
    private String factu;
    private String area;
    private String principal;
    private String contact;
    private String food;
    public String getFactu() {
        return factu;
    }
    public void setFactu(String factu) {
        this.factu = factu;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getPrincipal() {
        return principal;
    }
    public void setPrincipal(String principal) {
        this.principal = principal;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getFood() {
        return food;
    }
    public void setFood(String food) {
        this.food = food;
    }
    @Override
    public String toString() {
        return "厂商名："+factu+
        "\n厂商地区："+area+
        "\n厂商证书："+principal+
        "\n联系方式："+contact+
        "\n生产食品："+food;
    }
}
