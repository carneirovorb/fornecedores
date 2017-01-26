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
            String cnpj = rs.getString("cnpj");
            String telefone = rs.getString("telefone");
            String email = rs.getString("email");
            String endereco = rs.getString("endereco");
            
            Fornecedores f1 = new Fornecedores(id, nome, cnpj, telefone, email, endereco);
            listFornecedores.add(f1);
        }
        
        return listFornecedores;
    }
    
    public Fornecedores buscaSingle(String query) throws SQLException{
        
        Fornecedores f1 = new Fornecedores();
        
        Connection conexao;
        conexao = ConnectionDB.getConnection();
        
        PreparedStatement stmt = null;
        
        stmt = conexao.prepareStatement("SELECT * FROM `tb_fornecedores` WHERE nome = '" + query + "'" + " OR cnpj = '" + query+"'");
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            f1.setId(rs.getInt("id"));
            f1.setNome(rs.getString("nome"));
            f1.setCnpj(rs.getString("cnpj"));
            f1.setEmail(rs.getString("email"));
            f1.setEndereco(rs.getString("endereco"));
            f1.setTelefone(rs.getString("telefone"));         
        }
        
        return f1;
    }
    
}
