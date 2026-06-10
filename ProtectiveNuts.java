// MAHASISWA 1
public class ProtectiveNuts extends Plant {
    public ProtectiveNuts() {
        super("Protective Nuts", 500, 0);
    }

    @Override
    public void attack() {
        System.out.println(getNama() + " bertahan.");
    }

    @Override
    public void takeDamage(int damage) {
        terimaDamage(damage);
    }
}