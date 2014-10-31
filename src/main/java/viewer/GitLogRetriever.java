package viewer;

public class GitLogRetriever {

    private final CommandRunner commandRunner = new CommandRunner();

    private String[] hashes;
    private String[] messages;

    public void load() {
        String entireLog = commandRunner.exec("git log --oneline");
        String[] lines = entireLog.split(System.getProperty("line.separator"));
        hashes = new String[lines.length];
        messages = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] splitLine = lines[i].split(" ", 2);
            hashes[i] = splitLine[0];
            messages[i] = splitLine[1];
        }
    }

    public String[] getHashes() {
        return hashes;
    }

    public String[] getMessages() {
        return messages;
    }
}
