package InfluxDB;

import java.util.*;

/**
 * Created by mfehler on 13.06.17.
 */
public class WritePointsToInfluxDB {

  static String dbName = "still_test";

  private String measurement;
  private Map<String, String> tags;
  private Long time;
  private Map<String, Integer> fields = new HashMap<String, Integer>();


  List<String> keys= Arrays.asList("readoutduration", "readoutduration", "readoutduration");
  List<Integer> values = Arrays.asList(25000,8000,5000);

















}
