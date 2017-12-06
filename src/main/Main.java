package main;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gui.RelatorioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pessoa.CriaPessoaAleatoria;
import pessoa.Pessoa;
import sala.Sala;

public class Main extends Application {
	private static List<Pessoa> pessoas;
	private static CriaPessoaAleatoria fabricaDePessoas;

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		Parent root = fxmlLoader.load(getClass().getResource("Relatorio.fxml").openStream());
		RelatorioController controller = (RelatorioController) fxmlLoader.getController();

		for (Pessoa p : pessoas) {
			controller.addRow(p.toString(), p.getCartoes().toString(), p.getTempoNaSala() + "");
		}

		controller.setTempoMedioFila(fabricaDePessoas.getTempoMedioFila() + "s");
		controller.setTempoMedioSala(fabricaDePessoas.getTempoMedioSala() + "s");

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();

		Sala sala = new Sala(5);
		fabricaDePessoas = new CriaPessoaAleatoria(20, sala);

		executorService.execute(fabricaDePessoas);

		executorService.shutdown();

		while (!executorService.isTerminated())
			;
		System.out.println("Criando o relatorio...");
		pessoas = fabricaDePessoas.getListaDePessoasCriadas();
		launch(args);

	}
}
