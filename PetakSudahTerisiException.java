// Dibuat oleh Mahasiswa 2
public class PetakSudahTerisiException extends Exception {
    public PetakSudahTerisiException(int row, int col) {
        super("Petak ["+ row + "]["+ col + "] sudah terisi!");
    }
}