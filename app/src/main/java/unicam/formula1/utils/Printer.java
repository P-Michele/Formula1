package unicam.formula1.utils;

import unicam.formula1.model.abstracts.ICell;
import unicam.formula1.model.abstracts.Player;

public class Printer {

    /**
     * Prints the current position of the player.
     * @param player The player whose position is to be printed.
     */
    public static void printPlayerPosition(Player<?> player) {
        ICell<?> pos = player.getCurrentPosition();
        System.out.println(player.getName() + " è a posizione (" + pos.getX() + ", " + pos.getY() + ")");
    }

    /**
     * Prints the move made by the player.
     * @param player The player whose move is to be printed.
     * @param newPosition The new position of the player after the move.
     */
    public static void printMove(Player<?> player, ICell<?> newPosition) {
        if (newPosition != null) {
            System.out.println(player.getName() + " si è mosso a (" + newPosition.getX() + ", " + newPosition.getY() + ")");
        } else {
            System.out.println(player.getName() + " ha tentato un movimento non valido o si è schiantato.");
        }
    }

    /**
     * Announces that a player has been eliminated from the game.
     * @param player The player who has been eliminated.
     */
    public static void printPlayerElimination(Player<?> player) {
        System.out.println(player.getName() + " è stato eliminato dalla gara.");
    }

    /**
     * Announces that a player has won the race.
     * @param player The player who has won.
     */
    public static void printPlayerVictory(Player<?> player) {
        System.out.println(player.getName() + " ha vinto la gara!");
    }

}
