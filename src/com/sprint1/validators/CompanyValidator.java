package com.sprint1.validators;

import com.sprint1.exception.*;
import com.sprint1.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class CompanyValidator {

    public static void validateCompanyFields(String name, String industry, String email, String phone, String address) throws ValidationException {
        List<String> errors = new ArrayList<>();

        // Company Name
        try {
            ValidationUtil.validateNotEmpty(name);
        } catch (EmptyFieldException e) {
            errors.add("Company Name: " + e.getMessage());
        }

        // Industry Type
        try {
            ValidationUtil.validateNotEmpty(industry);
        } catch (EmptyFieldException e) {
            errors.add("Industry Type: " + e.getMessage());
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
