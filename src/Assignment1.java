//==========================================================================================================================================================================================
//Name: Assignment One ICS4U1 2015 
//Created By: Gilbert Lam
//Date: October 26th, 2015
//Java Version: Java 1.7
//==========================================================================================================================================================================================
//Problem Definition: Create two programs. The first is  a Boggle application that displays a board of random letters (5x5), and allows the user to enter words found on the board.  
//                    The application should report if the word entered by the user is indeed on the board. 
//                    Words can contain duplicate letters, but a single letter on the board may not appear twice in a single word.
//                    The second program is an application that generates 3 blobs of asterisks. 
//                    The program will let the user select a starting point, and if that starting point is part of the blob, it will clear that blob, leaving the other blobs intact.
//                    The program should program that implements a recursive algorithm to read a pattern of blobs and erase one that covers a point selected by the user.  
//                    A rectangular array (20x25) is to be used for the grid.
//==========================================================================================================================================================================================

//=====================================================================================================================================================//Imports all necessary classes
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
//=====================================================================================================================================================//End of importing classes

public class Assignment1 {//Start of Assignment1 class

	/*This is the time limit variable that determines how much time (in seconds) the user has for 1 round of boggle
	 *This variable is initialized as 180 because standard rules of boggle says that one round is 3 minutes long
	 *This variable is a static variable because this has to be accessed by 2 classes and needs to have only 1 value throughout every instance of this class
	 */
	static int timeLimit = 180;

	/**main method:
	 * creates the JFrame used for the entire program
	 * creates objects to other classes
	 * 
	 * List of local variables:
	 * programStart - JFrame object used to display the GUI <type NewJFrame>
	 * boggleGameObject - Object used to access variables and methods of other classes <type BoggleGame>
	 * optionsObject - Object used to access variables and methods of other classes <type OptionsMenu>
	 * asteriskBlobObject Object used to access variables and methods of other classes <type AsteriskBlob>
	 * assignment1Object - Object used to access variables and methods of other classes <type Assignment1>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		NewJFrame programStart = new NewJFrame(); //Creates a NewJFrame object
		BoggleGame boggleGameObject = new BoggleGame();//Declares and Instantiates the BoggleGame object
		OptionsMenu optionsObject = new OptionsMenu();//Declares and Instantiates the OptionsMenu object
		AsteriskBlob asteriskBlobObject = new AsteriskBlob();//Declares and Instantiates the AsteriskBlob object
		Assignment1 assignment1Object = new Assignment1();
		programStart.setDecoration(true);
		programStart.setJFrameSize(1200,720);
		programStart.setFrameLocation();
		programStart.setBackground();
		programStart.setLayout(null);
		assignment1Object.mainMenu(programStart, optionsObject, boggleGameObject, asteriskBlobObject);	//Calls the mainMenu method, and passes the programStart JFrame 
	}//end of main method

	/**mainMenu method
	 * This procedural method adds various components to the JFrame and gives the user 4 options (Asterisk, Boggle, Options, and Exit)
	 * Serves as the "Main Menu" for the program
	 * 
	 * List of local variables:
	 * getFont - Object used to access variables and methods of other classes <type FontSelection>
	 * titleFont - Font used for the title <type Font>
	 * closeButton - JButton used to create the GUI <type JButton>
	 * optionsButton - JButton used to create the GUI <type JButton>
	 * boggleButton - JButton used to create the GUI <type JButton> 
	 * asteriskButton - JButton used to create the GUI <type JButton>
	 * 
	 * 
	 * @param menuWindow
	 * @param optionsObject
	 * @param boggleGameObject
	 * @param asteriskBlobObject
	 */
	public void mainMenu(final NewJFrame menuWindow, final OptionsMenu optionsObject, final BoggleGame boggleGameObject, final AsteriskBlob asteriskBlobObject) {
		menuWindow.setVisibility(true);
		menuWindow.getContentPane().removeAll();//Removes all components on this JFrame
		FontSelection getFont = new FontSelection();//Declares and Instantiates the FontSelection object getFont
		Font titleFont = getFont.createFont(0);//Accesses the createFont method in the FontSelection class

		JLabel programTitle = new JLabel("Gilbert's Program");
		programTitle.setFont(titleFont);
		programTitle.setSize(550,300);
		programTitle.setLocation(350,50);
		programTitle.setVisible(true);

		JButton closeButton = new JButton("Close the Program");
		closeButton.setSize(200,20);
		closeButton.setLocation(500, 600);
		closeButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); //Terminates the program
			}
		});
		closeButton.setVisible(true);

		JButton optionsButton = new JButton("Options");
		optionsButton.setSize(200,20);
		optionsButton.setLocation(500, 500);
		optionsButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				optionsObject.displayOptions(menuWindow);//Accesses the displayOptions method in the OptionsMenu class
			}
		});
		optionsButton.setVisible(true);

		JButton boggleButton = new JButton("Boggle");
		boggleButton.setSize(200,20);
		boggleButton.setLocation(500, 400);
		boggleButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				boggleGameObject.startGame(menuWindow);//Accesses the startGame method in the BoggleGame class
			}
		});
		boggleButton.setVisible(true);

		JButton asteriskButton = new JButton("Asterisk");
		asteriskButton.setSize(200,20);
		asteriskButton.setLocation(500, 300);
		asteriskButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				asteriskBlobObject.startAsterisk(menuWindow);//Accesses the startAsterisk method in the AsteriskBlob class
			}
		});
		asteriskButton.setVisible(true);

		menuWindow.add(programTitle);
		menuWindow.add(optionsButton);
		menuWindow.add(asteriskButton);
		menuWindow.add(boggleButton);
		menuWindow.add(closeButton);
		menuWindow.setVisible(true);
		menuWindow.repaint();
	}//end of mainMenu method
}//end of Assignment1 class
//==========================================================================================================================================================================================
//==========================================================================================================================================================================================
//BoggleGame
//This class contains the code for the boggle game and performs the recursive search to confirm that the words are there
class BoggleGame  {	
	/**Constructor method for BoggleGane
	 * Empty because the object doesn't need to set any values until startGame method
	 */
	public BoggleGame() {

	}
	/**startGame method
	 * This method creates all the JComponents needed for the user to interact with
	 * 
	 * List of local variables:
	 * lettersFont - Font used for the boggle board <type Font>
	 * textAreaFont - Font used for the input area <type Font>
	 * strLetters - Array holding the randomized letters <type String[][]>
	 * border - Colored border for the input area <type Border>
	 * tempArray - String array used to hold the values of the highscore and the name of the person who got the highscore <type String>
	 * highScoreToBeat
	 * helpButton - JButton used to create the GUI <type JButton>
	 * helpWindow - JFrame used to display the instructions <type NewJFrame>
	 * timer - Object that counts down to create a time limit <type Timer>
	 * boggleBoard - Text Area used to display the letters to the user <type JTextArea>
	 * userInputBox - TextField where the user will type in the word <type JTextField>
	 * usedWordsList - Text Area to display the words that the user has entered <type JTextArea>
	 * usedWordsLength - Text Area to display the length of the words entered <type JTextArea>
	 * resultsWindow - JFrame used to display whether or not the word was found on the board <type NewJFrame>
	 * userWord - The user's input <type String>
	 * realWord - variable that represents if the word entered was a real English word <type Boolean>
	 * timerSeconds - JLabel used to display the time limit to the user <type JLabel>
	 * newBoard - JButton used to create the GUI <type JButton>
	 * startGame - JButton used to create the GUI <type JButton>
	 * backButton - JButton used to create the GUI <type JButton>
	 * file - a File object used to select a certain file <type File>
	 * fileReader - a FileReade object used to read a file <type FileReader>
	 * br - a BufferedReader object used for keyboard input <type BufferedReader>
	 * 
	 * @param boggleWindow - Uses the JFrame object from the other class in order to save memory and not create another JFrame <type NewJFrame>
	 */
	public void startGame(final NewJFrame boggleWindow){

		boggleWindow.getContentPane().removeAll();//Removes all the current components on the frame
		boggleWindow.setVisibility(true);

		FontSelection getFont = new FontSelection();//
		Font lettersFont = getFont.createFont(2);//Uses the getFont object to create a font usable by other JComponents
		Font textAreaFont = getFont.createFont(4);

		Border border = BorderFactory.createLineBorder(new Color(0,128,255), 3);

		final String[][] strLetters = new String[5][5];//Creates a string array of size 5 * 5
		setLettersArray(strLetters);//Calls the method setLettersArray to fill the array with random letters

		String[] tempArray = null;

		try {
			File file = new File("highscore.txt");                    
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);

			try {
				tempArray = br.readLine().split(" "); //splits the line into two parts, one for the score, and one for the name
			}
			catch (IOException e) {
				System.out.println("There was an error reading the highscore.txt file."); //Tells the user that there was an error
			}
			fileReader.close();
		}catch(IOException e) {
			System.out.println("There was an error");
		}

