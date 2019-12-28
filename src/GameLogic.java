import java.util.List;

public class GameLogic {

	private int team = 1;
	int placedArray[] = new int [42];
	
	
	public void switchTurn(){ 
		
		if (team == 2) {
			team = 1;
		}else if(team == 1){
			team = 2;
		}
	}
	

	

	
	//chooses slot 0 to 6 and presses enter
 public Winner findPieceSlot(int column){
	 
	switchTurn();
	 
	Winner winner = new Winner();
	int pos = column;
		
	 while(pos < placedArray.length + 7){
	
			if(pos > 41) {
				pos-=7;
				break;
			}
			else if(placedArray[pos] == 0){
					pos += 7;
			}else if(placedArray[pos] > 0 && (pos - 7) >= 0){
					pos-=7;
					break;
				}
			else{
				switchTurn();
					break;
				}
			}//end case 0 while loop

		//end of function, if pos greaater than length of fields its invalid
		//if pos under 42 call check winner function
	//	System.out.println(pos);
		if(pos > 41) {
			return winner;
		}
		placedArray[pos] = team; // for logic to check taken slots
		CreateGame game = new CreateGame(null);
		
		
		 CreateGame.setPiece(pos+7, team); // because of buttons add 7
		 winner = checkWinner();
		 return winner;
 }


public Winner checkWinner(){
	
	// try catch to handle arrays out of bounds
	Winner winner = new Winner();
	
	for(int i = 0; i < placedArray.length; i++) {
	//vertical
	try {
		if(placedArray[i] != 0 && placedArray[i] == placedArray[i + 7] && placedArray[i] == placedArray[i + 7*2] 
				&&  placedArray[i] == placedArray[i + 7*3]) {
			winner.setWinner(placedArray[i]);
			winner.addToIndexes(i, i + 7, i + 7*2, i + 7*3);
		}
	}catch(Exception err) {
		
	}
	
	//horizontal
	try {
		if(placedArray[i] != 0 && placedArray[i] == placedArray[i + 1] && placedArray[i] == placedArray[i + 2] 
				&&  placedArray[i] == placedArray[i + 3]) {
			winner.setWinner(placedArray[i]);
			winner.addToIndexes(i, i + 1, i + 2, i + 3);
		}
	}catch(Exception err) {
		
	}
	
	// down diagonal
	try {
		
		if(placedArray[i] != 0 && placedArray[i] == placedArray[i + 7 + 1] && placedArray[i] == placedArray[i + 7*2 + 2] 
				&&  placedArray[i] == placedArray[i + 7*3 + 3]) {
			winner.setWinner(placedArray[i]);
			winner.addToIndexes(i, i + 7 + 1, i + 7*2 + 2, i + 7*3 + 3);
		}
		
	}catch(Exception err) {
		
	}
	
	try { //up diagonal
		
		if(placedArray[i] != 0 && placedArray[i] == placedArray[(i - 7) + 1] && placedArray[i] == placedArray[(i - 7*2) + 2] 
				&&  placedArray[i] == placedArray[(i - 7*3) + 3]) {
			winner.setWinner(placedArray[i]);
			winner.addToIndexes(i, (i - 7) + 1, (i - 7*2) + 2, (i - 7*3) + 3);
		}
		
		
	}catch(Exception err) {
		
	}
	
}
	//checks if game is won
	if(winner.getGameWin()) {
	
		List <Integer> winningIndexes = winner.returnWinningIndexes();
		//returning start and end point of connect 4
		//System.out.println("winningIndexes " + winningIndexes.get(0) + " " + winningIndexes.get(1) + " " + winningIndexes.get(2) + " " + winningIndexes.get(3) + " " );
		CreateGame.drawWinner(winningIndexes.get(0), winningIndexes.get(1), winningIndexes.get(2), winningIndexes.get(3), winner.getTeam());
	}
	return winner;
	
}

public void resetGame(){
	placedArray = new int[42];
	team = 1;
}


}