package com.example.pizzaria.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/pizzaria";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        Connection connection = null;

        try {
            // Tentar estabelecer a conexão
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            if (connection != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            }
        } catch (SQLException e) {
            // Exibir mensagem de erro caso a conexão falhe
            System.err.println("Falha ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fechar a conexão
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
