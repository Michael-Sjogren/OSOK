package application;


import java.util.List;

public class Collision {

    List<Platform2D> platforms;
    Player player;

    public Collision(List<Platform2D> platforms, Player player) {
        this.platforms = platforms;
        this.player = player;


    }

    public void checkCollision() {
        checkNorth();
        checkEast();
        checkSouth();
        checkWest();
    }

    public void checkNorth() {
        for(int i = 0; i < platforms.size(); i++){
            if(player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(0).getBoundsInParent())){
            }
        }
    }

    public void checkEast() {
        for(int i = 0; i < platforms.size(); i++){
            if(player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(1).getBoundsInParent())){

            }
        }
    }

    public void checkSouth() {
        for(int i = 0; i < platforms.size(); i++){
            if(player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(2).getBoundsInParent())){

            }
        }
    }

    public void checkWest() {
        for(int i = 0; i < platforms.size(); i++){
            if(player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(3).getBoundsInParent())){

            }
        }

    }
}