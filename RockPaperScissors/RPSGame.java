package RockPaperScissorGame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class RPSGame extends JFrame {

    JPanel RPSPanel = new JPanel();
    Font gamefont = new Font(Font.SANS_SERIF, Font.BOLD, 100);

    // ----- Default Scores ----- //
    int AIScore = 0;
    int userScore = 0;
    int tieScore = 0;

    // ----- JButtons / Labels ----- //
    JButton rockButton = new JButton("Rock");
    JButton paperButton = new JButton("Paper");
    JButton scissorButton = new JButton("Scissors");
    JButton resetButton = new JButton("Reset");

    JLabel Score = new JLabel("Your Score: " + userScore + " AI Score: " + AIScore + "Tie Score: " + tieScore);

    // ----- Game ----- //
    public RPSGame() {

        // ----- Default Parameters for game ----- //
        super("Rock! Paper! Scissors!");
        setContentPane(RPSPanel);
        setSize(300, 800);
        setVisible(true);
        setLocation(500, 500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        /*
         * All of these are needed for the window to be visible,
         * Close and stay closed,
         * Open the window at a coordinate area,
         * and disallow resizing.
         */

        // ----- Buttons for game ----- //
        rockButton.setBounds(0, 0, 300, 150);
        rockButton.addActionListener(e -> MyClickEventHandler(e));
        add(rockButton);

        paperButton.setBounds(0, 150, 300, 150);
        paperButton.addActionListener(e -> MyClickEventHandler(e));
        add(paperButton);

        scissorButton.setBounds(0, 300, 300, 150);
        scissorButton.addActionListener(e -> MyClickEventHandler(e));
        add(scissorButton);

        resetButton.setBounds(0, 450, 300, 150);
        resetButton.addActionListener(e -> resetGame(e));
        add(resetButton);

        // ----- Panel for score ----- //
        Score.setBounds(30, 600, 300, 150);
        RPSPanel.add(Score);

    }

    // ----- Click Event Handler for RPSGame ----- //
    public void MyClickEventHandler(java.awt.event.ActionEvent e) {

        // ----- AI Choice Settings ----- //
        int AIChoice = (int) (Math.random() * 3) + 1; // Choices are numbered, and in order of RPS

        if (AIChoice == 1 && e.getSource() == rockButton) { // Rock Choices

            tieScore += 1;

        } else if (AIChoice == 1 && e.getSource() == paperButton) {

            userScore += 1;

        } else if (AIChoice == 1 && e.getSource() == scissorButton) {

            AIScore++;

        }

        if (AIChoice == 2 && e.getSource() == paperButton) { // Paper Choices

            tieScore++;

        } else if (AIChoice == 2 && e.getSource() == rockButton) {

            userScore += 1;

        } else if (AIChoice == 2 && e.getSource() == scissorButton) {

            AIScore++;

        }

        if (AIChoice == 3 && e.getSource() == scissorButton) { // Paper Choices

            tieScore++;

        } else if (AIChoice == 3 && e.getSource() == paperButton) {

            userScore += 1;

        } else if (AIChoice == 3 && e.getSource() == rockButton) {

            AIScore++;

        }

        Score.setText("Player Score: " + userScore + " AI Score: " + AIScore + " Tie Score: " + tieScore);

    }

    // ----- Reset Button Behavior ----- //

    public void resetGame(java.awt.event.ActionEvent e) {
        int reset = 0;
        if (e.getSource() == resetButton) {
            userScore = reset;
            AIScore = reset; // Good example of OOP through the rest, rather than refactoring values manually
            tieScore = reset;
            Score.setText("Player Score: " + userScore + " AI Score: " + AIScore + " Tie Score: " + tieScore);
        }
    }

}
