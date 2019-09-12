package Command;
import Duke.*;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    public void execute() {
        Ui.quitFunc();
    }
}
