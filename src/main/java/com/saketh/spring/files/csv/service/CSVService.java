package com.saketh.spring.files.csv.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.saketh.spring.files.csv.dao.CSVDao;
import com.saketh.spring.files.csv.helper.CSVHelper;
import com.saketh.spring.files.csv.model.Inventory;
import com.saketh.spring.files.csv.repository.InventoryRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
  @Autowired
  InventoryRepository repository;

  @Autowired
  CSVDao csvDao;
	private static final Logger logger = LogManager.getLogger(CSVService.class);

  public void save(MultipartFile file) {
    try {
      List<Inventory> inventories = CSVHelper.csvToInventory(file.getInputStream());
      logger.info("inventories :{}",inventories);
      repository.saveAll(inventories);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<Inventory> inventories = repository.findAll();

    ByteArrayInputStream in = CSVHelper.inventoriesToCSV(inventories);
    return in;
  }

  public List<Inventory> getAllInventories() {
    return repository.findAll();
  }

  public List<Inventory> getInventoriesBySupplierId(String suppliersList,String code,Pageable pageable){
    return csvDao.findBySupplierId(suppliersList,code,pageable);
  }
  
}
