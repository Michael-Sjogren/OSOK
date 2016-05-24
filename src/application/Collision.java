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
                player.setColDown(true);
            }else{
                player.setColDown(false);
            }
        }
    }

    public void checkEast() {
        for(int i = 0; i < platforms.size(); i++){
            if(player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(1).getBoundsInParent())){
                player.setColLeft(true);
            }else{
                player.setColLeft(false);
            }
        }
    }

    public void checkSouth() {
        for(int i = 0; i < platforms.size(); i++){
            if(player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(2).getBoundsInParent())){
                player.setColUp(true);
            }else{
                player.setColUp(false);
            }
        }
    }

    public void checkWest() {
        for(int i = 0; i < platforms.size(); i++){
            if(player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(3).getBoundsInParent())){
                player.setColRight(true);
            } else {
                player.setColRight(false);
            }
        }

    }
}