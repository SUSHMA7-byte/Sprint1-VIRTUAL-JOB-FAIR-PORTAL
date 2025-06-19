package com.sprint1.validators;

import com.sprint1.exception.*;
import com.sprint1.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class CandidateValidator {

    public static void validateCandidateFields(String name, String email, String phone, String resume, String college, String address) throws ValidationException {
        List<String> errors = new ArrayList<>();

        // Name
        try {
            ValidationUtil.validateNotEmpty(name);
        } catch (EmptyFieldException e) {
            errors.add("Name: " + e.getMessage());
        }

        // Email
        if (email == null || email.trim().isEmpty()) {
            errors.add("Email: Email field cannot be empty");
        } else {
            try {
                ValidationUtil.validateEmail(email);
            } catch (InvalidEmailException | EmptyFieldException e) {
                errors.add("Email: " + e.getMessage());
            }
        }

        // Phone
        if (phone == null || phone.trim().isEmpty()) {
            errors.add("Phone: Phone number field cannot be empty");
        } else {
            try {
                ValidationUtil.validatePhone(phone);
            } catch (InvalidMobileNumberException | EmptyFieldException e) {
                errors.add("Phone: " + e.getMessage());
            }
        }

        // Resume Link
        try {
            ValidationUtil.validateNotEmpty(resume);
        } catch (EmptyFieldException e) {
            errors.add("Resume Link: " + e.getMessage());
        }

        // College Name
        try {
            ValidationUtil.validateNotEmpty(college);
        } catch (EmptyFieldException e) {
            errors.add("College Name: " + e.getMessage());
        }

        // Address
        try {
            ValidationUtil.validateNotEmpty(address);
        } catch (EmptyFieldException e) {
            errors.add("Address: " + e.getMessage());
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
