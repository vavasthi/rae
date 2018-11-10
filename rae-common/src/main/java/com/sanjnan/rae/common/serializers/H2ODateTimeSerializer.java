package com.sanjnan.rae.common.serializers;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * Created by vinay on 3/3/16.
 */
public class H2ODateTimeSerializer extends JsonSerializer<DateTime> {

  private static DateTimeFormatter formatter = ISODateTimeFormat.dateTime();

  @Override
  public void serialize(final DateTime dateTime,
                        final JsonGenerator jsonGenerator,
                        final SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
    jsonGenerator.writeString(formatter.print(dateTime));
  }
}
