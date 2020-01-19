package thread;

import exception.CustomException;

public class ThreadMain {
	private Long totalThreads;
	private Long queueSize;
	private Long TaskCount;
	private ThreadPool pool;
	private static final Integer MAX_TASK_PER_THREAD = 10000;
	private static final Integer THREAD_QUEUE_FACTOR = 1;

	public Long getTotalThreads() {
		return totalThreads;
	}



	public Long getQueueSize() {
		return queueSize;
	}


	public Long getTaskCount() {
		return TaskCount;
	}


	public ThreadPool getPool() {
		return pool;
	}

	public void setPool(Long taskCount) throws CustomException {
		if (taskCount > 0) {
		this.TaskCount = taskCount;
		this.totalThreads = this.TaskCount / MAX_TASK_PER_THREAD;
		this.queueSize = this.totalThreads - THREAD_QUEUE_FACTOR ;
		pool = new ThreadPool(queueSize.longValue(), totalThreads.longValue());
		}else {
			throw new CustomException("Task Count is less than 1, Cannot create Pool ");
		}
	}

	public void Execute() throws CustomException {

		if (this.TaskCount > 1) {
			for (Long taskNumber = 1L; taskNumber <= this.TaskCount; taskNumber++) {
				CustomTaskExecutor task = new CustomTaskExecutor(taskNumber);
				try {
					this.pool.submitTask(task);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println("Done");
		} else {
			throw new CustomException("No Task Submitted to process");
		}

	}
}
