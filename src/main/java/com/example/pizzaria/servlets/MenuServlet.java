package com.example.pizzaria.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzaria", "root", "root");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Pizzas");

            while (rs.next()) {
                out.println("<div>");
                out.println("<h3>" + rs.getString("nome") + "</h3>");
                out.println("<p>" + rs.getString("descricao") + "</p>");
                out.println("<p>Preço: " + rs.getDouble("preco") + "</p>");
                out.println("<button>Adicionar ao Carrinho</button>");
                out.println("</div>");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
