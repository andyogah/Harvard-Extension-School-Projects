package cscie55.hw5.bank;

public class InsufficientFundsException extends BankException
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException(Account account, long withdrawal)
    {
        super(String.format("Attempt to withdraw %d from %s", withdrawal, account));
    }
}
