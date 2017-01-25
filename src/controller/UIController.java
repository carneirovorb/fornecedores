/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Fornecedor.Fornecedores;
import model.CadastraFornecedor;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BuscarFornecedor;

/**
 *
 * @author Vorb
 */
public class UIController implements Initializable {

    //View Cadastrar
    @FXML
    private TextField nomeLB;
    @FXML
    private TextField telefoneLB;
    @FXML
    private TextField cnpjLB;
    @FXML
    private TextField emailLB;
    @FXML
    private TextField ruaLB;
    @FXML
    private TextField numeroLB;
    @FXML
    private TextField cepLB;
    @FXML
    private TextField bairroLB;
    //Fim da View Cadastrar

    //View Buscar Fornecedor
    @FXML
    private TableView<Fornecedores> tbvFornecedores;
    @FXML
    private TableColumn<Fornecedores, String> tbcNome;
    @FXML
    private TableColumn<Fornecedores, Integer> tbcCnpj;
    @FXML
    private Button btFiltrar;
    @FXML
    private Button btRemover;
    @FXML
    private TextField tfNome;

    private List<Fornecedores> listFornecedores = new ArrayList<Fornecedores>();

    private ObservableList<Fornecedores> observableFornecedores;
    //Fim da View Buscar Fornecedor

    //View Cadastrar
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        System.out.println("You clicked me!");
        String nome = nomeLB.getText();
        String telefone = telefoneLB.getText();
        String cnpj = cnpjLB.getText();
        String email = emailLB.getText();
        String rua = ruaLB.getText();
        String numero = numeroLB.getText();
        String cep = cepLB.getText();
        String bairro = bairroLB.getText();

        String endereco = rua + ", " + numero + ", " + bairro + ", " + cep;

        CadastraFornecedor fornecedor = new CadastraFornecedor();
        fornecedor.cadastra(cnpj, nome, telefone, email, endereco);

    }
    //Fim da View Cadastrar

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarTableViewFornecedores();
        } catch (SQLException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Clique do mause em um elemento da lista de View
    @FXML
    public void listClick() {

        Fornecedores lista = new Fornecedores();
        lista = tbvFornecedores.getSelectionModel().getSelectedItem();

        if (lista != null) {

            String nome = lista.getNome();
            long cnpj = lista.getCnpj();
            String email = lista.getEmail();
            String endereco = lista.getEndereco();
            long telefone = lista.getTelefone();
            String text = "Nome: " + nome + "\n"
                    + "CNPJ: " + cnpj + "\n"
                    + "Email: " + email + "\n"
                    + "Endereço: " + endereco + "\n"
                    + "Telefone: " + telefone + ".";

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(nome);
            alert.setHeaderText("Informação do Fornecedor");
            alert.setContentText(text);

            ButtonType btEditar = new ButtonType("Editar");
            ButtonType btRemover = new ButtonType("Remover");
            ButtonType btCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(btEditar, btRemover, btCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == btEditar) {
                // ... user chose "One"
            } else if (result.get() == btRemover) {
                // ... user chose "Two"
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Celula Vazia");
            alert.setHeaderText("Informaçao do Fornecedor");
            alert.setContentText("Celula Vazia");

        }

    }
    //Fim da função de clique do mause.

    //View Buscar Fornecedores
    public void carregarTableViewFornecedores() throws SQLException {

        BuscarFornecedor busca = new BuscarFornecedor();

        tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));

        listFornecedores = busca.buscar();

        observableFornecedores = FXCollections.observableArrayList(listFornecedores);
        tbvFornecedores.setItems(observableFornecedores);
    }

    public void onFiltrar() {

        BuscarFornecedor busca = new BuscarFornecedor();

        Fornecedores lista = new Fornecedores();

        try {
            lista = busca.buscaSingle(tfNome.getText().toString());

            String nome = lista.getNome();
            long cnpj = lista.getCnpj();
            String email = lista.getEmail();
            String endereco = lista.getEndereco();
            long telefone = lista.getTelefone();
            String text = "Nome: " + nome + "\n"
                    + "CNPJ: " + cnpj + "\n"
                    + "Email: " + email + "\n"
                    + "Endereço: " + endereco + "\n"
                    + "Telefone: " + telefone + ".";

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(nome);
            alert.setHeaderText("Informação do Fornecedor");
            alert.setContentText(text);

            ButtonType btEditar = new ButtonType("Editar");
            ButtonType btRemover = new ButtonType("Remover");
            ButtonType btCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(btEditar, btRemover, btCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == btEditar) {
                // ... user chose "One"
            } else if (result.get() == btRemover) {
                // ... user chose "Two"
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

    }
    //Fim da View Buscar Fornecedores
}
