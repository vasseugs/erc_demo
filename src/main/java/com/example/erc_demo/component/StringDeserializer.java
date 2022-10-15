package com.example.erc_demo.component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class StringDeserializer extends com.fasterxml.jackson.databind.deser.std.StringDeserializer {

  @Override
  public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String value = super.deserialize(jsonParser, deserializationContext);
    return value != null ? value.trim() : null;
  }
}
