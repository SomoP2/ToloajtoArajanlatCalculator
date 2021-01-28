package quotePack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class QuoteController {

	@FXML
	private ImageView samplePic1= new ImageView();
	@FXML
	private ImageView samplePic2= new ImageView();
	@FXML
	private TextField userPhone = new TextField();
	@FXML
	private TextField userName = new TextField();
	@FXML
	private Text legalmaszlag = new Text();
	@FXML
	private Text orderPiece = new Text();
	@FXML
	private Text netPrice = new Text();
	@FXML
	private Text grossPrice = new Text();
	@FXML
	private Text fullPrice = new Text();
	@FXML
	private Text quoteNumber = new Text();
	@FXML
	private Text dearCustomer = new Text();
	@FXML
	private TableView<String> customerData = new TableView<String>();
	@FXML
	private TableColumn<Customer, String> cDatarow1 = new TableColumn<Customer, String>();
	@FXML
	private TableColumn<Customer, String> cDatarow2 = new TableColumn<Customer, String>();
	@FXML
	private TableView<String> doorData = new TableView<String>();
	@FXML
	private TableColumn<Doors, String> dDatarow1 = new TableColumn<Doors, String>();
	@FXML
	private TableColumn<Doors, String> dDatarow2 = new TableColumn<Doors, String>();
	@FXML
	private Button saveToPdf; //nem tudom hogy de biztos ki lehet menteni a borderpane tartalmát 

	@FXML
	public void initialize() {
		String legal = "A fenti árak nettó árak, a 27 % ÁFA értékét nem tartalmazzák.\r\n"
				+ "Ajánlatunk 30 napig érvényes.\r\n"
				+ "Az ajánlatban szereplő árak a tolóajtó fém vázszerkezetére vonatkoznak ajtólap és tok nélkül, személyes átvétellel dabasi irodánkban.\r\n"
				+ "Szállítás ideje (várhatóan): a megrendeléstől számított 4-5. hét.\r\n"
				+ "Az árajánlat tájékoztató jellegű, a rendelkezésre álló adatok alapján készült.\r\n"
				+ "Reméljük ajánlatunk megfelel az elképzeléseinek!";
		legalmaszlag.setText(legal);
	}

}
