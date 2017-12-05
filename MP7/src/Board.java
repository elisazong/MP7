
public class Board {

	int row;
	int column;
	int[][] board = new int[row][column];
	
	Board(int r, int c) {
		this.row = r;
		this.column = c;
		this.board = new int[r][c];
	}

	void setBoard() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (Math.random() * 3 < 2) {
					board[i][j] = 0;
				} else {
					board[i][j] = 1;
				}
			}
		}
	}

}