		//=============================================================
		JLabel highScoreToBeat = new JLabel("The Highscore to beat is " + tempArray[0] + " by " + tempArray[1], SwingConstants.CENTER);
		highScoreToBeat.setSize(1200,100);
		highScoreToBeat.setVisible(true);
		highScoreToBeat.setLocation(10,0);
		highScoreToBeat.setFont(textAreaFont);

		JButton helpButton = new JButton("Instructions");
		helpButton.setSize(150,20);
		helpButton.setLocation(1040,10);
		helpButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				NewJFrame helpWindow = new NewJFrame("Instructions");
				JOptionPane.showMessageDialog(helpWindow, "The game of Boggle is played on a square board with 25 random letters. "
						+ "\n The objective is to find words formed on the board by contiguous sequences of letters."
						+ "\n Letters are considered to be touching if they are horizontally, vertically, or diagonally adjacent.  "
						+ "\n Words can contain duplicate letters, but a single letter on the board may not appear twice in a single word"
						+ "\n Words cannot be submitted twice"
						+ "\n"
						+ "\n This program displays a board of random letters, look at these letters and form a word. "
						+ "\n Enter the word in the input area, then press submit (Enter Key works too). "
						+ "\n The application should report if the word entered by the user is indeed on the board."
						+ "\n If your word was valid and found on the board, you will be given a score which is equal to the word's length."
						+ "\n There will be a time limit to find the words (default is 3 minutes, but it can be changed through the Options Menu)"
						+ "\n When the time limit is up, the program will calculate your total score and display it to you."
						+ "\n"
						+ "\n Press the \"New Game\" button to quit the current game and play another one."
						+ "\n Good Luck, Have Fun");
			}
		});
		helpButton.setVisible(true);
		final Timer timer = new Timer();




		final JTextPane boggleBoard = new JTextPane();
		boggleBoard.setEditable(false);
		boggleBoard.setSize(450,450);
		boggleBoard.setLocation(400,100);
		boggleBoard.setBackground(new Color(51,155,253));
		boggleBoard.setFont(lettersFont);
		boggleBoard.setVisible(false);





		for(int i = 0; i < strLetters.length; i++) {
			for(int g = 0; g < strLetters[i].length; g++){
				boggleBoard.setText(boggleBoard.getText()+ strLetters[i][g] + " " ); //Displays the letters to the user 
			}
			boggleBoard.setText(boggleBoard.getText() + "\n");
		}



		final JTextField userInputBox = new JTextField("Enter your word here");
		userInputBox.setFont(textAreaFont);
		userInputBox.setSize(280, 30);
		userInputBox.setLocation(400,570);
		userInputBox.setVisible(false);
		userInputBox.setBorder(border);
		userInputBox.setBackground(new Color(153,204,255));
		userInputBox.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				userInputBox.setText(null);//Clears the current text inside the Text Field when the user clicks on it
			}
		});


		final JTextArea usedWordsList = new JTextArea("Words Entered");
		usedWordsList.setLocation(40,100);
		usedWordsList.setSize(200,700);
		usedWordsList.setVisible(true);
		usedWordsList.setFont(textAreaFont);
		usedWordsList.setBackground(new Color(51,155,253));
		usedWordsList.setEditable(false);

		final JLabel currentScore = new JLabel("Current Score: 0");
		currentScore.setVisible(true);
		currentScore.setLocation(850,130);
		currentScore.setSize(300,20);
		currentScore.setFont(textAreaFont);

		final JTextArea usedWordsLength = new JTextArea("Length");
		usedWordsLength.setLocation(280,100);
		usedWordsLength.setSize(60,450);
		usedWordsLength.setVisible(true);
		usedWordsLength.setFont(textAreaFont);
		usedWordsLength.setBackground(new Color(51,155,253));
		usedWordsLength.setEditable(false);

		final JOptionPane resultsWindow = new JOptionPane();
		final JButton submitWordButton = new JButton("Submit");
		submitWordButton.setFont(textAreaFont);
		submitWordButton.setSize(100, 30);
		submitWordButton.setLocation(690,570);
		submitWordButton.setVisible(false);
		submitWordButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent d) {
				String userWord = userInputBox.getText();//Creates the variable userWord and instantiates it from what the user had entered
				boolean realWord = false;
				if(userWord.equals(""))//If the text field was empty, program will run this code
					JOptionPane.showMessageDialog(resultsWindow, ("Remember to input some letters before submitting!")); //Tells the user that they have input nothing
				else if(checkIfWordUsed(usedWordsList, userWord)) {//calls the checkIfWordUsed method
					JOptionPane.showMessageDialog(resultsWindow, (userWord + " has already been used!"));
				}

				else {
					try {
						File file = new File("dictionary.txt");
						FileReader fileReader = new FileReader(file);
						BufferedReader br = new BufferedReader(fileReader);
						String currentReadWord;
						while((currentReadWord = br.readLine()) != null) {//While there is still text in the dictionary file
							if(userWord.equals(currentReadWord)) {
								realWord = true;//If the word was in the dictionary file, that means that it was a real word
								break;
							}
						}
						fileReader.close();

					} catch (IOException e) {
						System.out.println("Error reading Dictionary");
					}
					if(realWord == true) {
						if ((confirmWords(userWord, strLetters) == true)) {//Calls the method confirmWords
							JOptionPane.showMessageDialog(resultsWindow, (userWord + " was found on the board!"));//Tells the user that they word was on the board
							usedWordsList.setText(usedWordsList.getText() + "\n" + userWord);//Adds the word to the list of used words
							usedWordsLength.setText(usedWordsLength.getText() + "\n     " + userWord.length());
							userInputBox.setText(null);//Clears the word inside the text field
							currentScore.setText("Current Score: " + countUpScore(usedWordsLength));//Displays the current score to the user
						}
						else {
							JOptionPane.showMessageDialog(resultsWindow, (userWord + " was not found on the board!"));//Tells the user that the word was not found
							userInputBox.setText(null);
						}
					}
					else {
						JOptionPane.showMessageDialog(resultsWindow, (userWord + " is not a real word!"));//Tells the user that their word was not a real word
						userInputBox.setText(null);
					}
				}
			}
		});

		boggleWindow.getRootPane().setDefaultButton(submitWordButton);//Sets the Enter key on the keyboard to activate the ActionListener of submitWordButton
		userInputBox.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE) {
					e.consume(); //if the key typed was not a letter, the program will not process it
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				userInputBox.setText(userInputBox.getText().toUpperCase());//Changes the user's input to capitals
			}
		});
		final JLabel timerSeconds = new JLabel("Time Left: "+ Assignment1.timeLimit/60 + " minutes & " + Assignment1.timeLimit%60 + " seconds");//Displays the time limit to the user
		timerSeconds.setSize(400,200);
		timerSeconds.setLocation(850,10);
		timerSeconds.setFont(textAreaFont);
		timerSeconds.setVisible(true);



		final JButton newBoard = new JButton("New Game");
		newBoard.setSize(150,20);
		newBoard.setLocation(120,10);
		newBoard.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				timer.cancel();//Stops the timer
				startGame(boggleWindow);//Calls the startGame method in order to refresh all its components
			}
		});

		final JButton startGame = new JButton("Start the Game");
		startGame.setSize(150,20);
		startGame.setLocation(280,10);
		startGame.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				boggleBoard.setVisible(true);
				startGame.setVisible(false);
				submitWordButton.setVisible(true);
				userInputBox.setVisible(true);
				newBoard.setVisible(true);

				timer.scheduleAtFixedRate(new TimerTask() {
					int timeLimitThisSession = Assignment1.timeLimit;
					public void run() {

						timeLimitThisSession--;
						timerSeconds.setText("Time Left: " + timeLimitThisSession/60 + " minutes & "  + timeLimitThisSession%60 + " seconds");
						if (timeLimitThisSession == 0) {
							timer.cancel();//Stops the timer
							gameOver(countUpScore(usedWordsLength), boggleWindow);//Calls the gameOver method
						}
					}
				}, 0, 1000);//Waits 1 seconds before repeating this method
			}
		});

		JButton backButton = new JButton("Main Menu");
		backButton.setSize(100,20);
		backButton.setLocation(10,10);
		backButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				BoggleGame boggleGameObject = new BoggleGame();//Declares and Instantiates the BoggleGame object
				final OptionsMenu optionsObject = new OptionsMenu();//Declares and Instantiates the OptionsMenu object
				AsteriskBlob asteriskBlobObject = new AsteriskBlob();//Declares and Instantiates the AsteriskBlob object
				Assignment1 assignment1Object = new Assignment1();
				assignment1Object.mainMenu(boggleWindow, optionsObject, boggleGameObject, asteriskBlobObject);//calls the mainMenu method
				timer.cancel();//Cancels the timer
			}
		});
		backButton.setVisible(true);
		newBoard.setVisible(false);





		boggleWindow.add(startGame);
		boggleWindow.add(timerSeconds);
		boggleWindow.add(submitWordButton);
		boggleWindow.add(userInputBox);
		boggleWindow.add(newBoard);
		boggleWindow.add(boggleBoard);
		boggleWindow.add(currentScore);
		boggleWindow.add(backButton);
		boggleWindow.add(helpButton);
		boggleWindow.add(highScoreToBeat);
		boggleWindow.add(usedWordsList);
		boggleWindow.add(usedWordsLength);
		boggleWindow.repaint();
	}//End of startGame method

	/**countUpScore method
	 * This method adds up all the lengths of all the words entered in order to come up with a score
	 * 
	 * List of local variables:
	 * totalScore - The user's score <type int>
	 * allScores - An array that holds all the lengths of the words
	 * 
	 * @param usedWordsLength - The text area holding the information about the lengths of the words
	 * @return totalScore - The user's total score right now <type int>
	 */
	public int countUpScore(JTextArea usedWordsLength) {
		int totalScore = 0;
		String[] allScores = usedWordsLength.getText().split("\n     ");
		for (int p = 1; p < allScores.length; p ++) {
			totalScore = totalScore + Integer.parseInt(allScores[p]);
		}
		return totalScore;
	}//end of countUpScore method

	/**gameOver method
	 * This method displays the user's end score and allows them the chance to save their high score 
	 * 
	 * List of local variables:
	 * file - a File object used to select a certain file <type File>
	 * fileReader - a FileReade object used to read a file <type FileReader>
	 * br - a BufferedReader object used for keyboard input <type BufferedReader>
	 * temp - the current high score and name <type String>
	 * tempArray - An array that holds the data about the high score and the user's name <type String[][]>
	 * textAreaFont - Font used to display text <type Font>
	 * gameOverWindow - JFrame used to hold JComponents <type NewJFrame>
	 * currentHighScoreLabel - JLabel used to show the user the current high score <type JLabel>
	 * backButtonMenu - JButton used to create the GUI <type JButton>
	 * backButton - JButton used to create the GUI <type JButton>
	 * setNewScorePrompt - JButton used to create the GUI <type JButton>
	 * confirmSave - JButton used to create the GUI <type JButton>
	 * writer - PrintWriter object used to write to a file <type PrintWriter>
	 * name - The user's name <type String>
	 * 
	 * @param totalScore - The user's total score <type int>
	 * @param boggleWindow - The JFrame that holds the GUI components <type NewJFrame>
	 */
	public void gameOver(final int totalScore, final NewJFrame boggleWindow) {
		boggleWindow.setVisible(false);
		String temp = null;
		try {
			File file = new File("highscore.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			try {
				temp = br.readLine(); //Reads the highscore.txt file and sets temp to that value
			}
			catch (IOException e) {
				System.out.println("There was an error reading the highscore.txt file.");
			}
			fileReader.close();
		}catch(IOException e) {
			System.out.println("There was an error");
		}

		String tempArray[] = temp.split(" ");
		Font textAreaFont = new Font("Consolas", Font.BOLD, 21);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	
		final NewJFrame gameOverWindow = new NewJFrame("GAME OVER");
		gameOverWindow.setDecoration(true);
		gameOverWindow.setSize(600,360);
		gameOverWindow.setLocation(dim.width/2-gameOverWindow.getSize().width/2, dim.height/2-gameOverWindow.getSize().height/2);
		gameOverWindow.getContentPane().setBackground(new Color(102,178,255));
		gameOverWindow.setLayout(null);

		JLabel gameResults = new JLabel("Your end score was " + totalScore + ". Good Job.", SwingConstants.CENTER);//Display the end score to the user
		gameResults.setFont(textAreaFont);
		gameResults.setSize(600,50);
		gameResults.setLocation(0,10);
		gameResults.setVisible(true);

		JLabel currentHighScoreLabel = new JLabel("The current high score is " + tempArray[0] + " points by " + tempArray[1], SwingConstants.CENTER);//Displays the current high score to the user
		currentHighScoreLabel.setFont(textAreaFont);
		currentHighScoreLabel.setSize(600,50);
		currentHighScoreLabel.setLocation(0,60);
		currentHighScoreLabel.setVisible(true);

		JButton backButtonMenu = new JButton("Back to Main Menu");//
		backButtonMenu.setSize(180,20);
		backButtonMenu.setLocation(110,300);
		backButtonMenu.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				BoggleGame boggleGameObject = new BoggleGame();//Declares and Instantiates the BoggleGame object
				final OptionsMenu optionsObject = new OptionsMenu();//Declares and Instantiates the OptionsMenu object
				AsteriskBlob asteriskBlobObject = new AsteriskBlob();//Declares and Instantiates the AsteriskBlob object
				Assignment1 assignment1Object = new Assignment1();
				assignment1Object.mainMenu(boggleWindow, optionsObject, boggleGameObject, asteriskBlobObject);//Calls the mainMenu method
				gameOverWindow.setVisible(false);
			}
		});

		JButton backButton = new JButton("Back to Boggle");
		backButton.setSize(180,20);
		backButton.setLocation(310,300);
		backButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				startGame(boggleWindow);//Calls the startGame method
				gameOverWindow.setVisible(false);
			}
		});

		gameOverWindow.add(backButton);
		gameOverWindow.add(backButtonMenu);



		if (Integer.parseInt(tempArray[0])< totalScore) {//If the user's score is higher than the current high score
			Font textAreaFont1 = new Font("Consolas", Font.BOLD, 17);
			JLabel setNewScorePrompt = new JLabel("You've set a new highscore! Would you like to save this score?", SwingConstants.CENTER);
			setNewScorePrompt.setFont(textAreaFont);
			setNewScorePrompt.setFont(textAreaFont1);
			setNewScorePrompt.setSize(600,100);
			setNewScorePrompt.setLocation(0,120);
			setNewScorePrompt.setVisible(true);
			gameOverWindow.add(setNewScorePrompt);
			JButton confirmSave = new JButton("Save Score");
			confirmSave.setSize(100,20);
			confirmSave.setLocation(250,190);
			confirmSave.setVisible(true);
			gameOverWindow.add(confirmSave);
			confirmSave.addActionListener(new ActionListener () {
				public void actionPerformed(ActionEvent e) {
					NewJFrame frame = new NewJFrame("Save Highscore");
					String name = JOptionPane.showInputDialog(frame, "What's your name?");//Asks the user for their name
					try {
						PrintWriter writer = new PrintWriter("highscore.txt");
						writer.println(totalScore + " " + name);//Writes to the highscore.txt file
						writer.close();
					} catch (FileNotFoundException e1) {
						System.out.println("Failed to save high score.");
					}
				}
			});


			currentHighScoreLabel.setText(("The high score was " + tempArray[0] + " points by " + tempArray[1]));
		}

		gameOverWindow.add(currentHighScoreLabel);

		gameOverWindow.add(gameResults);
		gameOverWindow.setVisible(true);
	}//end of the gameOver method

	/**checkIfWordUsed method
	 * This method checks if the word has already been entered before
	 * 
	 * List of local variables:
	 * usedWord - A string used to create a pattern for the searcher
	 * searcher - A Pattern object used to create a pattern for match to look for
	 * match - A Matcher object that compares the searcher to the used words
	 * 
	 * @param usedWordsList - The text area that holds all the used words <type JTextArea>
	 * @param userWord - The input of the user <type String>
	 * @return - Whether the word has been used before or not <type Boolean>
	 */
	public boolean checkIfWordUsed(JTextArea usedWordsList, String userWord){
		String usedWord = "\\b"+userWord+"\\b";//Creates a String to look for
		Pattern searcher=Pattern.compile(usedWord);
		Matcher match=searcher.matcher(usedWordsList.getText());//Matches the pattern against the usedWordsList to determine if the word was used before
		return match.find();
	}//End of checkIfWordUsed method

	/**setLettersArray
	 * This method fills the array up with random letters
	 * 
	 * List of local variables:
	 * ARRAYLENGTH - A constant that represents the maximum length/width of the array <type byte>
	 * randomNumber - A Random object that generates a random number <type Random>
	 * strAlphabet - A string that contains all the letters of the alphabet <type String>
	 * 
	 * @param strLetters - An array that is to be filled by random letters
	 * @return - The filled array (strLetters) <type String[][]>
	 */
	public String[][] setLettersArray (String[][] strLetters) {
		final byte ARRAYLENGTH = 5;
		Random randomNumber = new Random();
		String strAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < ARRAYLENGTH; i++) {
			for (int a = 0; a < ARRAYLENGTH; a++ ) {
				strLetters[i][a] = (String.valueOf(strAlphabet.charAt(randomNumber.nextInt(strAlphabet.length()))));/*Generates a random number between 0 and 25, and uses that number
																													  to select a character which takes a position in the array
				 */
			}
		}
		return strLetters;

	}//End of setLettersArray

	/**confirmWords method
	 * This method finds the first letter of the word and then calls the findNextLetter method
	 * It will then determine if the word is found or not
	 * 
	 * List of local variables:
	 * usedPositions - A list that contains all the positions that have been checked in the array <type ArrayList>
	 * intCounter - A counter that represents the amount of times that the method has been called <type int>
	 * counter - A counter of how many times the program has tried to find the word <type int>
	 * startingX - Starting row of the search <type int>
	 * startingY - Starting column of the search <type int>
	 * 
	 * 
	 * @param userWord - The user's input <type String>
	 * @param strLetters - The array filled with letters <type String[][]>
	 * @return - Whether the word was found or not <type Boolean>
	 */
	public boolean confirmWords(String userWord, String[][] strLetters) {
		List<List<Integer>> usedPositions = new ArrayList<List<Integer>>();
		for (int y = 0; y < 5; y ++) {
			usedPositions.add(new ArrayList<Integer>());//Creates 5 rows for the list
		}
		final int ARRAYLENGTH = 5;
		for(int i = 0; i < ARRAYLENGTH; i++) {
			for (int j = 0; j < ARRAYLENGTH; j++) {
				if (String.valueOf(userWord.charAt(0)).equals(strLetters[i][j])) {//If the first letter was found on the board
					int intCounter = 0;
					int counter = 0;
					int startingX = i;
					int startingY = j;
					usedPositions.get(i).add(j);//Adds this position to the list
					if (findNextLetter(userWord, strLetters, intCounter, i, j, usedPositions, startingX, startingY, counter) == true)/*Calls the findNextLetter method and if it returns true, 
							              																							   this method will return true*/
						return true;
				}
			}
		}
		return false;//If the word was not found, return false
	}

	/**findNextLetter method
	 * This method performs a recursive search in the array for the user's input
	 * The program will choose the position in the array to move to based on:
	 * 
	 * 		1) Whether the current position is at the end of a row or column of the array (Prevents an ArrayOutOfBounds exception)
	 * 		2) Whether the next position is equal to the next letter that it is looking for
	 * 
	 * If the word was not found, the program will then restart the search from the starting position in case the word was surrounded by the same letter multiple times, and it moved
	 * in a direction where the word could not be found. 
	 * 		Example:      A G H P K       USER INPUT = "HUT"
	 * 					  G J V J E       The program may find H in row 3, column 3, and move to row 3, column 2 and would be unable to find the word "HUT"
	 * 					  Y U H L P       In order to fix this, the program will restart the search from the starting positions up to 7 times before it returns a false (word was not found)
	 * 					  B H J U L
	 * 					  Q W E R T
	 * 
	 * @param userWord - The user's input <type String>
	 * @param strLetters - The array filled with letters <type String[][]>
	 * @param intCounter - A counter that represents the amount of times that the method has been called <type int>
	 * @param rows - An integer representing the current row position of the recursive search <type int>
	 * @param columns - An integer representing the current column position of the recursive search <type int>
	 * @param usedPositions - A list that contains all the positions that have been checked in the array <type ArrayList>
	 * @param startingX - Starting row of the search <type int>
	 * @param startingY - Starting column of the search <type int>
	 * @param counter -  A counter of how many times the program has tried to find the word <type int>
	 * @return Whether the word was found or not <type Boolean>
	 */
	public boolean findNextLetter(String userWord, String[][] strLetters, int intCounter, int rows, int columns, List<List<Integer>> usedPositions, int startingX, int startingY, int counter ) {
		if (intCounter == (userWord.length() - 1)) 
			return true; //If intCounter reaches the length of the user's input, that means that the word was found, and it will return true
		else {
			if (rows!= 0 && strLetters[rows - 1][columns].equals(String.valueOf(userWord.charAt(intCounter+1))) && !usedPositions.get(rows - 1).contains(columns)) { //If we are not at the end of the array and the next position has the letter that we are looking for
				usedPositions.get(rows-1).add(columns);//Adds the next position to the usedPositions list
				return findNextLetter(userWord, strLetters, intCounter + 1, rows - 1, columns, usedPositions, startingX, startingY, counter);//Calls the findNextLetter method with updated positions and intCounter
			}
			if ((rows!= 0) && columns!=0 && strLetters[rows - 1][columns - 1].equals(String.valueOf(userWord.charAt(intCounter+1)))&& !usedPositions.get(rows - 1).contains(columns - 1)) {
				usedPositions.get(rows-1).add(columns - 1);
				return findNextLetter(userWord, strLetters, intCounter + 1, rows - 1, columns - 1, usedPositions, startingX, startingY, counter);

			}
			if(rows!=4 && strLetters[rows + 1][columns].equals(String.valueOf(userWord.charAt(intCounter+1)))&& !usedPositions.get(rows + 1).contains(columns)) {
				usedPositions.get(rows+1).add(columns);
				return findNextLetter(userWord, strLetters, intCounter + 1, rows + 1, columns, usedPositions, startingX, startingY, counter);

			}
			if(rows!=4 && columns!=4 && strLetters[rows + 1][columns + 1].equals(String.valueOf(userWord.charAt(intCounter+1)))&& !usedPositions.get(rows + 1).contains(columns + 1)) {
				usedPositions.get(rows+1).add(columns+1);
				return findNextLetter(userWord, strLetters, intCounter + 1, rows + 1, columns + 1, usedPositions, startingX, startingY, counter);

			}
			if(columns!=0 && strLetters[rows][columns - 1].equals(String.valueOf(userWord.charAt(intCounter+1)))&& !usedPositions.get(rows).contains(columns - 1)) {
				usedPositions.get(rows).add(columns - 1);
				return findNextLetter(userWord, strLetters, intCounter + 1, rows, columns - 1, usedPositions, startingX, startingY, counter);

			}

			if(columns!=4 && strLetters[rows][columns + 1].equals(String.valueOf(userWord.charAt(intCounter+1)))&& !usedPositions.get(rows).contains(columns + 1)) {
				usedPositions.get(rows).add(columns + 1);
				return findNextLetter(userWord, strLetters, intCounter + 1, rows, columns + 1, usedPositions , startingX, startingY, counter);

			}
			if((rows!=0) && (columns!=4) && strLetters[rows - 1][columns + 1].equals(String.valueOf(userWord.charAt(intCounter+1)))&& !usedPositions.get(rows - 1).contains(columns + 1)) {
				usedPositions.get(rows-1).add(columns + 1);
				return findNextLetter(userWord, strLetters, intCounter + 1, rows - 1, columns + 1, usedPositions, startingX, startingY, counter);

			}
			if((rows!=4) && (columns!=0) && strLetters[rows + 1][columns - 1].equals(String.valueOf(userWord.charAt(intCounter+1)))&& !usedPositions.get(rows + 1).contains(columns - 1)) {
				usedPositions.get(rows+1).add(columns-1);
				return findNextLetter(userWord, strLetters, intCounter + 1, rows + 1, columns - 1, usedPositions, startingX, startingY, counter);

			}
			if(counter < 7) {//If counter is under 7, increase it by one, and restart the search from the starting positions
				counter ++;
				rows = startingX;
				columns = startingY;
				return findNextLetter(userWord, strLetters, intCounter - intCounter, rows, columns, usedPositions, startingX, startingY, counter);//Calls itself, but decreases intCounter to 0
			}

		}	 	
		return false;//Returns false if the program has not found the word
	}//End of findNextLetter method

}//End of BoggleGame class

