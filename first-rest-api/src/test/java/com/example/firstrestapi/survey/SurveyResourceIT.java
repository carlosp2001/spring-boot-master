package com.example.firstrestapi.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";

    private static String GENERIC_QUESTIONS_URL = "/surveys/Survey1/questions";

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

    @Test
    void retrieveSpecificSurveyQuestion_basicScenario() throws JSONException {
        ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
        String expectedResponse = """
                        {"id":"Question1","description":"Most Popular Cloud Platform Today",
                        "options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
                """;

        JSONAssert.assertEquals(expectedResponse.trim(), responseEntity.getBody(), false);

        // Pruebas para verificar que el contenido sea JSON y también pruebas para verificar el código que devolvió el
        // sistema

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(responseEntity.getHeaders().get("Content-Type").get(0), "application/json");


//        System.out.println(responseEntity.getBody());
//        System.out.println(responseEntity.getHeaders());
//        assertEquals(expectedResponse.trim(), responseEntity.getBody());
    }

    @Test
    void retrieveAllSurveyQuestions_basicScenario() throws JSONException {
        ResponseEntity<String> responseEntity = template.getForEntity(GENERIC_QUESTIONS_URL, String.class);
        String expectedResponse = """
                        [
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
                            },
                            {
                            "id": "Question2",
                            "description": "Fastest Growing Cloud Platform",
                            "options": [
                            "AWS",
                            "Azure",
                            "Google Cloud",
                            "Oracle Cloud"
                            ],
                            "correctAnswer": "Google Cloud"
                            },
                            {
                            "id": "Question3",
                            "description": "Most Popular DevOps Tool",
                            "options": [
                            "Kubernetes",
                            "Docker",
                            "Terraform",
                            "Azure DevOps"
                            ],
                            "correctAnswer": "Kubernetes"
                            }
                      ]
                """;

        JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(responseEntity.getHeaders().get("Content-Type").get(0), "application/json");
    }


    @Test
    void addNewSurveyQuestion_basicScenario() {
        String requestBody = """
                {
                    "description": "Your Favorite Language",
                    "options": [
                        "Java",
                        "Python",
                        "JavaScript",
                        "Haskell"
                    ],
                    "correctAnswer": "Java"
                }
                """;
        // http://localhost:8080/surveys/Survey1/questions
        // POST
        // RequestBody
        // Content-Type application/json
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);

        ResponseEntity<String> responseEntity = template.exchange(GENERIC_QUESTIONS_URL, HttpMethod.POST, httpEntity, String.class);
//        System.out.println(responseEntity.getHeaders());
//        System.out.println(responseEntity.getBody());

        // Asserts
        // 201
        // Location: http://localhost:8080/surveys/Survey1/questions/83829382
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        String locationHeader = responseEntity.getHeaders().get("Location").get(0);
        assertTrue(locationHeader.contains("/surveys/Survey1/questions/"));

        // Las pruebas unitarias no deben tener efectos secundarios es por eso que después de crear el registro se debe
        // eliminar para evitar problemas con las demás pruebas

        // DELETE Request
        // locationHeader
        template.delete(locationHeader);
    }

}
