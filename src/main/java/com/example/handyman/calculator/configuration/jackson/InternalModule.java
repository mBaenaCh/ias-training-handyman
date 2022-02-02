package com.example.handyman.calculator.configuration.jackson;

import com.example.handyman.calculator.configuration.jackson.codecs.IdParser;
import com.example.handyman.calculator.domain.Id;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class InternalModule extends SimpleModule {
    private static final String NAME = "InternalModule";

    public InternalModule() {
        super(NAME, Version.unknownVersion());

        addSerializer(Id.class, new IdParser.Serializer());
        addDeserializer(Id.class, new IdParser.Deserializer());



    }
}