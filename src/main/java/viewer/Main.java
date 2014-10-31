package viewer;

import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static CommandRunner commandRunner = new CommandRunner();

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(commandRunner.exec("ping google.co.uk"));
        System.out.println(commandRunner.exec("ls"));
        System.out.println(commandRunner.exec("git log --oneline"));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }

}
