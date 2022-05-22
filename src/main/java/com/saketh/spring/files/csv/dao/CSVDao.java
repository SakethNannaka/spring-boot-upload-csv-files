package com.saketh.spring.files.csv.dao;

import java.util.List;

import com.saketh.spring.files.csv.model.Inventory;
import com.saketh.spring.files.csv.utils.InventoryRowMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

@Configuration
public class CSVDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final Logger logger = LogManager.getLogger(CSVDao.class);

    public List<Inventory> findBySupplierId(String supplierList, String code, Pageable pageable) {
        boolean value = StringUtils.isEmpty(code)? false : true;
        logger.info("value :{}", value);
        String querySql = (value == true) ? "SELECT * " +
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

    public List<Inventory> findByFilter(String suppliers, String code, String expiry, Pageable paging) {
        boolean value = StringUtils.isEmpty(expiry) ? false : true;
        boolean flag = StringUtils.isEmpty(code)? false : true;
        logger.info("code :{},flag :{}",code,flag);
        String querySql = (value == true) ? ((flag == false) ? ("SELECT * " +
                "FROM inventories " +
                "WHERE supplier in (" + suppliers +
                ")" + " AND exp < " + expiry + " LIMIT " + paging.getPageSize() + " " +
                "OFFSET " + paging.getOffset())
                : "SELECT * " +
                        "FROM inventories " +
                        "WHERE supplier in (" + suppliers +
                        ")" + " AND exp < " + expiry + " AND code = " + code + " LIMIT " + paging.getPageSize() + " " +
                        "OFFSET " + paging.getOffset())
                : (flag == false) ? ("SELECT * " +
                        "FROM inventories " +
                        "WHERE supplier in (" + suppliers +
                        ") LIMIT " + paging.getPageSize() + " " +
                        "OFFSET " + paging.getOffset())
                        : ("SELECT * " +
                                "FROM inventories " +
                                "WHERE supplier in (" + suppliers +
                                ")" + " AND code = " + code + " LIMIT " + paging.getPageSize() + " " +
                                "OFFSET " + paging.getOffset());

        logger.info("querySql :{}", querySql);
        List<Inventory> inventories = jdbcTemplate.query(querySql, new InventoryRowMapper());

        return inventories;
    }

}
