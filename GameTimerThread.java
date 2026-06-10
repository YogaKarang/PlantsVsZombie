// MAHASISWA 3
public class GameTimerThread extends Thread {
    private volatile boolean running = true;
    private Integer detik = 0;

    public int getDetik() {
        return detik;
    }

    public void stopThread() {
        running = false;
        interrupt();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
                detik++;
            } catch (InterruptedException e) {
                if (!running) {
                break;
                }
            }
        }
    }
}