//=======================================================================================================================================================================
//AsteriskBlob
//This class contains the code for the asterisk blob program and performs the recursive search to clear the blobs
class AsteriskBlob {
	/**Constructor method for AsteriskBlob
	 * Empty because the object doesn't need to set any values until startAsterisk method
	 */
	public AsteriskBlob() {

	}

	/**startAsterisk method
	 * This method displays the blobs to the user and allows them to select a coordinate
	 * 
	 * List of local variables:
	 * blobFont - Font used for the asterisk board <type Font>
	 * getFont - Object used to create a font <type FontSelection>
	 * backButton - JButton used to create the GUI <type JButton>
	 * model - Object used to format the JTable <type TableModel>
	 * asteriskGrid - JTable used to display the asterisks and allows the user to select a coordinate <type asteriskGrid>
	 * columnModel - - Object used to format the JTable <type TableModel>
	 * centerRenderer - Object used to format the text inside the cells of the JTable <type DefaultTableCellRenderer>
	 * usedPositions - ArrayList used to hold the positions that the recursive search has visited <type ArrayList>
	 * xCoord - The row of the user's selection <type int>
	 * yCoord - The column of the user's selection <type int>
	 * helpButton - JButton used to create the GUI <type JButton>
	 * 
	 * @param asteriskWindow - Uses the JFrame object from the other class in order to save memory and not create another JFrame <type NewJFrame>
	 */
	public void startAsterisk(final NewJFrame asteriskWindow){
		asteriskWindow.getContentPane().removeAll(); //Removes all components from the JFrame
		FontSelection getFont = new FontSelection();
		Font blobFont = getFont.createFont(1);
		SetUpAsteriskBlobs blobBoard = new SetUpAsteriskBlobs(19,24);
		String[] columnNames = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25"};
		JButton backButton = new JButton("Main Menu");
		backButton.setSize(100,20);
		backButton.setLocation(10,10);
		backButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				BoggleGame boggleGameObject = new BoggleGame();//Declares and Instantiates the BoggleGame object
				final OptionsMenu optionsObject = new OptionsMenu();//Declares and Instantiates the OptionsMenu object
				AsteriskBlob asteriskBlobObject = new AsteriskBlob();//Declares and Instantiates the AsteriskBlob object
				Assignment1 assignment1Object = new Assignment1();
				assignment1Object.mainMenu(asteriskWindow, optionsObject, boggleGameObject, asteriskBlobObject);//Calls the mainMenu method
			}
		});
		backButton.setVisible(true);

		TableModel model = new DefaultTableModel(blobBoard.getArray(), columnNames);
		final JTable asteriskGrid = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		TableColumnModel columnModel = asteriskGrid.getColumnModel();
		asteriskGrid.setSize(1180,650);
		asteriskGrid.setRowHeight(30);
		asteriskGrid.setSelectionBackground(new Color(0,205,255));
		asteriskGrid.setSelectionForeground(new Color(0,68,255));
		asteriskGrid.setCellSelectionEnabled(true);
		asteriskGrid.getTableHeader().setReorderingAllowed(false);
		asteriskGrid.setShowGrid(false);
		asteriskGrid.setFont(blobFont);


		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );

		for (int i = 0; i < 25; i++){
			columnModel.getColumn(i).setPreferredWidth(100);
			columnModel.getColumn(i).setCellRenderer(centerRenderer);
		}
		asteriskGrid.setBackground(new Color(51,155,253));
		asteriskGrid.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		asteriskGrid.doLayout();

		asteriskGrid.setLocation(10,60);
		asteriskGrid.setVisible(true);
		asteriskGrid.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				List<List<Integer>> usedPositions = new ArrayList<List<Integer>>();
				for (int y = 0; y < 21; y ++) {
					usedPositions.add(new ArrayList<Integer>());//Creates 20 rows for the ArrayList
				}
				int xCoord = asteriskGrid.getSelectedRow();
				int yCoord = asteriskGrid.getSelectedColumn();
				if (asteriskGrid.getValueAt(xCoord, yCoord) != " ") {
					asteriskGrid.setValueAt(" ",xCoord,yCoord);//If the current cell selected is no blank, we set it to blank
					clearBlob(xCoord, yCoord, asteriskGrid, usedPositions);//Calls the clearBlob method
				}
			}
		});

		JButton helpButton = new JButton("Help");
		helpButton.setSize(100,20);
		helpButton.setLocation(1090,10);
		helpButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				NewJFrame helpWindow = new NewJFrame("Help");
				JOptionPane.showMessageDialog(helpWindow, "This program displays a table consisting of 3 groups of asterisks and blank spaces."//Displays instructions to the user
						+ "\n The program will erase the whole group (or blob) of asterisks when one of the asterisks is clicked on."
						+ "\n Clicking on the white spaces will do nothing.  ");
			}
		});
		helpButton.setVisible(true);

		asteriskWindow.add(backButton);
		asteriskWindow.add(asteriskGrid);
		asteriskWindow.add(helpButton);



		asteriskWindow.repaint();
	}

	/**clearBlob method
	 * This method will perform a recursive search around the JTable in order to clear the blobs
	 * The program will select a new position based on:
	 * 
	 * 		1) If the cell is at the first/last row or first/last column (prevents an ArrayOutOfBounds exception)
	 * 		2) Whether of not the next position has a "*" or a " "
	 * 
	 * Once the program has finished the search, it will go back to every position is has visited and look at all the surrounding cells to make sure that they are all cleared
	 * This is required since it is possible for the program to block itself from completing the clearing of the blobs. For example:
	 * 
	 * 					********     **** ***
	 * 					********  -> **** ***    The program had cleared out a path, but that path has blocked itself from the rest of the blob
	 * 					********     ***    
	 *
	 * @param xCoord - The row that the program is currently searching <type int>
	 * @param yCoord - The column that the program is currently searching <type int>
	 * @param asteriskGrid - JTable used to display the asterisks and holds the asterisk blobs <type asteriskGrid>
	 * @param usedPositions - ArrayList used to hold the positions that the recursive search has visited <type ArrayList>
	 * @return - Whether or not the program has cleared the blob or not <type Boolean>
	 */
	public boolean clearBlob(int xCoord, int yCoord, JTable asteriskGrid, List<List<Integer>> usedPositions) {
		usedPositions.get(xCoord).add(yCoord); //Adds the current position to the usedPositions list
		if (asteriskGrid.getValueAt(xCoord - 1,yCoord - 1) == " " && 
				asteriskGrid.getValueAt(xCoord + 1,yCoord + 1) == " " && 
				asteriskGrid.getValueAt(xCoord + 1,yCoord - 1) == " " && 
				asteriskGrid.getValueAt(xCoord - 1,yCoord + 1) == " " &&   
				asteriskGrid.getValueAt(xCoord,yCoord - 1) == " " &&        
				asteriskGrid.getValueAt(xCoord + 1,yCoord) == " " &&
				asteriskGrid.getValueAt(xCoord - 1,yCoord) == " " &&
				asteriskGrid.getValueAt(xCoord,yCoord + 1) == " ") {
			boolean leave = false;
			for(int i = 0; i < 21; i++) {
				for (int j = 0; j < usedPositions.get(i).size(); j++) {
					if (asteriskGrid.getValueAt(i - 1,usedPositions.get(i).get(j) - 1) == " " && 
							asteriskGrid.getValueAt(i + 1,usedPositions.get(i).get(j) + 1) == " " && 
							asteriskGrid.getValueAt(i + 1,usedPositions.get(i).get(j) - 1) == " " &&  
							asteriskGrid.getValueAt(i - 1,usedPositions.get(i).get(j) + 1) == " " && 
							asteriskGrid.getValueAt(i,usedPositions.get(i).get(j) - 1) == " " && 
							asteriskGrid.getValueAt(i,usedPositions.get(i).get(j) + 1) == " " && 
							asteriskGrid.getValueAt(i - 1,usedPositions.get(i).get(j)) == " " && 
							asteriskGrid.getValueAt(i + 1,usedPositions.get(i).get(j)) == " ") {
					}
					/* The program checks if the surrounding blobs are blank, it they are, the program will 
					 * then check the surrounding cells of each visited position to make sure the blob is truly cleared
					 */
					else {
						xCoord = i;
						yCoord = usedPositions.get(i).get(j); //If the blob was not truly cleared. it will move itself back to the position to continue its search
						leave = true;
						break;
					}

				}
				if (leave == true) {
					break;
				}

			}
			if (leave != true)
				return true;
		}
		if (yCoord != 24 && asteriskGrid.getValueAt(xCoord,yCoord + 1) != " ") { //If the next position is not blank, the program will move to that position
			asteriskGrid.setValueAt(" ", xCoord, yCoord + 1); //Clears the cell
			return clearBlob(xCoord, yCoord + 1, asteriskGrid, usedPositions);//Calls the clearBlob recursive method

		}
		if(yCoord != 0 && asteriskGrid.getValueAt(xCoord,yCoord - 1) != " ") {
			asteriskGrid.setValueAt(" ", xCoord, yCoord - 1);
			return clearBlob(xCoord, yCoord - 1, asteriskGrid, usedPositions);

		}
		if(xCoord != 0 && yCoord != 0 && asteriskGrid.getValueAt(xCoord - 1,yCoord - 1) != " ") {
			asteriskGrid.setValueAt(" ", xCoord - 1, yCoord - 1);
			return clearBlob(xCoord - 1, yCoord - 1, asteriskGrid, usedPositions);
		}
		if(xCoord != 20 && yCoord !=24 && asteriskGrid.getValueAt(xCoord + 1,yCoord + 1) != " ") {
			asteriskGrid.setValueAt(" ", xCoord + 1, yCoord + 1);
			return clearBlob(xCoord + 1, yCoord + 1, asteriskGrid, usedPositions);
		}
		if(xCoord != 20 && yCoord != 0 && asteriskGrid.getValueAt(xCoord + 1,yCoord - 1) != " ") {
			asteriskGrid.setValueAt(" ", xCoord + 1, yCoord - 1);
			return clearBlob(xCoord + 1, yCoord - 1, asteriskGrid, usedPositions);
		}
		if(xCoord != 0 && yCoord != 24 && asteriskGrid.getValueAt(xCoord - 1,yCoord + 1) != " ") {
			asteriskGrid.setValueAt(" ", xCoord - 1, yCoord + 1);
			return clearBlob(xCoord - 1, yCoord + 1, asteriskGrid, usedPositions);
		}
		if(xCoord != 20 && asteriskGrid.getValueAt(xCoord + 1,yCoord) != " ") {
			asteriskGrid.setValueAt(" ", xCoord + 1, yCoord);
			return clearBlob(xCoord + 1, yCoord, asteriskGrid, usedPositions);
		}
		if (xCoord != 0 && asteriskGrid.getValueAt(xCoord - 1,yCoord) != " ") {
			asteriskGrid.setValueAt(" ", xCoord - 1, yCoord);
			return clearBlob(xCoord - 1, yCoord, asteriskGrid, usedPositions);
		}
		return false;
	}
}
//==========================================================================================================================================================================================
//OptionsMenu
//This class contains the code for the Options and allows the user to customize the dictionary list and the time limit for boggle
class OptionsMenu{
	/**Constructor method for OptionsMenu
	 * Empty because the object doesn't need to set any values until displayOptions method
	 */
	public OptionsMenu() {

	}
	
