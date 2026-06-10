// MAHASISWA 1
public class Arena {
    public static final int UKURAN = 5;
    private Plant[][] plants;
    private Zombie[][] zombies;

    public Arena() {
        plants = new Plant[UKURAN][UKURAN];
        zombies = new Zombie[UKURAN][UKURAN];
    }

    public boolean placePlant(Plant plant, int row, int col) 
    throws KoordinatTidakValidException, PetakSudahTerisiException {
        if (row < 0 || row >= UKURAN || col < 0 || col >= UKURAN) {
            throw new KoordinatTidakValidException(row, col);
        }
        if (plants[row][col] != null) {
            throw new PetakSudahTerisiException(row, col);
        }
        plants[row][col] = plant;
        return true;
    }

    public Plant[][] getPlants() {
        return plants;
    }
    
    public Zombie[][] getZombies() {
        return zombies;
    }

    public void tampilkanArena() {
        System.out.println("\n============= ARENA ==============");
        for (int i = 0; i < UKURAN; i++) {
            for (int j = 0; j < UKURAN; j++) {
                if (plants[i][j] instanceof FireBean) {
                    System.out.print("[F]");
                }
                else if (plants[i][j] instanceof GatlingBean) {
                    System.out.print("[G]");
                }
                else if (plants[i][j] instanceof ProtectiveNuts) {
                    System.out.print("[N]");
                }
                else if (zombies[i][j] != null) {
                    System.out.print("[Z]");
                }
                else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }
}