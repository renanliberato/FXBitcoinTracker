package app.priceindex.chart;

import app.priceindex.model.PriceIndex;
import app.priceindex.model.PriceIndexDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private LineChart<Date, Number> indexChart;

    @FXML
    private DateAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private ObservableList<PriceIndex> indexList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PriceIndexDAO dao = new PriceIndexDAO();

        this.indexList = FXCollections.observableArrayList(dao.fetchAll("2016-01-01", "2016-01-30"));

        this.indexChart.setTitle("Price Index 2016");

        XYChart.Series<Date, Number> series = new XYChart.Series();

        series.setName("Price Index");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (PriceIndex index : this.indexList) {
            try {
                series.getData().add(new XYChart.Data(df.parse(index.getDate()), index.getIndex()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        this.indexChart.getData().add(series);

        SimpleDateFormat dfToView = new SimpleDateFormat("dd/MM/yyyy");
        for (XYChart.Series<Date, Number> finalSeries : indexChart.getData()) {
            for (XYChart.Data<Date, Number> data : finalSeries.getData()) {
                Tooltip tooltip = new Tooltip(
                        data.getYValue() + "\n" + dfToView.format(data.getXValue())
                );
                Tooltip.install(data.getNode(),tooltip);
            }
        }

    }
}
