package com.sprint1.util;

import com.sprint1.exception.*;

public class ValidationUtil {

    public static String validatePhone(String phone) throws InvalidMobileNumberException, EmptyFieldException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new EmptyFieldException("Phone number field cannot be empty");
        }
        if (!phone.matches("^[1-9]{1}[0-9]{9}$")) {
            throw new InvalidMobileNumberException("Phone number must be 10 digits and should not start with 0");
        }
        return phone;
    }

    public static String validateEmail(String email) throws InvalidEmailException, EmptyFieldException {
        if (email == null || email.trim().isEmpty()) {
            throw new EmptyFieldException("Email field cannot be empty");
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new InvalidEmailException("Invalid email format");
        }
        return email;
    }

    public static String validateNotEmpty(String field) throws EmptyFieldException {
        if (field == null || field.trim().isEmpty()) {
            throw new EmptyFieldException(field + " cannot be empty");
        }
        return field;
    }
}
//package com.sprint1.util;
//
//        import com.sprint1.exception.EmptyFieldException;
//        import com.sprint1.exception.InvalidEmailException;
//        import com.sprint1.exception.InvalidMobileNumberException;
//
//public class ValidationUtil {
//
//    public static void validateName(String name) throws EmptyFieldException {
//        if (name == null || name.trim().isEmpty()) {
//            throw new EmptyFieldException("Name cannot be empty");
//        }
//    }
//
//    public static void validateEmail(String email) throws InvalidEmailException {
//        if (email == null || !email.matches("^(.+)@(.+)$")) {
//            throw new InvalidEmailException("Invalid email format");
//        }
//    }
//
//    public static void validatePhone(String phone) throws InvalidMobileNumberException {
//        if (phone == null || !phone.matches("^[1-9][0-9]{9}$")) {
//            throw new InvalidMobileNumberException("Phone must be 10 digits and not start with 0");
//        }
//    }
//
//    public static void validatePassword(String password) throws EmptyFieldException {
//        if (password == null || password.trim().isEmpty()) {
//            throw new EmptyFieldException("Password cannot be empty");
//        }
//    }
//}

