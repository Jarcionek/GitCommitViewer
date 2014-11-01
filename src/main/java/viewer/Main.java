package viewer;

import javax.swing.SwingUtilities;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }

}
