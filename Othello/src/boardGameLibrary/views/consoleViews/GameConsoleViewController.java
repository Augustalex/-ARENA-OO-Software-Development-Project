package boardGameLibrary.views.consoleViews;

import utilities.router.consoleRouter.ConsoleViewController;

/**
 * Created by August on 2016-10-20.
 */
public abstract class GameConsoleViewController extends ConsoleViewController {

    private String viewOutput = "";

    @Override
    public void loadView() {
        this.output.print(viewOutput);
    }

    public void bufferText(String text){
        this.viewOutput = this.viewOutput.concat(text);
    }

    public void printText(){
        this.loadView();
    }

    public void resetBuffer(){
        this.viewOutput = "";
    }

    public void clearView(){
        this.output.print("\033[H\033[2J");
        this.output.flush();
    }

    public void appendText(String text){
        this.output.print(text);
    }
}
