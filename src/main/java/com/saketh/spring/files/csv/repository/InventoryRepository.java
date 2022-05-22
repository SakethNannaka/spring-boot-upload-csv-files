package com.saketh.spring.files.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.saketh.spring.files.csv.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findBySupplier(String supplier);
    

    // @Query("select * from inventories where supplier in (?1) ")
    // Inventory findBySupplierId(String supplierList);

}
