package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file));) {
            String line;
            String name = null;
            Integer age = null;
            String email = null;
            Long phone = null;

            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split(": ");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    switch (key) {
                        case "Name":
                            name = value;
                            break;
                        case "Age":
                            age = Integer.parseInt(value);
                            break;
                        case "Email":
                            email = value;
                            break;
                        case "Phone":
                            phone = Long.parseLong(value);
                            break;
                    }
                }
            }

            if (name != null && age != null && email != null && phone != null) {
                return new Profile(name, age, email, phone);
            } else {
                throw new IllegalArgumentException("Invalid data in the file");
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        return null;
    }
}
