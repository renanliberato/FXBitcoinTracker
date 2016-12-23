package com.renanliberato.home;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private GridPane homeGridPane;

    @FXML
    private Button homePriceIndexButton, statusButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        HomeVBox priceIndexVBox = new HomeVBox(homeGridPane, homePriceIndexButton, new Button("Chart"), new Button("Table"), 0, 0);

        priceIndexVBox.setTopButtonAction(event -> {
            try {
                new com.renanliberato.priceindex.chart.PriceIndex().start(Home.getStage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        priceIndexVBox.setBottomButtonAction(event -> {
            try {
                new com.renanliberato.priceindex.table.PriceIndex().start(Home.getStage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
