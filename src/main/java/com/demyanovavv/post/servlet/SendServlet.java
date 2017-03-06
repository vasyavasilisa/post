package com.demyanovavv.post.servlet;

/**
 * Created by USER on 06.03.2017.
 */

import com.demyanovavv.post.util.MailSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/first.html")
public class SendServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String USER_NAME = "sender"; // отправитель
    private static final String SENDER_HOST = "hostname_sender";
    private static final String PASSWORD = "password"; // Отправитель
    private static final String RECEIVER = "receiver";// Получатель
    private static final String RECEIVER_HOST = "hostname_receiver";// Получатель
    private static final String SUBJECT = "subject";// Тема
    private static final String BODY = "body";// Тело

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        StringBuilder from = new StringBuilder();
        String user_name = request.getParameter(USER_NAME);
        String sender_host = request.getParameter(SENDER_HOST);
        from.append(user_name).append(sender_host);

        String pass = request.getParameter(PASSWORD);

        StringBuilder receiver_adress = new StringBuilder();
        String receiver = request.getParameter(RECEIVER);
        String receiver_host = request.getParameter(RECEIVER_HOST);
        receiver_adress.append(receiver).append(receiver_host);

        String[] to = {receiver_adress.toString()}; // list of recipient email
        // addresses
        String subject = request.getParameter(SUBJECT);
        String body = request.getParameter(BODY);

        ////////////////////////
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<head>");
        out.println("<meta charset=UTF-8\">");
        out.println("<title>Server</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h4>Отправитель: " + from + "</h4>");
        out.println("<h4>Получатель: " + receiver_adress.toString() + "</h4>");
        //////////////////////////////////////
        MailSender send = new MailSender();
        send.sendFromMail(request, response, from.toString(), pass, to, subject, body, sender_host, receiver_host);
    }


}