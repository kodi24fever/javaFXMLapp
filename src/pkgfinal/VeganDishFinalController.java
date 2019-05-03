package pkgfinal;
/**
 * @author Frank Izquierdo
 * @author Misaykel Molina
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;



public class VeganDishFinalController implements Initializable {
ObservableList list = FXCollections.observableArrayList();
//ObservableList listOfProcedures = FXCollections.observableArrayList();
  
@FXML
private ListView<String> ingredients;
//private ListView<String> procedures;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    loadData(ingredients); 
    //loadData(procedures);  
    }    
    private void loadData(ListView<String> data)
    {
        // Here we have to put a for loop or while loop to get data from the server
        
        list.removeAll(list);
        String a= "ingredient #1";
        String b= "ingredient #2";
        String c= "ingredient #3";
        
        list.addAll(a,b,c);
        data.getItems().addAll(list);   
    }
}
