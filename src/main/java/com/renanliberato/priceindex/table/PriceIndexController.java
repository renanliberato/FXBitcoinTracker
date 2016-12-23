package com.renanliberato.priceindex.table;

import com.renanliberato.priceindex.model.PriceIndex;
import com.renanliberato.priceindex.search.SearchController;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PriceIndex, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PriceIndex, String> index) {
                SimpleStringProperty property = new SimpleStringProperty();
                DateFormat fromDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat toDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    property.setValue(toDateFormat.format(fromDateFormat.parse(index.getValue().getDate())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return property;
            }
        });
        indexColumn.setCellValueFactory(new PropertyValueFactory<PriceIndex, Double>("index"));

        searchController.getSearchButton().setOnAction((ActionEvent event) -> {
            Task searchTask = new Task() {
                @Override
                protected Object call() throws Exception {
                    indexList = searchController.search();
                    Platform.runLater(() -> {
                        indexTable.setItems(indexList);
                    });
                    return null;
                }
            };

            Thread searchThread = new Thread(searchTask, "Table Searcher");
            searchThread.start();
        });
    }
}
