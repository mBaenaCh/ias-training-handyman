package com.example.handyman.calculator.configuration.jackson;

import com.example.handyman.calculator.configuration.jackson.codecs.IdParser;
import com.example.handyman.calculator.configuration.jackson.codecs.LastNameParser;
import com.example.handyman.calculator.configuration.jackson.codecs.NameParser;
import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.LastName;
import com.example.handyman.calculator.domain.Name;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class InternalModule extends SimpleModule {
    private static final String NAME = "InternalModule";

    public InternalModule() {
        super(NAME, Version.unknownVersion());

        addSerializer(Id.class, new IdParser.Serializer());
        addDeserializer(Id.class, new IdParser.Deserializer());

        addSerializer(Name.class, new NameParser.Serializer());
        addDeserializer(Name.class, new NameParser.Deserializer());

        addSerializer(LastName.class, new LastNameParser.Serializer());
        addDeserializer(LastName.class, new LastNameParser.Deserializer());
    }
}