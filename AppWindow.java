import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AppWindow extends JFrame{


	static int highscorelist[];
	
	
	AppWindow()
	{
		super("2048 Clone");
		String imgPath = "favicon.png";
		InputStream imgStream = Game.class.getResourceAsStream(imgPath);
		BufferedImage myImg;		
		try
		{
			myImg = ImageIO.read(imgStream);
			setIconImage(myImg);
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	JMenuBar menu = new JMenuBar();
	{
		this.setJMenuBar(menu);
		
		JMenu file = new JMenu("File");
		menu.add(file);
		
		JMenuItem newGame = new JMenuItem("New Game");
		file.add(newGame);
		
		 class NewGame implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				GameIFrame startGame = new GameIFrame();
				add(startGame);
				startGame.setVisible(true);
                             
                highscorelist=startGame.highscores;
                
 			}
            
            
		}
		newGame.addActionListener(new NewGame());
		
		JMenuItem hScores = new JMenuItem("View High Scores");
		file.add(hScores);
		
		class HighScores implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) 
            {
				
				HSIFrame hscoresFrame = new HSIFrame(highscorelist);
				add(hscoresFrame);
				hscoresFrame.setVisible(true);
			}			
		}
            
		hScores.addActionListener(new HighScores());
		
		JMenu about = new JMenu("About");
		menu.add(about);
		
		JMenuItem rules = new JMenuItem("2048 Rules");
		about.add(rules);
		
		class Rules implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				RulesIFrame rulesFrame = new RulesIFrame();
				add(rulesFrame);
				rulesFrame.setVisible(true);
			}			
		}
		rules.addActionListener(new Rules());
		
		JMenuItem team = new JMenuItem("Development Team");
		about.add(team);
		
		class Team implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TeamIFrame teamFrame = new TeamIFrame();
				add(teamFrame);
				teamFrame.setVisible(true);
			}			
		}
		team.addActionListener(new Team());

		JMenu quit = new JMenu("Quit");
		menu.add(quit);
		
		JMenuItem exit = new JMenuItem("Exit Program");
		quit.add(exit);
		
		class ExitProgram implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}			
		}
		exit.addActionListener(new ExitProgram());
		
	}
	
}

