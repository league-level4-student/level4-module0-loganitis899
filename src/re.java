import java.util.Random;

public class re {
	public static void main(String[] args) {
		
		for(int i=0; i<250;i++) {
			Random rand = new Random();
			int randy=rand.nextInt(20);
			if(randy==4000) {
				System.out.println("1");
				System.exit(0);
			} else {
				System.out.println("0");
			}
		}
	}

}
