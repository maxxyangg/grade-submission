package com.example.gradesubmission.validation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ScoreValidator implements ConstraintValidator<Score, String>{

    List<String> scores = Arrays.asList(
        "A+", "A", "A-",
        "B+", "B", "B-",
        "C+", "C", "C-",
        "D+", "D", "D-",
        "F"
    );

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        if(arg0 == null) {
            return false;
        }else{
            for(String score: scores){
                if(arg0.equals(score)){
                    return true;
                }
            }
        }
        return false;
    }
    

}
