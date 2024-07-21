package com.ifgoiano.topfilmes.api.apiException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    /*
        MethodArgumentNotValidException.
        Essa exceção é lançada pelo Spring quando a validação de
        um argumento(parâmetro) do método falha, e é o que acontece quando
        você usa a anotação @Valid em um parâmetro de método.
    */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<Field> list = new ArrayList<Field>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {

            Field field = new Field();
            field.setFieldName(error.getField());
            field.setMessage(error.getDefaultMessage());

            list.add(field);

        });

        var status = HttpStatus.BAD_REQUEST;

        var problem = new Problem();
        problem.setTitle("Existem campos não fornecidos corretamente!");
        problem.setDateHour(OffsetDateTime.now());
        problem.setStatus(status.value());
        problem.setList(list);

        return ResponseEntity.badRequest().body(problem);
    }

}
