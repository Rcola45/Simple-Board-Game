import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class Window extends JFrame implements ActionListener{
	private String empty="\u25A1", player="\u25C9", enemy="\u25A0", points="\u25EF";
	private Board b;
	private JLabel[][] grid;
	private int score;
	private JLabel scoreL=new JLabel("Score: "+score), playerL, enemyL, emptyL, pointsL;
	private JPanel board=new JPanel(new GridLayout(10,10));
	private JPanel arrows=new JPanel(new BorderLayout());
	private JPanel game=new JPanel(new FlowLayout());
	private JPanel legend=new JPanel(new FlowLayout());
	private JButton up,down,left,right;

	
	public Window(){
		b=new Board(10,10);
		grid=new JLabel[10][10];
		for(int k=0;k<10;k++){
			for(int l=0;l<10;l++){
				grid[k][l]=new JLabel(empty, SwingConstants.CENTER);
				board.add(grid[k][l]);
				b.setSpot(k,l,0);
			}
		}
		add(1);
		for(int k=0;k<10;k++){
			
			for(int l=0;l<10;l++){
					if(b.getSpot(k, l)==1){
						b.setPlayerI(k);
						b.setPlayerJ(l);
				}
			}
		}
		
		add(2); //Add enemy
		add(3); //Add a point
		
		up=new JButton(new ImageIcon("up.jpg"));
		up.addActionListener(this);
		up.setMnemonic(KeyEvent.VK_UP);
		down=new JButton(new ImageIcon("down.jpg"));
		down.addActionListener(this);
		down.setMnemonic(KeyEvent.VK_DOWN);
		left=new JButton(new ImageIcon("left.jpg"));
		left.addActionListener(this);
		left.setMnemonic(KeyEvent.VK_LEFT);
		right=new JButton(new ImageIcon("right.jpg"));
		right.addActionListener(this);
		right.setMnemonic(KeyEvent.VK_RIGHT);
		
		
		arrows.add(up, BorderLayout.NORTH);
		arrows.add(right,BorderLayout.EAST);
		arrows.add(left,BorderLayout.WEST);
		arrows.add(down,BorderLayout.CENTER);
		
		playerL=new JLabel("Player: \u25C9  ||");
		emptyL=new JLabel("Empty Spot: \u25A1  ||");
		enemyL=new JLabel("Enemy: \u25A0  ||");
		pointsL=new JLabel("Points: \u25EF  ||");
		legend.add(playerL);
		legend.add(emptyL);
		legend.add(enemyL);
		legend.add(pointsL);
		legend.add(scoreL);
		
		game.add(board);
		game.add(arrows);
		game.add(legend);
		add(game);
		setTitle("Game");
		setSize(380,370);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public void add(int spot){
		int ri=(int) (Math.random()*10);
		int rj=(int) (Math.random()*10);
		while(b.getSpot(ri, rj)!=0){
			ri=(int) (Math.random()*10);
			rj=(int) (Math.random()*10);
		}
		b.setSpot(ri, rj, spot);
		updateBoard();
	}
	
	public void updateBoard(){
		int spot=0;
		for(int k=0;k<10;k++){
			for(int l=0;l<10;l++){
				spot=b.getSpot(k,l);
				if(spot==0)
					grid[k][l].setText(empty);
				else if(spot==1){
					grid[k][l].setText(player);
				}
				else if(spot==2)
					grid[k][l].setText(enemy);
				else
					grid[k][l].setText(points);
			}
		}
		scoreL.setText("Score: "+score);
	}
	
	public void actionPerformed(ActionEvent e){
		try{
			int nextSpot;
			if(e.getSource()==up){
				if(b.getPlayerI()!=0){
					nextSpot=b.getSpot(b.getPlayerI()-1, b.getPlayerJ());
					if(nextSpot==0){
						b.setSpot(b.getPlayerI(), b.getPlayerJ(), 0);
						b.setSpot(b.getPlayerI()-1, b.getPlayerJ(), 1);
					}else if(nextSpot==2){
						JOptionPane.showMessageDialog(null, "You Lose!");
						System.exit(0);
					}else if(nextSpot==3){
						add(2);
						add(3);
						score++;
						b.setSpot(b.getPlayerI(), b.getPlayerJ(), 0);
						b.setSpot(b.getPlayerI()-1, b.getPlayerJ(), 1);
					}
				}
			}else if(e.getSource()==down){
				if(b.getPlayerI()!=9){
					nextSpot=b.getSpot(b.getPlayerI()+1, b.getPlayerJ());
					if(nextSpot==0){
						b.setSpot(b.getPlayerI(), b.getPlayerJ(), 0);
						b.setSpot(b.getPlayerI()+1, b.getPlayerJ(), 1);
					}else if(nextSpot==2){
						JOptionPane.showMessageDialog(null, "You Lose!");
						System.exit(0);
					}else if(nextSpot==3){
						add(2);
						add(3);
						score++;
						b.setSpot(b.getPlayerI(), b.getPlayerJ(), 0);
						b.setSpot(b.getPlayerI()+1, b.getPlayerJ(), 1);
					}
					
				}
			}else if(e.getSource()==left){
				if(b.getPlayerJ()!=0){
					nextSpot=b.getSpot(b.getPlayerI(), b.getPlayerJ()-1);
					if(nextSpot==0){
						b.setSpot(b.getPlayerI(), b.getPlayerJ(), 0);
						b.setSpot(b.getPlayerI(), b.getPlayerJ()-1, 1);
					}else if(nextSpot==2){
						JOptionPane.showMessageDialog(null, "You Lose!");
						System.exit(0);
					}else if(nextSpot==3){
						add(2);
						add(3);
						score++;
						b.setSpot(b.getPlayerI(), b.getPlayerJ(), 0);
						b.setSpot(b.getPlayerI(), b.getPlayerJ()-1, 1);
					}
					
				}
			}else if(e.getSource()==right){
				if(b.getPlayerJ()!=9){
					nextSpot=b.getSpot(b.getPlayerI(), b.getPlayerJ()+1);
					if(nextSpot==0){
						b.setSpot(b.getPlayerI(), b.getPlayerJ(), 0);
						b.setSpot(b.getPlayerI(), b.getPlayerJ()+1, 1);
					}else if(nextSpot==2){
						JOptionPane.showMessageDialog(null, "You Lose!");
						System.exit(0);
					}else if(nextSpot==3){
						add(2);
						add(3);
						score++;
						b.setSpot(b.getPlayerI(), b.getPlayerJ(), 0);
						b.setSpot(b.getPlayerI(), b.getPlayerJ()+1, 1);
					}
				}
			}

			updateBoard();
		}catch(Exception x){
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Window myWindow = new Window();
	}
	

}
