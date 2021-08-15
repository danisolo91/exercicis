package domain;

public class MediaPlayerState {

	private int counter;
	private MediaPlayerStatus status;
	private boolean running;

	public MediaPlayerState() {
		counter = 0;
		status = MediaPlayerStatus.STOP;
		running = true;
	}

	public synchronized void incrementCounter() {
		counter = counter + 1;
	}

	public synchronized int getCounter() {
		return counter;
	}

	public synchronized void setCounter(int counter) {
		this.counter = counter;
	}

	public synchronized boolean isRunning() {
		return running;
	}

	public synchronized void setRunning(boolean running) {
		this.running = running;
	}

	public synchronized MediaPlayerStatus getStatus() {
		return status;
	}

	public synchronized void setStatus(MediaPlayerStatus status) {
		this.status = status;
	}
}
