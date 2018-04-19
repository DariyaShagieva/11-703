package ru.itis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * VerificationWork
 * <p>
 * 19 04 2018
 *
 * @author Nita
 */
public class Scanner {

    public int nextInt(){
        InputStream currentChar = System.in;
        int c;
        StringBuilder builder = new StringBuilder();
        try {
            while ((c = currentChar.read()) != 10){
                if (Character.isDigit((char)c) || (char)c == '-') {
                    builder.append((char)c);
                } else throw new IllegalStateException();
            }
        } catch (IOException e){
            throw new IllegalStateException(e);
        }
        return Integer.parseInt(String.valueOf(builder));
    }
}
