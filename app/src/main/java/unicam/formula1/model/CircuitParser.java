package unicam.formula1.model;

import unicam.formula1.model.abstracts.CellType;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Handles parsing of a track configuration file and constructs a {@link Track} object based on the file content.
 * This parser reads the dimensions and cell types from a file to create the track and set up its structure,
 * including the cells and their neighbors.
 */
public class CircuitParser {

    /**
     * Parses the track configuration from a BufferedReader and constructs a Track object.
     * The first line of the input file must contain the width and height of the track, followed by lines representing
     * the track rows with specific cell types.
     *
     * @param br the BufferedReader that reads from the configuration file.
     * @return a fully constructed Track object with all cells and their neighbors properly initialized.
     * @throws IOException if there is an issue reading the file, such as if the file is empty,
     *         the dimensions are missing, or the cell rows are incorrect in length.
     */
    public Track parseFile(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line == null) {
            throw new IOException("Configuration file is empty or invalid");
        }
        String[] dimensions = line.split(" ");
        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);
        Track track = new Track(width, height);

        for (int y = 0; y < height; y++) {
            line = br.readLine();
            if (line == null || line.length() != width) {
                throw new IOException("Line dimension is invalid or file is incomplete");
            }
            for (int x = 0; x < width; x++) {
                CellType cellType = CellType.fromSymbol(line.charAt(x));
                Cell cell = new Cell(x, y, cellType);
                track.addCell(cell);
            }
        }

        setNeighbors(track);
        return track;
    }

    /**
     * Sets the neighboring cells for each cell in the track.
     * Neighbors are determined based on the adjacent cells in all eight directions (vertical, horizontal, and diagonal).
     *
     * @param track the Track object for which neighbors are to be set.
     */
    private void setNeighbors(Track track) {
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };
        for (int y = 0; y < track.getHeight(); y++) {
            for (int x = 0; x < track.getWidth(); x++) {
                Cell cell = track.getCell(x, y);
                if (cell == null) continue;
                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    Cell neighbor = track.getCell(nx, ny);
                    if (neighbor != null) {
                        cell.addNeighbor(neighbor);
                    }
                }
            }
        }
    }

}
