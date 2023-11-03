package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
        Button button = new Button("Start Bouncing Ball");
        button.setOnAction(e -> {
            Stage secondaryStage = new Stage();
            Pane pane = new Pane();
            Scene scene = new Scene(pane, SCENE_HEIGHT, SCENE_WIDTH);
            Circle circle = new Circle(20, Color.RED);
            Circle circleOne = new Circle(20, Color.GREEN);
            circle.relocate(10, 10);
            circleOne.relocate(2, 5);
            Button pauseButton = new Button("Pause"); // create the pause button
            pane.getChildren().addAll(circle, circleOne, pauseButton);

            pauseButton.setLayoutX(20);
            pauseButton.setLayoutY(20);
            secondaryStage.setTitle("Bouncing Ball");
            secondaryStage.setScene(scene);
            secondaryStage.show();

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), new EventHandler<ActionEvent>() {
                double dx1 = 2;
                double dy1 = 5;

                double dx2 = 3;
                double dy2 = 6;

                @Override
                public void handle(ActionEvent actionEvent) {
                    circle.setLayoutX(circle.getLayoutX() + dx1);
                    circle.setLayoutY(circle.getLayoutY() + dy1);

                    circleOne.setLayoutX(circleOne.getLayoutX() + dx2);
                    circleOne.setLayoutY(circleOne.getLayoutY() + dy2);

                    Bounds bounds = pane.getBoundsInLocal();

                    System.out.println(bounds.getMinX());
                    System.out.println(bounds.getMaxX());

                    if (circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius()) ||
                            circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius())) {
                        System.out.println("Out of bounds X " + circle.getLayoutX());

                        dx1 = -dx1;
                    }

                    if (circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius()) ||
                            circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius())) {
                        System.out.println("Out of bounds Y " + circle.getLayoutY());
                        dy1 = -dy1;
                    }

                    if (circleOne.getLayoutX() <= (bounds.getMinX() + circleOne.getRadius()) ||
                            circleOne.getLayoutX() >= (bounds.getMaxX() - circleOne.getRadius())) {
                        System.out.println("Out of bounds X " + circleOne.getLayoutX());

                        dx2 = -dx2;
                    }

                    if (circleOne.getLayoutY() <= (bounds.getMinY() + circleOne.getRadius()) ||
                            circleOne.getLayoutY() >= (bounds.getMaxY() - circleOne.getRadius())) {
                        System.out.println("Out of bounds Y " + circleOne.getLayoutY());
                        dy2 = -dy2;
                    }
                    if (circleOne.intersects(circle.getBoundsInParent()) || circle.intersects(circleOne.getBoundsInParent())) {
                        double tempDx = dx1;
                        double tempDy = dy1;
                        dx1 = dx2;
                        dy1 = dy2;
                        dx2 = tempDx;
                        dy2 = tempDy;
                    }
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            final boolean[] isPlaying = {true};
            pauseButton.setOnAction(b -> {
                if (isPlaying[0]) { // if the animation is paused, resume it and change the button text
                    timeline.play();
                    pauseButton.setText("Pause");
                } else { // if the animation is playing, pause it and change the button text
                    timeline.pause();
                    pauseButton.setText("Resume");
                }
                isPlaying[0] = !isPlaying[0]; // toggle the animation state
            });
    });
    Pane mainPane = new Pane();
        mainPane.getChildren().add(button);

    // Create a scene and set it to the primary stage
    Scene mainScene = new Scene(mainPane, 200, 200);
        stage.setTitle("Bouncing Ball Button");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}