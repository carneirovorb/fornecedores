/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
 
import model.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static javafx.application.Application.launch;
 
/**
 *
 * @author Vorb
 */
public class RemoverFornecedor {
 
    public void remove(int id) throws SQLException {
 
        Connection conexao;
        conexao = ConnectionDB.getConnection();
 
        PreparedStatement stmt = null;
 
        stmt = conexao.prepareStatement("DELETE FROM tb_fornecedores WHERE id="+id);
       
         stmt.executeUpdate();
       
        ConnectionDB.closeConnection(conexao, stmt);
       
    }
}