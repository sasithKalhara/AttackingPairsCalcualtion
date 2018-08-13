public class NumberOfAttackingPairs {

    public static void main(String[] args) {
       
      int[][] array = {
            {0, 0, 0, 0, 0, 1, 0, 0}, 
            {1, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 1, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0},
      };
int rowCounter = 0;
int diagonalCounterRight = 0;
int diagonalCounterLeft = 0;
int sum = 1;

while (sum <= 14){
    for (int i = 0; i < array.length; i++){
	for (int j = 0; j < array[i].length; j++){
            if(i+j == sum){
		for(int m = i+1; m<=7; m++){
                    for(int n=j-1; n>=0; n--){
			if(array[i][j] == array[m][n] && array[i][j] == 1 && n+m == sum){
                            //System.out.print("|" + i + j + "," + m + n + "|" + " ");
                            diagonalCounterLeft++;
			}
                    } 
		}
            }
	}
    }sum=sum+1;   
}
System.out.print("Left Diagonal Attacking Pairs " + diagonalCounterLeft + "\n" );

for(int i = 0; i < array.length; i++){
    for (int j = 0; j < array[i].length; j++){
        int k=i+1;
        int l=j+1;
        while(k<=7 && l <=7){
            if(array[i][j]== array[k][l] && array [i][j]==1){
                System.out.print("|" + i + j + "," + k + l + "|" + " "); 
                diagonalCounterRight++;
            } 
            k++;
            l++;
        }
    }
}
System.out.print("Right Diagonal Attacking Pairs " + diagonalCounterRight + "\n" );

for (int i = 0; i < array.length; i++) {
    for (int j = 0; j < array[i].length; j++) {
        for (int k = 0; k < 8 && k != j; k++){
            if (array[i][j]== array[i][k] && array[i][j] == 1){
                rowCounter++;
                //System.out.print("|" + i + j + "," + i + k + "|" + " ");
            }
        }
    }
}
System.out.print("Row Attacking Pairs " + rowCounter + "\n" );
 
    }
}
