package IoThread;

import java.util.*;
import java.util.function.*;

public class IoThread implements Runnable {

	private static LinkedList<IoWrapper> IoQueue = new LinkedList<IoWrapper>();

	private Scanner inputScanner = new Scanner(System.in);

	private static Thread t;
	private String threadName = "IOThread";

	private static boolean run = true;

	/**
	* Handles displaying the output to the user and getting input from the user
	*
	*/
	public void run() {
		while (this.run) {

			IoWrapper currentIo = this.IoQueue.poll();

			while (currentIo != null) {
				System.out.print(currentIo.getDisplayText());

				if (currentIo.requiresUserInput()) {
					currentIo.consumeResponse(this.inputScanner.nextLine());
				}

				currentIo = this.IoQueue.poll();
			}

		}
	}

	/**
	* Initialzies the thread and calls Thread.start()
	*
	*/
	public void start() {
		if (this.t == null) {
			this.t = new Thread(this, this.threadName);
			this.t.start();
		}
	}

	/**
	* Waits for all the currently added IO to be run, then stops the thread
	*
	*/
	public void stop() {
		this.run = false;
	}

	/**
	*	Adds an IO call to the queue, which will eventually print out the given String, but wont ask for user input
	*
	*@param text the String to be printed to the screen
	*
	*/
	public void addIo(String text) {

		this.IoQueue.add(new IoWrapper(text));

	}

	/**
	* Adds an IO call to the queue, which will eventually print out the given String and will ask for user input
	*
	*@param text the String to be printed to the screen
	*@param consumer an implementation of java.util.function.Consumer{@literal <T>} whose .accept() function will be called on the user input
	*
	*/
	public void addIo(String text, Consumer<String> consumer) {
		this.IoQueue.add(new IoWrapper(text, consumer));
	}
}