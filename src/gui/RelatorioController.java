package gui;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RelatorioController implements Initializable {
	@FXML
	private TableView<RelatorioModelo> tabela;
	@FXML
	private TableColumn<RelatorioModelo, String> nome;
	@FXML
	private TableColumn<RelatorioModelo, String> cartao;
	@FXML
	private TableColumn<RelatorioModelo, String> sala;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cartao.setCellValueFactory(new PropertyValueFactory<>("cartao"));
		sala.setCellValueFactory(new PropertyValueFactory<>("sala"));

		tabela.setItems(listaDePessoas());
	}

	private ObservableList<RelatorioModelo> listaDePessoas() {
		return FXCollections.observableArrayList(Arrays.asList(new RelatorioModelo("aaaaaaa", "b", "c"),
				new RelatorioModelo("a", "b", "c"),
				new RelatorioModelo("fgfgfa", "asgasb", "asdfgas")));
	}
}
