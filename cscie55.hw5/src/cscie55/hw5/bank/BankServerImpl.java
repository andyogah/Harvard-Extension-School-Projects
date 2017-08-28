package cscie55.hw5.bank;

/**
 * BankServerImpl Java class
 * 
 * @author Andrew Ogah
 * @since 11-25-2015
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import cscie55.hw5.bank.command.Command;
import cscie55.hw5.bank.command.CommandStop;

public class BankServerImpl implements BankServer {

	private final int threadCount; // keeps count of all the threads

	private Queue<Command> commandQueue = new LinkedList<Command>(); // commands
																		// will
																		// reside
																		// in
																		// this
																		// queue
																		// after
																		// being
																		// submitted
																		// by
																		// the
																		// execute
																		// method
																		// until
																		// they
																		// are
																		// removed
																		// from
																		// the
																		// queue
																		// and
																		// executed
																		// by a
																		// CommandExecutionThread
	private ArrayList<CommandExecutionThread> workerThreads = new ArrayList<CommandExecutionThread>(); // this
																										// threads
																										// do
																										// the
																										// actual
																										// work
																										// of
																										// the
																										// server,
																										// pulling
																										// Commands
																										// off
																										// the
																										// queue
																										// and
																										// executing
																										// them
	private Bank bank;

	/**
	 * constructor that initializes the BankServerImpl's state.
	 * 
	 */
	public BankServerImpl(Bank bank, int threadCount, boolean isBlockedExecuted) {

		this.bank = bank;
		this.threadCount = threadCount;

		// init thy threads
		for (int i = 0; i < threadCount; i++) {
			workerThreads.add(new CommandExecutionThread(bank, commandQueue, isBlockedExecuted));
		}

		// start your engines
		for (CommandExecutionThread thread : workerThreads) {
			thread.start();// FindBug keeps throwing a bug here. I am not sure
							// it's a good practice to start a thread at same
							// time an object is
							// being created. My research on this pointed to
							// starting the thread outside the constructor in a
							// method. There are
							// other ways to start the thread preventing this
							// bug. However, doing that will be against the
							// requirement of this
							// homework that specifically stated we should
							// create and start the thread in the constructor.
							// So, i am leaving this as
							// it is.
		}

	}

	/**
	 * this method adds a Command to the command queue
	 */
	@Override
	public void execute(Command command) {
		// This simply adds the given Command to the queue of commands

		synchronized (commandQueue) {
			commandQueue.add(command);
			commandQueue.notifyAll();
		}
	}

	/**
	 * this method adds a Stop command to the queue for each thread and cause
	 * each CommandExecutionThread to terminate
	 */
	@Override
	public void stop() throws InterruptedException {

		synchronized (commandQueue) {
			for (int i = 0; i < threadCount; i++) {
				commandQueue.add(new CommandStop());
			}
			commandQueue.notifyAll();
		}

		for (CommandExecutionThread thread : workerThreads) {
			thread.join();
		}

	}

	/**
	 * @return the total balance of the bank using the Bank.totalBalance()
	 */
	@Override
	public long totalBalances() {
		// invoking Bank.totalBalances()
		return bank.totalBalances();
	}

}
