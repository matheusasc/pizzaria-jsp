package com.example.pizzaria.servlets;

import com.example.pizzaria.model.Item;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PedidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Item> carrinho = (List<Item>) session.getAttribute("carrinho");

        if (carrinho != null && !carrinho.isEmpty()) {
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String telefone = request.getParameter("telefone");

            request.setAttribute("clienteNome", nome);
            request.setAttribute("clienteEndereco", endereco);
            request.setAttribute("clienteTelefone", telefone);
            request.setAttribute("carrinho", carrinho);

            // Limpar o carrinho após o pedido ser finalizado
            session.removeAttribute("carrinho");

            request.getRequestDispatcher("confirmacao.jsp").forward(request, response);
        } else {
            response.sendRedirect("carrinho.jsp");
        }
    }
}

