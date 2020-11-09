import java.util.*;
class TicTacToe{
	public static void main(String[] args) throws Exception {
		tictacboard tb=new tictacboard();
		System.out.println("Welcome to Tic Tac Toe Game\nLet's begin the game...");
		Thread.sleep(1000);
		Scanner sc=new Scanner(System.in);

		System.out.print("Enter Player1 Name: ");
		String name1=sc.nextLine();
		System.out.print("Enter your Symbol(either x or o or any character): ");
		char[] symbol1=sc.nextLine().toCharArray();
		tictacplayer p1=new tictacplayer(name1,symbol1[0]);

		System.out.print("Enter Player2 Name: ");
		String name2=sc.nextLine();
		System.out.print("Enter your Symbol(either x or o or any character): ");
		char[] symbol2=sc.nextLine().toCharArray();
		tictacplayer p2=new tictacplayer(name2,symbol2[0]);
		
		System.out.println("\n\nNote:\nBefore you start playing, give your location as row_number<space>column_number\nrow_numbers=[1,2,3],column_numbers=[1,2,3]");
		Thread.sleep(5000);
		System.out.println("Take a look on the empty board.");
		tb.display_board();
		int won=0;
		outer: for(int i=0;i<9;i++){
			if(i%2==0){
				System.out.print(p1.name+" location >>>");
				int r=sc.nextInt(),c=sc.nextInt();
				while(r<1 || r>3 || c<1||c>3||tb.board[r-1][c-1]!=' '){
					System.out.println("Error:Dont try to mark at same place and pls give input in given range");
					r=sc.nextInt();c=sc.nextInt();
				}
				tb.board[r-1][c-1]=p1.symbol;
			}
			if(i%2!=0){
				System.out.print(p2.name+" location >>>");
				int r=sc.nextInt(),c=sc.nextInt();
				while(r<1 || r>3 || c<1||c>3||tb.board[r-1][c-1]!=' '){
					System.out.println("Error:Dont try to mark at same place and pls give input in given range");
					r=sc.nextInt();c=sc.nextInt();
				}
				tb.board[r-1][c-1]=p2.symbol;
			}
			tb.display_board();
			if(tb.check(p1.symbol)){
				System.out.println(p1.name+" Won");won=1;break outer;
			}
			if(tb.check(p2.symbol)){
				System.out.println(p2.name+" Won");won=1;break outer;
			}
		}
		if (won==0)
		System.out.println("<---Draw match--->");
	}
}

class tictacplayer{
	String name;
	char symbol;
	tictacplayer(String name,char symbol){
		this.name=name;
		this.symbol=symbol;
	}

}


class tictacboard{
	static char board[][]=new char[3][3];
	static char symbol;
	tictacboard(){
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board.length;j++)
				board[i][j]=' ';
	}
	static boolean check(char x){
		boolean hprev=true,vprev=true,dprev1=true,dprev2=true;
		for(int i=0;i<board.length;i++){
			hprev=vprev=true;
			for(int j=0;j<board.length;j++){
				if(board[i][j]!=x)
					hprev=false;
				if(board[j][i]!=x)
					vprev=false;
				if(i==j && board[i][j]!=x)
					dprev1=false;
				if(i==j && board[i][board.length-j-1]!=x)
					dprev2=false;
				if(j==board.length-1 && (vprev==true|hprev==true))
					return true;
			}
		}
		return dprev1|dprev2;
	}
	void display_board(){
		for(int j=0;j<board.length;j++){
			char []arr=board[j];
			System.out.print(" ");
			for(int i=0;i<arr.length;i++){
				System.out.print(arr[i]);
				if(i==arr.length-1)System.out.print("\n");
				if(i<arr.length-1)System.out.print("|");
			}
			if(j<board.length-1)System.out.println("__|_|__");
		}
		System.out.println("  | |  ");
	}
}