	/**displayOptions method
	 * This method displays the dictionary to the user and allows them to add or search for a word. It also gives them to option to change the time limit for boggle.
	 * 
	 * List of local variables:
	 * file - a File object used to select a certain file <type File>
	 * fileReader - a FileReade object used to read a file <type FileReader>
	 * br - a BufferedReader object used for keyboard input <type BufferedReader>
	 * disctionaryWords - A list that holds all the words in the dictionary <type ArrayList>
	 * lowerEtched - A border that surrounds the dictionary list <type Border>
	 * title - A title border <type TitledBorder>
	 * dictionaryColumn - A text area where all the words are shown <type JTextArea>
	 * dictionaryDisplay - A JScrollPane that allows the user to scroll down the text area <type JScrollPane>
	 * backButtomMenu - A button that allows the user to go back to the menu <type JButton>
	 * addToDictionary - A button that allows the user to add a word to the dictionary <type JButton>
	 * wordEntered - The word that the user had entered <type String>
	 * writer - PrintWriter object used to write to a file <type PrintWriter>
	 * wordSearch - A text field for the user to enter a word to search for <type JTextField>
	 * submitAWord - A JButton that allows the user to enter a word to find <type JButton>
	 * setTimeLimitMinutes - A text field that allows the user to enter the amount of minutes for the time limit <type JTextField>
	 * setTimeLimitSeconds - A text field that allows the user to enter the amount of seconds for the time limit <type JTextField>
	 * setTimeChange - A Button that allows the user to submit their time change <type JButton>
	 * r - A Runnable object that displays the dictionary in the text area in another thread <type Runnable>
	 * 
	 * @param optionsWindow - Uses the JFrame object from the other class in order to save memory and not create another JFrame <type NewJFrame>
	 */
	public void displayOptions(final NewJFrame optionsWindow){
		optionsWindow.getContentPane().removeAll();
		final List<String> dictionaryWords = new ArrayList<String>();
		try {
			File file = new File("dictionary.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String currentReadWord;
			while((currentReadWord = br.readLine()) != null) {
				dictionaryWords.add(currentReadWord);//Adds all the words into dictionaryWords List
			}
			fileReader.close();

		} catch (IOException e) {
			System.out.println("Error reading Dictionary");
		}
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder title = BorderFactory.createTitledBorder(lowerEtched, "List of Words");



		final JTextArea dictionaryColumn = new JTextArea();
		dictionaryColumn.setEditable(false);
		dictionaryColumn.setSize(100,600);
		dictionaryColumn.setLocation(0,10);
		dictionaryColumn.setVisible(true);
		dictionaryColumn.setLineWrap(true);
		dictionaryColumn.setBackground(new Color(51,155,253));


		JScrollPane dictionaryDisplay = new JScrollPane(dictionaryColumn);
		dictionaryDisplay.setLocation(50,80);
		dictionaryDisplay.setBorder(title);
		dictionaryDisplay.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		dictionaryDisplay.setSize(200,600);
		dictionaryDisplay.setBackground(new Color(51,155,253));


		JButton backButtonMenu = new JButton("Back to Main Menu");
		backButtonMenu.setSize(200,20);
		backButtonMenu.setLocation(10,10);
		backButtonMenu.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				BoggleGame boggleGameObject = new BoggleGame();//Declares and Instantiates the BoggleGame object
				final OptionsMenu optionsObject = new OptionsMenu();//Declares and Instantiates the OptionsMenu object
				AsteriskBlob asteriskBlobObject = new AsteriskBlob();//Declares and Instantiates the AsteriskBlob object
				Assignment1 assignment1Object = new Assignment1();
				assignment1Object.mainMenu(optionsWindow, optionsObject, boggleGameObject, asteriskBlobObject);//Calls the mainmenu method
			}
		});

		JButton addToDictionary = new JButton("Add a word to dictionary");
		addToDictionary.setSize(200, 30);
		addToDictionary.setLocation(260,650);
		addToDictionary.setVisible(true);
		addToDictionary.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				NewJFrame frame = new NewJFrame("Save Word");
				try {
					String wordEntered = JOptionPane.showInputDialog(frame, "Please enter the word").toUpperCase();
					try {
						PrintWriter writer = new PrintWriter (new FileOutputStream(new File("dictionary.txt"),true));
						writer.println("\n" + wordEntered);//Adds the word into dictionary.txt
						writer.close();
						dictionaryColumn.setText(dictionaryColumn.getText() + " " + wordEntered +"\n");
					} catch (FileNotFoundException e1) {
						System.out.println("Failed to save word.");
					}
				} catch (NullPointerException e1){
					JOptionPane.showMessageDialog(frame, "Please enter a word!");
				}

			}
		});

		final JTextField wordSearch = new JTextField("Search for a word...");
		wordSearch.setSize(200,30);
		wordSearch.setLocation(260,80);
		wordSearch.setVisible(true);
		wordSearch.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				wordSearch.setText(null);
			}
		});
		wordSearch.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();//If the key pressed is not a letter and is not backspace, the program will ignore it
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				wordSearch.setText(wordSearch.getText().toUpperCase());//Changes the words entered into uppser case
			}


		});

		JButton submitWordToFind = new JButton("Submit");
		submitWordToFind.setSize(200,20);
		submitWordToFind.setLocation(260,110);
		submitWordToFind.setVisible(true);
		submitWordToFind.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				int pos = 0;//Represents the current line read
				String keyWord = (" " + wordSearch.getText());//Adds a space in front of the user's input for easier comparison
				dictionaryColumn.requestFocusInWindow();
				if (keyWord != null && keyWord.length() > 0) {
					Document document = dictionaryColumn.getDocument();//Creates a document out of the text area
					int findLength = keyWord.length();
					try {
						boolean found = false;
						// search position becomes 0 if we're at the end of the document
						if (pos + findLength > document.getLength()) {
							pos = 0;
						}
						// if the program has not reached the end of the document, run this code
						while (pos + findLength <= document.getLength()) {
							// Extract the text from the document            
							String match = document.getText(pos, findLength);
							if (match.equals(keyWord)) {//compares the text with the document
								found = true;
								break;
							}
							pos++;//Adds 1 to pos, reads next line next time
						}

						if (found) {
							// Get the rectangle of the where the text would be visible to the user
							Rectangle viewRect = dictionaryColumn.modelToView(pos);
							// Scroll to make the rectangle visible
							dictionaryColumn.scrollRectToVisible(viewRect);
							// Highlight the text
							dictionaryColumn.setCaretPosition(pos + findLength);
							dictionaryColumn.moveCaretPosition(pos);
							// Move the search position beyond the current match
							pos += findLength;
						}
						else {
							NewJFrame frame = new NewJFrame();
							JOptionPane.showMessageDialog(frame, "Word was not found!");//Tells the user that their word was not found
						}

					} catch (Exception f) {
						f.printStackTrace();
					}

				}
			}

		});


		final JTextField setTimeLimitMinutes = new JTextField();
		setTimeLimitMinutes.setLocation(900,100);
		setTimeLimitMinutes.setSize(30,20);
		setTimeLimitMinutes.setVisible(true);
		setTimeLimitMinutes.addKeyListener(new KeyListener() {//Start of a KeyListener
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {//only run this if c is less than 0 and larger than 9
					e.consume(); //Stops the event from processing
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});//End of the key listener



		final JTextField setTimeLimitSeconds = new JTextField();
		setTimeLimitSeconds.setLocation(1000,100);
		setTimeLimitSeconds.setSize(30,20);
		setTimeLimitSeconds.setVisible(true);
		setTimeLimitSeconds.addKeyListener(new KeyListener() {//Start of a KeyListener
			public void keyTyped(KeyEvent e) {//Start of keyTyped
				char c = e.getKeyChar();//Assigns c to the value of the key the user pressed
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {//only run this if c is less than 0 and larger than 9
					e.consume(); //Stops the event from processing
				}//end of if statement
			}//end of keyTyped

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});//End of the key listener

		JPanel changeTimeLimit = new JPanel();
		changeTimeLimit.setLayout(null);
		changeTimeLimit.add(setTimeLimitSeconds);
		changeTimeLimit.add(setTimeLimitMinutes);
		changeTimeLimit.setLocation(860, 80);
		changeTimeLimit.setSize(300, 60);
		changeTimeLimit.setBackground(new Color (51,155,253));
		Border lowerEtched1 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder title1 = BorderFactory.createTitledBorder(lowerEtched1, "Change Boggle Time Limit");
		changeTimeLimit.setBorder(title1);
		changeTimeLimit.setOpaque(false);
		changeTimeLimit.revalidate();

		JLabel minutesAndSeconds = new JLabel("minutes &            seconds.");
		minutesAndSeconds.setBounds(940, 100, 200, 20);

		JButton submitTimeChange = new JButton("Submit Time Limit Change");
		submitTimeChange.setSize(300,20);
		submitTimeChange.setLocation(860,140);
		submitTimeChange.setVisible(true);
		submitTimeChange.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (setTimeLimitSeconds.getText().equals("")) {
					setTimeLimitSeconds.setText("00");

				}
				if (setTimeLimitMinutes.getText().equals(""))
					setTimeLimitMinutes.setText("0");
				if(setTimeLimitMinutes.getText().equals("0") && setTimeLimitSeconds.getText().equals("00"))
					JOptionPane.showMessageDialog(new JFrame(), "Time limit has to be at least above 0!");//Tells the user that the time limit has to be above 0
				else {
					Assignment1.timeLimit = (Integer.parseInt(setTimeLimitMinutes.getText()) * 60) + (Integer.parseInt(setTimeLimitSeconds.getText()));
					JOptionPane.showMessageDialog(new JFrame(), "Time limit change successful!");//Notifies the user of the time limit change
				}
			}
		});


		optionsWindow.add(submitTimeChange);
		optionsWindow.add(setTimeLimitSeconds);
		optionsWindow.add(setTimeLimitMinutes);
		optionsWindow.add(changeTimeLimit);
		optionsWindow.add(submitWordToFind);
		optionsWindow.add(backButtonMenu);
		optionsWindow.add(addToDictionary);
		optionsWindow.add(wordSearch);
		optionsWindow.add(dictionaryDisplay);
		optionsWindow.add(minutesAndSeconds);



		Runnable r = new ShowWords(dictionaryWords, dictionaryColumn, title, dictionaryDisplay);/*Makes a runnable thread out of the class ShowWords
																								This allows the user to interact with the rest of the program while the list
																								is still running.*/
		new Thread(r).start();

		optionsWindow.repaint();
	}
}
//==========================================================================================================================================================================================
//NewJFrame
//This class contains the code to set up the program's JFrames
class NewJFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xSize, ySize;
	/**NewJFrame
	 * This method is the default constructor for the class and declares a new JFrame
	 * 
	 */
	public NewJFrame() {
		new JFrame(); //Constructor ( used to set the initial state of the object)
	}
	/**NewJFrame
	 * This method is a constructor for the class a declares a new JFrame
	 * 
	 * @param strTitle - The title of the current window <type String>
	 */
	public NewJFrame(String strTitle) {
		new JFrame(strTitle); //Constructor ( used to set the initial state of the object)
	}
	/**setJFrameSize method
	 * This method sets the size of the JFrame
	 * 
	 * @param x - Width of JFrame <type int>
	 * @param y - Length of JFrame <type int>
	 */
	public void setJFrameSize(int x, int y) {
		xSize = x;
		ySize = y;
		setSize(x,y); //Takes the width and length and sets the JFrame's size 
	}
	/**setBackground method
	 * This method sets the background of the JFrame
	 * 
	 */
	public void setBackground(){
		getContentPane().setBackground(new Color(51,155,253));//Sets the background color of the JFrame
	}
	/**setFrameLocation method
	 * This method sets the location of the JFrame
	 * 
	 */
	public void setFrameLocation(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getXSize()/2, dim.height/2-this.getYSize()/2);//Centers the JFrame based on the current screen size of the user
	}
	/**setVisibility method
	 * This method sets the JFrame's visibility
	 * 
	 * @param visible
	 */
	public void setVisibility(boolean visible){
		setVisible(visible);//Sets the JFrame's visibility 
	}
	public void setDecoration(boolean b) {
		setUndecorated(b);
	}
	/**getXSize method
	 * This method gets the width of the JFrame
	 * 
	 * @return - The width of the JFrame <type int>
	 */
	public int getXSize(){
		return xSize;
	}
	/**getYSize method
	 * This method gets the height of the JFrame
	 * 
	 * @return - The height of the JFrame <type int>
	 */
	public int getYSize(){
		return ySize;
	}
}
//==========================================================================================================================================================================================
//SetUpAsteriskBlobs
//This class contains the code to set up the arrays for the asterisk program

