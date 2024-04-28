# Projeto de Sistema Bancário Em Java Com Utilização Threads
  Este projeto é uma atividade indivídual da disciplina de Porgramação Concorrente e Distribuída da Universidade Católica de Brasília. Você pode conferir mais informações da atividade sugerida logo abaixo.
  
  ***IMPORTANTE: TODO O PROJETO FOI FEITO UTILIZANDO JAVA 20. APESAR DO PROJETO SOLICITAR JAVA 17 NÃO HOUVE IMPEDIMENTOS QUANTO UTILIZAR VERSÕES MAIS RECENTES.***

# Sistema Bancário

Crie um projeto (em Java 17) para representar um sistema bancário utilizando threads e os conceitos
vistos em sala.

- O sistema deve conter pelo menos as seguintes entidades:
  - Banco
  - Loja
  - Funcionário
  - Cliente
  - Conta

- E deve conter um total de:
  - 1 banco
  - 2 lojas
  - 4 funcionários (2 por loja)
  - 5 clientes

- Cada cliente:
  - Deve ser uma thread;
  - Deve possuir uma conta com um saldo inicial de R$ 1.000,00;
  - Deve realizar compras, de R$ 100,00 ou R$ 200,00, alternando as lojas, até o saldo da conta estar
    vazio.
    
- Cada loja:
  - Deve possuir uma conta, para receber os pagamentos dos clientes;
  - Deve pagar os funcionários assim que possuir o valor dos seus salários (R$ 1.400,00);
  
- Cada funcionário:
  - Deve ser uma thread;
  - Deve possuir duas contas, uma para receber o salário da loja e outra de investimentos;
  - Deve investir 20% do salário na conta de investimentos logo após seu recebimento.
  
- O banco:
  - Deve intermediar as transações de forma síncrona e coordenada, garantindo a consistência dos
    saldos das contas.

# Como o projeto está estruturado ?

- src
  - Main (Classe)
   
  - BankSystem (package)
    - Account
    - Bank 
        
  - Exception (package)
    - InsufficientFundException
        
  - Person (package)
    - Client
    - Employee
    - Person 
       
  - Store (package)
    - Store
       
    

