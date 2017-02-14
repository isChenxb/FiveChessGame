

import java.util.Scanner;

public class FiveChessGame {

	/**
	 * @param args
	 */
	static int N=15;
	public static String[][] chess=new String[N][N];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fillChessBoard();
		while(true){
			showChess();
			putChess();
		}

	}
	//填充棋盘
	// ┏ ┳ ┓ ┣ ╋ ┫ ┗ ┻ ┛  ━  ┃
	public static void fillChessBoard(){
		for (int i=0;i<chess.length;i++){
			for (int j=0;j<chess[i].length;j++){
				if (i==0){
					if (j==0){
						chess[i][j]="┏";
					}
					if (j>0 && j<chess[i].length-1){
						chess[i][j]="┳";
					}
					if (j==chess[i].length-1){
						chess[i][j]="┓";
					}
				}
				if (i>0 && i<chess.length-1){
					if (j==0){
						chess[i][j]="┣";
					}
					if (j>0 && j<chess[i].length-1){
						chess[i][j]="╋";
					}
					if (j==chess[i].length-1){
						chess[i][j]="┫";
					}
				}
				if (i==chess.length-1){
					if (j==0){
						chess[i][j]="┗";
					}
					if (j>0 && j<chess[i].length-1){
						chess[i][j]="┻";
					}
					if (j==chess[i].length-1){
						chess[i][j]="┛";
					}
				}
			}
		}
	}
	
	//显示棋盘
	public static void showChess(){
		System.out.println("  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("  ┃**** **** ****        五子棋大战       **** ****  ****┃");
		System.out.println("  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		for (int i=0;i<chess.length;i++){
			if (i==0){
				System.out.print("  ");
				for (int n=0;n<chess[i].length;n++){
					System.out.print((char)('A'+n)+"   ");
				}
				System.out.println();
			}
			for (int j=0;j<chess[i].length;j++){
				if (j==0){
					System.out.print(" "+(char)('A'+i));
				}
				System.out.print(chess[i][j]);
				if (j!=chess[i].length-1){
					System.out.print("━");
				}
			}
			System.out.println();
			if (i<chess.length -1){
				System.out.print("  ");
				for (int k=0;k<chess[i].length;k++){
					System.out.print("┃  ");
				}
			}
			System.out.println();
		}
	}
	
	//下子
	//●○★▲
	static int sum=0;   //下棋步数
	public static void putChess(){
		int x=0;
		int y=0;
		String player="";
		if (sum%2==0){
			player="●";
		}else{
			player="○";
		}
		System.out.print("请"+player+"方输入落子坐标（0退出）：");
		Scanner input=new Scanner(System.in);
		String str=input.next();
		
		//若用户输入0则退出游戏
		if (str.equals("0")){
			System.out.println(player+"已逃跑，游戏已退出");
			if (player.equals("●")){
				player="○";
			}else{
				player="●";
			}
			System.out.println("赢家是"+player);
			System.exit(0);
		}
		//判断用户输入是否有效
		str=str.toUpperCase(); //转换为大写
		if (str.length()!=2){
			System.out.println("输入字符无效");
			return;
		}else{
			x=str.charAt(0)-'A';
			y=str.charAt(1)-'A';
			if (x<0 || x>=chess.length || y<0 || y>=chess[0].length){
				System.out.println("输入字符无效");
				return;
			}
		}
		
		//判断该坐标是否已存在棋子
		if (chess[x][y].equals("●") || chess[x][y].equals("○")){
			System.out.println("该坐标上已有棋子！");
			return;
		}
		//落子
		chess[x][y]=player;
		sum++;
		
		//检查输赢
		if (sum>=9){
			if (checkWin(player,x,y)){
				showChess();
				System.out.println("胜负已出，赢家是"+player);
				System.exit(0);
			}
		}
	}
	
	//判断输赢
	public static boolean checkWin(String player,int x,int y){
		int nNum=0;   //连子的个数
		//1.判断棋子从左上到右下是否五连
		//1.1统计落点到左上连子数
		int i=x;
		int j=y;
		while(chess[i][j].equals(player)){
			nNum++;
			if (nNum==5){
				return true;
			}
			i--;
			j--;
			if (i<0 || j<0){
				break;
			}
		}
		//1.2统计落点右下的连子数
		i=x;
		j=y;
		while(chess[i][j].equals(player)){
			nNum++;
			if (nNum==6){
				return true;
			}
			i++;
			j++;
			if (i>=N || j>=N){
				break;
			}
		}
		//2.判断棋子从右上到左下是否五连
		//2.1统计落点到右上的连子数
		nNum=0;
		i=x;
		j=y;
		while(chess[i][j].equals(player)){
			nNum++;
			if (nNum==5){
				return true;
			}
			i--;
			j++;
			if (i<0 || j>=N){
				break;
			}
		}
		//2.2统计落点左下的连子数
		i=x;
		j=y;
		while(chess[i][j].equals(player)){
			nNum++;
			if (nNum==6){
				return true;
			}
			i++;
			j--;
			if (i>=N || j<0){
				break;
			}
		}
		//3.判断棋子从左到右是否五连
	    //3.1统计落点到左的连子数
		nNum=0;
		i=x;
		j=y;
		while(chess[i][j].equals(player)){
			nNum++;
			if (nNum==5){
				return true;
			}
			j--;
			if (j<0){
				break;
			}
		}
		////3.2统计落点到右的连子数
		i=x;
		j=y;
		while(chess[i][j].equals(player)){
			nNum++;
			if (nNum==6){
				return true;
			}
			j++;
			if (j>=N){
				break;
			}
		}
		 //4.1统计落点到上的连子数
		nNum=0;
		i=x;
		j=y;
		while(chess[i][j].equals(player)){
			nNum++;
			if (nNum==5){
				return true;
			}
			i--;
			if (i<0){
				break;
			}
		}
		////4.2统计落点到下的连子数
		i=x;
		j=y;
		while(chess[i][j].equals(player)){
			nNum++;
			if (nNum==6){
				return true;
			}
			i++;
			if (i>=N){
				break;
			}
		}
		return false;
	}

}
