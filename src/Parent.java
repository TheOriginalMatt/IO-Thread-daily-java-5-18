import java.util.function.*;
import java.util.*;

public class Parent {
	public static void main(String args[]) {
		IoThread i = new IoThread();

		// Consumer<String> c = x -> Parent.printString(x);

		i.start();

		i.addIo("This is a printed line, no input required!\n");
		i.addIo("This line requiers input:", new C());

		i.stop();

		try {
			Thread.sleep(1000);
		} catch (Exception e) { e.printStackTrace(); }

		new HandleInput().printResponses();

	}
}

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