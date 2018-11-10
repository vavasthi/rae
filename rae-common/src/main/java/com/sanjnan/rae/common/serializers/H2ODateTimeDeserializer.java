package com.sanjnan.rae.common.serializers;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * Created by vinay on 3/3/16.
 */
public class H2ODateTimeDeserializer extends JsonDeserializer<DateTime> {

  private static DateTimeFormatter formatter = ISODateTimeFormat.dateTime();

  @Override
  public DateTime deserialize(final JsonParser jsonParser,
                              final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    String dateStr = null;
    String timeStr = null;
    String fieldName = null;

    dateStr = jsonParser.getValueAsString();
    if (dateStr != null) {

      return DateTime.parse(dateStr, formatter);
    }
    return null;
  }
}
