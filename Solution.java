import java.util.Scanner;

class BowlingGame{
	
	private boolean gameStatus = true;
	boolean[] strike = new  boolean[21];
	//keeping the scores in a 2D array
	int[][] scores = new int[10][2];
	int bonus = 0;
	int index = 0;
	//int[][] scores = {{10,0},{10,0},{9,0},{0,0},{0,0},{0,0},{0,0},{0,0},{10,0},{10,10}};//debug
	public void roll(int numPinsKnockedOut){
		int pos = index%2;
		int row = index/2;
		scores[row][pos]=numPinsKnockedOut;
	}
	public int getScore(){
		int sum = 0;
		//results for each frame is stored in a 1D array
		int[] frame = new int[10];
		
		for(int i=0;i<scores.length;i++){
			//when a strike is made and it is not the frame before the last frame
			if(scores[i][0]==10&&i<8){
				frame[i]+=scores[i][0];
				int scoresAfter = 1;
				int r = i+1;
				
				while(scoresAfter<3){
					frame[i]+=scores[r][0];
					if(scores[r][0]==10){
						r=r+1;
						scoresAfter++;
					}else{
						
						scoresAfter++;
						if(scoresAfter==3)break;
						frame[i]+=scores[r][1];
						scoresAfter++;
					}
				}
			}else if(scores[i][0]==10&&i==8){// it is the frame before the last one gets a strike
				frame[i]+=scores[i][0]+scores[9][0]+scores[9][1];
				
			}else if(i<9){// if a spare happens
				if(scores[i][0]+scores[i][1]==10){
					frame[i]+=scores[i][0]+scores[i][1]+scores[i+1][0];
				}else{
					frame[i]+=scores[i][0]+scores[i][1];
				}
			}else{
				frame[i]+=scores[i][0]+scores[i][1]+bonus;
			}
			
		}
//calculating for each frame
		for(int i=0;i<frame.length;i++){
			sum += frame[i];
			System.out.println(frame[i]);
		}
		return sum;
	}
	public void setStatus(boolean v){
		gameStatus = v;
	}
	public boolean getStatus(){
		return gameStatus;
	}
	
	public boolean isFinished(){
		return gameStatus;
	}
}
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BowlingGame game = new BowlingGame();
		Scanner in =new Scanner(System.in);
		boolean bonus=false;
		System.out.println("Starting the game");
		game.setStatus(false);
		System.out.println("1.Enter the number of pins knocked");
		System.out.println("2.Check the status of the game");
		System.out.println("3.Check the score");
		//int count=0;
		while(true){
			int numPins = 0;
			System.out.print("Your Choice: ");
			int userChoice = Integer.parseInt(in.nextLine());
			if(userChoice==1){
				if(game.index==21){
				
					System.out.println("The game has ended. No more rolls can be entered");
					continue;
				}
				if(game.index<20){
					System.out.print("Enter your number for the "+(game.index/2+1)+"th frame: ");
					numPins = Integer.parseInt(in.nextLine());
				}
				if(game.index==20){
					if(bonus){
						System.out.print("Enter your number for the bonus: ");
						numPins = Integer.parseInt(in.nextLine());
						game.bonus = numPins;
					}
					
					game.index++;
					game.setStatus(true);
					continue;
				}else if(numPins>10){
					System.out.println("Incorrect input. Try again");
					continue;
				}
				if(game.index<18&&numPins==10){
					if(game.index%2!=0){
						System.out.println("Check your scores.. exiting program due to faulty input");
						return;
					}else{
						game.roll(numPins);
						game.index++;
					}
				}else if(game.index>=18&&numPins==10){
					bonus = true;
					game.roll(numPins);
				}else{
					if(game.index%2==1){
						int i = game.index-1;
						if(game.index<18){
							if(game.scores[i/2][i%2]+numPins>10){
								System.out.println("Check your scores.. exiting program due to faulty input");
								return;
							}
						}else{
							if(game.scores[i/2][i%2]!=10&&game.scores[i/2][i%2]+numPins>10){
								System.out.println("Check your scores.. exiting program due to faulty input");
								return;
							}else if(game.scores[i/2][i%2]!=10&&game.scores[i/2][i%2]+numPins==10){
								if(game.index>=18)
								bonus = true;
							}
						}
						
					}
					game.roll(numPins);
				}
				if(game.index==19&&bonus==false){
					game.index++;
				}
				game.index++;
			}else if(userChoice==2){
				if(!game.getStatus())
					System.out.println("The game has not ended yet");
				else
					System.out.println("Game ended");
			}else if(userChoice==3){
				System.out.println("The final score is: "+game.getScore());
				System.exit(0);
			}else{
				System.out.println("Invalid input. Try again");
			}
		}
	}
}
			
			
		