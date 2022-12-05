package TicTacToe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {

    // -- "J" handlers -- //

    JPanel panel = new JPanel();

    JButton BTNA1, BTNA2, BTNA3,
            BTNB1, BTNB2, BTNB3,
            BTNC1, BTNC2, BTNC3;
    String currentPlayer = "x";
    Font font = new Font(Font.SERIF, Font.BOLD, 100);

    JMenuBar menubar = new JMenuBar();

    // -- -- //

    public TicTacToe() {
        super("Tic Tac Toe");

        setContentPane(panel);
        panel.setLayout(new GridLayout(3, 3));
        panel.setVisible(true);

        // -- Buttons, and listeners -- //

        BTNA1 = new JButton();
        panel.add(BTNA1);
        BTNA1.addActionListener(e -> MyClickEventHandler(e));

        BTNA2 = new JButton();
        panel.add(BTNA2);
        BTNA2.addActionListener(e -> MyClickEventHandler(e));

        BTNA3 = new JButton();
        panel.add(BTNA3);
        BTNA3.addActionListener(e -> MyClickEventHandler(e));

        BTNB1 = new JButton();
        panel.add(BTNB1);
        BTNB1.addActionListener(e -> MyClickEventHandler(e));

        BTNB2 = new JButton();
        panel.add(BTNB2);
        BTNB2.addActionListener(e -> MyClickEventHandler(e));

        BTNB3 = new JButton();
        panel.add(BTNB3);
        BTNB3.addActionListener(e -> MyClickEventHandler(e));

        BTNC1 = new JButton();
        panel.add(BTNC1);
        BTNC1.addActionListener(e -> MyClickEventHandler(e));

        BTNC2 = new JButton();
        panel.add(BTNC2);
        BTNC2.addActionListener(e -> MyClickEventHandler(e));

        BTNC3 = new JButton();
        panel.add(BTNC3);
        BTNC3.addActionListener(e -> MyClickEventHandler(e));

        // -- -- //



        // -- MENU BAR OPTIONS -- //

        setJMenuBar(menubar);

        JMenu gameOptions = new JMenu("Game Options");
        menubar.add(gameOptions);

        JMenuItem resetGame = new JMenuItem("Reset the game");
        gameOptions.add(resetGame);
        resetGame.addActionListener(e -> resetGame());

        JMenu otheroptions = new JMenu("Other options");
        menubar.add(otheroptions);

        // -- -- //



        // -- MENU OPTIONS -- //

        setSize(400, 400);

        setVisible(true);

        setResizable(false);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // -- -- //

    }

    public void resetGame() {

        currentPlayer = "x";

        // -- Manually reset all buttons to init state -- //

        BTNA1.setText("");
        BTNA1.setBackground(null);
        BTNA1.setEnabled(true);

        BTNA2.setText("");
        BTNA2.setBackground(null);
        BTNA2.setEnabled(true);

        BTNA3.setText("");
        BTNA3.setBackground(null);
        BTNA3.setEnabled(true);

        BTNB1.setText("");
        BTNB1.setBackground(null);
        BTNB1.setEnabled(true);

        BTNB2.setText("");
        BTNB2.setBackground(null);
        BTNB2.setEnabled(true);

        BTNB3.setText("");
        BTNB3.setBackground(null);
        BTNB3.setEnabled(true);

        BTNC1.setText("");
        BTNC1.setBackground(null);
        BTNC1.setEnabled(true);

        BTNC2.setText("");
        BTNC2.setBackground(null);
        BTNC2.setEnabled(true);

        BTNC3.setText("");
        BTNC3.setBackground(null);
        BTNC3.setEnabled(true);

        // -- -- //

    }

    public void MyClickEventHandler(ActionEvent e) {

        // -- On player actions -- //

        JButton btn = (JButton) e.getSource();
        btn.setText(currentPlayer);

        if (currentPlayer.equals("x"))
            btn.setBackground(Color.RED);
        else
            btn.setBackground(Color.BLUE);

        btn.setEnabled(false);
        btn.setFont(font);

        // -- -- //


        if (checkForWinner()) {

            JOptionPane.showMessageDialog(null, "We have a winner!" + " " + currentPlayer + " " + "wins!"); // Victory message

            // -- Disable all buttons -- //

            BTNA1.setEnabled(false);
            BTNA2.setEnabled(false);
            BTNA3.setEnabled(false);
            BTNB1.setEnabled(false);
            BTNB2.setEnabled(false);
            BTNB3.setEnabled(false);
            BTNC1.setEnabled(false);
            BTNC2.setEnabled(false);
            BTNC3.setEnabled(false);

            //  -- -- //

        }
        switchPlayer();
    }

    // Check for winner
    public boolean checkForWinner() {

        // -- Check rows for win condition -- //

        // Check first row
        if (BTNA1.getText().equals(currentPlayer)
                && BTNA2.getText().equals(currentPlayer)
                && BTNA3.getText().equals(currentPlayer))
            return true;

        // Check second row
        if (BTNB1.getText().equals(currentPlayer)
                && BTNB2.getText().equals(currentPlayer)
                && BTNB3.getText().equals(currentPlayer))
            return true;

        // Check third row
        if (BTNC1.getText().equals(currentPlayer)
                && BTNC2.getText().equals(currentPlayer)
                && BTNC3.getText().equals(currentPlayer))
            return true;

        // -- -- //



        // -- Check columns for win condition -- //

        // Check first column
        if (BTNA1.getText().equals(currentPlayer)
                && BTNB1.getText().equals(currentPlayer)
                && BTNC1.getText().equals(currentPlayer))
            return true;

        // Check second column
        if (BTNA2.getText().equals(currentPlayer)
                && BTNB2.getText().equals(currentPlayer)
                && BTNC2.getText().equals(currentPlayer))
            return true;

        // Check third column
        if (BTNA3.getText().equals(currentPlayer)
                && BTNB3.getText().equals(currentPlayer)
                && BTNC3.getText().equals(currentPlayer))
            return true;

        // -- -- //
         

        // check first diagonal
        if (BTNA1.getText().equals(currentPlayer)
                && BTNB2.getText().equals(currentPlayer)
                && BTNC3.getText().equals(currentPlayer))
            return true;

        // check second diagonal
        if (BTNA3.getText().equals(currentPlayer)
                && BTNB2.getText().equals(currentPlayer)
                && BTNC1.getText().equals(currentPlayer))
            return true;
        // all other cases
        return false;

    }

    public void switchPlayer() {

        if (currentPlayer.equals("x")) {

            currentPlayer = "o";

        } else {

            currentPlayer = "x";

        }

    }

}
