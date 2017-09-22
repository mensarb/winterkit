package com.mute.winter.winterkit.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author dkoller
 * @since 23.05.2016
 */
public class LogCatReader {

    private static final String processId = Integer.toString(android.os.Process.myPid());

    public String read(){
        StringBuilder builder = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");

        try {
            String[] command = new String[] { "logcat", "-d" };

            Process process = Runtime.getRuntime().exec(command);

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(processId)) {
                    builder.append(line);
                    builder.append(lineSeparator);
                }
            }

            return builder.toString();
        } catch (IOException ex) {
            return ex.toString();
        }
    }
}