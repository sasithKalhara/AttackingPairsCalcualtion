import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EightQueen {

	public static int numberOfQueens = 8;
	private int[][] chessBoard;
	private int[] queenPositions;

	public static void main(String[] args) {

		boolean climb = true;
	
		
		while (climb) {
			
			EightQueen chessBoard = new EightQueen(new int[8][8], new int[8]);

			// randomly place queens
			chessBoard.placeQueens();
			System.out.println("Initial board:");
			chessBoard.printBoard();
			System.out.println("Pairs of attacking queens: " + chessBoard.calculateCost() + "\n");

//			initial cost
			int initialCost = chessBoard.calculateCost();
			
			
			if (initialCost == 0){
				climb = false;
				}
			
			boolean best = false;
			
			int[] bestQueenPositions = new int[8];

			int currentCost = chessBoard.calculateCost();
			for (int j = 0; j < 8; j++) {
				
				best = false;
				
				for (int i = 0; i < 8 ; i++) {
															
				
					chessBoard.moveQueen(i, j);
						
						if (chessBoard.calculateCost() < currentCost) {
							best = true;
							currentCost = chessBoard.calculateCost();
							bestQueenPositions[j] = i;
						}else
						// reset to original
						chessBoard.resetQueen(i, j);

					
				}
				// Removing marker
				chessBoard.resetBoard(j);
				
				if (best) {
					//  place queen in the best position
					chessBoard.placeBestQueen(j, bestQueenPositions[j]);
					System.out.println("Best board found with the iteration of column " + j);
					chessBoard.printBoard();
					
				} else {
					if(j != 7){
					System.out.println("Best board found with the iteration of column " + j);
					chessBoard.printBoard();
					
					}else if(j == 7){
						System.out.println("\n");
						System.out.println("Board with least number of attacking queens");
						chessBoard.printBoard();
						System.out.println("Pairs of queens attacking each other: "+ chessBoard.calculateCost());
						int finalCost = chessBoard.calculateCost();
						if(finalCost == 0 ){
							System.out.println("A Global Minimum was obtained");
						}else{
							System.out.println("A Local Minimum was obtained");
						}
					}
				}
			} 
			climb = false;

			if (chessBoard.calculateCost() == 0){
				climb = false;
			}
		}
	}

	public EightQueen(int[][] board, int[] positions) {

		this.chessBoard = board;
		this.queenPositions = positions;

	}

	private int[] generateQueens() {

		List<Integer> randomNumber = new ArrayList<Integer>();

		Random r = new Random();
		for (int i = 0; i < 8; i++) {
			randomNumber.add(r.nextInt(8));
		}

		int[] randomQueenPositions = new int[8];

		for (int i = 0; i < 8; i++) {
			randomQueenPositions[i] = randomNumber.get(i);
		}

		return randomQueenPositions;
	}

	public void placeQueens() {

		queenPositions = generateQueens();

		for (int i = 0; i < chessBoard.length; i++) {
			chessBoard[queenPositions[i]][i] = 1;
		}

	}

	public int calculateCost(){

		int totalPairs = 0;
		int totalPairsRow = 0;
		int totalPairsDiagonal = 0;
		
//		int rowAttckCount = 0;
		
		//  rows
		for (int i = 0; i < chessBoard.length; i++) {
			List<Integer> rowAttackingPairs = new ArrayList<>();
			for (int j = 0; j < chessBoard[i].length; j++) {

				if (chessBoard[i][j] == 1) {
					
//					rowAttckCount ++;
					rowAttackingPairs.add(1);
				}

			}
			if (rowAttackingPairs.size() != 0){
				totalPairsRow = (rowAttackingPairs.size() - 1);
				}
		}
//		System.out.print( rowAttckCount);			

		// diagonal 
		
		for (int sum = 0; sum <= 14; sum++) {
			List<Integer> diaginalLeftAttackingPairs = new ArrayList<>();;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (i + j - sum == 0) {
						if (chessBoard[i][j] == 1) {
							diaginalLeftAttackingPairs.add(1);
						}
					}
				}
			}
			if (diaginalLeftAttackingPairs.size() != 0){
				totalPairsDiagonal = (diaginalLeftAttackingPairs.size() - 1);
				}
		}
		totalPairs = totalPairsRow + totalPairsDiagonal;
		return totalPairs;
	}
	


	public void moveQueen(int row, int col) {

		chessBoard[queenPositions[col]][col] = 2;

		chessBoard[row][col] = 1;

	}

 void resetQueen(int row, int col) {

		if (chessBoard[row][col] == 1)
			chessBoard[row][col] = 0;
	}

	public void resetBoard(int col) {

		for (int i = 0; i < 8; i++) {
			if (chessBoard[i][col] == 2)
				chessBoard[i][col] = 1;
		}
	}

	public void placeBestQueen(int col, int queenPos) {

		for (int i = 0; i < 8; i++) {
			if (chessBoard[i][col] == 1)
				chessBoard[i][col] = 2;

		}
		chessBoard[queenPos][col] = 1;
		for (int i = 0; i < 8; i++) {
			if (chessBoard[i][col] == 2)
				chessBoard[i][col] = 0;

		}
	}

	public void printBoard() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(chessBoard[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
