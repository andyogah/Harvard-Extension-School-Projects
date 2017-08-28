package cscie55.hw5.bank;

/**
 * CommandExecutionThread Java class
 * 
 * @author Andrew Ogah
 * @since 11-25-2015
 * @version 1.0
 */
import java.util.Queue;

import cscie55.hw5.bank.command.Command;

public class CommandExecutionThread extends Thread {

	private Bank bank;
	private Queue<Command> commandQueue;
	private boolean executeCommandInsideMonitor;

	/**
	 * constructor that initializes the CommandExecutionThread's state.
	 * 
	 */
	public CommandExecutionThread(Bank bank, Queue<Command> commandQueue, boolean executeCommandInsideMonitor) {
		
		this.bank = bank;
		this.commandQueue = commandQueue;
		this.executeCommandInsideMonitor = executeCommandInsideMonitor;
	}

	/**
	 * this method pulls Commands from the queue and executes them while the
	 * command queue is non-empty
	 */
	public void run() {
		
		while (true) {
			Command command;
			do {
				synchronized (commandQueue) {

					command = commandQueue.poll();
					if (command == null) {
						try {
							commandQueue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			} while (command == null); 

			// break if we get a stop command
			if (command.isStop())
				break;

			try {
				if (executeCommandInsideMonitor) {
					synchronized (commandQueue) {
						command.execute(bank);
					}
				} else {
					command.execute(bank);
				}
			} catch (InsufficientFundsException e) {
				// Do Nothing if you're broke
			}
		}

	}

	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	/**
	 * @return the commandQueue
	 */
	public Queue<Command> getCommandQueue() {
		return commandQueue;
	}

	/**
	 * @param commandQueue
	 *            the commandQueue to set
	 */
	public void setCommandQueue(Queue<Command> commandQueue) {
		this.commandQueue = commandQueue;
	}

	/**
	 * @return the executeCommandInsideMonitor
	 */
	public boolean isExecuteCommandInsideMonitor() {
		return executeCommandInsideMonitor;
	}

	/**
	 * @param executeCommandInsideMonitor
	 *            the executeCommandInsideMonitor to set
	 */
	public void setExecuteCommandInsideMonitor(boolean executeCommandInsideMonitor) {
		this.executeCommandInsideMonitor = executeCommandInsideMonitor;
	}

}
