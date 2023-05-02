package ui;
import java.util.Scanner;
import model.Controller;
public class Main{ 
	private Scanner reader;
	private Controller controller;
	Main(){
		reader=new Scanner(System.in);
		controller=new Controller();
	}
	public static void main(String args[]){
		Main exe=new Main();
		exe.menu();


	}
	public void menu(){
		boolean execute=true;
		int option;
		while(execute){
			System.out.println("Type a number");
			System.out.println("1 to register");
			option=reader.nextInt();
			switch (option) {
				case 1:
					addUser();
					break;
				case 2:
					System.out.println("Nose");
					break;
				default:
					execute=false;
			}
			reader.nextLine();
		}


	}
	public void addUser(){
		String name="";
		String id="";
		System.out.println("Type your name");
		name=reader.next();
		System.out.println("Type your id");
		id=reader.next();
	}


}