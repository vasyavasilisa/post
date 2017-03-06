package com.demyanovavv.post.util;

/**
 * Created by USER on 06.03.2017.
 */

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MailSender {

    public MailSender() {
    }

    public void sendFromMail(HttpServletRequest request, HttpServletResponse response, String from, String pass,
                             String[] to, String subject, String body, String sender_host, String receiver_host) throws IOException {

        Properties props1 = new Properties();
        String hosname_sender = "smtp." + receiver_host.substring(1);
        List<String> data = new ArrayList<>();
        data.add("mail.smtp.starttls.enable=true");
        data.add("mail.smtp.host=" + hosname_sender);
        data.add("mail.smtp.user=" + from);
        data.add("mail.smtp.password=" + pass);
        data.add("mail.smtp.port=587");
        data.add("mail.smtp.auth=true");
        Path path = Paths.get("D:/", "config1.txt");
        Files.write(path, data);

        props1.load(new FileInputStream(new File("D:/config1.txt")));



        Session session = Session.getDefaultInstance(props1);
        MimeMessage message = new MimeMessage(session);
        PrintWriter out = response.getWriter();

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (String i : to) {
                int k = 0;
                toAddress[k++] = new InternetAddress(i);

            }

            for (InternetAddress i : toAddress) {
                message.addRecipient(Message.RecipientType.TO, i);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtps");
            transport.connect("smtp." + sender_host.substring(1), from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            out.println("<h4>Ошибка адреса:" + ae + "</h4>");
            ae.printStackTrace();
        } catch (MessagingException me) {
            out.println("<h4>Ошибка сообщения:" + me + "</h4>");
            me.printStackTrace();
        }
    }
}
