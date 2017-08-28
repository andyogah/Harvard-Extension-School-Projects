package cscie55.hw5.bank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankImpl implements Bank {

	// Maps are atomic objects, no need to "sync" operations specific to Map
	Map<Integer, Account> existingAccounts = new ConcurrentHashMap<Integer, Account>();

	@Override
	public void addAccount(Account account) throws DuplicateAccountException {
		if (existingAccounts.containsKey(account.id())) {
			throw new DuplicateAccountException(account.id());
		}

		existingAccounts.put(Integer.valueOf(account.id()), account);
	}

	/*
	 * 
	 * @Override public void transferWithoutLocking(int fromId, int toId, long
	 * amount) throws InsufficientFundsException {
	 * 
	 * existingAccounts.get(fromId).withdraw(amount);
	 * existingAccounts.get(toId).deposit(amount);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Override public void transferLockingBank(int fromId, int toId, long
	 * amount) throws InsufficientFundsException {
	 * 
	 * synchronized(this){ existingAccounts.get(fromId).withdraw(amount);
	 * existingAccounts.get(toId).deposit(amount); }
	 * 
	 * }
	 */

	public void deposit(int accountId, long amount) {
		Account account = existingAccounts.get(accountId);

		synchronized (account) {
			account.deposit(amount);
		}

	}

	@Override
	public void transfer(int fromId, int toId, long amount) throws InsufficientFundsException {

		Account fromAccount = existingAccounts.get(fromId);
		Account toAccount = existingAccounts.get(toId);

		synchronized (fromAccount) {
			fromAccount.withdraw(amount);
		}
		synchronized (toAccount) {
			toAccount.deposit(amount);
		}

	}

	@Override
	public long totalBalances() {

		long total = 0;
		for (Account account : existingAccounts.values()) {
			total += account.balance();
		}
		return total;

	}

	public int numberOfAccounts() {
		return existingAccounts.keySet().size();
	}

}
