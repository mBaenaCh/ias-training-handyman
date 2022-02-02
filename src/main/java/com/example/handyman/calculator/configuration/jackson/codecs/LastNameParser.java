package com.example.handyman.calculator.configuration.jackson.codecs;


import com.example.handyman.calculator.domain.LastName;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class LastNameParser {

    public static class Serializer extends JsonSerializer<LastName> {

        @Override
        public void serialize(LastName value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.toString());
        }
    }

    public  static class Deserializer extends JsonDeserializer<LastName> {

        @Override
        public LastName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return new LastName(p.getValueAsString());
        }
    }

}
