// ler o JSON enviado pelo backend (GET)
function getJson(url){
  var xhr = new XMLHttpRequest();	
  xhr.open('GET', url, true);
  xhr.responseType = 'json';

  xhr.onload = function() {
    var status = xhr.status;
    if (status === 200) {
	  console.log("GET Efetuado \n" + "Resposta tipo: " + xhr.responseType +"\n");
      console.log(xhr.response);

    } else {
      console.log("Erro no GET");
    }
  };
  xhr.send();	
};


// Envia JSON para o backend (POST)
function postJSON(form, data, url){
  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);

  xhr.onload = function() {
    var status = xhr.status;
    if (status === 200) {
      console.log(xhr.response);
      alert(xhr.response);
      form.reset();
    } else {
		var jsonResponse = JSON.parse(xhr.responseText);
      alert("Erro Num: " + xhr.status + "\n" + jsonResponse["message"]);
		  console.log(jsonResponse["message"]);
		console.log(jsonResponse);
    }
    };

  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify(data));
  console.log(xhr.responseText);
  xhr.onerror = function(e) {
      alert("Error Status: " + e.target.status);
  };
  console.log(data); //data send
}


// faz requerimento de qualquer tipo (GET, POST...)
function makeRequest(method, url, form = null) {
    return new Promise(function (resolve, reject) {
        let xhr = new XMLHttpRequest();
        xhr.open(method, url);
        xhr.onload = function () {
            if (this.status >= 200 && this.status < 300) {
				if(form) form.reset();
                resolve(JSON.parse(xhr.response));
            } else {
                reject({
                    status: this.status,
                    statusText: xhr.statusText
                });
            }
        };
        xhr.onerror = function () {
            reject({
                status: this.status,
                statusText: xhr.statusText
            });
        };
        xhr.send();
    });
}


// cadastra novo cliente
function cadastrar(){
  var form = document.getElementById("form-cadastro")
  var url = "http://localhost:8080/cadastro";
  var data = {
      "email" : document.getElementById("email").value,
      "senha" :  document.getElementById("senha").value,
      "nome" : document.getElementById("nome").value,
	  "renda": document.getElementById("renda").value,
	  "cpf": document.getElementById("cpf").value,
	  "rg": document.getElementById("rg").value,
	  "endereco": document.getElementById("endereco").value
  }

	campoVazio(data, postJSON(form, data, url));

}

// Faz um pedido de empréstimo
function pedir_emprestimo(){
  var form = document.getElementById("form-emprestimo");
  var url = "http://localhost:8080/cliente/pedir_emprestimo";
  var data = {
    "valor" : toBigDecimal(document.getElementById("valor").value),
    "parcelas" :  document.getElementById("parcelas").value,
    "primeira_parcela": document.getElementById("primeira-parcela").value
  }
  campoVazio(data, postJSON(form, data, url));
}

// converte string ppara formato apropriado antes de enviar papra o backend
function toBigDecimal(str){
	return str.replace(",",".");
}

// Transforma o valor em string e adiciona R$
function mostraReais(obj, atrib, str){
	//if (attr === "valor") emprestimo[attr] = mostraReais(emprestimo[attr]);
	if(atrib == str){
		var valor = obj[atrib];
		if(valor%1 !=0) {
			valor = valor.toString().replace(".",",");
		}else{
			valor = valor.toString()+",00";
		}
		return ("R$ " +valor);
	} else{
		return obj[atrib];
	}
}


// Carrega informações da página cliente.html ao chamar o body
async function loadCliente(){
	var url = "http://localhost:8080/cliente/perfil";
    const cliente = await makeRequest("GET",url);
    var label_cliente = document.createElement("label");
	var atributos = ["email","renda"];
	// cria elementos para os atribuos selecionado do cliente
	for(var atributo in cliente){
		if(atributos.includes(atributo)){
			cliente[atributo] = mostraReais(cliente, atributo, "renda");
			label_cliente.appendChild(document.createTextNode(atributo+ " : " + cliente[atributo]))
			var br = document.createElement("br");
			label_cliente.appendChild(br);
		}
	}
	clienteLabel.appendChild(label_cliente);
	
	url = "http://localhost:8080/cliente/emprestimos";
	//label para adicionar os elementos
	var label_emprestimo = document.createElement("label");
	label_emprestimo.id = "label_emprestimo";
	const emprestimos = await makeRequest("GET", url);
	// cria os botões de detalhar para cada empréstimo listado
	var listarEmprestimos = function(){
		for (var emprestimo of emprestimos){
			var btn = document.createElement("button");
			var codigo = emprestimo["id"];
			btn.innerHTML = "DETALHAR";
			btn.id = "btnDetalhar" + codigo;
			btn.onclick = function (){
				detalharEmprestimo(emprestimo);
			};
			label_emprestimo.appendChild(btn);
			console.log(btn.id);
			
		// cria elementos para os atribuos selecionado do emprestimo
	    for(attr in emprestimo){
			if(["id","valor","parcelas"].includes(attr)){
		      emprestimo[attr] = mostraReais(emprestimo, attr,"valor");
		      label_emprestimo.appendChild(document.createTextNode(attr + ":  " + emprestimo[attr] +" | "));
			}
	    }
			var br = document.createElement("br");
			label_emprestimo.appendChild(br);
				
		}
		emprestimo_div.appendChild(label_emprestimo)
	}
	
	listarEmprestimos();
}

// Apaga a lista de empréstimos e exibe os detalhes apenas do empréstiom selecionado
function detalharEmprestimo(emprestimo){
	document.getElementById("label_emprestimo").remove();
	var label_emprestimo = document.createElement("label");
	label_emprestimo.id = "label_emprestimo"
	for(attr in emprestimo){
      //emprestimo[attr] = mostraReais(emprestimo, attr,"valor");
      label_emprestimo.appendChild(document.createTextNode(attr + ":  " + emprestimo[attr]));
	  var br = document.createElement("br");
	  label_emprestimo.appendChild(br);
    }
	var btn = document.createElement("button");
	btn.innerHTML = "Voltar para Perfil";
	btn.onclick = function(){
		location.href = '/cliente.html';
	};

	label_emprestimo.appendChild(btn);
	emprestimo_div.appendChild(label_emprestimo);
}


// Testa se alguum campo do form está vazio if TRUE brea, else chama a func
function campoVazio(obj, func){
	for (attr in obj){
		console.log(attr.value);
		if (obj[attr] == ""){
			alert("Campo " + attr.toUpperCase() + " deve ser preenchido!\n" + obj[attr]);
			break;
		}
	}
	return func;
}