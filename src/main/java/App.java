import ui.Console;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
         Console view  = new Console();

        try {
            view.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
