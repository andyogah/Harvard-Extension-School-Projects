package cscie55.hw5.bank;

public abstract class BankException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected BankException(String message)
    {
        super(message);
    }
}
