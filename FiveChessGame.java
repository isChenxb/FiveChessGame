

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
	//�������
	// �� �� �� �� �� �� �� �� ��  ��  ��
	public static void fillChessBoard(){
		for (int i=0;i<chess.length;i++){
			for (int j=0;j<chess[i].length;j++){
				if (i==0){
					if (j==0){
						chess[i][j]="��";
					}
					if (j>0 && j<chess[i].length-1){
						chess[i][j]="��";
					}
					if (j==chess[i].length-1){
						chess[i][j]="��";
					}
				}
				if (i>0 && i<chess.length-1){
					if (j==0){
						chess[i][j]="��";
					}
					if (j>0 && j<chess[i].length-1){
						chess[i][j]="��";
					}
					if (j==chess[i].length-1){
						chess[i][j]="��";
					}
				}
				if (i==chess.length-1){
					if (j==0){
						chess[i][j]="��";
					}
					if (j>0 && j<chess[i].length-1){
						chess[i][j]="��";
					}
					if (j==chess[i].length-1){
						chess[i][j]="��";
					}
				}
			}
		}
	}
	
	//��ʾ����
	public static void showChess(){
		System.out.println("  ����������������������������������������������������������");
		System.out.println("  ��**** **** ****        �������ս       **** ****  ****��");
		System.out.println("  ����������������������������������������������������������");
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
					System.out.print("��");
				}
			}
			System.out.println();
			if (i<chess.length -1){
				System.out.print("  ");
				for (int k=0;k<chess[i].length;k++){
					System.out.print("��  ");
				}
			}
			System.out.println();
		}
	}
	
	//����
	//�����
	static int sum=0;   //���岽��
	public static void putChess(){
		int x=0;
		int y=0;
		String player="";
		if (sum%2==0){
			player="��";
		}else{
			player="��";
		}
		System.out.print("��"+player+"�������������꣨0�˳�����");
		Scanner input=new Scanner(System.in);
		String str=input.next();
		
		//���û�����0���˳���Ϸ
		if (str.equals("0")){
			System.out.println(player+"�����ܣ���Ϸ���˳�");
			if (player.equals("��")){
				player="��";
			}else{
				player="��";
			}
			System.out.println("Ӯ����"+player);
			System.exit(0);
		}
		//�ж��û������Ƿ���Ч
		str=str.toUpperCase(); //ת��Ϊ��д
		if (str.length()!=2){
			System.out.println("�����ַ���Ч");
			return;
		}else{
			x=str.charAt(0)-'A';
			y=str.charAt(1)-'A';
			if (x<0 || x>=chess.length || y<0 || y>=chess[0].length){
				System.out.println("�����ַ���Ч");
				return;
			}
		}
		
		//�жϸ������Ƿ��Ѵ�������
		if (chess[x][y].equals("��") || chess[x][y].equals("��")){
			System.out.println("���������������ӣ�");
			return;
		}
		//����
		chess[x][y]=player;
		sum++;
		
		//�����Ӯ
		if (sum>=9){
			if (checkWin(player,x,y)){
				showChess();
				System.out.println("ʤ���ѳ���Ӯ����"+player);
				System.exit(0);
			}
		}
	}
	
	//�ж���Ӯ
	public static boolean checkWin(String player,int x,int y){
		int nNum=0;   //���ӵĸ���
		//1.�ж����Ӵ����ϵ������Ƿ�����
		//1.1ͳ����㵽����������
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
		//1.2ͳ��������µ�������
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
		//2.�ж����Ӵ����ϵ������Ƿ�����
		//2.1ͳ����㵽���ϵ�������
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
		//2.2ͳ��������µ�������
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
		//3.�ж����Ӵ������Ƿ�����
	    //3.1ͳ����㵽���������
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
		////3.2ͳ����㵽�ҵ�������
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
		 //4.1ͳ����㵽�ϵ�������
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
		////4.2ͳ����㵽�µ�������
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
