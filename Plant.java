// MAHASISWA 1
public abstract class Plant implements Attackable {
    private String nama;
    private int health;
    private int damage;
    private static Integer totalPlantCreated = 0;

    public Plant(String nama, int health, int damage) {
        this.nama = nama;
        this.health = health;
        this.damage = damage;
        totalPlantCreated++;
    }

    public void terimaDamage(int damage) {
        health -= damage;
        System.out.println(nama + " terkena serangan. HP: " + health);
    }

    public static int getTotalPlantCreated() {
        return totalPlantCreated;
    }

    public abstract void takeDamage(int damage);
    
    public String getNama() {
        return nama;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }
}