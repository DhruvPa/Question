package thread;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<Type> {
	private Queue<Type> queue = new LinkedList<Type>();
	private Long EMPTY = 0L;
	private Long MAX_TASK_IN_QUEUE = -1L;

	public BlockingQueue(Long size) {
		this.MAX_TASK_IN_QUEUE = size;
	}

	public synchronized void enqueue(Type task) throws InterruptedException {
		while (this.queue.size() == this.MAX_TASK_IN_QUEUE) {
			wait();
		}
		if (this.queue.size() == EMPTY) {
			notifyAll();
		}
		this.queue.offer(task);
	}

	public synchronized Type dequeue() throws InterruptedException {
		while (this.queue.size() == EMPTY) {
			wait();
		}
		if (this.queue.size() == this.MAX_TASK_IN_QUEUE) {
			notifyAll();
		}
		return this.queue.poll();
	}
}