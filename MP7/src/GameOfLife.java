import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class GameOfLife {
	
	/** Created a game board with user input.
	 * 
	 * @return a game board with user input
	 */
	public static Board createBoard() {
        System.out.print("How many rows will you have? (20-50): ");
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        while (row > 50 || row < 20) {
            System.out.print("Sorry. The number of rows must be between 20 and 50: ");
            row = scanner.nextInt();
        }
        System.out.print("How many columns will you have? (20-50): ");
        int column = scanner.nextInt();
        while (column > 50 || column < 20) {
            System.out.print("Sorry. The number of rows must be between 20 and 50: ");
            column = scanner.nextInt();
        }
        scanner.close();
        Board user = new Board(row, column);
        user.setBoard();
        return user;
	}
	
	/** Count the number of life around a certain position and
	 * 	determine that position is alive or dead
	 * 
	 * @param user	- the input board
	 * @param rowPos	- row position on the board
	 * @param colPos	- column position on the board
	 * @return 1 if the cell is alive, 0 if the cell is dead
	 */
	public static int countNeighbour(Board user, int rowPos, int colPos) {
		int count = 0;
		int left, right, upper, lower;
		
		/* if the cell is on the edge, the surrounding cells will be found
		 * on the opposite edges. 
		 * 
		 */
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
		
		/*	count the number of surrounding lives
		 * 
		 */
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
	
	/** refresh the board and assign the cells with the rules
	 * 	if there are two alive cells around one certain cell, it 
	 * 	remain its current status. if there are three alive cells,
	 * 	the cell is alive. The other surrounding status will kill 
	 * 	the cell.
	 * 
	 * @param user	- input game board
	 */
	public static void runGame(Board user) {
		/*	create a copy of the current board
		 * 
		 */
		Board temp = new Board(user.row, user.column);
		for (int i = 0; i < user.row; i++) {
			for (int j = 0; j < user.column; j++) {
				temp.board[i][j] = user.board[i][j];
			}
		}
		/* refresh the board using the precious status
		 * 
		 */
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
	
	/** refresh the current JPanel.
	 * 	Red cells are alive. Orange cells are dead.
	 * 
	 * @param user	- the current game board
	 * @param panel	- the panel contains the color information
	 */
	public static void printBoard(Board user, JPanel[][] panel) {
		for (int i = 0; i < user.row; i++) {
			for (int j = 0; j < user.column; j++) {
				if (user.board[i][j] == 0) {
					panel[i][j].setBackground(Color.ORANGE);
				} else {
					panel[i][j].setBackground(Color.RED);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		Board user = createBoard();
		/* create a new window to show the animation
		 * 
		 */
		JFrame frame = new JFrame("Game of Life");
		
		/* place a refreshable panel at the center of the window
		 * The color of the panel is dark gray which will separate
		 * the cells.
		 * 
		 */
		JPanel panel1 = new JPanel();
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel1.setBackground(Color.DARK_GRAY);
		
		/* create an 2d array of cells to contain the color information
		 * 
		 */
		JPanel[][] panel = new JPanel[user.row][user.column];
		JPanel panel1_1 = new JPanel();
		frame.getContentPane().add(panel1_1, BorderLayout.CENTER);
		panel1_1.setLayout(new GridLayout(user.row, user.column, 3, 3));
		
		/* initialize all the cells to white.
		 * 
		 */
		for (int i = 0; i < user.row; i++) {
			for (int j = 0; j < user.column; j++) {
				panel[i][j] = new JPanel();
				panel[i][j].setBackground(Color.WHITE);
				panel1_1.add(panel[i][j]);
			}
		}
		/* Set the size of the board and place it at the center of the screen.
		 * 
		 */
		frame.setSize(user.column * 15, user.row * 15);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		/* show the window.
		 * 
		 */
		frame.setVisible(true);
		/* start the game
		 * 
		 */
		while (true) {
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
