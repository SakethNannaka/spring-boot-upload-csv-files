package com.saketh.spring.files.csv.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.saketh.spring.files.csv.model.Inventory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
  private static final Logger logger = LogManager.getLogger(CSVHelper.class);

  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Id", "Title", "Description", "Published" };

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Inventory> csvToInventory(InputStream is) {
    logger.info("In  csvToInventory");
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Inventory> inventories = new ArrayList<Inventory>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
        java.util.Date date;
        try {
          date = sdf1.parse(csvRecord.get("exp"));
        }catch (ParseException e) {
          logger.info("fail to parse expiry time: {} " + e.getMessage());
          continue;
        }
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        Inventory inventory = new Inventory(
        csvRecord.get("code"),
        csvRecord.get("name"),
        csvRecord.get("batch"),
        Long.parseLong(csvRecord.get("stock")),
        Long.parseLong(csvRecord.get("deal")),
        Long.parseLong(csvRecord.get("free")),
        Double.parseDouble(csvRecord.get("mrp")),
        Double.parseDouble(csvRecord.get("rate")),
        sqlStartDate,
        csvRecord.get("company"),
        csvRecord.get("supplier"));
        inventories.add(inventory);
      }
      logger.info("invertories :{}",inventories);
      return inventories;
    } catch (IOException e) {
      logger.error("fail to parse CSV file: {} " + e);
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    } 
  }

  public static ByteArrayInputStream inventoriesToCSV(List<Inventory> inventories) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Inventory inventory : inventories) {
        List<String> data = Arrays.asList( inventory.getCode(),
        inventory.getName(),
        inventory.getBatch(),
        String.valueOf(inventory.getStock()),
        String.valueOf(inventory.getDeal()),
        String.valueOf(inventory.getFree()),
        String.valueOf(inventory.getMrp()),
        String.valueOf(inventory.getRate()),
        String.valueOf(inventory.getExpiry()),
        inventory.getCompany(),
        inventory.getSupplier());
        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }

  

}
