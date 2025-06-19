package com.sprint1.validators;

import com.sprint1.exception.EmptyFieldException;
import com.sprint1.exception.InvalidEmailException;
import com.sprint1.exception.InvalidMobileNumberException;
import com.sprint1.exception.ValidationException;
import com.sprint1.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class EmployeeValidator {

    public static void validateEmployeeFields(String name,String role,String phone,String email) throws ValidationException {
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

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }


    }

}
