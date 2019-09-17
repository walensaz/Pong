package pong.multiplayer;

public class Game {

    private String gameName;
    private String description;
    private String user;

    public Game(String gameName, String description, String user) {
        this.gameName = gameName;
        this.description = description;
        this.user = user;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameName='" + gameName + '\'' +
                ", description='" + description + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
