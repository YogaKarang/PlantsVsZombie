// MAHASISWA 3
import java.io.FileWriter;
import java.io.IOException;

public class AutoSaveThread extends Thread {
    private final int SAVE_INTERVAL_MS = 1000;
    private volatile boolean running = true;
    private volatile int zombieDikalahkan = 0;

    public AutoSaveThread() {
        setDaemon(true);
    }

    public void updateStatus(int zombieDikalahkan) {
        this.zombieDikalahkan = zombieDikalahkan;
    }

    public void stopThread() {
        running = false;
        interrupt();
    }

    private void saveProgress() {
        try {
            FileWriter writer = new FileWriter("autosave.txt");
            writer.write("Zombie Dikalahkan : " + zombieDikalahkan);
            writer.close();
        } catch (IOException e) {
            System.out.println("[ERROR AUTOSAVE] " + e.getMessage());
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(SAVE_INTERVAL_MS);
                saveProgress();
            } catch (InterruptedException e) {
                if (!running) {
                break;
                }
            }
        }
        saveProgress();
    }
}