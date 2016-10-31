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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TableColumn<Fornecedores, Integer> tbcId;
    @FXML
    private TableColumn<Fornecedores, String> tbcNome;
    @FXML
    private TableColumn<Fornecedores, Integer> tbcCnpj;
    @FXML
    private TableColumn<Fornecedores, Integer> tbcTelefone;
    @FXML
    private TableColumn<Fornecedores, String> tbcEmail;
    @FXML
    private TableColumn<Fornecedores, String> tbcEndereco;
    
    private List<Fornecedores> listFornecedores = new ArrayList<Fornecedores>();
    
    private ObservableList<Fornecedores> observableFornecedores;
    //Fim da View Buscar Fornecedor

    //View Cadastrar
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        System.out.println("You clicked me!");
        String nome = nomeLB.getText();
        int telefone = Integer.parseInt(telefoneLB.getText());
        int cnpj = Integer.parseInt(cnpjLB.getText());
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
    
    //View Buscar Fornecedores
    public void carregarTableViewFornecedores() throws SQLException{
        
        BuscarFornecedor busca = new BuscarFornecedor();
        
        tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        tbcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        
        listFornecedores = busca.buscar();
        
        observableFornecedores = FXCollections.observableArrayList(listFornecedores);
        tbvFornecedores.setItems(observableFornecedores);
    }
    //Fim da View Buscar Fornecedores
}
