package unicam.formula1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import unicam.formula1.model.Cell;
import unicam.formula1.model.GameEngine;
import unicam.formula1.model.abstracts.ITrack;
import unicam.formula1.model.abstracts.Player;
import unicam.formula1.view.TrackView;

import java.io.IOException;
import java.util.List;

/**
 * Controls the race simulation and manages interactions between the game engine and the UI.
 * This class handles starting the simulation, updating the UI based on the game state, and
 * displaying the track and players on the UI.
 */
public class RaceController {

    @FXML
    private Button startButton;

    @FXML
    private Pane trackPane;

    private GameEngine<Cell> gameEngine;
    private TrackView trackView;

    /**
     * Simulates a single race step by invoking the game engine's play method,
     * updates the UI to reflect any changes in the game state, and checks if the race has terminated.
     * Disables the start button and notifies via console when the race is over.
     */
    @FXML
    private void simulate() {
        gameEngine.play();
        if (gameEngine.isTerminated()) {
            startButton.setDisable(true);
        }
        this.trackView.update();
    }

    /**
     * Initializes the race controller, setting up the game environment by loading the track,
     * players, and integrating them into the TrackView for visualization.
     * This method must be called to properly set up the game and UI before simulation starts.
     *
     * @throws IOException if there is an error setting up the game, such as an issue loading the track configuration.
     */
    public void initialize() throws IOException {
        GameController gameController = new GameController();
        gameController.initialize();

        ITrack<Cell> track = gameController.getTrack();
        List<Player<Cell>> players = gameController.getPlayers();

        this.trackView = new TrackView(track, players);
        this.trackPane.getChildren().add(trackView);
        this.trackView.update();
        this.gameEngine = new GameEngine<>(players);
    }
}
