/*
 * The MIT License
 *
 * Copyright 2023 daviramos.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.com.lacamentohoraextra.DAO;

import br.com.lacamentohoraextra.Models.ProjetoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daviramos
 */
public class ProjetoDAO {
     public List<ProjetoModel> listarProjetos() throws SQLException {
        List<ProjetoModel> lista = new ArrayList<>();
        Connection connection = ConexaoSQL.iniciarConexao();
        
        if (ConexaoSQL.status == true) {
            PreparedStatement consultaSQL;
            ResultSet resultSet;
            try {
                String query = "SELECT nome, cliente, verba, prazo FROM projeto";
                consultaSQL = connection.prepareStatement(query);
                resultSet = consultaSQL.executeQuery();
                
                while (resultSet.next()) {
                    ProjetoModel projeto = new ProjetoModel();
                    
                    projeto.setNome(resultSet.getString("nome"));
                    projeto.setCliente(resultSet.getString("cliente"));
                    projeto.setVerba(resultSet.getString("verba"));
                    projeto.setPrazo(resultSet.getString("prazo"));
                    
                    lista.add(projeto);
                }
                connection.close();
            }
            catch (SQLException e) {
                Logger.getLogger(
                        ConexaoSQL.class.getName()).log(
                        Level.SEVERE,
                        e.getMessage(),
                        e);
            }
            finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
        return lista;
    }
}
