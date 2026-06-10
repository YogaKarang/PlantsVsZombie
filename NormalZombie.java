// MAHASISWA 1
public class NormalZombie extends Zombie {
    public NormalZombie() {
        super("Normal Zombie", 80, 20);
    }

    @Override
    public void suaraKhas() {
        System.out.println("Braaaains!");
    }

    @Override
    public void serang() {
        System.out.println(getNama() + " menyerang!");
    }
}