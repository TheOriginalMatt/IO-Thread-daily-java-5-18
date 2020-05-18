# IO Thread
## Daily Java Project for: 5/18/2020

### Description:

A thread that handles interacting with the user

### Daily Java Projects
I'm creating a small Java project each day to help me learn Java. These aren't rigorously tested, but instead exist just so I can get a better understanding of some portion of Java.

### Points of Interest

+ It was an interesting problem storing IO calls, and a Queue with my own `IoWrapper` class did, at least in my opionion, a great job.
+ The harder problem though was how to handle the user input since you'd have to make the main thread wait for inputs, which inheritely is tricky on multi-threaded projects. But with the use of the `Consumer<T>` interface and using a static Queue, I think it has all the functionality if it wasn't implemented in its own thread.
+ This project was also the worst of all the daily Java projects I've done so far with reguards to lack of planning, while I had an idea of how to handle storing IO calls, I figured out how to handle the user input on the fly, requiring more than one rewrite of a noteable portion of my code. I think from this point forward I'll have to spend more time designing the code before I jump into writing it.

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