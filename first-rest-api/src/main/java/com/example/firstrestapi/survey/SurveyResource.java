package com.example.firstrestapi.survey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyResource {
    private SurveyService surveyService;

    public SurveyResource(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    /**
     * Ruta para obtener todas encuestas
     *
     * @return
     */
    @RequestMapping("/surveys")
    public List<Survey> retrieveAllSurveys() {
        return surveyService.retrieveAllSurveys();
    }

    /**
     * Ruta para obtener la información de una encuesta en específico
     *
     * @param surveyId
     * @return
     */
    @RequestMapping("/surveys/{surveyId}")
    public Survey retrieveOneSurvey(@PathVariable String surveyId) {
        Survey survey = surveyService.retrieveSurveyById(surveyId);
        if (survey == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return survey;
    }

    /**
     * Ruta para obtener todas las preguntas de una encuesta en específico
     *
     * @param surveyId
     * @return
     */
    @RequestMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveAllSurveyQuestions(@PathVariable String surveyId) {
        List<Question> questions = surveyService.retrieveAllSurveyQuestions(surveyId);
        if (questions == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return questions;
    }

    /**
     * Ruta para obtener una pregunta específica de una encuesta
     *
     * @param surveyId
     * @param questionId
     * @return
     */
    @RequestMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveSpecificSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
        Question question = surveyService.retrieveSpecificSurveyQuestion(surveyId, questionId);
        if (question == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return question;
    }

    /**
     * Ruta para crear una nueva pregunta en una encuesta
     *
     * @param surveyId
     * @param question
     */
    @RequestMapping(value = "/surveys/{surveyId}/questions", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewSurveyQuestion(@PathVariable String surveyId, @RequestBody Question question) {
        String questionId = surveyService.addNewSurveyQuestion(surveyId, question);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{questionId}")
                .buildAndExpand(questionId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Ruta para obtener una pregunta específica de una encuesta
     *
     * @param surveyId
     * @param questionId
     * @return
     */
    @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
        String surveyIdRemoved = surveyService.deleteSurveyQuestion(surveyId, questionId);
        if (surveyIdRemoved == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateSurveyQuestion(@PathVariable String surveyId,
                                                       @PathVariable String questionId,
                                                       @RequestBody Question question) {
        surveyService.updateSurveyQuestion(surveyId, questionId, question);

        return ResponseEntity.noContent().build();
    }
}
