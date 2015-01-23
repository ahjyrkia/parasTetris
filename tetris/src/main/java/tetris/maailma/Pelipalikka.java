package tetris.maailma;

public class Pelipalikka {

    private int x;
    private int y;
    private int liikeX;
    private int liikeY;

    public Pelipalikka(int x, int y) {
        this.x = x;
        this.y = y;
        liikeX = 0;
        liikeY = 0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void liiku() {
        x = x + liikeX;
        liikeX = 0;

        y = y + liikeY;
        liikeY = 0;

        if (this.x < 0) {
            setX(0);
        }

    }

    public boolean osuukoSeiniin() {
        if (this.x < 0) {
            return true;
        }

        return false;
    }

    public void liikeKoordinaattienMuutos(String mihin) {
        if (mihin.equals("right")) {
            liikeX += 20;

        }
        if (mihin.equals("left")) {
            liikeX -= 20;

        }
        if (mihin.equals("up")) {
            liikeY -= 20;

        }
        if (mihin.equals("down")) {
            liikeY += 20;

        }

    }

    public String getMuoto() {
        return "L";
    }
}
