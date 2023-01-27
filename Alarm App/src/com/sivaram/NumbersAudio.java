package audio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class NumbersAudio {

	public static void main(String[] args) {
		NumbersMp3 num = new NumbersMp3();
		num.fun();
	}
}
class NumbersMp3
{
	private Scanner scanner = new Scanner(System.in);
	protected void fun()
	{
		System.out.println("Enter the number...");
		String number = scanner.next();
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(number);
		while(! matcher.matches())
		{
			System.out.println("Invalid number..Please enter valid number");
			number = scanner.next();
			matcher = pattern.matcher(number);
		}
		
		for(int i=0;i<number.length();i++)
		{
			checkNumber(number.charAt(i));
		}
	}
	private void checkNumber(char number)
	{
		FileInputStream fis = null;//new FileInputStream("C:\\Users\\saran\\Music\\Hope-Xxxtentacion.mp3");
	    Player playMP3 = null;
	    switch (number) {
		case '0':
			palyAudio(number, "zero", fis, playMP3);
			break;
		case '1':
			palyAudio(number, "one", fis, playMP3);
			break;
		case '2':
			palyAudio(number, "two", fis, playMP3);
			break;
		case '3':
			palyAudio(number, "three", fis, playMP3);
			break;
		case '4':
			palyAudio(number, "four", fis, playMP3);
			break;
		case '5':
			palyAudio(number, "five", fis, playMP3);
			break;
		case '6':
			palyAudio(number, "six", fis, playMP3);
			break;
		case '7':
			palyAudio(number, "seven", fis, playMP3);
			break;
		case '8':
			palyAudio(number, "eight", fis, playMP3);
			break;
		case '9':
			palyAudio(number, "nine", fis, playMP3);
			break;
		default:
			break;
		}
	}
	private void palyAudio(char num, String word, FileInputStream fis, Player playMp3)
	{
		try {
			fis = new FileInputStream("C:\\Users\\saran\\Music\\numbers\\"+num+".mp3");
			playMp3 = new Player(fis);
			playMp3.play();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File problem...");
		} catch (JavaLayerException e) {
			e.printStackTrace();
			System.out.println("Player problem...");
		}
		if(playMp3.isComplete())
		{
			System.out.print(" "+word+" ");
			return;
		}
	}
}