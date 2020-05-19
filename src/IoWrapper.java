package IoThread;

import java.util.function.*;

public class IoWrapper {
	private String displayText;
	private boolean requireUserInput;
	private Consumer<String> consumer;


	/**
	* Creates an instance of the IoWrapper class, requiring user input
	*
	*@param text the String to be printed to the screen
	*@param consumer an implementation of java.util.function.Consumer{@literal <T>} whose .accept() function will be called on the user input
	*
	*/
	public IoWrapper(String text, Consumer<String> consumer) {
		this.setDisplayText(text);
		this.requireUserInput(true);
		this.setConsumer(consumer);
	}

	/**
	* Creates an instance of the IoWrapper class, not requiring user input
	*
	*@param text the String to be printed to the screen
	*
	*/
	public IoWrapper(String text) {
		this.setDisplayText(text);
		this.requireUserInput(false);
	}

	/**
	* Sets the value of this.displayText
	*
	*@param displayText the text to be printed to the screen
	*
	*/
	private void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	/**
	* Returns the String given to be displayed on the screen
	*
	*@return the String given to be displayed on the screen
	*
	*/
	public String getDisplayText() {
		return this.displayText;
	}

	/**
	* Sets a flag for whether this IO call requires user input
	*
	*@param requiresInput whether this IO call requires user input
	*
	*/
	private void requireUserInput(boolean requiresInput) {
		this.requireUserInput = requiresInput;	
	}

	/**
	* Returns whether this IO call requires user input
	*
	*@return true if this IO call requires user input, false otherwise
	*
	*/
	public boolean requiresUserInput() {
		return this.requireUserInput;
	}

	/**
	* Sets the implementation of Consumer {@literal <T>} to be called on the user input
	*
	*@param consumer implementation of Consumer {@literal <T>} to be called on the user input
	*
	*/
	private void setConsumer(Consumer<String> consumer) {
		this.consumer = consumer;
	}

	/**
	* If this IO call requiers user input, the given string is sent to the consumer
	*
	*@param text the String to be given to the consumer
	*
	*/
	public void consumeResponse(String text) {
		if (this.requiresUserInput()) {
			this.consumer.accept(text);
		}
	}

}
