/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Vorb
 */
public class UIController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
