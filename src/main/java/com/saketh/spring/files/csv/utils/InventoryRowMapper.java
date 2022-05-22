package com.saketh.spring.files.csv.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.saketh.spring.files.csv.model.Inventory;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class InventoryRowMapper implements RowMapper<Inventory> {
    public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setCode(rs.getString("code"));
        inventory.setName(rs.getString("name"));
        inventory.setBatch(rs.getString("batch"));
        inventory.setStock(rs.getLong("stock"));
        inventory.setDeal(rs.getLong("deal"));
        inventory.setFree(rs.getLong("free"));
        inventory.setMrp(rs.getDouble("mrp"));
        inventory.setRate(rs.getDouble("rate"));
        java.util.Date  utilDate = new java.util.Date(rs.getDate("exp").getTime());
        inventory.setExpiry(utilDate);
        inventory.setCompany(rs.getString("company"));
        inventory.setSupplier(rs.getString("supplier"));
        return inventory;
    }
}
