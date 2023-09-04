package atm;
import java.util.Scanner;
class AtmPrompt{
	String name;
	String accno;
	String pin;
	float bal = 100000;
	int tran = 0;
	String tranHistory = " ";
	public void register() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter your name:");
		this.name = sc.nextLine();
		System.out.println("\nEnter your account number:");
		this.accno = sc.nextLine();
		System.out.println("\nSet your 4 digit pin number:");
		this.pin = sc.nextLine();
		System.out.println("\nPIN set successfully");
		System.out.println("\nRegistration completed successfully");
	}
	public boolean login() {
		boolean isLog = false;
		System.out.println("\nPlease insert your card");
		Scanner sc = new Scanner(System.in);
		while(!isLog) {
			System.out.println("\nEnter your PIN:");
			String Pin1 = sc.nextLine();
			if(Pin1.equals(pin)) {
				System.out.println("\nLogin Successful");
				isLog = true;
			}
			else {
				System.out.println("\nIncorrect PIN.Please try again");
			}
		}
		return isLog;
	}
	public void withdraw() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter the amount to withdraw:");
		float amount = sc.nextFloat();
		try {
			if(bal >= amount) {
				tran++;
				bal = bal-amount;
				System.out.println("\nPlease collect your cash");
				String str = amount + "Rs. Withdrawn";
				tranHistory = tranHistory.concat(str);
			}
			else {
				System.out.println("\nInsufficient balance in your account");
			}
		}
		catch(Exception e) {
		}
	}
	public void deposit() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter the amount to deposit:");
		float amount = sc.nextFloat();
		try {
			if(amount <= 100000f) {
				tran++;
				bal = bal+amount;
				System.out.println("\nAmount deposited succesfully");
				String str = amount + "Rs. Deposited";
				tranHistory = tranHistory.concat(str);
			}
			else {
				System.out.println("\nDeposit limit is 100000.00");
			}
		}
		catch(Exception e) {
		}
	}
	public void transfer() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter recipient's account number:");
		String acn = sc.nextLine();
		System.out.println("\nEnter recipient's IFSC code:");
		String ifsc = sc.nextLine();
		System.out.println("\nEnter the amount to transfer:");
		float amount = sc.nextFloat();
		try {
			if(bal >= amount) {
				if(amount <= 60000f) {
					tran++;
					bal = bal-amount;
					System.out.println("\nSuccessfully transferred to "+acn);
					String str = amount + " Rs. transferred to " +acn;
					tranHistory = tranHistory.concat(str);
				}
				else {
					System.out.println("\nLimit is 60000");
				}
			}
			else {
				System.out.println("\nInsufficient balance in your account");
			}
		}
		catch(Exception e) {
		}
	}
	public void checkBal() {
		System.out.println(bal + "Rs.");
	}
	public void tranHistory() {
		if(tran == 0) {
			System.out.println("\nEmpty");
		}
		else {
			System.out.println("\n" + tranHistory);
		}
	}
}
public class AtmMain{
	public static int takeInput(int limit) {
		int input = 0;
		boolean flag = false;
		while(!flag) {
			try {
				Scanner sc = new Scanner(System.in);
				input = sc.nextInt();
				flag = true;
				if(flag && input>limit || input<0) {
					System.out.println("Selevt from the given " +limit);
					flag = true;
				}
			}
			catch(Exception e) {
				System.out.println("Enter only integer value");
				flag = false;
			}
		}
		return input;
	}
	public static void main(String args[]) {
		System.out.println("*****************************************************");
		System.out.println("*           WELCOME TO AURORA BANK ATM              *");
		System.out.println("*****************************************************");
		System.out.println("\n1.Register \n2.Exit");
		int choice = takeInput(2);
		if(choice == 1) {
			AtmPrompt A = new AtmPrompt();
			A.register();
			while(true) {
				System.out.println("\n1.Login \n2.Exit");
				System.out.println("Enter your choice:");
				int ch = takeInput(2);
				if(ch == 1) {
					if(A.login()) {
						System.out.println("\n***********HELLO!  " +A.name+ "*************");
						boolean isComplete = false;
						while(!isComplete) {
							System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
							System.out.println("\nEnter your choice:");
							int ch1 = takeInput(6);
							switch(ch1) {
							case 1:
								A.withdraw();
								break;
								
							case 2:
								A.deposit();
								break;
								
							case 3:
								A.transfer();
								break;
								
							case 4:
								A.checkBal();
								break;
								
							case 5:
								A.tranHistory();
								break;
								
							case 6:
								isComplete = true;
								break;
							}
						}
					}
				}
				else {
					System.exit(0);
				}
			}
		}
		else {
			System.exit(0);
		}
	}

}