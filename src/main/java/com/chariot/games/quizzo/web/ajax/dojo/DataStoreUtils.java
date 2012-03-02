package com.chariot.games.quizzo.web.ajax.dojo;

public class DataStoreUtils {

  public static String asReadStoreForSelect(String identifier, String label,
                                            String itemsAsJsonStringArray) {
    StringBuffer buf = new StringBuffer(itemsAsJsonStringArray.length()*2);
    return buf.append("{")
        .append(asJsonPair("identifier", identifier))
        .append(", ")
        .append(asJsonPair("label", label))
        .append(", ")
        .append("\"items\": ")
        .append(itemsAsJsonStringArray)
        .append("}")
        .toString();
  }

  public static String asJsonPair(String key, String value) {
    return new StringBuffer(key.length() + value.length() + 10)
        .append("\"")
        .append(key)
        .append("\": ")
        .append("\"")
        .append(value)
        .append("\"")
        .toString();
  }

  public static void main (String[] args) {
    System.out.println("testing");
    String itemsAsJsonStringArray =
        "[ { \"runState\" : \"1\", \"label\" : \"First Run\"} , " +
        "{ \"runState\" : \"2\", \"label\" : \"Second Run\"} ]";
    System.out.println(asReadStoreForSelect("runState", "label", itemsAsJsonStringArray));

  }

}
