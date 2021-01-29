package quotePack;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import application.UiController;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class QuoteController {

	@FXML
	private ImageView samplePic1 = new ImageView();
	@FXML
	private ImageView samplePic2 = new ImageView();
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
	private Button saveToPdf = new Button(); // nem tudom hogy de biztos ki lehet menteni a borderpane tartalmát
	@FXML
	private BorderPane ScreenToPdf;

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

	public void makePdf() {
//csinál egy snapshotot és lementi prfben, 1440p kijelzőn jól néz ki egyenlőre így jó lesz 

		try {

			WritableImage nodeshot = new WritableImage(10, 10);

			nodeshot = UiController.currentQuote.getScene().getRoot().snapshot(new SnapshotParameters(), null);
			File file = new File("ajanlat.png");

			ImageIO.write(SwingFXUtils.fromFXImage(nodeshot, null), "png", file);

			file.createNewFile();

			PDDocument doc = new PDDocument();
			PDPage page = new PDPage(PDRectangle.A3);
			PDImageXObject pdimage;
			PDPageContentStream content;

			pdimage = PDImageXObject.createFromFile("ajanlat.png", doc);
			content = new PDPageContentStream(doc, page);
			content.drawImage(pdimage, 0, -40); // képet mozgatja hogy elférjen
			content.close();

			doc.addPage(page);
			doc.save("pdf_file.pdf");
			doc.close();
			file.delete();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("pdf saved");
	}

}
