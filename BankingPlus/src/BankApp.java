import java.util.*;
import java.util.Map.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BankApp {
	public static void main(String[] args) throws ParseException, IOException {
		Scanner in = new Scanner(System.in);
		int number;
		String name;
		double bal = 0;
		// Create a hashmap to store account info.
		HashMap<Integer, Account> map = new HashMap<Integer, Account>();
		HashMap<Integer, Transaction> map2 = new HashMap<Integer, Transaction>();

		// Read from file myText.txt
		try {
			File file = new File(
					"C:\\Users\\rvhu321005ur\\workspace\\BankingPlus\\src\\myText.txt");
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] array = line.split(",");
				Account temp_acct_from_file = new Account();
				int temp_num = Integer.parseInt(array[0]);
				String temp_name = array[1];
				double temp_bal = Double.parseDouble(array[2]);

				temp_acct_from_file.setAccount_num(temp_num);
				temp_acct_from_file.setAccount_name(temp_name);
				temp_acct_from_file.setAccount_bal(temp_bal);
				map.put(temp_num, temp_acct_from_file);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// test reading from file:
		//System.out.println(map.get(8001).getAccount_name());

		// Adding accounts to Hashmap.
		int choice;
		System.out.println("Welcome to Evil Corp Savings and Loan");
		System.out.println("Please create the user account(s)");
		System.out.println("******************************************************");
		choice = Validator.getInt(in,
				"Enter an account # or 0 to stop entering accounts : ");
		while (choice != 0) {
			if (!map.containsKey(choice)) {
				number = choice;
				name = Validator.getString(in, "Enter the name for account# "
						+ number);
				bal = Validator.getDouble(in, "Enter the balance for account# "
						+ number);
				Account acct = new Account();
				acct.setAccount_num(number);
				acct.setAccount_name(name);
				acct.setAccount_bal(bal);
				map.put(number, acct);
				// List the current accounts and its balance.
				System.out.println("The balance of account# " + number + " is "
						+ bal + ".");
				System.out.println("The account holder is " + name);
				System.out.println("---------------------------------------------------------");
				choice = Validator.getInt(in,
						"Enter an account # or 0 to stop entering accounts : ");
			} else {
				System.out.println("Account already exist!");
				choice = Validator.getInt(in,
						"Enter an account # or 0 to stop entering accounts : ");
			}
		}
		// Start to input a transaction
		String type = null;
		String choice2;
		int number2;
		double amount = 0;
		int i = 0;
		choice2 = Validator
				.getType(
						in,
						"Enter a transaction type (Check, Debit card, Deposit or Withdrawal) or q to finish :");

		while (!choice2.equalsIgnoreCase("q")) {
			if (choice2.equalsIgnoreCase("c")) {
				type = "Check";
			} else if (choice2.equalsIgnoreCase("dc")) {
				type = "Debit Card";
			} else if (choice2.equalsIgnoreCase("d")) {
				type = "Deposit";
			} else if (choice2.equalsIgnoreCase("w")) {
				type = "Withdrawal";
			}
			boolean isValid = true;
			number2 = Validator.getInt(in, "Enter the account # :");
			while (isValid == true) {
				if (!map.containsKey(number2)) {
					System.out.println("Account does not exist!");
					isValid=true;
					number2 = Validator.getInt(in, "Enter the account # :");
				} else {
					isValid=false;
				}
			}
			amount = Validator.getDouble(in, "Enter the amount of the "
					+ type + " :");
			System.out.println("Enter the date of the " + type + " :");
			String aaa = in.next();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String cunvertCurrentDate = aaa;
			Date date = new Date();
			date = df.parse(cunvertCurrentDate);
			// end input
			// Calculate balance
			if (type.equalsIgnoreCase("Deposit")) {
				map.get(number2).calbal_p(amount);
			} else {
				map.get(number2).calbal_m(amount);
			}

			System.out.println("The balance of account# " + number2 + " is "
					+ map.get(number2).getAccount_bal() + ".");
			System.out.println("The account holder is "
					+ map.get(number2).getAccount_name());
			System.out.println("---------------------------------------------------------");
			// Add to transaction
			Transaction trac = new Transaction();
			trac.setAcct_num(number2);
			trac.setTransaction_type(type);
			trac.setAmount(amount);
			trac.setDate(date);
			map2.put(i, trac);
			i++;
			choice2 = Validator.getType(in,"Enter a transaction type (Check, Debit card, Deposit or Withdrawal) or q to finish :");

		}
		/*
		 * // sort transaction map. int j; Transaction temp = new Transaction();
		 * for (j = 0; j < i; j++) { for (int z = 1; z < (i - j); z++) { double
		 * aa = getElapsedDay(map2.get(z - 1).getDate()); double bb =
		 * getElapsedDay(map2.get(z).getDate()); if (aa > bb) { // swap the
		 * elements! temp = map2.get(z - 1); map2.put(z - 1, map2.get(z));
		 * map2.put(z, temp); } } }
		 * 
		 * // Operating on accounts.2 int x, y, acctnum;
		 * 
		 * for (x = 0; x < i; x++) { if
		 * (map2.get(x).getTransaction_type().equalsIgnoreCase("Deposit")) {
		 * acctnum = map2.get(x).getAcct_num();
		 * map.get(acctnum).calbal_p(map2.get(x).getAmount()); } else { acctnum
		 * = map2.get(x).getAcct_num();
		 * map.get(acctnum).calbal_m(map2.get(x).getAmount()); } }
		 */
		// Empty myText.txt
		FileWriter fileOut = new FileWriter("C:\\Users\\rvhu321005ur\\workspace\\BankingPlus\\src\\myText.txt");
		fileOut.write("");
		fileOut.close();

		// Write the whole account map to myText.txt
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File("C:\\Users\\rvhu321005ur\\workspace\\BankingPlus\\src\\myText.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist!");
		} finally {
			Iterator<Integer> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				int key = iterator.next();
				// String value = map.get(key).toString();
				int temp_num = map.get(key).getAccount_num();
				String temp_name = map.get(key).getAccount_name();
				double temp_bal = map.get(key).getAccount_bal();
				writer.println(temp_num + "," + temp_name + "," + temp_bal);
			}
		}
		writer.close();

		// print account and transaction.
		System.out.println("********************************************");
		for (Entry<Integer, Account> entry : map.entrySet()) {
			Integer key = entry.getKey();
			double value = entry.getValue().getAccount_bal();
			System.out.println("The balance of account# " + key + " is "
					+ value);
			System.out.println();
		}
		System.out.println("********************************************");
		for (Entry<Integer, Transaction> entry : map2.entrySet()) {
			Integer num = entry.getValue().getAcct_num();
			String type1 = entry.getValue().getTransaction_type();
			double amount1 = entry.getValue().getAmount();
			Date date = entry.getValue().getDate();

			System.out.println("Date: " + date);
			System.out.println("Transaction type:  " + type1);
			System.out.println("Account# " + num);
			System.out.println("Amount: " + amount1);
			System.out.println();
		}
		String choice3 = Validator.getString(in,"Do you want to close an account?(y/n)");

		while (choice3.equalsIgnoreCase("y")) {
			int acct_n = Validator.getInt(in,"Please enter the account number: ");
			double bal3 = map.get(acct_n).getAccount_bal();
			if (bal3 == 0) {
				map.remove(acct_n);
				System.out.println("Account# " + acct_n + " is removed!");
			} else {
				System.out.println("The balance of account# " + acct_n + " is "
						+ bal3 + " , cannot be removed!");
			}
			choice3 = Validator.getString(in,"Do you want to close an account?(y/n)");
		}
		System.out.println("Welcome back!");
	}

	public static double getElapsedDay(Date aaa) {
		double elapsedDay;
		Calendar cal = Calendar.getInstance();
		cal.setTime(aaa);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		// System.out.println(year +" "+ (month)+" "+day );
		GregorianCalendar d = new GregorianCalendar(year, month, day);
		GregorianCalendar s = new GregorianCalendar(1970, 1, 1);
		Date start = s.getTime();
		long startDateMS = start.getTime();
		Date end = d.getTime();
		long endDateMS = end.getTime();
		long elapsedMS = endDateMS - startDateMS;
		elapsedDay = elapsedMS / (24 * 60 * 60 * 1000);
		return elapsedDay;
	}
}