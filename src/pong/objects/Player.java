package pong.objects;

public class Player {

    private int score;
    private long timeSinceLastScore;

    public Player() {
        this.score = 0;
        this.timeSinceLastScore = System.currentTimeMillis();
    }

    public void addScore() {
        if(timeSinceLastScore + 1000 <= System.currentTimeMillis()) {
            score++;
            timeSinceLastScore = System.currentTimeMillis();
        }
    }

    public long getTimeSinceLastScore() {
        return timeSinceLastScore;
    }

    public int getScore() {
        return score;
    }
}
