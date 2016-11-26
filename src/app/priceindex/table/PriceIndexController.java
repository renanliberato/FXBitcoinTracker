package app.priceindex.table;

import app.priceindex.model.PriceIndex;
import app.priceindex.model.PriceIndex;
import app.priceindex.model.PriceIndexDAO;
import app.priceindex.search.SearchController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by renan on 11/22/2016.
 */
public class PriceIndexController implements Initializable {

    @FXML
    private SearchController searchController;

    @FXML
    private TableView indexTable;

    @FXML
    private TableColumn dateColumn, indexColumn;

    private ObservableList<PriceIndex> indexList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dateColumn.setCellValueFactory(new PropertyValueFactory<PriceIndex, String>("date"));
        indexColumn.setCellValueFactory(new PropertyValueFactory<PriceIndex, Double>("index"));

        searchController.getSearchButton().setOnAction((ActionEvent event) -> {
            this.indexList = searchController.search();

            indexTable.setItems(indexList);
        });
    }
}
