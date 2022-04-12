package com.example.javaegypitanidinfoextractor.restservice;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.regex.*;  

public class EgyptianIDInfo {
    public static Map<String, String> governoratesMap = Stream.of(new String[][]{
        {"01", "Cairo"},
        {"02", "Alexandria"},
        {"03", "Port Said"},
        {"04", "Suez"},
        {"11", "Damietta"},
        {"12", "Dakahlia"},
        {"13", "Ash Sharqia"},
        {"14", "Kaliobeya"},
        {"15", "Kafr El - Sheikh"},
        {"16", "Gharbia"},
        {"17", "Monoufia"},
        {"18", "El Beheira"},
        {"19", "Ismailia"},
        {"21", "Giza"},
        {"22", "Beni Suef"},
        {"23", "Fayoum"},
        {"24", "El Menia"},
        {"25", "Assiut"},
        {"26", "Sohag"},
        {"27", "Qena"},
        {"28", "Aswan"},
        {"29", "Luxor"},
        {"31", "Red Sea"},
        {"32", "New Valley"},
        {"33", "Matrouh"},
        {"34", "North Sinai"},
        {"35", "South Sinai"},
        {"88", "Foreign"}
    }).collect(Collectors.toMap(p -> p[0], p -> p[1]));
    public static Pattern egyptianIDRegex = Pattern.compile("(2|3)[0-9][0-9][0-1][0-9][0-3][0-9](01|02|03|04|11|12|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|29|31|32|33|34|35|88)\\d\\d\\d\\d\\d");


	private final String info;
    private final String egyptianIDNumber;



    public EgyptianIDInfo(String egyptianIDNumber){
        this.egyptianIDNumber = egyptianIDNumber;
        this.info = isValidEgyptianIDNumber()? extractInfo():"Could not Extract Info. Please Provide A Valid ID Number.";
    }


    public boolean isValidEgyptianIDNumber(){
        return egyptianIDNumber.length() == 14 && egyptianIDRegex.matcher(egyptianIDNumber).matches();
    }

    public String extractInfo(){
        Map<String, String> infoMap = Stream.of(new String[][]{
            {"ID Number", egyptianIDNumber},
            {"Birthdate", extractBirthDate()},
            {"Birth Governorate", extractGovernorate()},
            {"Serial", extractSerial()},
            {"Gender", extractGender()},
            {"Check Digit", extractCheckDigit()}
        }).collect(Collectors.toMap(p -> p[0], p -> p[1]));

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = "Could not Extract Info.";
        try {
            jsonResult = mapper.writerWithDefaultPrettyPrinter()
            .writeValueAsString(infoMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

    private String extractBirthDate(){
        StringBuilder birthDayStringBuilder = new StringBuilder();
        birthDayStringBuilder.append(19 + Integer.parseInt(egyptianIDNumber.charAt(0) + "") - 2);
        birthDayStringBuilder.append(egyptianIDNumber.substring(1, 3));
        birthDayStringBuilder.append("-");
        birthDayStringBuilder.append(egyptianIDNumber.substring(3, 5));
        birthDayStringBuilder.append("-");
        birthDayStringBuilder.append(egyptianIDNumber.substring(5, 7));
        return birthDayStringBuilder.toString();

    }

    private String extractGovernorate(){
        return governoratesMap.get(egyptianIDNumber.substring(7, 9));
    }

    private String extractSerial(){
        return egyptianIDNumber.substring(9, 13);
    }

    private String extractGender(){
        if(Integer.parseInt(egyptianIDNumber.substring(12,13)) % 2 == 0)
            return "Female";
        else
            return "Male";
    }

    private String extractCheckDigit(){
        return egyptianIDNumber.substring(13);
    }

    public String getInfo(){
        return this.info;
    }
}
