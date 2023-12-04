package com.example.firstrestapi.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.*;

public class JsonAssertTest {

    @Test
    void jsonAssert_learningBasics() throws JSONException {
        String expectedResponse = """
                        {"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
                """;

        String actualResponse = """
                        {"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
                """;

        // Nos ayuda a comparar dos JSON sin importar si hay espacios en blanco o no, el parámetro strict nos ayuda
        // con saber si queremos comparar exactamente los JSON o si solo queremos comparar los valores especificados
        JSONAssert.assertEquals(expectedResponse, actualResponse, true);
    }
}


