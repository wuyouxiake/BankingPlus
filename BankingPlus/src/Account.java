public class Account {
	private int account_num;
	private String account_name;
	
	
	
	public int getAccount_num() {
		return account_num;
	}
	public void setAccount_num(int account_num) {
		this.account_num = account_num;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public double getAccount_bal() {
		return account_bal;
	}
	public void setAccount_bal(double account_bal) {
		this.account_bal = account_bal;
	}
	private double account_bal;
	
	public double calbal_p(double ele){
		return account_bal=account_bal+ele;
	}
	public double calbal_m(double ele){
		account_bal-=ele;
		if(account_bal<0){
			account_bal-=35;
		}
		return account_bal;
	}
}