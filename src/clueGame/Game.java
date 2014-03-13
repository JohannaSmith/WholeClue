package clueGame;

import java.util.ArrayList;

public class Game {

	private Board ourGameBoard;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Card> deck = new ArrayList<Card>();
	public Game() {
		// TODO Auto-generated constructor stub
	}
	
	public void deal() {
		
	}
	public void loadConfigFiles(){
		
	}
	public void selectAnswer() {
		//what to put in the 'closet'
	}
	public void handleAccusation(String room, String weapon, String person, Player accusingPlayer) {
		
	}
	public boolean checkAccusation(Solution solution) {

		return false;
	}
	
	//getters for testing
	public ArrayList<Card> getDeck() {
		//return deck;
		return new ArrayList<Card>();
	}
	public ArrayList<Player> getPlayers() {
		//return players;
		return new ArrayList<Player>();
	}

}
