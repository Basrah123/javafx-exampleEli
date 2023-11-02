package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {

    private final static int SCENE_HEIGHT = 500;
    private final static int SCENE_WIDTH = 500;

    @Override
    public void start(Stage stage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, SCENE_HEIGHT, SCENE_WIDTH);
        Circle circle = new Circle(20, Color.RED);
        Circle circleOne = new Circle(10, Color.GREEN);
        circle.relocate(10,10);
        circleOne.relocate(5,5);
        pane.getChildren().add(circle);
        pane.getChildren().add(circleOne);

        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), new EventHandler<ActionEvent>() {
            double dx = 2;
            double dy = 5;

            double dx1 = 3;
            double dy1 = 6;
            @Override
            public void handle(ActionEvent actionEvent) {
                circle.setLayoutX(circle.getLayoutX() + dx);
                circle.setLayoutY(circle.getLayoutY() + dy);

                circleOne.setLayoutX(circleOne.getLayoutX() + dx1);
                circleOne.setLayoutY(circleOne.getLayoutY() + dy1);

                Bounds bounds = pane.getBoundsInLocal();

                System.out.println(bounds.getMinX());
                System.out.println(bounds.getMaxX());

                if(circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius()) ||
                        circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius())) {
                    System.out.println("Out of bounds X " + circle.getLayoutX());

                    dx = -dx;
                }

                if(circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius()) ||
                        circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius())) {
                    System.out.println("Out of bounds Y " + circle.getLayoutY());
                    dy = -dy;
                }

                if(circleOne.getLayoutX() <= (bounds.getMinX() + circleOne.getRadius()) ||
                        circleOne.getLayoutX() >= (bounds.getMaxX() - circleOne.getRadius())) {
                    System.out.println("Out of bounds X " + circleOne.getLayoutX());

                    dx1 = -dx1;
                }

                if(circleOne.getLayoutY() <= (bounds.getMinY() + circleOne.getRadius()) ||
                        circleOne.getLayoutY() >= (bounds.getMaxY() - circleOne.getRadius())) {
                    System.out.println("Out of bounds Y " + circleOne.getLayoutY());
                    dy1 = -dy1;
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }

}