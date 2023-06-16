package com.crud.restapi.Entidades;
import java.sql.*;

public class CRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/crud_group";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Conectar ao banco de dados
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            // Criar um registro
            criarRegistro(connection, "Luquian César", "Luquian.santos@outlook.com");
            // Ler todos os registros
            lerRegistros(connection);
            // Atualizar um registro
            atualizarRegistro(connection, 1, "Kauã", "Kauãultimoromantico@hotmail.com");
            // Excluir um registro
            excluirRegistro(connection, 9);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar a conexão com o banco de dados
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void criarRegistro(Connection connection, String nome, String email) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nome);
        statement.setString(2, email);
        int linhasAfetadas = statement.executeUpdate();
        System.out.println(linhasAfetadas + " registro(s) criado(s) com sucesso.");
        statement.close();
    }
    public static void lerRegistros(Connection connection) throws SQLException {
        String sql = "SELECT * FROM usuarios";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            String email = resultSet.getString("email");
            System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
        }
        resultSet.close();
        statement.close();
    }
    public static void atualizarRegistro(Connection connection, int id, String nome, String email) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nome);
        statement.setString(2, email);
        statement.setInt(3, id);
        int linhasAfetadas = statement.executeUpdate();
        System.out.println(linhasAfetadas + " registro(s) atualizado(s) com sucesso.");
        statement.close();
    }
    public static void excluirRegistro(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int linhasAfetadas = statement.executeUpdate();
        System.out.println(linhasAfetadas + " registro(s) excluído(s) com sucesso.");
        statement.close();
    }
}