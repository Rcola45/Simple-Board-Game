
public class Board {
	private int xi,xj,height,width;
	private int[][] mainBoard;
	
	public Board(int h, int w){
		xi=0;
		xj=0;
		height=h;
		width=w;
		//0=nothing
		//1=player
		//2=enemy
		//3=points
		mainBoard=new int[height][width];
		
		//might have to switch height and width in loop?
		for(int k=0;k<height;k++){
			for(int l=0;l<width;l++){
				mainBoard[k][l]=0;
			}
		}
		System.out.println(mainBoard[2][4]);
	}
	
	public int[][] getBoard(){
		return mainBoard;
	}
	
	public int getSpot(int i,int j){
		return mainBoard[i][j];
	}
	
	public int getPlayerI(){
		return xi;
	}
	
	public int getPlayerJ(){
		return xj;
	}
	
	public void setPlayerI(int i){
		xi=i;
	}
	
	public void setPlayerJ(int j){
		xj=j;
	}
	
	public void setSpot(int i, int j,int spot){
		mainBoard[i][j]=spot;
		if(spot==1){
			xi=i;
			xj=j;
		}
		
	}
	
}
