// MAHASISWA 1
public class GatlingBean extends Plant {
    public GatlingBean() {
        super("Gatling Bean", 150, 50);
    }

    @Override
    public void attack() {
        System.out.println(getNama() + " menembakkan 3 peluru!");
    }

    @Override
    public void takeDamage(int damage) {
        terimaDamage(damage);
    }
}