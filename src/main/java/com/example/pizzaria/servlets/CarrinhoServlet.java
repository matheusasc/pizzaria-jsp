package com.example.pizzaria.servlets;


import com.example.pizzaria.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarrinhoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        List<Item> carrinho = (List<Item>) session.getAttribute("carrinho");

        if (carrinho == null) {
            out.println("<h2>Seu carrinho está vazio.</h2>");
        } else {
            double total = 0;
            out.println("<h2>Itens no seu carrinho:</h2>");
            for (Item item : carrinho) {
                out.println("<p>" + item.getNome() + " - " + item.getPreco() + " - Quantidade: " + item.getQuantidade() + "</p>");
                total += item.getPreco() * item.getQuantidade();
            }
            out.println("<h3>Total: " + total + "</h3>");
        }
        out.println("<a href='menu.jsp'>Continuar comprando</a>");
        out.println("<a href='carrinho.jsp'>Finalizar compra</a>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Item> carrinho = (List<Item>) session.getAttribute("carrinho");

        if (carrinho == null) {
            carrinho = new ArrayList<>();
            session.setAttribute("carrinho", carrinho);
        }

        String action = request.getParameter("action");

        // Verifica se action não é nulo para evitar NullPointerException
        if (action != null) {
            int id = 0;
            String nome = null;
            double preco = 0.0;

            // Verifica se id não é nulo antes de tentar converter para int
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.trim().isEmpty()) {
                id = Integer.parseInt(idParam.trim());
            }

            // Verifica se nome não é nulo antes de atribuir
            nome = request.getParameter("nome");

            // Verifica se preco não é nulo antes de converter para double
            String precoParam = request.getParameter("preco");
            if (precoParam != null && !precoParam.trim().isEmpty()) {
                try {
                    preco = Double.parseDouble(precoParam.trim());
                } catch (NumberFormatException e) {
                    // Tratar caso não consiga converter para double
                    System.out.println("Erro ao converter preço para double: " + e.getMessage());
                }
            }

            if ("adicionar".equals(action)) {
                boolean itemExiste = false;
                for (Item item : carrinho) {
                    if (item.getId() == id) {
                        item.setQuantidade(item.getQuantidade() + 1);
                        itemExiste = true;
                        break;
                    }
                }

                if (!itemExiste) {
                    carrinho.add(new Item(id, nome, preco, 1));
                }
            } else if ("remover".equals(action)) {
                // Utiliza Iterator para evitar ConcurrentModificationException ao remover itens durante a iteração
                for (Iterator<Item> iterator = carrinho.iterator(); iterator.hasNext();) {
                    Item item = iterator.next();
                    if (item.getId() == id) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }

        response.sendRedirect("carrinho.jsp");
    }

}