import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HSIFrame extends JInternalFrame{
	
	HSIFrame(int [] scores){
        
		super("High Scores", true, true, true, true);			//Set name. Set Min, Max, resize and iconifable to true
		this.setBounds(300,200,200,200);						//Set bounds for window
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JTextArea display = new JTextArea(20, 20);				//Set text area for 20 rows and 20 columns. 
		JScrollPane scrollPane = new JScrollPane(display);		//Allow for scrolling (not needed here really)
		display.setEditable(false);								//Set text area to uneditable
		File hsFile = new File("highscores.txt");				//Set file up
		String filecontents="";
        
                            
            try 
            {
                FileWriter hsWrite= new FileWriter(hsFile);
                BufferedReader br = new BufferedReader(new FileReader(hsFile)); //Set up to read contents from file
                StringBuilder sb = new StringBuilder();                
                
                if(hsFile.createNewFile())							//Check to see if the file exists						 
                {	                        
                //If not, create the file and enter the null high scores

				for(int i = 0; i < 10; i++) 
				{
					hsWrite.append(String.valueOf(i + 1) + " 0    --/--/----\n");
				}hsWrite.close();
			
			
			String line = br.readLine();

                while(line != null)
                {
                    sb.append(line + "\n");
                    line = br.readLine();
                }

                filecontents= sb.toString();
                display.append(filecontents); //Print the results to the text area
                br.close();
                }
        else
        {

            for(int i=10; i>=1; i--)
            {
                
                sb.append("Highscore "+(10-(i-1))+ " " +scores[i-1] +"\n");								            
            }   
                filecontents=sb.toString();
                display.append(filecontents);
            br.close();           
        }                
            
		}catch(IOException e)
		{
																//Something clever here. Should never execute.
		}
            
        
		add(display);
		display.setVisible(true);
        
        hsFile.deleteOnExit();
        
	}

}

