import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable {
	private String command;

	public WorkerThread(String s) {
		this.command = s;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
		processCommand();
		System.out.println(Thread.currentThread().getName() + " End.");
	}

	private void processCommand() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return this.command;
	}
}

public class ThreadSimpleThreadPool {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 3; i++) {
			Runnable worker = new WorkerThread("Job : " + i);
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}
}
