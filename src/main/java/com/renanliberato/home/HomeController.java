package com.renanliberato.home;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by renan on 11/22/2016.
 */
public class HomeController implements Initializable {

    @FXML
    private GridPane homeGridPane;

    @FXML
    private Button homePriceIndexButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Button homePriceIndexTableButton = new Button("Table");
        Button homePriceIndexChartButton = new Button("Chart");
        VBox priceIndexVbox = new VBox(homePriceIndexChartButton, homePriceIndexTableButton);

        homePriceIndexTableButton.setPrefSize(280,70);
        homePriceIndexChartButton.setPrefSize(280,70);

        homePriceIndexTableButton.setOnAction(event -> {
            try {
                new com.renanliberato.priceindex.table.PriceIndex().start(Home.getStage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        homePriceIndexChartButton.setOnAction(event -> {
            try {
                new com.renanliberato.priceindex.chart.PriceIndex().start(Home.getStage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        homePriceIndexButton.setOnAction(event -> {
            FadeTransition expandTransition = new FadeTransition(Duration.millis(300), homeGridPane);
            expandTransition.setFromValue(1.0);
            expandTransition.setToValue(0.0);
            expandTransition.play();
            expandTransition.setOnFinished(eventTransition -> {
                homeGridPane.getChildren().remove(0);
                homeGridPane.getChildren().add(0, priceIndexVbox);
                FadeTransition afterExpandTransition = new FadeTransition(Duration.millis(300), homeGridPane);
                afterExpandTransition.setFromValue(0.0);
                afterExpandTransition.setToValue(1.0);
                afterExpandTransition.play();
            });
        });
    }
}
