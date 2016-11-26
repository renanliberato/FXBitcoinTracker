package app.priceindex.search;

import app.priceindex.model.PriceIndex;
import app.priceindex.model.PriceIndexDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Created by renan on 11/26/2016.
 */
public class SearchController implements Initializable {

    @FXML
    private DatePicker fromField, toField;

    @FXML
    private Button searchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public ObservableList<PriceIndex> search() {
        LocalDate from = fromField.getValue();
        LocalDate to   = toField.getValue();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        PriceIndexDAO dao = new PriceIndexDAO();
        return FXCollections.observableArrayList(dao.fetchAll(from.format(formatter), to.format(formatter)));
    }

    public DatePicker getFromField() {
        return fromField;
    }

    public DatePicker getToField() {
        return toField;
    }

    public Button getSearchButton() {
        return searchButton;
    }
}
