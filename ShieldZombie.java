// Dibuat oleh Mahasiswa 1
public class ShieldZombie extends Zombie {

    private int shield = 50;

    public ShieldZombie() {
        super("Shield Zombie", 120, 15);
    }

    @Override
    public void terimaDamage(int damage) {

        if (shield > 0) {

            shield -= damage;

            if (shield < 0) {
                shield = 0;
            }

            System.out.println(
                    "Shield tersisa : "
                            + shield
            );

        } else {

            super.terimaDamage(damage);
        }
    }

    @Override
    public void suaraKhas() {

        System.out.println("CLANK!");
    }

    @Override
    public void serang() {

        System.out.println(
                getNama()
                        + " menyerang dengan perisai!"
        );
    }
}