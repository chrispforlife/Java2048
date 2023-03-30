import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TeamIFrame extends JInternalFrame{
	
	TeamIFrame()
	{
		super("Development Team", true, true, true, true);
		this.setBounds(100,100,450,300);						//Set bounds for window
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JTextArea display = new JTextArea(300, 300);				//Set text area for 20 rows and 20 columns. 
		JScrollPane scrollPane = new JScrollPane(display);		//Allow for scrolling (not needed here really)
		display.setEditable(false);								//Set text area to uneditable
		File rFile = new File("team.txt");						//Set file up
		
		try 
		{
			if(rFile.createNewFile())							//Check to see if the file exists						 
			{													//If not, create the file and enter the null high scores
				FileWriter rWrite = new FileWriter(rFile);
				StringBuilder rules = new StringBuilder();
				rules.append("Development Team for 2048 Clone\r\n\n");
				rules.append("Christian Pierre Paul \n"
                        + "Pursuing a B.S in Computer Science at Florida State University. \n"
                        + "My hobbies include gaming, basketball, running, and going to the gym. \n"
                        + "Contribues as part of FSU's club basketball as Treasurer. \n"
                        + "Also serves as the Ambassador and Treasurer \n"
                        + "of Southern Scholarship Foundation. \n"
                        + "\r\n");
                rules.append("Roy J. Avrett \n"
						+ "Pursuing a B.S in Computer Science at Florida State University. \n"
						+ "Hobbies include endurance running and programming. \n"
						+ "Contributes as part of the MX Linux Development Team \n"
						+ "in the role of Translation Coordinator. \n"
						+ "Also serves as President of the FSU Online ACM Club and a \n"
						+ "proud member of Upsilon Pi Epsilon. \n\n"
						+ "Github: github.com/ravrett"
						+ "\r\n");
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

