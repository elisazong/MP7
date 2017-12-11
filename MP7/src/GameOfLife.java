import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class GameOfLife {
	
	public static Board createBoard() {
        System.out.print("How many rows will you have? (6-30): ");
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        while (row > 30 || row < 6) {
            System.out.print("Sorry. The number of rows must be between 6 and 30: ");
            row = scanner.nextInt();
        }
        System.out.print("How many columns will you have? (10-50): ");
        int column = scanner.nextInt();
        while (column > 50 || column < 10) {
            System.out.print("Sorry. The number of rows must be between 10 and 50: ");
            column = scanner.nextInt();
        }
        scanner.close();
        Board user = new Board(row, column);
        user.setBoard();
        return user;
	}
	
	public static int countNeighbour(Board user, int rowPos, int colPos) {
		int count = 0;
		int left, right, upper, lower;
		if (rowPos == 0) {
			upper = user.row - 1;
		} else {
			upper = rowPos - 1;
		}
		if (rowPos == user.row - 1) {
			lower = 0;
		} else {
			lower = rowPos + 1;
		}
		if (colPos == 0) {
			left = user.column - 1;
		} else {
			left = colPos - 1;
		}
		if (colPos == user.column - 1) {
			right = 0;
		} else {
			right = colPos + 1;
		}
		if (user.board[upper][left] == 1) {
			count++;
		}
		if (user.board[upper][colPos] == 1) {
			count++;
		}
		if (user.board[upper][right] == 1) {
			count++;
		}
		if (user.board[rowPos][left] == 1) {
			count++;
		}
		if (user.board[rowPos][right] == 1) {
			count++;
		}
		if (user.board[lower][left] == 1) {
			count++;
		}
		if (user.board[lower][colPos] == 1) {
			count++;
		}
		if (user.board[lower][right] == 1) {
			count++;
		}
		return count;
	}
	
	public static void runGame(Board user) {
		Board temp = new Board(user.row, user.column);
		for (int i = 0; i < user.row; i++) {
			for (int j = 0; j < user.column; j++) {
				temp.board[i][j] = user.board[i][j];
			}
		}
		for (int i = 0; i < user.row; i++) {
			for (int j = 0; j < user.column; j++) {
				if (countNeighbour(temp, i, j) == 3) {
					user.board[i][j] = 1;
				} else if (countNeighbour(temp, i, j) == 2) {
				} else {
					user.board[i][j] = 0;
				}			
			}
		}
	}
	
	public static void printBoard(Board user, JPanel[][] panel) {
		for (int i = 0; i < user.row; i++) {
			for (int j = 0; j < user.column; j++) {
				if (user.board[i][j] == 0) {
					panel[i][j].setBackground(Color.CYAN);;
				} else {
					panel[i][j].setBackground(Color.RED);;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		Board user = createBoard();
		JFrame frame = new JFrame("Game of Life");
		JPanel[][] panel = new JPanel[user.row][user.column];
		
		JPanel panel1 = new JPanel();
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel1.setBackground(Color.DARK_GRAY);
		
		JPanel panel1_1 = new JPanel();
		frame.getContentPane().add(panel1_1, BorderLayout.CENTER);
		panel1_1.setLayout(new GridLayout(user.row, user.column, 10, 10));
		
		for (int i = 0; i < user.row; i++) {
			for (int j = 0; j < user.column; j++) {
				panel[i][j] = new JPanel();
				panel[i][j].setBackground(Color.WHITE);
				panel1_1.add(panel[i][j]);
			}
		}		
		frame.setLocation(500, 500);
		frame.setSize(500, 500);
		
		frame.setVisible(true);
		
		
		while (true) {
			System.out.println();
			runGame(user);
			printBoard(user, panel);
			try {
				  Thread.sleep(500);
				} catch (InterruptedException ex) {
				  Thread.currentThread().interrupt();
				  throw new RuntimeException(ex);
				}
		}
	}

}
