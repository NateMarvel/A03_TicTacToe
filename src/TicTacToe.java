import java.util.Scanner;
public class TicTacToe
{
        private static final int ROWS = 3;
        private static final int COLS = 3;
        private static String[][] board = new String[ROWS][COLS];
        public static void main(String[] args)
        {
            Scanner in = new Scanner(System.in);
            String player = "X";
            boolean gameOver = false;
            boolean playAgain = false;
            do {
                clearBoard();
                player = "X";
                gameOver = false;
                do {
                    display();
                    int row = 0;
                    int col = 0;
                    boolean validMove = false;
                    do {
                        row = SafeInput.getRangedInt(in, "Player " + player + ", enter your row", 1, 3) - 1;
                        col = SafeInput.getRangedInt(in, "Player " + player + ", enter your column", 1, 3) - 1;
                        validMove = isValidMove(row, col);
                        if (!validMove) {
                            System.out.println("That cell is already taken. Try again!");
                        }
                    } while (!validMove);
                    board[row][col] = player;
                    if (isWin(player)) {
                        display();
                        System.out.println("Player " + player + " wins!");
                        gameOver =true;
                    } else if (isTie()) {
                        display();
                        System.out.println("It's a tie!");
                        gameOver = true;
                    } else {
                        if (player.equals("X")) {
                            player = "O";
                        } else {
                            player = "X";
                        }
                    }
                } while (!gameOver);
                playAgain = SafeInput.getYNConfirm(in, "Play again?");
            } while(playAgain);
        }
        private static void clearBoard()
        {
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    board[r][c] = " ";
                }
            }
        }
        private static void display()
        {
            System.out.println();
            System.out.println("   1   2   3");
            for (int r = 0; r < ROWS; r++) {
                System.out.print(" " + (r + 1) + " ");
                for (int c = 0; c < COLS; c++) {
                    System.out.print(board[r][c]);
                    if (c < COLS - 1) {
                        System.out.print(" | ");
                    }
                }
                System.out.println();
                if (r < ROWS - 1) {
                    System.out.println("   ---+---+---");
                }
            }
            System.out.println();
        }
        private static boolean isValidMove(int row, int col)
        {
            return board[row][col].equals(" ");
        }
        private static boolean isWin(String player)
        {
            return isRowWin(player) || isColWin(player) || isDiagWin(player);
        }
        private static boolean isRowWin(String player)
        {
            for (int r = 0; r < ROWS; r++) {
                if (board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player)) {
                    return true;
                }
            }
            return false;
        }
        private static boolean isColWin(String player)
        {
            for (int c = 0; c < COLS; c++) {
                if (board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player)) {
                    return true;
                }
            }
            return false;
        }
        private static boolean isDiagWin(String player)
        {
            return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
        }
        private static boolean isBlocked(String a, String b, String c)
        {
            boolean hasX = a.equals("X") || b.equals("X") || c.equals("X");
            boolean hasO = a.equals("O") || b.equals("O") || c.equals("O");
            return hasX && hasO;
        }
        private static boolean isTie()
        {
            for (int r = 0; r < ROWS; r++) {
                if (!isBlocked(board[r][0], board[r][1], board[r][2])) {
                    return false;
                }
            }
            for (int c = 0; c < COLS; c++) {
                if (!isBlocked(board[0][c], board[1][c], board[2][c])) {
                    return false;
                }
            }
            if (!isBlocked(board[0][0], board[1][1], board[2][2])) {
                return false;
            }
            if (!isBlocked(board[0][2], board[1][1], board[2][0])) {
                return false;
            }
            return true;
        }
}
