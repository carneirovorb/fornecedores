/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Fornecedor.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class BuscarFornecedor {
    
    public List<Fornecedores> buscar() throws SQLException{
        
        List<Fornecedores> listFornecedores = new ArrayList<Fornecedores>();
        
        Connection conexao;
        conexao = ConnectionDB.getConnection();
        
        PreparedStatement stmt = null;
       
        stmt = conexao.prepareStatement("SELECT * FROM `tb_fornecedores` WHERE 1");
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            int cnpj = rs.getInt("cnpj");
            int telefone = rs.getInt("telefone");
            String email = rs.getString("email");
            String endereco = rs.getString("endereco");
            
            Fornecedores f1 = new Fornecedores(id, nome, cnpj, telefone, email, endereco);
            listFornecedores.add(f1);
        }
        
        return listFornecedores;
    }
    
}
