// Dibuat oleh Mahasiswa 1
public class FireBean extends Plant {
    public FireBean() {
        super("Fire Bean", 100, 30);
    }

    @Override
    public void attack() {
        System.out.println(getNama() + " menembakkan api!");
    }

    @Override
    public void takeDamage(int damage) {
        terimaDamage(damage);
    }
}