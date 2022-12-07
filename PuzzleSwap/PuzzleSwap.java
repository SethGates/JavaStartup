package PuzzleSwap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.File;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

// ----- Specific behavior for mouse, on buttons ----- //
class FancyButton extends JButton {

    public FancyButton() {

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                setBorder(BorderFactory.createLineBorder(Color.CYAN));

            }

            @Override
            public void mouseExited(MouseEvent e) {

                setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Set color back to original Gray

            }

        });

    }

}

public class PuzzleSwap extends JFrame {

    // ----- Variables for Game ----- //
    JPanel PZGPanel;

    final int NUMROWS = 3, NUMCOLS = 4;
    int HEIGHT = 300, WIDTH = 300;

    BufferedImage OGImage;
    BufferedImage scaledImage;

    List<FancyButton> allButtons = new ArrayList<FancyButton>();
    List<FancyButton> solution = new ArrayList<FancyButton>();

    // ----- Start of Game ----- //
    public PuzzleSwap() {
        super("Move the pieces!");

        PZGPanel = new JPanel(); // Init panel
        PZGPanel.setLayout(new GridLayout(NUMROWS, NUMCOLS));
        add(PZGPanel); // Add to JFrame

        // ----- Catch block for exceptions, image related ----- //
        try {
            OGImage = loadImage();

            // ----- Get attributes for image ----- //
            int OGWidth = OGImage.getWidth();
            int OGHeight = OGImage.getHeight();

            // ----- Rescaling image to window ----- //
            HEIGHT = (int) ((double) OGWidth / OGHeight * WIDTH);

            // ----- Rescaled image functions ----- //
            scaledImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            var g = scaledImage.createGraphics();
            g.drawImage(OGImage, 0, 0, WIDTH, HEIGHT, null);
            g.dispose();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage(),
                    "Error Title", JOptionPane.ERROR_MESSAGE);
                    
        }

        // ----- For loop to implement buttons ----- //
        for (int i = 0; i < NUMROWS * NUMCOLS; i++) {

            int row = i / NUMCOLS;
            int col = i % NUMCOLS;

            FancyButton FancyButton = new FancyButton();
            // PZGPanel.add(FancyButton);
            allButtons.add(FancyButton);
            FancyButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            FancyButton.addActionListener(e -> MyClickEventHandler(e));

            Image imageSlice = createImage(new FilteredImageSource(scaledImage.getSource(), new CropImageFilter(
                    col * WIDTH / NUMCOLS, row * HEIGHT / NUMROWS, WIDTH / NUMCOLS, HEIGHT / NUMROWS)));

            FancyButton.setIcon(new ImageIcon(imageSlice));

            if (i == NUMROWS * NUMCOLS - 1) {

                FancyButton.setBorderPainted(false);
                FancyButton.setContentAreaFilled(false);
                FancyButton.setIcon(null);

            } else {

                FancyButton.setIcon(new ImageIcon(imageSlice));

            }

            // ----- Shuffle function ----- //
            solution = List.copyOf(allButtons);
            
            Collections.shuffle(allButtons); // Shuffles our button list

            for (var btn : allButtons) // Now places the buttons on the panel after they are shuffled

            {

                PZGPanel.add(btn);

            }

        }

        // ----- Window Settings ----- //
        setSize(HEIGHT, WIDTH);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ----- Actions upon click ----- //
    public void MyClickEventHandler(ActionEvent e) {

        FancyButton btn = (FancyButton) e.getSource(); // Determines clicked button
        int j = allButtons.indexOf(btn);
        int btnROW = j / NUMCOLS, btnCOL = j % NUMCOLS;

        int i = -1;

        for (i = 0; i < allButtons.size(); i++) { // For loop to find empty button

            if (allButtons.get(i).getIcon() == null) {

                break;

            }

        }

        int emptyRow = i / NUMCOLS, emptyCOL = i % NUMCOLS;

        if ((emptyRow == btnROW && Math.abs(emptyCOL - btnCOL) == 1)
                || (emptyCOL == btnCOL && Math.abs(emptyRow - btnROW) == 1)) {

            Collections.swap(allButtons, i, j);

            PZGPanel.removeAll();
            for (var btn_ : allButtons) {

                PZGPanel.add(btn_);

            }

            PZGPanel.validate();

        }

        if (allButtons.equals(solution)) {

            JOptionPane.showMessageDialog(null, "You win!");

        }

    }

    // ----- Handles loading image to game ----- //
    BufferedImage loadImage() throws IOException {

        return ImageIO.read(new File("ArtPZG.jpg"));

    }

}
