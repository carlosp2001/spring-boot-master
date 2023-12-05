package com.example.firstrestapi.survey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

    @Test
    void retrieveSpecificSurveyQuestion_basicScenario() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        System.out.println(mvcResult.getResponse().getStatus());
    }
}
