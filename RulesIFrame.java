import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RulesIFrame extends JInternalFrame{
	
	RulesIFrame()
	{
		super("Rules", true, true, true, true);
		this.setBounds(100,100,550,350);						//Set bounds for window
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JTextArea display = new JTextArea(400, 400);				//Set text area for 20 rows and 20 columns. 
		JScrollPane scrollPane = new JScrollPane(display);		//Allow for scrolling (not needed here really)
		display.setEditable(false);								//Set text area to uneditable
		File rFile = new File("rules.txt");						//Set file up
		
		try 
		{
			if(rFile.createNewFile())							//Check to see if the file exists						 
			{													//If not, create the file and enter the null high scores
				FileWriter rWrite = new FileWriter(rFile);
				StringBuilder rules = new StringBuilder();
				rules.append("The Game of 2048\r\n\n");
				rules.append("2048 is a game where you combine numbered tiles in order to gain a higher numbered tile. \n"
						+ "In this game you start with two tiles, the lowest possible number available is two. \n"
						+ "Then you will play by combining the tiles with the same number to have a tile with \n"
						+ "the sum of the number on the two tiles. A lot of strategies have been devised in order \n"
						+ "to obtain the 2048 and be a winner in this game. But others have strived for a greater \n"
						+ "height. Which is a score beyond 2048.\r\n"
						+ "\r\n"
						+ "The game of 2048 does not include complicated controls. The controls you’ll be using are \n"
						+ "just upward, downward, and sideways (Keep in mind you will use arrow keys to move). The rules are also simple. You just need to move the \n"
						+ "tiles and every time you move one, another tile pops up in a random manner anywhere in the \n"
						+ "box. When two tiles with the same number on them collide with one another as you move them, \n"
						+ "they will merge into one tile with the sum of the numbers written on them initially. \n"
						+ "\r\n"
						+ "Credit: https://levelskip.com/puzzle/How-to-play-2048\n");
				rWrite.write(rules.toString());
				rWrite.close();
			}
			
			BufferedReader br = new BufferedReader(new FileReader(rFile)); //Set up to read contents from file
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
			while(line != null)
			{
				sb.append(line + "\n");
				line = br.readLine();
			}
			
			String result = sb.toString();
			display.append(result);								//Print the results to the text area
			br.close();
		}catch(IOException e)
		{
																//Something clever here. Should never execute.
		}
		add(display);
		display.setVisible(true);
		
	}
}

