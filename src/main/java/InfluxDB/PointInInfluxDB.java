package InfluxDB;

import org.influxdb.dto.Point;

import java.util.*;

/**
 * Created by mfehler on 13.06.17.
 */
public interface PointInInfluxDB {


  public Map<String, String> createTags(final String tagKey, List <String> values);

/*
 List<String> values = Arrays.asList("1", "2", "3");


    String myTagKey = "identifier";


 @Override
  public Map<String, String> createTags(String tagKey, List<String> values) {

    {
      final Map<String, String> tags = new HashMap<>( );

      for (int i=0; i<values.size(); i++) {
        tags.put(tagKey, values.get(i));
        System.out.println("Tags" + tags.toString());

      }
      return tags;

    }
  }
 */

}





