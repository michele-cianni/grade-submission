package com.github.michele.cianni.gradesubmission.validation;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ScoreValidator implements ConstraintValidator<Score, String> {

    private enum ScoreValue {
        A("A", "A+", "A-"), 
        B("B", "B+", "B-"), 
        C("C", "C+", "C-"), 
        D("D", "D+", "D-"), 
        F("F");

        final String[] values;

        ScoreValue(String... values) {
            this.values = values;
        }

        boolean isValid(String value) {
            return Objects.nonNull(value) && Arrays.asList(values).contains(value);
        }

        static Stream<ScoreValue> stream() {
            return Stream.of(values());
        }
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ScoreValue.stream().anyMatch(scoreValue -> scoreValue.isValid(value));
    }

    
    
}
