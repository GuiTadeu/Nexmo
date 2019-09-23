package com.nexmo.client;

import java.io.IOException;
import java.util.Scanner;

import com.nexmo.client.verify.CheckResponse;
import com.nexmo.client.verify.VerifyClient;
import com.nexmo.client.verify.VerifyRequest;
import com.nexmo.client.verify.VerifyResponse;
import com.nexmo.client.verify.VerifyStatus;

public class Client {
    
    public static void main(String[] args) throws IOException, NexmoClientException {

        /**
        * Criando o Cliente Nexmo
        * */
	NexmoClient client = new NexmoClient.Builder()
	    .apiKey("INSIRA SUA API KEY")
	    .apiSecret("INSIRA SUA API SECRET")
            .build();
	VerifyClient verifyClient = client.getVerifyClient();

	/**
	* Configurando o Telefone (DDI+DDD+TEL), Empresa e
	* Tamanho do Token (4-6)
	* */
	VerifyRequest request = new VerifyRequest("SEU TELEFONE", "O NOME DA SUA EMPRESA");
	request.setLength(4);

	/**
	* Enviando a mensagem via SMS
	* */
	VerifyResponse verifyResponse = verifyClient.verify(request);

	System.out.println("Digite o Código Recebido: ");

	Scanner input = new Scanner(System.in);
	String code = input.nextLine();

	String requestId = verifyResponse.getRequestId();

	/**
	* Checando se o Token digitado pelo usuário é 
	* o mesmo enviado por mensagem pela API.
	* */
	CheckResponse checkResponse = verifyClient.check(requestId, code);
	if (checkResponse.getStatus() == VerifyStatus.OK) {
	    System.out.println("SUCESSO!");
	} else {
	    System.out.println("ERRO!");
	}

	input.close();
    }
}
