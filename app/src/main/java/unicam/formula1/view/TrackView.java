package unicam.formula1.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import unicam.formula1.model.Cell;
import unicam.formula1.model.abstracts.ITrack;
import unicam.formula1.model.abstracts.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Visual representation of the racetrack and players. This class manages the rendering of the track
 * and players onto a canvas, using distinct colors to differentiate between various elements and players.
 */
public class TrackView extends Pane {

    private static final int CELL_SIZE = 30;  // The size of each cell in pixels
    private final ITrack<Cell> track;
    private final List<Player<Cell>> players;
    private final Canvas canvas;
    private final Map<Player<Cell>, Color> playerColors;

    /**
     * Constructs a TrackView with specified track and players.
     *
     * @param track the track to be displayed.
     * @param players the list of players whose positions are to be shown on the track.
     */
    public TrackView(ITrack<Cell> track, List<Player<Cell>> players) {
        this.track = track;
        this.players = players;
        this.canvas = new Canvas(track.getWidth() * CELL_SIZE, track.getHeight() * CELL_SIZE);
        this.playerColors = new HashMap<>();
        this.getChildren().add(canvas);
        assignColorsToPlayers();
        drawTrack();
    }

    /**
     * Assigns unique colors to each player for visual distinction on the canvas.
     */
    private void assignColorsToPlayers() {
        Color[] colors = {Color.BLUE, Color.RED, Color.CYAN, Color.YELLOW, Color.PURPLE};
        int colorIndex = 0;
        for (Player<Cell> player : players) {
            playerColors.put(player, colors[colorIndex % colors.length]);
            colorIndex++;
        }
    }

    /**
     * Draws the track onto the canvas based on the cell types of each cell in the track.
     */
    private void drawTrack() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Cell[][] cells = track.getCellsAsMatrix();
        for (int y = 0; y < track.getHeight(); y++) {
            for (int x = 0; x < track.getWidth(); x++) {
                Cell cell = cells[y][x];
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case TRACK:
                            gc.setFill(Color.GRAY);
                            break;
                        case START:
                            gc.setFill(Color.GREEN);
                            break;
                        case FINISH:
                            gc.setFill(Color.RED);
                            break;
                        case OIL:
                            gc.setFill(Color.BLACK);
                            break;
                        case OFF_TRACK:
                            gc.setFill(Color.BROWN);
                            break;
                        default:
                            gc.setFill(Color.WHITE);
                            break;
                    }
                    gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    /**
     * Updates the visual representation of the track and players. This method should be called
     * whenever the game state changes and a visual update is needed.
     */
    public void update() {
        drawTrack();
        drawPlayers();
    }

    /**
     * Draws players on the track, placing a colored oval at each player's current position.
     */
    private void drawPlayers() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (Player<Cell> player : players) {
            Cell pos = player.getCurrentPosition();
            if (pos != null) {
                gc.setFill(playerColors.get(player));
                gc.fillOval(pos.getX() * CELL_SIZE, pos.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

}
