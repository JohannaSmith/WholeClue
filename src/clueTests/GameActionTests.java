package clueTests;

import java.awt.Color;
import java.util.ArrayList;





import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ComputerPlayer;
import clueGame.Game;
import clueGame.Player;
import clueGame.RoomCell;
import clueGame.RoomCell.DoorDirection;
import clueGame.Suggestion;

public class GameActionTests {

	private static Game ourGame;
	
	@BeforeClass
	public static void setup() {
		ourGame = new Game();
		ourGame.loadConfigFiles("./ourboardfiles/StartCharacters.txt", "./ourboardfiles/Weapons.txt");
		ourGame.deal();
	}
	
	@Test
	public void computerSuggestion(){
		RoomCell cPlayerLoc = new RoomCell('C', DoorDirection.RIGHT);
		ComputerPlayer cPlayer = new ComputerPlayer("Miss Scarlet", cPlayerLoc, Color.RED);

		String room = ourGame.getGameBoard().getRoom(cPlayerLoc.getInitial());
		ArrayList<Card> deck = ourGame.getDeck();
		
		//Creating pseudo seen deck for Computer Player
		for(int i = 1; i< 6; i++){
			cPlayer.updateSeen(deck.get(i)); //Adds all weapon cards to seen except 'Wrench'
		}
		for(int i = 7; i < 12; i++){
			cPlayer.updateSeen(deck.get(i)); //Adds all person cards to seen except 'Miss Scarlet'
		}
		
		//Only Possible Suggestion for Computer
		Suggestion expected = new Suggestion("Miss Scarlet", "Wrench", "room");
		Suggestion actual = cPlayer.createSuggestion(room, ourGame.getDeck());
		Assert.assertEquals(expected, actual);
		
		
		
		//Four Different Possible Solutions with 1 Room
		cPlayerLoc = new RoomCell('D', DoorDirection.UP);
		cPlayer = new ComputerPlayer("Mrs. Peacock", cPlayerLoc, Color.BLUE);
		room = ourGame.getGameBoard().getRoom(cPlayerLoc.getInitial());
		
		//Creates new pseudo deck for new computer player
		for(int i = 2; i< 6; i++){
			cPlayer.updateSeen(deck.get(i)); //Adds all weapon cards to seen except 'Wrench' and 'Rope'
		}
		for(int i = 8; i < 12; i++){
			cPlayer.updateSeen(deck.get(i)); //Adds all person cards to seen except 'Miss Scarlet' and 'Mrs. Peacock'
		}
		
		actual = cPlayer.createSuggestion(room, deck);
		
		//Test for the four possibilities of suggestions by individual field
		Assert.assertEquals(room, actual.getRoom());
		Assert.assertTrue(actual.getName().equals("Miss Scarlet") || actual.getName().equals("Mrs. Peacock"));
		Assert.assertTrue(actual.getWeapon().equals("Wrench") || actual.getWeapon().equals("Rope"));
		
		
	}
	
}
