package com.saketh.spring.files.csv.dao;

import java.util.List;

import com.saketh.spring.files.csv.model.Inventory;
import com.saketh.spring.files.csv.utils.InventoryRowMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class CSVDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final Logger logger = LogManager.getLogger(CSVDao.class);

    public List<Inventory> findBySupplierId(String supplierList, String code, Pageable pageable) {
        boolean value = code == null ? false : true;
        logger.info("pageable :{}", pageable);
        String querySql = (value == false) ? "SELECT * " +
                "FROM inventories " +
                "WHERE supplier in (" + supplierList +
                ")" + " AND code = " + code + " LIMIT " + pageable.getPageSize() + " " +
                "OFFSET " + pageable.getOffset()
                : "SELECT * " +
                        "FROM inventories " +
                        "WHERE supplier in (" + supplierList +
                        ") LIMIT " + pageable.getPageSize() + " " +
                        "OFFSET " + pageable.getOffset();

        logger.info("querySql :{}", querySql);
        List<Inventory> inventories = jdbcTemplate.query(querySql, new InventoryRowMapper());

        return inventories;
    }

}
