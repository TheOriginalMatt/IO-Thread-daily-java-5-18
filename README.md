# IO Thread
## Daily Java Project for: 5/18/2020

### Description:

A thread that handles interacting with the user

### Daily Java Projects
I'm creating a small Java project each day to help me learn Java. These aren't rigorously tested, but instead exist just so I can get a better understanding of some portion of Java.

### Points of Interest

### Compiling

There is a build file using ant, which can be run by `$ant` and running `Parent.class` in `/build`, or you can go into `./src` and compile it by hand

### Using `IoThread`
Example - Printing a two strings to the screen, one requiring user input and one not.

```
import java.util.function.*;
import java.util.*;

public class Parent {
	public static void main(String args[]) {
		IoThread i = new IoThread();

		Consumer<String> c = x -> System.out.println(x);

		i.start();

		i.addIo("This is a printed line, no input required!\n");
		i.addIo("This line requiers input:", c);

		i.stop();


	}
}

```

Example - Storing the user responses in a queue to be used later. Change the `Consumer` and add `HandleInput` as follows

```
class C implements Consumer<String> {
	public void accept(String str) {
		HandleInput h = new HandleInput();
		h.appendResponse(str);
	}
}

class HandleInput {

	private static LinkedList<String> responses = new LinkedList<String>();

	public void appendResponse(String str) {
		this.responses.add(str);
	}

	public void printResponses() {
		ListIterator itr = this.responses.listIterator();

		while(itr.hasNext()) {
			System.out.println(itr.next());
			itr.remove();
		}
	}
}
```

You can then print the user inputs by running `new HandleInput().printResponses();`

### Documentation

No more handwritten documentation for me!

[IoThread](https://theoriginalmatt.github.io/IO-Thread-daily-java-5-18/IoThread.html)

[IoWrapper](https://theoriginalmatt.github.io/IO-Thread-daily-java-5-18/IoWrapper.html)