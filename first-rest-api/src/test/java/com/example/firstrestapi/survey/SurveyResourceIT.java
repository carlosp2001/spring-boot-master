package com.example.firstrestapi.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

// http://localhost:8080/surveys/Survey1/questions/Question1
// {
//"id": "Question1",
//"description": "Most Popular Cloud Platform Today",
//"options": [
//"AWS",
//"Azure",
//"Google Cloud",
//"Oracle Cloud"
//],
//"correctAnswer": "AWS"
//}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {
    @Autowired
    private TestRestTemplate template;

    String str = """
            {
                "id": "Question1",
                "description": "Most Popular Cloud Platform Today",
                "options": [
                "AWS",
                "Azure",
                "Google Cloud",
                "Oracle Cloud"
                ],
                "correctAnswer": "AWS"
            }
            """;

    private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";



    @Test
    void retrieveSpecificSurveyQuestion_basicScenario() throws JSONException {
        ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
        String expectedResponse = """
                        {"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
                """;

        JSONAssert.assertEquals(expectedResponse.trim(), responseEntity.getBody(), false);

//        System.out.println(responseEntity.getBody());
//        System.out.println(responseEntity.getHeaders());
//        assertEquals(expectedResponse.trim(), responseEntity.getBody());
    }
}
