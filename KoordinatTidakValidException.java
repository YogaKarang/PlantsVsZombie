// MAHASISWA 2
public class KoordinatTidakValidException extends Exception {
    public KoordinatTidakValidException(int row, int col) {
        super("Posisi ["+ row + "]["+ col+ "] tidak valid!");
    }
}