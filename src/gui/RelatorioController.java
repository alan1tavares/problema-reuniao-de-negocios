package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RelatorioController implements Initializable {
	@FXML
	private Label tempoMedioFila;
	@FXML
	private Label tempoMedioSala;
	@FXML
	private TableView<RelatorioModelo> tabela;
	@FXML
	private TableColumn<RelatorioModelo, String> nome;
	@FXML
	private TableColumn<RelatorioModelo, String> cartao;
	@FXML
	private TableColumn<RelatorioModelo, String> tempo;
	private ObservableList<RelatorioModelo> observableArrayList = FXCollections.observableArrayList();;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cartao.setCellValueFactory(new PropertyValueFactory<>("cartao"));
		tempo.setCellValueFactory(new PropertyValueFactory<>("tempo"));

		tabela.setItems(observableArrayList);
	}

	public void addRow(String nome, String cartoes, String tempo) {
		observableArrayList.add(new RelatorioModelo(nome, cartoes, tempo));
	}

	public void setTempoMedioFila(String tempoMedioFila) {
		this.tempoMedioFila.setText(tempoMedioFila);
	}

	public void setTempoMedioSala(String tempoMedioSala) {
		this.tempoMedioSala.setText(tempoMedioSala);
	}
	
	
}
