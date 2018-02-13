package br.com.harlan.avaliabus.view.services;

public interface Messages {
    String FIREBASE_AUTH_WEAK_PASSWORD_EXCEPTION = "Digite uma senha mais forte, contendo mais caracteres e com letras e números.";
    String FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION = "O e-mail digitado é inválido, digite um novo e-mail.";
    String FIREBASE_AUTH_USER_COLLISION_EXCEPTION = "Esse e-mail já está em uso no aplicativo.";
    String AUTH_EXCEPTION = "Erro ao cadastrar usuário.";
    String LOGIN_SUCESSFUL = "Login efetuado com sucesso.";
    String FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_LOGIN = "Usuário ou senha incorretos.";
    String FIREBASE_AUTH_INVALID_USER_EXCEPTION_LOGIN = "Usuário não cadastrado.";
    String LOGIN_EXCEPTION = "Desculpe, mas algo deu errado ao validar seu usuário.";
    String EVALUATION_SENT_SUCESSFUL = "Avaliação enviada com sucesso.";
    String EVALUATON_SENT_EXCEPTION_LOGIN_REQUIRED = "É necessário está logado para registrar uma avaliação.";
    String EVALUATION_SENT_NETWORK_EXCEPTION = "Não foi possível estabelecer uma conexão com a internet.";
    String EVALUATION_SENT_EXCEPTION = "Ocorreu um erro ao enviar sua avaliação.";
    String IMAGES_SENT_SUCESSFUL = "Imagens enviadas com sucesso.";
    String IMAGES_UPLOADING = "Aguarde um instante enquanto envio as imagens pra você.\n";
    String IMAGES_PERCENT_UPLOAD = "% concluído";
    String IMAGES_UPLOAD_TITLE_PROGRESSS_DIALOG = "Enviando imagens";
    String SESSION_CLOSED = "Sessão encerrada";
    String ARRAY_ADAPTER_NULL = "Ocorreu um erro ao tentar exibir suas avaliações.";
}