import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    // largeure et hauteur de la fenetre

    int borderWidth = 700;
    int borderHeight = 650; // 50px pour le text au top

    JFrame frame = new JFrame("Tic-Tac-toe");// pour creer la fenetre et le paramettre est le nom de la fenetre
    JLabel textLabel = new JLabel();// pour donner un titre a notre appli
    JPanel textJPanel = new JPanel();
    JPanel borderPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;
    int times = 0;

    int scoreX = 0;
    int scoreO = 0;

    JLabel scoreLabel = new JLabel();

    // constructeur
    // le fram contient tout la fenetre
    TicTacToe() {
        frame.setVisible(true);// visibilite
        frame.setSize(borderWidth, borderHeight);// taille de la fenetre
        frame.setLocationRelativeTo(null);// centre la fenetre au milieu de l'ecran
        frame.setResizable(false);// redimensionner la fenetre non
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// fermeture de la fenetre sur le clique x
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);// background-color
        textLabel.setForeground(Color.white);// background-color
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));// font-size, font-family, font-weight
        textLabel.setHorizontalAlignment(JLabel.CENTER);// centre le text
        textLabel.setText("Tic-Tac-Toe");// titre du jeu
        textLabel.setOpaque(true);

        textJPanel.setLayout(new BorderLayout());
        textJPanel.add(textLabel);// ajout du text dans l'interface
        frame.add(textJPanel, BorderLayout.NORTH);// orientation du text (haut:noth, bas:south, gauch:west, droit:est)

        borderPanel.setLayout(new GridLayout(3, 3));
        borderPanel.setBackground(Color.darkGray);
        frame.add(borderPanel);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                borderPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                // tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton tile = (JButton) e.getSource();

                        if (gameOver)
                            return;
                        if (tile.getText() == "") {

                            tile.setText(currentPlayer);
                            times++;
                            checkWinner();
                            // test avec operateur terneur ?
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }

                        }
                    }
                });

            }
        }
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        scoreLabel.setText("Scores - X: " + scoreX + " | O: " + scoreO);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        textJPanel.add(resetButton, BorderLayout.SOUTH); // Ajout du bouton réinitialiser en bas
        textJPanel.add(scoreLabel, BorderLayout.CENTER); // Affichage des scores

    }

    void checkWinner() {
        // gagnat sur l'horizontal
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText() == "")
                continue;

            if (board[i][0].getText() == board[i][1].getText() &&
                    board[i][1].getText() == board[i][2].getText()) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);
                }
                gameOver = true;
                return;
            }

        }
        // definir le gagnant sur la vertical

        for (int n = 0; n < 3; n++) {
            if (board[0][n].getText() == "")
                continue;

            if (board[0][n].getText() == board[1][n].getText() &&
                    board[1][n].getText() == board[2][n].getText()) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[j][n]);
                }
                gameOver = true;
                return;
            }
        }
        // gagnant sur la diagonale
        if (board[0][0].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][2].getText() &&
                board[0][0].getText() != "") {
            for (int i = 0; i < 0; i++) {
                setWinner(board[i][i]);
            }
        }
        // anti-diagonale
        if (board[0][2].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][0].getText() &&
                board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if (times == 9) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    setTie(board[i][j]);
                }
            }
            gameOver = true;
        }
    }

    // definir le gagnat en vert
    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + "is the winner !");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie !");
    }

    // reinitialiser le jeu

    void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
                board[i][j].setBackground(Color.darkGray);
                board[i][j].setForeground(Color.white);
            }
        }
        currentPlayer = playerX;
        textLabel.setText(currentPlayer + "'s turn.");
        gameOver = false;
    }
}
