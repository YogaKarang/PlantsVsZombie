// Mahasiswa 1 = OOP
// Mahasiswa 2 = Exception
// Mahasiswa 3 = Thread

import java.util.Random;
import java.util.Scanner;

public class GameManager {
    private Arena arena = new Arena();
    private Scanner input = new Scanner(System.in);
    private Random random = new Random();
    private Integer zombieDikalahkan = 0;
    private final int TARGET_KILL = 20;
    private AutoSaveThread autoSave;
    private GameTimerThread timer;

    public void fasePersiapan() {
        while (true) {
            try {
                System.out.println("\n================================");
                System.out.println("      PLANTS VS ZOMBIES");
                System.out.println("================================");
                System.out.println("1. Fire Bean");
                System.out.println("2. Gatling Bean");
                System.out.println("3. Protective Nuts");
                System.out.println("0. Mulai Permainan");
                System.out.println("================================");
                System.out.print("Pilih : ");

                int pilih = input.nextInt();

                if (pilih == 0) {
                    boolean adaTanaman = false;
                    for (int i = 0; i < Arena.UKURAN; i++) {
                        for (int j = 0; j < Arena.UKURAN; j++) {
                            if (arena.getPlants()[i][j] != null) {
                                adaTanaman = true;
                                break;
                            }
                        }
                    }
                    if (!adaTanaman) {
                        System.out.println("Pasang minimal 1 tanaman terlebih dahulu!");
                        continue;
                    }
                    break;
                }
                Plant plant;
                switch (pilih) {
                    case 1:
                        plant = new FireBean();
                        break;
                    case 2:
                        plant = new GatlingBean();
                        break;
                    case 3:
                        plant = new ProtectiveNuts();
                        break;
                    default:
                        throw new PilihanTidakValidException(pilih);
                }
                System.out.print("Baris (0-4) : ");
                int row = input.nextInt();
                System.out.print("Kolom (0-4) : ");
                int col = input.nextInt();
                arena.placePlant(plant, row, col);
                System.out.println(plant.getNama() + " berhasil dipasang!");

            } catch (Exception e) {
                System.out.println("[ERROR] "+ e.getMessage());
            } finally {
                System.out.println("Input selesai diproses.");
            }
        }
    }

    private Zombie createZombie() {
        int pilih = random.nextInt(2);
        if (pilih == 0) {
            return new NormalZombie();
        }
        return new ShieldZombie();
    }
    private void spawnZombie() {
        int lane = random.nextInt(Arena.UKURAN);
        if (arena.getZombies()[lane][4] == null) {
            Zombie zombie = createZombie();
            arena.getZombies()[lane][4] = zombie;
            System.out.println("\n==================================");
            System.out.println(zombie.getNama() + " muncul di lane " + lane);
            System.out.println("==================================");
            zombie.suaraKhas();
        }
    }

    private void plantAttack() {
        for (int i = 0; i < Arena.UKURAN; i++) {
            for (int j = 0; j < Arena.UKURAN; j++) {
                Plant plant = arena.getPlants()[i][j];
                if (plant == null || plant instanceof ProtectiveNuts) {
                    continue;
                }
                for (int z = j + 1; z < Arena.UKURAN; z++) {
                    Zombie zombie = arena.getZombies()[i][z];
                    if (zombie != null) {
                        plant.attack();
                        zombie.terimaDamage(plant.getDamage());
                        if (zombie.getHealth() <= 0) {
                            arena.getZombies()[i][z] = null;
                            zombieDikalahkan++;
                            autoSave.updateStatus(zombieDikalahkan);
                            System.out.println("==================================");
                            System.out.println("Zombie mati!");
                            System.out.println("==================================");
                        }
                        break;
                    }
                }
            }
        }
    }

    private boolean moveZombie() {
        for (int row = 0; row < Arena.UKURAN; row++) {
            for (int col = 4; col > 0; col--) {
                Zombie zombie = arena.getZombies()[row][col];
                if (zombie == null) {
                    continue;
                }
                Plant plant = arena.getPlants()[row][col - 1];
                if (plant != null) {
                    zombie.serang();
                    plant.takeDamage(zombie.getDamage());
                    if (plant.getHealth() <= 0) {
                        arena.getPlants()[row][col - 1] = null;
                    }
                } else {
                    arena.getZombies()[row][col - 1] = zombie;
                    arena.getZombies()[row][col] = null;
                    System.out.println(zombie.getNama() + " bergerak ke kiri");
                    if (col - 1 == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void startGame() {
        fasePersiapan();

        autoSave = new AutoSaveThread();
        timer = new GameTimerThread();

        autoSave.start();
        timer.start();

        boolean gameOver = false;
        while (!gameOver && zombieDikalahkan < TARGET_KILL) {
            spawnZombie();
            plantAttack();

            gameOver = moveZombie();
            autoSave.updateStatus(zombieDikalahkan);
            arena.tampilkanArena();

            System.out.println("\n==================================");
            System.out.println("Zombie Dikalahkan : " + zombieDikalahkan + "/" + TARGET_KILL);
            System.out.println("Waktu Bermain : " + timer.getDetik() + " detik");
            System.out.println("==================================");

            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }

        autoSave.updateStatus(zombieDikalahkan);
        autoSave.stopThread();
        timer.stopThread();

        if (zombieDikalahkan >= TARGET_KILL) {
            System.out.println("\n==================================");
            System.out.println("         KAMU MENANG!");
            System.out.println("==================================");

        } else {
            System.out.println("\n==================================");
            System.out.println("          GAME OVER");
            System.out.println("==================================");
        }
    }
}