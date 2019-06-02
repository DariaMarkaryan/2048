package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.Field;

public class View extends Application {
    private static final int SQUARE_SCALE = 100;
    private static final int STAGE_WIDTH = 410;
    private static final int STAGE_HEIGHT = 480;

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void createGrids(GridPane gridPane){
        ColumnConstraints column1 = new ColumnConstraints(100);
        gridPane.getColumnConstraints().add(column1);
        ColumnConstraints column2 = new ColumnConstraints(100);
        gridPane.getColumnConstraints().add(column2);
        ColumnConstraints column3 = new ColumnConstraints(100);
        gridPane.getColumnConstraints().add(column3);
        ColumnConstraints column4 = new ColumnConstraints(100);
        gridPane.getColumnConstraints().add(column4);

        RowConstraints row1 = new RowConstraints(100);
        gridPane.getRowConstraints().add(row1);
        RowConstraints row2 = new RowConstraints(100);
        gridPane.getRowConstraints().add(row2);
        RowConstraints row3 = new RowConstraints(100);
        gridPane.getRowConstraints().add(row3);
        RowConstraints row4 = new RowConstraints(100);
        gridPane.getRowConstraints().add(row4);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Field field = new Field();
        field.startGame();
        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();

        root.setCenter(gridPane);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Game");

        MenuItem newGameItem = new MenuItem("New Game");
        MenuItem replayItem = new MenuItem("Replay");

        root.setTop(menuBar);
        menu.getItems().addAll(newGameItem, replayItem);
        menuBar.getMenus().add(menu);

        createGrids(gridPane);

        Text message = new Text("Score: " + Integer.toString(field.score));
        root.setTop(message);

        for (int j = 0; j < Field.SIZE; j++)
            for (int i = 0; i < Field.SIZE; i++) {
                if (!field.getCells()[j][i].isEmpty())
                    gridPane.add(new Tile(field.getCells()[j][i].getValue().getNumberOnCell()), i, j);
            }
        field.printField();

        Scene scene = new Scene(root, 400, 400);
        scene.setOnKeyPressed(event -> {
            if ((event.getCode().equals(KeyCode.UP) ||
                    event.getCode().equals(KeyCode.DOWN) ||
                    event.getCode().equals(KeyCode.LEFT) ||
                    event.getCode().equals(KeyCode.RIGHT))){

                field.moveCells(event.getCode().getCode());
            gridPane.getChildren().clear();
            createGrids(gridPane);
            field.emptyCells = 0;
            for (int j = 0; j < Field.SIZE; j++)
                for (int i = 0; i < Field.SIZE; i++){
                    if (!field.getCells()[j][i].isEmpty()) {
                        Tile tile = new Tile(field.getCells()[j][i].getValue().getNumberOnCell());
                        gridPane.add(tile, i, j);
                        field.emptyCells++;
                    }
                }
                Text message2 = new Text("Score: " + Integer.toString(field.score));
                root.setTop(message2);

                if(field.isFail){
                }
            }
        });

        stage.setScene(scene);
        stage.setTitle("2048");
        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.show();
    }

    private class Tile extends StackPane {
        private int value;

        Tile(int value) {
            this.value = value;
            Rectangle rect = new Rectangle(SQUARE_SCALE, SQUARE_SCALE);
            rect.setStroke(Color.ROSYBROWN);
            rect.setFill(Color.THISTLE);
            Text text = new Text();
            text.setText(Integer.toString(value));
            text.setFont(Font.font("Verdana", 50));
            text.setFill(Color.INDIGO);
            getChildren().addAll(rect, text);
        }
    }
}
/*
 *
 * */