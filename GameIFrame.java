import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameIFrame extends JInternalFrame{
    
    //highscores array
    static int[] highscores=new int[10];

    //the game board
	static int[][] tiles = new int[4][4];
	
	//Player score
	static int totScore = 0;
	
    //determines whether or not game is over
    boolean gameover;
    
	//Define the colors to be used on the tiles
	Color two = new Color(238, 228, 218);
	Color four = new Color(237, 224, 200);
	Color eight = new Color(242, 177, 121);
	Color sixteen = new Color(245, 149, 99);
	Color thirtyTwo = new Color(246, 124, 95);
	Color sixtyFour = new Color(246, 94, 59);
	Color oneTwentyEight = new Color(237, 207, 114);
	Color twoFiftySix = new Color(237, 204, 97);
	Color fiveTwelve = new Color(237, 200, 80);
	Color tenTwentyFour = new Color(237, 197, 63);
	Color twentyFourtyEight = new Color(237, 194, 46);
    
    //updates the highscores list
    void updatehighscores(int score)
    {
        for(int i=0; i<10; i++)
        {
            if (highscores[i]<score)
            {
                highscores[i]=score;
                break;
            }
        }
        Arrays.sort(highscores);
    }
    
    //prints the list of highscores for testing
//     void printhighscores()
//     {
//         for(int i=10; i>=1; i--)
//         {
//             System.out.println("Highscore "+(10-(i-1))+ " " +highscores[i-1]);
//         }
//     }
  
    private static boolean full()
    {
        for (int row=0; row<4; row++)
        {
            for(int col=0; col<4; col++)
            {
                //if any member of board is == to zero, then the board is not full
                if(tiles[row][col]==0)
                {
                	return false;
                }
            }
        }
        return true;
    }
    
    //checks to see if game ending conditions have occurred
     static boolean gameOver() 
    {
    	 if (full())
    	 {
        	//check to see if there is a valid move going up
        	for(int row = 0; row < 4; row++) 
        	{
        		for(int col = 0; col < 4; col++) 
        		{
        			if(row != 0) //Do not need to check the bottom row. Would throw an exception
        			{
        				if(tiles[row][col] == tiles[row - 1][col]) 
        				{
        					return false;
        				}
        			}
        		}
        	}
        	
        	//check to see if there is a valid move going down
        	for(int row = 0; row < 4; row++) 
        	{
        		for(int col = 0; col < 4; col++) 
        		{
        			if(row != 3) //Do not need to check the top row. Would throw an exception
        			{
        				if(tiles[row][col] == tiles[row + 1][col]) 
        				{
        					return false;
        				}
        			}
        		}
        	}
        	
        	//check to see if there is a valid move going right
        	for(int row = 0; row < 4; row++) 
        	{
        		for(int col = 0; col < 4; col++) 
        		{
        			if(col != 3) //Do not need to check the top row. Would throw an exception
        			{
        				if(tiles[row][col] == tiles[row][col + 1]) 
        				{
        					return false;
        				}
        			}
        		}
        	}
        	
        	//check to see if there is a valid move going left
        	for(int row = 0; row < 4; row++) 
        	{
        		for(int col = 0; col < 4; col++) 
        		{
        			if(col != 0) //Do not need to check the top row. Would throw an exception
        			{
        				if(tiles[row][col] == tiles[row][col - 1]) 
        				{
        					return false;
        				}
        			}
        		}
        	}
        	
        	//Test to see if a tile value is equal to 2048
        	for(int row = 0; row < 4; row++) 
        	{
        		for(int col = 0; col < 4; col++) 
        		{
        			if(tiles[row][col] == 2048)
        			{
        				return true;
        			}
        		}
        	}
        	return true;
        } 
    	 return false;        
    }
    
    
     static boolean gameOvercase2()
    {
        boolean gameover=false;
        
        for (int row=0; row<4; row++)
        {
               for(int col=0; col<4; col++)
               {
                   if (tiles[row][col]>=2048)
                       gameover=true;
               }
        }
             
         
        return gameover;
    }    
   
    int getendScore(int[][] tiles)
    {
       return totScore;
    }
	
	GameIFrame()
	{
		super("Play 2048!", true, true, true, true);			//Set name. Set Min, Max, resize and iconifable to true
		this.setBounds(250,20,430, 700);						//Set bounds for window
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		setLayout(null);
		mainPanel.setBounds(100, 50, 500, 700);	
        
		JButton move = new JButton("MOVE");
		move.setBounds(160, 500, 100, 80);
		move.setFont(move.getFont().deriveFont(10.0f));
		add(move);
		move.setVisible(false);        
        
        JLabel endText= new JLabel("Game Over");
        endText.setBounds(160, 500, 100, 80);
        endText.setFont(endText.getFont().deriveFont(14.0f));
        add(endText);
        endText.setVisible(false);
		
		JButton play = new JButton("PLAY!");
		play.setBounds(10, 5, 70, 40);
		play.setFont(play.getFont().deriveFont(10.0f));
		add(play);
		play.setVisible(true);
        
		JLabel score = new JLabel("SCORE: " + String.valueOf(totScore));
		score.setBounds(250, 5, 100, 50);
		score.setFont(score.getFont().deriveFont(14.0f));
		add(score);
		score.setVisible(false);	
        
		MyPanel board = new MyPanel();
        
		class Play implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Random rand = new Random(); //seed random
				//fill tiles array with zeroes
				for(int row = 0; row < 4; row++) 
				{
					for(int col = 0; col < 4; col++) 
					{
						tiles[row][col] = 0;
					}
				}
				//pick two random tiles and give them a value of 2 or four
				for(int index = 0; index < 2; index ++) 
				{
					int row = rand.nextInt(4);
					int col = rand.nextInt(4);
					int value = rand.nextInt(2);
					if(value == 0 && tiles[row][col] == 0) tiles[row][col] = 2;
					else if(value == 1 && tiles[row][col] == 0) tiles[row][col] = 4;
					else index--;
				}
				
				//set play button invisible
                play.setVisible(false);
				endText.setVisible(false);
                score.setVisible(true);	
                move.setVisible(true);
				repaint();
			}			
		}
        
		play.addActionListener(new Play());
        
        
		class MergeUp implements KeyListener
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
                int key=e.getKeyCode();
                
				if(!gameOver() && !gameOvercase2()) 
				{
                    if(key==KeyEvent.VK_UP)
                    {

                        Random rand = new Random(); //seed random

                        //move all the tiles up. Start at top of Grid.
                        for(int row = 0; row <  4; row++) 
                        {
                            for(int col = 0; col < 4; col++) 
                            {
                                if(tiles[row][col] == 0 && row != 3 && tiles[row + 1][col] != 0) 
                                {
                                    tiles[row][col] = tiles[row + 1][col];
                                    tiles[row + 1][col] = 0;
                                    row = 0;
                                    col = 0;
                                }
                            }
                         }

                        //join the tiles if they match
                        for(int col = 0; col < 4 ; col++)
                        {

                            if(tiles[0][col] != 0 && tiles[0][col] == tiles[1][col]) 
                            {
                                tiles[0][col] += tiles[1][col];
                                tiles[1][col] = 0;
                            }
                            else if(tiles[1][col] != 0 && tiles[1][col] == tiles[2][col]) 
                            {
                                tiles[1][col] += tiles[2][col];
                                tiles[2][col] = 0;
                            }else if(tiles[2][col] != 0 && tiles[2][col] == tiles[3][col]) 
                            {
                                tiles[2][col] += tiles[3][col];
                                tiles[3][col] = 0;
                            }
                        }

                        //move the tiles up again
                        for(int row = 0; row <  4; row++) 
                        {
                            for(int col = 0; col < 4; col++) 
                            {
                                if(tiles[row][col] == 0 && row != 3 && tiles[row + 1][col] != 0) 
                                {
                                    tiles[row][col] = tiles[row + 1][col];
                                    tiles[row + 1][col] = 0;
                                    row = 0;
                                    col = 0;
                                }
                            }
                        }

                        if(!full()) {
                        //Get a new random tile
                            for(int index = 0; index < 1; index ++) 
                            {
                                int x = rand.nextInt(4);
                                int y = rand.nextInt(4);
                                int value = rand.nextInt(2);
                                if(value == 0 && tiles[x][y] == 0) tiles[x][y] = 2;
                                else if(value == 1 && tiles[x][y] == 0) tiles[x][y] = 4;
                                else index--;
                            }
                        }

                        //Set the score				
                        int Score = 0;
                        for(int [] inner : tiles) 
                        {
                            for(int i : inner)
                            Score += i;
                        }
                        totScore=Score;
                        score.setText("SCORE: " + String.valueOf(totScore));
                        repaint();
                    }
				}
                else 
				{
                    updatehighscores(totScore);
					play.setVisible(true);
					endText.setVisible(true);
                    move.setVisible(false);
					//checkHighScore();
					resetGame();
					score.setText("SCORE: " + String.valueOf(totScore));
					repaint();
				}
			}
            
            @Override
			public void keyReleased(KeyEvent e) {}
            
            @Override
			public void keyTyped(KeyEvent e) {}
           
        }
		move.addKeyListener(new MergeUp());
        
		
        class MergeDown implements KeyListener
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
                int key=e.getKeyCode();
                
				if(!gameOver() && !gameOvercase2()) 
				{
                    if(key==KeyEvent.VK_DOWN)
                    {
                        Random rand = new Random(); //seed random


                        //move all the tiles down. Start at bottom of grid.
                        for(int row = 0; row < 4; row++) 
                        {
                            for(int col = 0; col < 4; col++) 
                            {
                                if(tiles[row][col] != 0 && row != 3 && tiles[row + 1][col] == 0 )
                                {
                                    tiles[row + 1][col] = tiles[row][col];
                                    tiles[row][col] = 0;
                                    row = 0;
                                    col = 0;
                                }
                            }
                        }

                        //join the tiles if they match

                        for(int col = 0; col < 4 ; col++)
                        {
                            if(tiles[3][col] != 0 && tiles[3][col] == tiles[2][col]) 
                            {
                                tiles[3][col] += tiles[2][col];
                                tiles[2][col] = 0;
                            }else if(tiles[2][col] != 0 && tiles[2][col] == tiles[1][col]) 
                            {
                                tiles[2][col] += tiles[1][col];
                                tiles[1][col] = 0;
                            }else if(tiles[1][col] != 0 && tiles[1][col] == tiles[0][col]) 
                            {
                                tiles[1][col] += tiles[0][col];
                                tiles[0][col] = 0;
                            }
                        }


                        //move the tiles down again
                        for(int row = 0; row < 4; row++) 
                        {
                            for(int col = 0; col < 4; col++) 
                            {
                                if(tiles[row][col] != 0 && row != 3 && tiles[row + 1][col] == 0 )
                                {
                                    tiles[row + 1][col] = tiles[row][col];
                                    tiles[row][col] = 0;
                                    row = 0;
                                    col = 0;
                                }
                            }
                        }

                        if(!full()) 
                        {
                            //Get a new random tile
                            for(int index = 0; index < 1; index ++) 
                            {
                                int x = rand.nextInt(4);
                                int y = rand.nextInt(4);
                                int value = rand.nextInt(2);
                                if(value == 0 && tiles[x][y] == 0) tiles[x][y] = 2;
                                else if(value == 1 && tiles[x][y] == 0) tiles[x][y] = 4;
                                else index--;
                            }
                        }

                        //Set the score				
                        int Score = 0;
                        for(int [] inner : tiles) 
                        {
                            for(int i : inner)
                                Score += i;
                        }
                        totScore=Score;
                        score.setText("SCORE: " + String.valueOf(totScore));
                        repaint();
                    }
				}
                else 
				{
                    updatehighscores(totScore);
					play.setVisible(true);
					endText.setVisible(true);
                    move.setVisible(false);
// 					//checkHighScore();
					resetGame();
					score.setText("SCORE: " + String.valueOf(totScore));
					repaint();
				}
			}
            
            @Override
			public void keyReleased(KeyEvent e) {}
            
            @Override
			public void keyTyped(KeyEvent e) {}
		} move.addKeyListener(new MergeDown());
		

        
		class MergeLeft implements KeyListener
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
                int key=e.getKeyCode();
                
				if(!gameOver() && !gameOvercase2())
				{
                    if(key==KeyEvent.VK_LEFT)
                    {
                        Random rand = new Random(); //seed random

                        //move all the tiles left. Start at right of grid.
                        for(int row = 0; row < 4; row++) 
                        {
                            for(int col = 0; col < 4; col++) 
                            {
                                if(tiles[row][col] == 0 && col != 3 && tiles[row][col + 1] !=0 )
                                {
                                    tiles[row ][col] = tiles[row][col + 1];
                                    tiles[row][col + 1] = 0;
                                    col = 0;
                                    row = 0;
                                }
                            }
                        }

                        //join the tiles if they match

                        for(int row = 0; row < 4; row++)
                        {
                            if(tiles[row][0] != 0 && tiles[row][0] == tiles[row][1]) 
                            {
                                tiles[row][0] += tiles[row][1];
                                tiles[row][1] = 0;
                            }else if(tiles[row][1] != 0 && tiles[row][1] == tiles[row][2]) 
                            {
                                tiles[row][1] += tiles[row][2];
                                tiles[row][2] = 0;
                            }else if(tiles[row][2] != 0 && tiles[row][2] == tiles[row][3]) 
                            {
                                tiles[row][2] += tiles[row][3];
                                tiles[row][3] = 0;
                            }
                        }

                        //move the tiles left again
                        for(int row = 0; row < 4; row++) 
                        {
                            for(int col = 0; col < 4; col++) 
                            {
                                if(tiles[row][col] == 0 && col != 3 && tiles[row][col + 1] !=0 )
                                {
                                    tiles[row ][col] = tiles[row][col + 1];
                                    tiles[row][col + 1] = 0;
                                    col = 0;
                                    row = 0;
                                }
                            }
                        }

                        if(!full()) 
                        {
                            //Get a new random tile
                            for(int index = 0; index < 1; index ++) 
                            {
                                int x = rand.nextInt(4);
                                int y = rand.nextInt(4);
                                int value = rand.nextInt(2);
                                if(value == 0 && tiles[x][y] == 0) tiles[x][y] = 2;
                                else if(value == 1 && tiles[x][y] == 0) tiles[x][y] = 4;
                                else index--;
                            }
                        }

                        //Set the score				
                        int Score = 0;
                        for(int [] inner : tiles) 
                        {
                            for(int i : inner)
                                Score += i;
                        }
                        totScore=Score;
                        score.setText("SCORE: " + String.valueOf(totScore));
                        repaint();
                    }
				}
                else 
				{
                    updatehighscores(totScore);
					play.setVisible(true);
					endText.setVisible(true);
                    move.setVisible(false);                    
					//checkHighScore();
					resetGame();
					score.setText("SCORE: " + String.valueOf(totScore));
					repaint();
				}
			}
            
            @Override
			public void keyReleased(KeyEvent e) {}
            
            @Override
			public void keyTyped(KeyEvent e) {}
            
		}
        move.addKeyListener(new MergeLeft());
        
        class MergeRight implements KeyListener
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
                int key=e.getKeyCode();
                
				if(!gameOver() && !gameOvercase2()) 
				{
                    if(key==KeyEvent.VK_RIGHT)
                    {
                        Random rand = new Random(); //seed random

                        //move all the tiles right. Start at right of grid.
                        for(int row = 0; row < 4; row++) 
                        {
                            for(int col = 3; col >= 0; col--) 
                            {
                                if(tiles[row][col] == 0 && col != 0 && tiles[row][col - 1] !=0 )
                                {
                                    tiles[row ][col] = tiles[row][col - 1];
                                    tiles[row][col - 1] = 0;
                                    col = 3;
                                    row = 0;
                                }
                            }
                        }

                        //join the tiles if they match
                        for(int row = 0; row < 4; row++)
                        {
                            if(tiles[row][3] != 0 && tiles[row][3] == tiles[row][2]) 
                            {
                                tiles[row][3] += tiles[row][2];
                                tiles[row][2] = 0;
                            }else if(tiles[row][2] != 0 && tiles[row][2] == tiles[row][1]) 
                            {
                                tiles[row][2] += tiles[row][1];
                                tiles[row][1] = 0;
                            }else if(tiles[row][1] != 0 && tiles[row][1] == tiles[row][0]) 
                            {
                                tiles[row][1] += tiles[row][0];
                                tiles[row][0] = 0;
                            }
                        }

                        //move the tiles right again
                        for(int row = 0; row < 4; row++) 
                        {
                            for(int col = 3; col >= 0; col--) 
                            {
                                if(tiles[row][col] == 0 && col != 0 && tiles[row][col - 1] !=0 )
                                {
                                    tiles[row ][col] = tiles[row][col - 1];
                                    tiles[row][col - 1] = 0;
                                    col = 3;
                                    row = 0;
                                }
                            }
                        }					

                        if(!full()) 
                        {
                            //Get a new random tile
                            for(int index = 0; index < 1; index ++) 
                            {
                                int x = rand.nextInt(4);
                                int y = rand.nextInt(4);
                                int value = rand.nextInt(2);
                                if(value == 0 && tiles[x][y] == 0) tiles[x][y] = 2;
                                else if(value == 1 && tiles[x][y] == 0) tiles[x][y] = 4;
                                else index--;
                            }
                        }

                        //Set the score				
                        int Score = 0;
                        for(int [] inner : tiles) 
                        {
                            for(int i : inner)
                                Score += i;
                        }
                        totScore=Score;
                        score.setText("SCORE: " + String.valueOf(totScore));
                        repaint();
                    }
				}
                else 
				{
                    updatehighscores(totScore);
					play.setVisible(true);
					endText.setVisible(true);
                    move.setVisible(false);                    
					//checkHighScore();
					resetGame();
					score.setText("SCORE: " + String.valueOf(totScore));
					repaint();
				}
			 }
            @Override
			public void keyReleased(KeyEvent e) {}
            
            @Override
			public void keyTyped(KeyEvent e) {}            
		}
		move.addKeyListener(new MergeRight());
        
		//add board and panel. set both to visible. Do this step last.
		add(board);
		board.setVisible(true);
		add(mainPanel);
		mainPanel.setVisible(true);
	}
	
	
	
    //Method to reset the board and the score
	public void resetGame() {
        
		for(int row = 0; row < 4; row++) 
		{
			for(int col = 0; col < 4; col++)
			{
				tiles[row][col] = 0;
			}
		}totScore = 0;
		repaint();
		
	}


	class MyPanel extends JPanel
	{
		MyPanel()
		{
			setBounds(10, 70, 600, 500);				
		}
		
		@Override
		public void paintComponent(Graphics g) 
		{
			//Draws the play grid, colors the tiles based on the value in tiles
			//and draws the char array to give the value
			
			super.paintComponent(g);			
			//Graphics2D board = (Graphics2D) g;
			for(int row = 0; row < 400; row += 100) 
			{
				for(int col = 0; col < 400; col += 100) 
				{
					g.drawRect(row, col, 100, 100);
					//Need to divide i and j to get 0 through 3 for the tiles array
					if(tiles[row/100][col/100] != 0) 
					{
						g.setColor(tileColor(tiles[row/100][col/100]));
						//Not perfect but creates a smaller fill for the tile in the grid
						g.fillRect(col + 3, row + 3, 95, 95 );
						String value = String.valueOf(tiles[row/100][col/100]);
						char[] rectVal = value.toCharArray();
						//set the color back to black before moving on
						g.setColor(Color.BLACK);
						g.setFont(g.getFont().deriveFont(30.0f));
						g.drawChars(rectVal, 0, rectVal.length, col + (51 - rectVal.length), row + 51);
						
					}
				}
			}
		}

		//Switch statement to return the color based on the value.
		//Use only with the tiles array.
		private Color tileColor(int i) 
		{			
			switch(i) 
			{
			case 2:
				return two;
			case 4:
				return four;
			case 8:
				return eight;
			case 16:
				return sixteen;
			case 32:
				return thirtyTwo;
			case 64:
				return sixtyFour;
			case 128:
				return oneTwentyEight;
			case 512:
				return fiveTwelve;
			case 1024:
				return tenTwentyFour;
			case 2048:
				return twentyFourtyEight;
			default:
					return Color.WHITE;
			}
		}
        
	}
}

