package MemoryGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MemoryGame extends JFrame {

    // ---------- Instance Variables ---------- //

    private final int NUMROWS = 3;
    private final int NUMCOLS = 4;
    private ArrayList<JButton> buttonsList = new ArrayList<JButton>();
    private ArrayList<Color> colorslist = new ArrayList<Color>();
    private JButton currentlySelected;

    GridLayout gridLayout = new GridLayout(NUMROWS, NUMCOLS);

    private void populateColorsList() {

        colorslist.add(Color.RED);
        colorslist.add(Color.RED);

        colorslist.add(Color.BLUE);
        colorslist.add(Color.BLUE);

        colorslist.add(Color.GREEN);
        colorslist.add(Color.GREEN);

        colorslist.add(Color.YELLOW);
        colorslist.add(Color.YELLOW);

        colorslist.add(Color.BLACK);
        colorslist.add(Color.BLACK);

        colorslist.add(Color.WHITE);
        colorslist.add(Color.WHITE);

        colorslist.add(Color.PINK);
        colorslist.add(Color.PINK);

        colorslist.add(Color.GRAY);
        colorslist.add(Color.GRAY); // Copied each color twice, to make parallel arrays of colors. Colors for buttons

        for (int i = 0; i < buttonsList.size(); i++) { // Use buttonsList size for reliable random sort size

            int r = (int) (Math.random() * buttonsList.size()); // Select and swap new random position in ArrayList

            Color tmp = colorslist.get(i);
            colorslist.set(i, colorslist.get(r)); // Teacup method to swap these values and set them
            colorslist.set(r, tmp);

        }

    }

    // ---------- Event Handler ---------- //

    public void MyEventHandler(ActionEvent e) {

        JButton currentBtn = (JButton) (e.getSource());
        int position = buttonsList.indexOf(currentBtn); // Find pos of button
        Color c = colorslist.get(position); // Get corresponding color. This is why we repeated all colors
        currentBtn.setBackground(c);
        currentBtn.setEnabled(false); // Prevent double clicks

        if (currentlySelected == null) { // First button clicked

            currentlySelected = currentBtn;

        } else { // Second button clicked

            if (!currentlySelected.getBackground().equals(currentBtn.getBackground())) {

                JOptionPane.showMessageDialog(this, "Not a match");
                currentBtn.setEnabled(true);
                currentlySelected.setEnabled(true);
                currentBtn.setBackground(null);
                currentlySelected.setBackground(null);

            }
            currentlySelected = null;

        }

    }

    // ---------- Memory Game Heart Code ---------- //

    public MemoryGame() {
        super("Memory Game | Made by Seth Gates");
        setLayout(gridLayout);

        // ---------- Buttons --------- //

        for (int i = 0; i < NUMROWS * NUMCOLS; i++) {

            JButton btn = new JButton();
            buttonsList.add(btn);
            add(btn);
            btn.addActionListener(e -> MyEventHandler(e)); // Call to handler on press of button

        }

        // ---------- Game Sizings and conditions ---------- //

        populateColorsList();
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

}
