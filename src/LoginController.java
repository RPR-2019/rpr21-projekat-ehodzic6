import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ImageView slika;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image slika1=new Image(getClass().getResourceAsStream("/img/slika.jpg"));
        slika.setImage(slika1);
        slika.setOpacity(0.3);
    }
}
