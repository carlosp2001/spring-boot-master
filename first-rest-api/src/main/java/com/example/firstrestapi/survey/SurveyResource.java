package com.example.firstrestapi.survey;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class SurveyResource {
    private SurveyService surveyService;

    public SurveyResource(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    // Solicitud para mostrar todas las encuestas
    @RequestMapping("/surveys")
    public List<Survey> retrieveAllSurveys() {
        return surveyService.retrieAllSurveys();
    }

    // Solicitud para mostrar una encuesta en específico
    @RequestMapping("/surveys/{surveyId}")
    public Survey retrieveOneSurvey(@PathVariable String surveyId) {
        Survey survey = surveyService.retrieveSurveyById(surveyId);
        if (survey == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return survey;
    }
}
