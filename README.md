# EMPRÉSTIMOS NOW!
Sistema desenvolvido para o projeto da TQI Evolution 2022 - Back-end. 
Seguem as informações:

**Backend:** Java, Spring Boot, PostgreSQL.
**Frontend:** JavaScript, CSS, HTML.


## Cenário
Uma empresa de empréstimo precisa criar um sistema de análise de crédito para fornecer aos seus clientes as seguintes funcionalidades:

 1. Cadastro de clientes
           O cliente pode cadastrar: nome, e-mail, CPF, RG, endereço completo, renda e senha.
 2. Login
        A autenticação será realizada por e-mail e senha.
 3. Solicitação de empréstimo
	- Para solicitar um empréstimo, precisamos do valor do empréstimo, data da primeira parcela e quantidade de parcelas.
	 - O máximo de parcelas será 60 e a data da primeira parcela deve ser no máximo 3 meses após o dia atual.
iv. Acompanhamento das solicitações de empréstimo
    - O cliente pode visualizar a lista de empréstimos solicitados por ele mesmo e também os detalhes de um de seus empréstimos.
    - Na listagem, devemos retornar no mínimo o código do empréstimo, o valor e a quantidade de parcelas.
    - No detalhe do empréstimo, devemos retornar: código do empréstimo, valor, quantidade de parcelas, data da primeira parcela, e-mail do cliente e renda do cliente.

## Sistema
O Sistema possui 3 páginas.

 **1)** index.html - Cliente faz seu cadastro e login.
 **2)** cliente.html - Perfil do cliente. Mostra e-mail (username), renda e uma lista com todos os empréstimos feitos (id, valor e parcelas). Os detalhes de cada empréstimo são exibidos nessa dela também (botão DETALHAR).
 **3)** ficha-emprestimo.html - Ficha para pedir um novo empréstimo.
