package chegg;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleMove extends Application {

    private Circle circ;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane p = new Pane();

        circ = new Circle( 50, 50, 20, Color.FORESTGREEN);
        p.getChildren().add(circ);

        // *A* Register the click handler to handle click events on the pane p
        p.addEventFilter(MouseEvent.MOUSE_CLICKED, new ClickHandler()); //registering our own click handler

        Scene sc = new Scene(p, 300, 300);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Moving Circle");
        primaryStage.show();
    }

    private class ClickHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            // *B* move the circle circ to the position of the mouse click
            circ.setCenterX(event.getSceneX()); //getting X position of mouse click
            circ.setCenterY(event.getSceneY()); //getting Y position of mouse click
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}