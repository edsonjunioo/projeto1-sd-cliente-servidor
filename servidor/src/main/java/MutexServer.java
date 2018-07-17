import sun.awt.Mutex;

public abstract class MutexServer extends Thread  {
    private Mutex mutex;

    public MutexServer() {
        this.mutex = new Mutex();
    }

    public void Lock() {
        this.mutex.lock();
    }

    public void Unlock() {
        this.mutex.unlock();
    }
}