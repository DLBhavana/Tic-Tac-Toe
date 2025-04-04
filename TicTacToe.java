import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];  // 3x3 grid
    private static char currentPlayer = 'X';  // Player X starts first
    private static boolean gameOver = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        do {
            // Initialize the board for a new game
            initializeBoard();
            printBoard();

            // Game loop
            while (!gameOver) {
                if (currentPlayer == 'X') {
                    playerMove(scanner);
                } else {
                    computerMove(random);
                }
                printBoard();
                checkForWinner();
                switchPlayer();
            }

            // Ask if the players want to restart the game
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine().trim().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;  // Exit the game loop
            }

            // Reset game over flag for the next round
            gameOver = false;

        } while (true);

        System.out.println("Thanks for playing Tic Tac Toe!");
        scanner.close();
    }

    // Initialize the board to empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Print the current state of the board
    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Handle the player's move
    private static void playerMove(Scanner scanner) {
        int row, col;
        boolean validMove = false;

        // Ask the current player to make a move
        while (!validMove) {
            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.print("Enter row (1-3): ");
            row = scanner.nextInt() - 1;  // Convert to 0-indexed
            System.out.print("Enter column (1-3): ");
            col = scanner.nextInt() - 1;  // Convert to 0-indexed

            // Validate the move
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                validMove = true;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.nextLine();  // Clear the newline character left in the buffer
    }

    // Handle the computer's move
    private static void computerMove(Random random) {
        int row, col;
        boolean validMove = false;

        // Randomly choose a valid move for the computer
        while (!validMove) {
            row = random.nextInt(3);
            col = random.nextInt(3);

            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                validMove = true;
                System.out.println("Computer's turn.");
                System.out.println("Computer chooses row " + (row + 1) + " and column " + (col + 1));
            }
        }
    }

    // Switch between players
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Check if there is a winner or a draw
    private static void checkForWinner() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                announceWinner(board[i][0]);
                return;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                announceWinner(board[0][i]);
                return;
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            announceWinner(board[0][0]);
            return;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            announceWinner(board[0][2]);
            return;
        }

        // Check for draw (if the board is full)
        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    isDraw = false;
                    break;
                }
            }
        }

        if (isDraw) {
            announceDraw();
        }
    }

    // Announce the winner
    private static void announceWinner(char winner) {
        System.out.println("Player " + winner + " wins!");
        gameOver = true;
    }

    // Announce a draw
    private static void announceDraw() {
        System.out.println("It's a draw!");
        gameOver = true;
    }
}
