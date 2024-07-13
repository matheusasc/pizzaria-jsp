<%@ page import="java.util.*, com.example.pizzaria.model.Item" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%
    List<Item> carrinho = (List<Item>) session.getAttribute("carrinho");
%>
<html>
<head>
    <title>Carrinho de Compras</title>
    <link rel="stylesheet" type="text/css" href="css/carrinho.css">
</head>
<body>
    <div class="container">
        <h1>Carrinho de Compras</h1>
        <div>
            <%
                if (carrinho == null || carrinho.isEmpty()) {
            %>
            <h2>Seu carrinho está vazio.</h2>
            <%
            } else {
                double total = 0;
            %>
            <h2>Itens no seu carrinho:</h2>
            <%
                for (Item item : carrinho) {
                    total += item.getPreco() * item.getQuantidade();
            %>
            <div class="item">
                <p><%= item.getNome() %> - <%= item.getPreco() %> - Quantidade: <%= item.getQuantidade() %></p>
                <form action="carrinho" method="post">
                    <input type="hidden" name="id" value="<%= item.getId() %>">
                    <input type="hidden" name="action" value="remover">
                    <button type="submit">Remover</button>
                </form>
            </div>

            <%
                }
            %>
            <h3>Total: <%= total %></h3>
            <form action="pedido" method="post">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" required>
                <label for="endereco">Endereço:</label>
                <input type="text" id="endereco" name="endereco" required>
                <label for="telefone">Telefone:</label>
                <input type="text" id="telefone" name="telefone" required>
                <button type="submit">Confirmar Pedido</button>
            </form>
            <%
                }
            %>
        </div>
        <a href="menu.jsp">Continuar Comprando</a>
    </div>
</body>
</html>
