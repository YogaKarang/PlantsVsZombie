// MAHASISWA 2
public class PilihanTidakValidException extends Exception {
    public PilihanTidakValidException(int pilihan) {
        super("Pilihan " + pilihan + " tidak tersedia!");
    }
}