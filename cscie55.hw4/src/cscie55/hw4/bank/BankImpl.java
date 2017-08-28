package cscie55.hw4.bank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankImpl implements Bank{
	
	 // A Map interface specifying the keys, values and the key-value pairs
    Map<Integer,Account> currentAccounts = new ConcurrentHashMap<Integer,Account>();
    
    
    /**
   	 * 
   	 * this method adds an account to number of existing account  
   	 * It also throws Duplicate Account Exception 
   	 * 
   	 * @param account
   	 */
    @Override
    public void addAccount(Account account) throws DuplicateAccountException {
        if(currentAccounts.containsKey(account.id())){
            throw new DuplicateAccountException(account.id());
        }
               
       currentAccounts.put(Integer.valueOf(account.id()), account);
    }

    

    /**
	 * 
	 * this method transfer amount between Ids that have not been locked  
	 * It also throws Insufficient Fund Exception 
	 * 
	 * @param fromId, toId, amount
	 */
    @Override
    public void transferWithoutLocking(int fromId, int toId, long amount)
            throws InsufficientFundsException {
        
        currentAccounts.get(fromId).withdraw(amount);
        currentAccounts.get(toId).deposit(amount);
            }

    
    /**
	 * 
	 * this method transfer amount between Ids within a bank that has been locked  
	 * It also throws Insufficient Fund Exception 
	 * 
	 * @param fromId, toId, amount
	 */
    @Override
    public void transferLockingBank(int fromId, int toId, long amount)
            throws InsufficientFundsException {
        
        synchronized(this){
            currentAccounts.get(fromId).withdraw(amount);
            currentAccounts.get(toId).deposit(amount);
        }

    }

    
    /**
	 * 
	 * this method transfer amount between account that are Locked 
	 * It also throws Insufficient Fund Exception 
	 * 
	 * @param fromId, toId, amount
	 */
    @Override
    public void transferLockingAccounts(int fromId, int toId, long amount)
            throws InsufficientFundsException {
        
        Account fromAccount = currentAccounts.get(fromId);
        Account toAccount   = currentAccounts.get(toId);
        
        synchronized(fromAccount){
            fromAccount.withdraw(amount);
        }
        synchronized(toAccount){
            toAccount.deposit(amount);
        }

    }

    
    /**
	 * @return the total balance of the accounts 
	 */
    @Override
    public long totalBalances() {

        long total = 0;
        for(Account account : currentAccounts.values()){
            total += account.balance();
        }
        return total;    
            
    }

    /**
	 * @return the number of accounts currently in the back
	 */
    @Override
    public int numberOfAccounts() {
        return currentAccounts.keySet().size();
    }

}
	
	
	
	