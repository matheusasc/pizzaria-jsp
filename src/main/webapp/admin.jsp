<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Área Administrativa</title>
    <link rel="stylesheet" type="text/css" href="css/admin.css">
</head>
<body>
    <div class="container">
        <h1>Área Administrativa</h1>
        <form action="admin" method="post">
            <label for="nome">Nome da Pizza:</label>
            <input type="text" id="nome" name="nome" required>
            <label for="descricao">Descrição:</label>
            <input type="text" id="descricao" name="descricao" required>
            <label for="preco">Preço:</label>
            <input type="text" id="preco" name="preco" required>
            <button type="submit">Adicionar Pizza</button>
        </form>
        <div>
            <%-- Aqui o AdminServlet irá inserir a lista de pedidos --%>
        </div>
    </div>
    <a href="index.jsp">Voltar para a Página Inicial</a>
</body>
</html>
