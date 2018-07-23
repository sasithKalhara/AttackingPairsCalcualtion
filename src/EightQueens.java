import java.util.Random;


public class EightQueens {

   boolean[][] bd; 
   int    size;

   EightQueens(int n) {
       size = n;
       bd = new boolean[n][n];
   }
	    
   public static void main(String[] argv) {
	   int numberOfQueens = 8; 
	   EightQueens chessBoard = new EightQueens(numberOfQueens);
	   Random random = new Random();
	   int  position = random.nextInt(7) + 0;
	        
	   chessBoard.checkPosition(0, position);

	    }


	    public void dump() {

	        for (int i = 0; i < size ; i++) {
	            String l = "";
	            for (int j = 0; j < size; j++) {
	                if ( bd[i][j]) {
	                    l += " Q";
	                } else {
	                    l += " .";
	                }
	            }
	            System.out.println(l);
	        }
	    }

	    private boolean isValidPos(int x,  int y) {
	        int i, j0, j1, c;    
	        for (i = x-1, c = 1; i >= 0; i--, c++) {
	            // horizontal line check
	            if (bd[i][y]){
	            	
	                return false;
	            }

	            // diagonal1 check 
	            j0 = y - c;
	            if (j0 >= 0 && bd[i][j0]){
	            	
	                return false;
	            }

	            // diagonal2 check
	            j1 = y + c;
	            if (j1 < size && bd[i][j1]){
	            	
	                return false;
	            }
	        }
	        return true;
	    }

	    private boolean checkPosition(int x, int y) {
	        int j;
	        for ( j = y; j < size ; j++) {
	            if (isValidPos(x, j)) {
	                bd[x][j] = true;

	                if (x + 1 >= size ) {
	                    dump();
	                    bd[x][j] = false;

	                    return false;
	                }

	                if (!checkPosition(x+1, 0)) {
	                    return false;
	                }
	                bd[x][j] = false;
	            } 
	        }
	        return true;
	    }
	}