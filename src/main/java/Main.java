import javax.swing.*;

public class Main {

    private static final String TITLE = "Adventure Game";

    public static void main(String[] args) {

        // create a window using JFrame
        JFrame window = new JFrame();

        // this lets the window properly close when user clicks the close ("x") button
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // this makes the window unable to be resized
        window.setResizable(false);

        // this sets the title of the window
        window.setTitle(TITLE);

        // this makes the window location non-specific and makes the window be displayed at the center of the screen
        window.setLocationRelativeTo(null);

        // this makes the window visible
        window.setVisible(true);

    }

}
