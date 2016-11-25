package app.footer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by renan on 11/25/2016.
 */
public class FooterController implements Initializable {

    @FXML
    private Hyperlink hyperlink;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hyperlink.setOnAction(event -> {
            URI u = null;
            try {
                u = new URI("http://www.coindesk.com/price/");
                java.awt.Desktop.getDesktop().browse(u);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
