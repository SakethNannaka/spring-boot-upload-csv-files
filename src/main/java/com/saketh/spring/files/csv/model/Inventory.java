package com.saketh.spring.files.csv.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventories")
public class Inventory {
  @Id
  @Column(name = "code")
  private String code;

  @Column(name = "name")
  private String name;

  @Column(name = "batch")
  private String batch;

  @Column(name = "stock")
  private Long stock;

  @Column(name = "deal")
  private long deal;

  @Column(name = "free")
  private long free;

  @Column(name = "mrp")
  private Double mrp;

  @Column(name = "rate")
  private Double rate;

  @Column(name = "exp")
  private Date expiry;

  @Column(name = "company")
  private String company;

  @Column(name = "supplier")
  private String supplier;

  public Inventory() {

  }
  

  public Inventory(String code, String name, String batch, long stock, long deal, long free, Double mrp, Double rate,
      Date expiry, String company, String supplier) {
    this.code = code;
    this.name = name;
    this.batch = batch;
    this.stock = stock;
    this.deal = deal;
    this.free = free;
    this.mrp = mrp;
    this.rate = rate;
    this.expiry = expiry;
    this.company = company;
    this.supplier = supplier;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBatch() {
    return batch;
  }

  public void setBatch(String batch) {
    this.batch = batch;
  }

  public long getStock() {
    return stock;
  }

  public void setStock(long stock) {
    this.stock = stock;
  }

  public long getDeal() {
    return deal;
  }

  public void setDeal(long deal) {
    this.deal = deal;
  }

  public long getFree() {
    return free;
  }

  public void setFree(long free) {
    this.free = free;
  }

  public Double getMrp() {
    return mrp;
  }

  public void setMrp(Double mrp) {
    this.mrp = mrp;
  }

  public Double getRate() {
    return rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }

  public Date getExpiry() {
    return expiry;
  }

  public void setExpiry(Date expiry) {
    this.expiry = expiry;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getSupplier() {
    return supplier;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  @Override
  public String toString() {
    return "Inventory [batch=" + batch + ", code=" + code + ", company=" + company + ", deal=" + deal + ", expiry="
        + expiry + ", free=" + free + ", mrp=" + mrp + ", name=" + name + ", rate=" + rate + ", stock=" + stock
        + ", supplier=" + supplier + "]";
  }

}
