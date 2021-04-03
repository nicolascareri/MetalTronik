package com.example.metalTest.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Validated
@Deprecated
public class JsonDateDeserializer extends JsonDeserializer<Date> {

    @NotNull
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        df.setLenient(false);
        try {
            return df.parse(jsonParser.getText());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
