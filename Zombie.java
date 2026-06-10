// Dibuat oleh Mahasiswa 1
public abstract class Zombie {
    private String nama;
    private int health;
    private int damage;

    // Dibuat oleh Mahasiswa 3
    private static Integer totalZombieCreated = 0;

    public Zombie(String nama, int health, int damage) {
        this.nama = nama;
        this.health = health;
        this.damage = damage;
        totalZombieCreated++;
    }

    public void terimaDamage(int damage) {
        health -= damage;
        System.out.println(nama + " terkena serangan. HP: " + health);
    }

    public static int getTotalZombieCreated() {
        return totalZombieCreated;
    }

    public String getNama() {
        return nama;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public abstract void suaraKhas();

    public abstract void serang();
}