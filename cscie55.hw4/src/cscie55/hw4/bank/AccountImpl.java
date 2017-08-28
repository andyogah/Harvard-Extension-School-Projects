package cscie55.hw4.bank;

public class AccountImpl implements Account {

	private long balance = 0;    // balance of an account
    private final int id;        // Identification number for account
    
          

	/**
	 * constructor that initializes the elevator's state.
	 * 
	 * @param Id
	 *           
	 */
    public AccountImpl(int id){
        this.id = id; 
    }
    
        
    /**
   	 * 
   	 * this method return the Id of an account 
   	 *  
   	 */
    @Override
    public int id() {
        return id;
    }

    /**
   	 * 
   	 * this method return the balance of an account 
   	 *  
   	 */
    @Override
    public long balance() {
        return balance;
    }
    
    /**
   	 * 
   	 * this method deposits an amount to the account 
   	 * It also throws Illegal Argument Exception if amount depositing is out of the ordinary
   	 * 
   	 * @param amount
   	 */
    @Override
    public void deposit(long amount) {
        if(amount <= 0){
            throw new IllegalArgumentException(
                    String.format("%s is an invalid value for a Deposit. Deposit should be "
                    		+ "other than 0 or Negative Number", amount) );
        }
        
        balance += amount;
        
        //long is signed, if the balance overflows we need to notify
        if(balance < 0){
            throw new RuntimeException("Overflow Detected on Account: " + this.id());
        }
        
    }

    /**
	 * 
	 * this method withdraws an amount from the account 
	 * It also throws Insufficient Fund Exception 
	 * 
	 * @param amount
	 */
    @Override
    public void withdraw(long amount) throws InsufficientFundsException {
    	
    	    if(amount <= 0){
            throw new IllegalArgumentException(
                    String.format("%s is an invalid value for a Withdrawal. Withdrawal should be "
                    		+ "other than 0 or Negative Number", amount) );
        }
        
        
        if(balance - amount < 0 ){
            throw new InsufficientFundsException(this, amount);
        }
        balance -= amount;
       
        
    }
}
