package com.example.firstrestapi.survey;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// SurveyResource
@WebMvcTest(controllers = SurveyResource.class)
public class SurveyResourceTest {
    @MockBean
    private SurveyService surveyService;

    @Autowired
    private MockMvc mockMvc;

    // Mock -> Necesitamos hacer mock de este método surveyService.retrieveSpecificSurveyQuestion(surveyId, questionId)

    // Fire a request
    // /surveys/{surveyId}/questions/{questionId}
    // http://localhost:8080/surveys/Survey1/questions/Question1 GET

    private static String SPECIFIC_QUESTION_URL = "http://localhost:8080/surveys/Survey1/questions/Question1";

    private static String GENERIC_QUESTION_URL = "http://localhost:8080/surveys/Survey1/questions";

    @Test
    void retrieveSpecificQuestion_404Scenario() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL).accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    void retrieveSpecificSurveyQuestion_basicScenario() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL).accept(MediaType.APPLICATION_JSON);

        Question question = new Question("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");

        when(surveyService.retrieveSpecificSurveyQuestion("Survey1", "Question1"))
                .thenReturn(question);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String expectedResponse = """
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

        assertEquals(200, mvcResult.getResponse().getStatus());

        System.out.println(mvcResult.getResponse().getContentAsString());
        JSONAssert.assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString(), false);

    }

    // addNewSurveyQuestion
    // POST Request
    // http://localhost:8080/surveys/Survey1/questions/
    // 201
    // Location: http://localhost:8080/surveys/Survey1/questions/808362

    @Test
    void addNewSurveyQuestion_basicScenario() throws Exception {
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

        when(surveyService.addNewSurveyQuestion(anyString(), any())).thenReturn("SOME_ID");

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(GENERIC_QUESTION_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON);


        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(201, response.getStatus());

        String locationHeader = response.getHeader("Location");
        System.out.println(locationHeader);
        // JSONAssert.assertEquals(, mvcResult.getResponse().getContentAsString(), false);
        assertTrue(locationHeader.contains("/surveys/Survey1/questions/SOME_ID"));

    }

}
