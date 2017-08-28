package cscie55.hw5.bank;

public class DuplicateAccountException extends BankException
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateAccountException(int accountId)
    {
        super(String.format("Attempt to add a second account with id %d", accountId));
    }
}
