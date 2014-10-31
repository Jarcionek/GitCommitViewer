package viewer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {

    private final GitLogRetriever gitLogRetriever = new GitLogRetriever();
    private final CommandRunner commandRunner = new CommandRunner();

    private final JRadioButton[] checkedOuts;
    private final JRadioButton[] diffs;
    private final JButton applyButton;
    private final int rows;

    public Window() {
        gitLogRetriever.load();
        rows = gitLogRetriever.getHashes().length;

        checkedOuts = new JRadioButton[rows];
        diffs = new JRadioButton[rows];
        applyButton = new JButton("apply");
        applyButton.addActionListener(actionListener());

        JPanel panel = new JPanel(new GridLayout(rows + 2, 3));
        panel.add(new JLabel("checked out"));
        panel.add(new JLabel("diff"));
        panel.add(new JLabel("hash and commit message"));
        for (int i = 0; i < rows; i++) {
            checkedOuts[i] = new JRadioButton();
            panel.add(checkedOuts[i]);

            diffs[i] = new JRadioButton();
            panel.add(diffs[i]);

            JTextField textField = new JTextField(gitLogRetriever.getHashes()[i] + ": " + gitLogRetriever.getMessages()[i]);
            textField.setBorder(null);
            textField.setEditable(false);
            panel.add(textField);
        }
        panel.add(new JLabel());
        panel.add(applyButton);
        panel.add(new JLabel());
        setContentPane(panel);

        ButtonGroup one = new ButtonGroup();
        ButtonGroup two = new ButtonGroup();
        for (JRadioButton checkedOut : checkedOuts) {
            one.add(checkedOut);
        }
        for (JRadioButton diff : diffs) {
            two.add(diff);
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private ActionListener actionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = -1;
                int j = -1;
                for (i = 0; i < rows; i++) {
                    if (checkedOuts[i].isSelected()) {
                        break;
                    }
                }
                for (j = 0; j < rows; j++) {
                    if (diffs[j].isSelected()) {
                        break;
                    }
                }
                String commandOne = "git checkout " + gitLogRetriever.getHashes()[j];
                String commandTwo = "git reset --soft " + gitLogRetriever.getHashes()[i];
                System.out.println(commandOne);
                System.out.println(commandRunner.exec(commandOne));
                System.out.println(commandTwo);
                System.out.println(commandRunner.exec(commandTwo));
            }
        };
    }

}
