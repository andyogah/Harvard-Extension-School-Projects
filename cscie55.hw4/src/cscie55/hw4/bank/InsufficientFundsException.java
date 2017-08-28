package cscie55.hw4.bank;

public class InsufficientFundsException extends Exception
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
