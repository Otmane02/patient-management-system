package com.aababou.patientservice.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {errors.put(error.getField(), error.getDefaultMessage());});
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlePatientNotFoundException(PatientNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}





//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.HashMap;
//import java.util.Map;
//
///*
//author otman
//    */
//@ControllerAdvice  //“je vais gérer les erreurs pour TOUT le projet”
//public class GlobalExceptionHandler {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String,String>> handleValidationException(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(
//                error -> errors.put(error.getField(), error.getDefaultMessage())) ;
//
//        return ResponseEntity.badRequest().body(errors);
//    }
//
//}
/*
package com.aababou.patientservice.exception;

// 👉 Import des classes nécessaires
import org.springframework.http.ResponseEntity; // pour retourner une réponse HTTP (status + body)
import org.springframework.web.bind.MethodArgumentNotValidException; // exception de validation (@Valid)
import org.springframework.web.bind.annotation.ControllerAdvice; // pour gérer les erreurs globalement
import org.springframework.web.bind.annotation.ExceptionHandler; // pour intercepter une exception spécifique

import java.util.HashMap;
import java.util.Map;



// 👉 Cette annotation dit :
// "je vais gérer les exceptions pour tous les controllers du projet"
@ControllerAdvice
public class GlobalExceptionHandler {

    // 👉 Cette méthode sera appelée automatiquement
    // quand une erreur de validation se produit (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        // 👉 Map pour stocker les erreurs
        // format : "nomChamp" -> "messageErreur"
        Map<String, String> errors = new HashMap<>();

        // 👉 ex contient toutes les erreurs de validation
        // getFieldErrors() = liste des erreurs sur les champs (name, age, ...)
        // forEach = boucle sur chaque erreur
        ex.getBindingResult().getFieldErrors().forEach(

                // 👉 pour chaque erreur :
                // error.getField() = nom du champ (ex: name)
                // error.getDefaultMessage() = message (ex: must not be blank)
                error -> errors.put(
                        error.getField(),
                        error.getDefaultMessage()
                )
        );

        // 👉 retourner une réponse HTTP 400 (Bad Request)
        // avec la liste des erreurs dans le body (JSON)
        return ResponseEntity
                .badRequest()   // status 400
                .body(errors);  // contenu de la réponse
    }
}
 */
