import java.util.Scanner;

public class LifeOfGame {
	
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
	
	public static void printBoard(Board user) {
		for (int i = 0; i < user.row; i++) {
			for (int j = 0; j < user.column; j++) {
				if (user.board[i][j] == 0) {
					System.out.print("- ");
				} else {
					System.out.print("* ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Board user = createBoard();
		while (true) {
			System.out.println();
			runGame(user);
			printBoard(user);
			try {
				  Thread.sleep(500);
				} catch (InterruptedException ex) {
				  Thread.currentThread().interrupt();
				  throw new RuntimeException(ex);
				}
		}
	}

}
