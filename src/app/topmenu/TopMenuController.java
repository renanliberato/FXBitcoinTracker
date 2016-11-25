package app.topmenu;

import app.home.Home;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by renan on 11/23/2016.
 */
public class TopMenuController implements Initializable {

    @FXML
    private Button toHomeButton;

    @FXML
    private MenuItem toPriceIndexTable, toPriceIndexChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toHomeButton.setOnAction((ActionEvent event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../resources/home/home.fxml"));
                Home.getStage().setScene(new Scene(root, 600, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        toPriceIndexTable.setOnAction((ActionEvent event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../resources/priceindex/table/priceindex.fxml"));
                Home.getStage().setScene(new Scene(root, 600, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        toPriceIndexChart.setOnAction((ActionEvent event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../resources/priceindex/chart/priceindex.fxml"));
                Home.getStage().setScene(new Scene(root, 600, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