class SetUpAsteriskBlobs {
	private String[][] board;
	/**SetUpAsteriskBlobs
	 * This is the constructor method for the class
	 * 
	 * @param x - The rows of the array <type int>
	 * @param y - The columns of the array <type int>
	 */
	public SetUpAsteriskBlobs (int x, int y){
		this.board = new String[x][y];//Declares the string array
		this.board = generateBlob();//Calls the generateBlob method
	}//End of SetUpAsteriskBlobs method
	/**generateBlob method
	 * This method calls the fillBoard method
	 * 
	 * @return - The filled array <type String[][]>
	 */
	public String[][] generateBlob(){
		return (fillBoard(generateNumber()));//Calls the fillBoard method with the generateNumber method
	}//End of generateBlob method
	/**generateNumber
	 * This method generates a random integer between 1 and 3
	 * 
	 * @return - The randomly generated integer <type int>
	 */
	public int generateNumber(){
		Random randomNumber = new Random();
		return randomNumber.nextInt(3);
	}//End of generateNumber method
	/**fillBoard
	 * This method will fill the array with a pre-designed board based on the random number.
	 * 
	 * @param randomInteger - The randomly generated integer used to select a board <type int>
	 * @return - The array filled with asterisks and spaces <type String[][]>
	 */
	public String[][] fillBoard(int randomInteger){
		String[][] blobDesign1 = {
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," ","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" ","*","*","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," ","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," ","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," ","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," ","*"," "," "," "," "," "," "," "," "," "," ","*","*"," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," ","*","*","*","*","*","*","*","*","*","*"," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," ","*","*","*","*","*","*","*","*"," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," ","*","*","*","*","*","*"," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," ","*","*","*","*"," "," "," ","*","*","*","*"," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," ","*","*"," "," "," "," "," ","*","*"," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","*","*","*","*"," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","*","*"," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","*","*","*","*"," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","*","*"," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},

		};
		String[][] blobDesign2 = {
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," ","*"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," ","*"," "," "," "," "," "," "," "," "," "," "," "," ","*","*","*","*"," "," "," "," "," "," "},
				{" "," ","*","*","*"," "," "," "," "," "," "," "," "," "," ","*"," "," "," "," "," "," "," "," "," "},
				{" ","*","*","*","*"," "," ","*"," "," "," "," "," "," "," ","*"," "," "," "," "," "," "," "," "," "},
				{" ","*","*","*","*","*","*","*","*"," "," "," "," "," "," ","*"," "," "," "," "," "," "," "," "," "},
				{" ","*","*","*","*","*","*","*","*"," "," "," "," "," "," ","*"," ","*","*","*"," "," "," "," "," "},
				{" "," ","*","*","*"," "," ","*"," "," "," "," "," "," "," ","*"," "," ","*"," "," "," "," "," "," "},
				{" "," ","*"," "," "," "," "," "," "," "," "," "," "," "," ","*"," "," ","*"," "," "," "," "," "," "},
				{" "," ","*"," "," "," "," ","*","*"," "," "," ","*","*"," "," ","*","*","*"," "," "," "," "," "," "},
				{" "," ","*"," "," "," ","*","*","*","*"," ","*","*","*","*"," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," ","*","*","*","*","*","*","*","*","*"," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," ","*","*","*","*","*","*","*","*","*"," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," ","*","*","*","*","*","*","*","*","*"," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," ","*","*","*","*","*","*","*"," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," ","*","*","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," ","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," ","*"," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},		
		};
		String[][] blobDesign3 = {
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," ","*"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","*","*"," "},
				{" ","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","*","*","*"," "},
				{" "," ","*","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","*","*","*","*"," "},
				{" ","*","*","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "," ","*","*","*","*","*"," "},
				{" "," ","*","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," ","*","*","*","*","*","*"," "},
				{" "," "," ","*","*","*"," "," "," "," "," "," "," "," "," "," "," ","*","*","*","*","*","*","*"," "},
				{" "," "," "," "," "," "," "," ","*","*","*","*","*"," "," "," "," "," ","*","*","*","*","*","*"," "},
				{" "," "," "," "," "," "," "," "," ","*","*","*"," "," "," "," "," "," "," ","*","*","*","*","*"," "},
				{" "," "," "," "," "," "," "," "," ","*","*","*"," "," "," "," "," "," "," "," ","*","*","*","*"," "},
				{" "," "," "," "," "," "," "," "," ","*","*","*"," "," "," "," "," "," "," "," "," ","*","*","*"," "},
				{" "," "," "," "," "," "," "," "," ","*","*","*"," "," "," "," "," "," "," "," "," "," ","*","*"," "},
				{" "," "," "," "," "," "," "," ","*","*","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," ","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," ","*","*","*"," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," ","*"," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},

		};
		if(randomInteger == 1)
			return blobDesign1;
		else if(randomInteger == 2)
			return blobDesign2;
		else
			return blobDesign3;

	}//End of fillBoard method
	/**getArray
	 * This method returns the board that is to be used for the asterisk program
	 * 
	 * @return - The asterisk program array <type String[][]>
	 */
	public String[][] getArray() {
		return board;
	}//End of getArray method
}//End of SetUpAsteriskBlobs class
//==========================================================================================================================================================================================
//ShowWords
//This class contains the code to display the dictionary to the user
class ShowWords implements Runnable{//JLABEL
	private List<String> s;
	private JTextArea a;
	private TitledBorder t;
	private JScrollPane p;
	/**ShowWords
	 * Constructor class for ShowWords
	 * 
	 * @param array - The array that contains all the words <type ArrayList>
	 * @param area - The Text Area that displays all the words <type JTextArea>
	 * @param title - The title that notifies the loading status to the user <type TitledBorder>
	 * @param scrollPane - The scrol pane that allows the user to scroll through words <type JScrollPane>
	 */
	public ShowWords (List<String> array, JTextArea area, TitledBorder title, JScrollPane scrollPane) {
		a = area;
		s = array;
		t = title;
		p = scrollPane;
	}//End of ShowWords method
	/**run
	 * This method runs the following code, which displays the words to the user 
	 * 
	 */
	public void run() {
		for( int i = 0; i < s.size(); i++) {
			a.append(" "+ s.get(i) + "\n");
			t.setTitle((i*100/s.size()) + "% loaded, please wait..."); //Gives a % progress to the user
			p.repaint();
		}
		t.setTitle("List of Words");


	}//End of run method
}//End of ShowWords class
//==========================================================================================================================================================================================
//FontSelection
//This class contains the code to create a font 
class FontSelection {
	Font font;
	/**FontSelection
	 * Blank constructor because no values have to be set
	 * 
	 */
	public FontSelection(){

	}
	/**createFont method
	 * This method returns a specific font based on the integer a
	 * 
	 * @param a - An integer used to determine which font is to be returned
	 * @return - A font that will be used by JComponents <type Font>
	 */
	public Font createFont(int a){
		if (a == 0) 
			return font = new Font("Verdana", Font.BOLD, 50);
		else if (a == 1)
			return font = new Font("Courier New", Font.PLAIN, 30);
		else if (a == 2)
			return font = new Font("Courier New", Font.BOLD, 70);
		else
			return font = new Font("Courier New", Font.PLAIN, 16);
	}
}//End of FontSelection class

