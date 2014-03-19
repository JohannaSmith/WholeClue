package clueGame;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import java.lang.reflect.Field;



public class Game {

	private Board ourGameBoard = new Board();
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Card> deck = new ArrayList<Card>();
	public Game() {
		// TODO Auto-generated constructor stub
	}
	
	public void deal() {
		
	}
	public void loadConfigFiles(String p, String w){
		try{
			ourGameBoard.loadConfigFiles();
			loadWeapons(w);
			loadPlayers(p);
		} catch(BadConfigFormatException b) {
			System.out.println("Bad config file. Please look at your config file and make sure you haven't ruined something.");
		} catch(FileNotFoundException e) {
			System.out.println("File Not Found! Please make sure your files are accessible.");
		}
		
	}
	private void loadWeapons(String weapons) throws BadConfigFormatException, FileNotFoundException { //possibly adjust
		//BadConfigFormatException to account for weird shit
		Scanner scan = new Scanner(new FileReader(weapons));
		String thisWeapon;
		while(scan.hasNextLine()) {
			thisWeapon = scan.nextLine();
			Card card = new Card(thisWeapon, CardType.WEAPON);
			deck.add(card);
		}
		scan.close();
	}

	private void loadPlayers(String playerFile) throws FileNotFoundException, BadConfigFormatException {
		Scanner scan = new Scanner(new FileReader(playerFile));
		String[] theSplit = new String[3];
		String ourPlayer, color, local;
		BoardCell cell = new WalkwayCell();
		while(scan.hasNextLine()) {
			theSplit = scan.nextLine().split(";");
			ourPlayer = theSplit[0].trim();
			color = theSplit[1].trim();
			local = theSplit[2].replace('(', ' ').replace(')', ' ').trim();
			theSplit = local.split(",");
			//System.out.println(theSplit[0] + " " + theSplit[1]);
			cell = ourGameBoard.getCellAt(Integer.parseInt(theSplit[0]), Integer.parseInt(theSplit[1]));
			//Player p = new Player(ourPlayer, cell);
			deck.add(new Card(ourPlayer, CardType.PERSON));
			Player p;
				if (ourPlayer.equals("Ms. Scarlet")) {
					p = new HumanPlayer(ourPlayer, cell, convertColor(color));
				}
				else {
					p = new ComputerPlayer(ourPlayer, cell, convertColor(color));
				}
				players.add(p);
		}
		scan.close();
	}
	
	public Color convertColor(String strColor) {
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined } 
		}
		return color;
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
