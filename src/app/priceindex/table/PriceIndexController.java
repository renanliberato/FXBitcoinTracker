package app.priceindex;

import app.priceindex.model.PriceIndex;
import app.priceindex.model.PriceIndex;
import app.priceindex.model.PriceIndexDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by renan on 11/22/2016.
 */
public class PriceIndexController implements Initializable {

    @FXML
    private TableView indexTable;

    @FXML
    private TableColumn dateColumn, indexColumn;

    private ObservableList<PriceIndex> indexList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PriceIndexDAO dao = new PriceIndexDAO();

        this.indexList = FXCollections.observableArrayList(dao.fetchAll("2016-01-01", "2016-03-14"));

        indexTable.setItems(indexList);

        dateColumn.setCellValueFactory(new PropertyValueFactory<PriceIndex, Integer>("date"));
        indexColumn.setCellValueFactory(new PropertyValueFactory<PriceIndex, Double>("index"));

    }
}
