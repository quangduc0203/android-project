package com.example.project.validate;

import java.util.regex.Pattern;

public class Validate {
    public static boolean isPhone(String input){
        String phonePattern = "\\d{10}";
        if(Pattern.matches(phonePattern,input)){
            return  true;
        }
        else
            return false;
    }
    public static boolean isEmail(String input){
        String EMAIL_PATTERN =
                "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        if(Pattern.matches(EMAIL_PATTERN,input)){
            return true;
        }
        else
            return false;
    }
}
