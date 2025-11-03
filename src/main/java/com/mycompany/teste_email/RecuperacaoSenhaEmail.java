/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.teste_email;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class RecuperacaoSenhaEmail {
    
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.print("Digite o e-mail do cliente: ");
        String emailCliente = scanner.nextLine();

        // gera codigo aleatorio
        String codigo = String.format("%06d", random.nextInt(1_000_000));

        // config gmail
        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;
        String smtpUser = "EMAIL@GMAIL.com";
        String smtpPass = "APP_PASSWORD"

        // config smtp
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUser, smtpPass);
            }
        });

        // monta email e envia
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(smtpUser));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailCliente));
        message.setSubject("TODOIT - Código de Recuperação de Senha");
        message.setText("Olá!\nSeu código de recuperação é: " + codigo +
                "\nDigite este código no software para redefinir sua senha.");

        Transport.send(message);
        System.out.println("Código enviado para o e-mail do cliente!");

        System.out.print("Digite o código recebido: ");
        String codigoRecebido = scanner.nextLine();

        // Verifica se o código está correto
        if (codigo.equals(codigoRecebido)) {
            System.out.println("Código correto! Você pode redefinir a senha.");
        } else {
            System.out.println("Código incorreto. Tente novamente.");
        }

        scanner.close();
    }
}
