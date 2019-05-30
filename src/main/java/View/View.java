package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
    import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


public class View extends Application{
    private static final int SQUARE_SCALE = 100;

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
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

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(25);
        gridPane.getColumnConstraints().add(column1);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(25);
        gridPane.getColumnConstraints().add(column2);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(25);
        gridPane.getColumnConstraints().add(column3);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(25);
        gridPane.getColumnConstraints().add(column4);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(25);
        gridPane.getRowConstraints().add(row1);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(25);
        gridPane.getRowConstraints().add(row2);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(25);
        gridPane.getRowConstraints().add(row3);
        RowConstraints row4 = new RowConstraints();
        row4.setPercentHeight(25);
        gridPane.getRowConstraints().add(row4);

        gridPane.setGridLinesVisible(true);

        gridPane.add(new Tile(2),1,1);

        Scene scene = new Scene(root, 400,400);
        stage.setScene(scene);
        stage.setTitle("2048");
        stage.setMaxWidth(410);
        stage.setMinWidth(410);
        stage.setMaxHeight(460);
        stage.setMinHeight(460);
        stage.show();
    }

    private class Tile extends StackPane {
        private int value ;
        private Rectangle rect = new Rectangle(SQUARE_SCALE ,SQUARE_SCALE );
        private Text text  = new Text();
        public Tile(int value) {
            this.value = value;
            rect.setStroke(Color.BISQUE);
            rect.setFill(Color.OLIVEDRAB);
            text.setText(Integer.toString(value));
            getChildren().addAll(rect,text);
        }
    }
}