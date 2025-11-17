
# Sistema de Recuperação de Senha por E-mail — Java

Este projeto implementa um sistema funcional de recuperação de senha via e-mail utilizando Java, Jakarta Mail e autenticação SMTP do Gmail.  
O programa gera um código de verificação de 6 dígitos, envia para o e-mail do cliente e valida o código inserido pelo usuário no terminal.

---

## Objetivo do Projeto

Demonstrar na prática como integrar Java com serviços externos de e-mail, incluindo:

- Envio automático de código de verificação
- Autenticação SMTP segura (App Password do Gmail)
- Validação de código digitado pelo usuário
- Fluxo real utilizado em sistemas de redefinição de senha

---

## Tecnologias Utilizadas

- Java 8+
- Jakarta Mail (JavaMail)
- SMTP Gmail
- App Password do Google

---

## Dependências

### Maven

```xml
<dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>jakarta.mail</artifactId>
    <version>2.0.1</version>
</dependency>
```

### Sem Maven

Adicione o arquivo:

```
jakarta.mail.jar
```

---

## Configuração do Gmail

Para que o envio funcione, é necessário gerar uma App Password:

1. Ative a verificação em duas etapas na sua conta Google  
2. Acesse: https://myaccount.google.com/apppasswords  
3. Gere uma senha para "Mail"  
4. Use no campo `smtpPass` no código  

---

## Código Completo

```java
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
        String smtpPass = "APP_PASSWORD";

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
```

---

## Como Executar

```bash
javac RecuperacaoSenhaEmail.java
java RecuperacaoSenhaEmail
```

---

## Exemplo de E-mail Enviado

```
Olá!
Seu código de recuperação é: 123456
Digite este código no software para redefinir sua senha.
```


## Licença

Livre para uso e modificação.
