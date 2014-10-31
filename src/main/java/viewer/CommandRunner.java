package viewer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandRunner {

    public String exec(String command) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine())!= null) {
                stringBuilder.append(line).append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

}
