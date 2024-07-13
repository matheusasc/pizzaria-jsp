<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzaria", "root", "root");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM Pizzas");
%>
<html>
<head>
    <title>Menu de Pizzas</title>
    <link rel="stylesheet" type="text/css" href="css/menu.css">
</head>
<body>
    <div class="container">
        <h1>Menu de Pizzas</h1>
        <div class="menu-itens">
            <%
                while (rs.next()) {
            %>
            <div class="pizza-item">
                <h3><%= rs.getString("nome") %></h3>
                <p><%= rs.getString("descricao") %></p>
                <p>Preço: <%= rs.getDouble("preco") %></p>
                <form action="carrinho" method="post">
                    <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
                    <input type="hidden" name="nome" value="<%= rs.getString("nome") %>">
                    <input type="hidden" name="preco" value="<%= rs.getDouble("preco") %>">
                    <input type="hidden" name="action" value="adicionar">
                    <button type="submit">Adicionar ao Carrinho</button>
                </form>
            </div>
            <%
                }
                rs.close();
                stmt.close();
                con.close();
            %>
        </div>
        <a href="carrinho.jsp">Ver Carrinho</a>
        <a href="index.jsp">Voltar para a Página Inicial</a>
    </div>
</body>
</html>
