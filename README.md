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

Sem Maven

Adicione o arquivo:

jakarta.mail.jar

Configuração do Gmail

Para que o envio funcione, é necessário gerar uma App Password:

    Ative a verificação em duas etapas na sua conta Google

    Acesse: https://myaccount.google.com/apppasswords

Gere uma senha para "Mail"

Use no campo smtpPass no código
