package com.renanliberato.priceindex.chart;

import com.renanliberato.priceindex.model.PriceIndex;
import com.renanliberato.priceindex.search.SearchController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by renan on 11/22/2016.
 */
public class PriceIndexController implements Initializable {

    @FXML
    private SearchController searchController;

    @FXML
    private LineChart<Date, Number> indexChart;

    @FXML
    private DateAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private ObservableList<PriceIndex> indexList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.indexChart.setTitle("Price Index 2016");

        XYChart.Series<Date, Number> series = new XYChart.Series();
        series.setName("Price Index");
        this.indexChart.getData().add(series);

        searchController.getSearchButton().setOnAction((ActionEvent event) -> {
            this.indexList = searchController.search();

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            series.getData().clear();
            for (PriceIndex index : this.indexList) {
                try {
                    series.getData().add(new XYChart.Data(df.parse(index.getDate()), index.getIndex()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            SimpleDateFormat dfToView = new SimpleDateFormat("dd/MM/yyyy");
            for (XYChart.Series<Date, Number> finalSeries : indexChart.getData()) {
                for (XYChart.Data<Date, Number> data : finalSeries.getData()) {
                    Tooltip tooltip = new Tooltip(
                            data.getYValue() + "\n" + dfToView.format(data.getXValue())
                    );
                    Tooltip.install(data.getNode(),tooltip);
                }
            }
        });
    }
}
