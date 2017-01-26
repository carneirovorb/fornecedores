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
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.AlterarFornecedor;
import model.BuscarFornecedor;
import model.RemoverFornecedor;

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

    
    
        //View Cadastrar
    @FXML
    private TextField nomeEdit;
    @FXML
    private TextField telefoneEdit;
    @FXML
    private TextField cnpjEdit;
    @FXML
    private TextField emailEdit;
    @FXML
    private TextField ruaEdit;
    @FXML
    private TextField numeroEdit;
    @FXML
    private TextField cepEdit;
    @FXML
    private TextField bairroEdit;
    //Fim da View Cadastrar
    
    
    //View Buscar Fornecedor
    @FXML
    private TableView<Fornecedores> tbvFornecedores;
    @FXML
    private TableView<Fornecedores> tabPane;
    @FXML
    private TableColumn<Fornecedores, String> tbcNome;
    @FXML
    private TableColumn<Fornecedores, String> tbcCnpj;
    @FXML
    private Button btFiltrar;
    @FXML
    private Button btRemover;
    @FXML
    private TextField tfNome;
    
    @FXML
    private AnchorPane editarFornecedor;
 
 
    private List<Fornecedores> listFornecedores = new ArrayList<>();

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

        String endereco = rua + "," + numero + "," + bairro + "," + cep;

        CadastraFornecedor fornecedor = new CadastraFornecedor();
        fornecedor.cadastra(cnpj, nome, telefone, email, endereco);
         carregarTableViewFornecedores();

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

    int id;
    
    //rotina editar usuário
    @FXML
    public void editar(Fornecedores lista){
        
        System.out.println("entrou");
        String nome = lista.getNome();
        String cnpj =  lista.getCnpj();
        String email = lista.getEmail();
        String telefone =  lista.getTelefone();
        id = lista.getId();
        String endereco = lista.getEndereco();
        String[] parts = endereco.split (Pattern.quote (","));
        
        
       editarFornecedor.setVisible(true);
       nomeEdit.setText(nome);
       telefoneEdit.setText(telefone);
       cnpjEdit.setText(cnpj);
       emailEdit.setText(email);
       ruaEdit.setText(parts[0]);
       numeroEdit.setText(parts[1]);
       bairroEdit.setText(parts[2]);
       cepEdit.setText(parts[3]);
    }
    
    @FXML
     public void cancelaEdit(){
        editarFornecedor.setVisible(false);

    }
     
     
     @FXML
     public void limparCampos(){
        nomeLB.setText("");
       telefoneLB.setText("");
       cnpjLB.setText("");
       emailLB.setText("");
       ruaLB.setText("");
       numeroLB.setText("");
       bairroLB.setText("");
       cepLB.setText("");

    }
     
     
     
     
    
    @FXML
    public void salvaEdit() throws SQLException{
        System.out.println("entrou");
        String cnpjNew = cnpjEdit.getText();
        String nomeNew = nomeEdit.getText();
        String telefoneNew = telefoneEdit.getText();
        String emailNew = emailEdit.getText();
        
        String enderecoNew = ruaEdit.getText() + "," + numeroEdit.getText() + "," + bairroEdit.getText() + "," + cepEdit.getText();
        AlterarFornecedor altera = new AlterarFornecedor();
        altera.edita(cnpjNew, nomeNew, telefoneNew, emailNew, enderecoNew, id);
        editarFornecedor.setVisible(false);
        carregarTableViewFornecedores();

    }
     
       //fim rotina editar usuário
    
    
    
     
    //Clique do mause em um elemento da lista de View
    @FXML
    public void listClick() throws SQLException {

        Fornecedores lista = new Fornecedores();
        lista = tbvFornecedores.getSelectionModel().getSelectedItem();

        if (lista.getNome() != null) {

            String nome = lista.getNome();
            String cnpj = lista.getCnpj();
            String email = lista.getEmail();
            String endereco = lista.getEndereco();
            String telefone = lista.getTelefone();
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
                
                
                editar(lista);
                
                
            } else if (result.get() == btRemover) {
                System.out.println("removeu");
                RemoverFornecedor remove = new RemoverFornecedor();
                remove.remove(lista.getId());
                carregarTableViewFornecedores();
                
            }
        }else{
        
            try {
                
            } catch (Exception e) {
                System.err.println("e");
            }
            
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

    
    
    //remover diretamente
    @FXML
    public void onRemove() {

         if(!tfNome.getText().equals("")){
        System.out.println("remover");
        
        BuscarFornecedor busca = new BuscarFornecedor();

        Fornecedores lista = new Fornecedores();

        
        
        try {

            lista = busca.buscaSingle(tfNome.getText());           
            String nome = lista.getNome();
            

                String cnpj = lista.getCnpj();
                String email = lista.getEmail();
                String endereco = lista.getEndereco();
                String telefone = lista.getTelefone();
                String text = "Nome: " + nome + "\n"
                        + "CNPJ: " + cnpj + "\n"
                        + "Email: " + email + "\n"
                        + "Endereço: " + endereco + "\n"
                        + "Telefone: " + telefone + ".";

                if(lista.getNome()!=null){
                    
             
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(nome);
                alert.setHeaderText("Confirmar exclusão do fornecedor?");
                alert.setContentText(text);

                ButtonType btRemover = new ButtonType("Sim, Remover");
                ButtonType btCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(btRemover, btCancel);

                Optional<ButtonType> result = alert.showAndWait();

                 if (result.get() == btRemover) {
                    RemoverFornecedor remove = new RemoverFornecedor();
                    remove.remove(lista.getId());
                    carregarTableViewFornecedores();
                   } 
                 
             }else{
                     Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Erro");
                    alert.setHeaderText(null);
                    alert.setContentText("Fornecedor Não encontrado!");

                    alert.showAndWait();
                 }   
             }
       
              
         catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
         }
    }
    //Fim da View Buscar Fornecedores
    
    
    
    
    public void onFiltrar() {
        
        if(!tfNome.getText().equals("")){

        BuscarFornecedor busca = new BuscarFornecedor();

        Fornecedores lista = new Fornecedores();

        try {
            lista = busca.buscaSingle(tfNome.getText());              
                String nome = lista.getNome();          
                String cnpj = lista.getCnpj();
                String email = lista.getEmail();
                String endereco = lista.getEndereco();
                String telefone = lista.getTelefone();
                String text = "Nome: " + nome + "\n"
                        + "CNPJ: " + cnpj + "\n"
                        + "Email: " + email + "\n"
                        + "Endereço: " + endereco + "\n"
                        + "Telefone: " + telefone + ".";

                 if(lista.getNome()!=null){

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
                            editar(lista);
                         } else if (result.get() == btRemover) {
                          System.out.println("removeu");
                          RemoverFornecedor remove = new RemoverFornecedor();
                          remove.remove(lista.getId());
                          carregarTableViewFornecedores();

            }
                        
            
                 }else{
                     Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Erro");
                    alert.setHeaderText(null);
                    alert.setContentText("Fornecedor Não encontrado!");

                    alert.showAndWait();
                 }                       
       
             }  
         catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
        
    }
    }
    //Fim da View Buscar Fornecedores
}
