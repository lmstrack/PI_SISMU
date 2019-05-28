let codigoDoador = document.getElementById("codigo-doador");
let nomeDoador = document.getElementById("nome-doador");
let cidadeDoador = document.getElementById("cidade-doador");
let cpfCnpjDoador = document.getElementById("cpf-cnpj-doador");
let enderecoDoador = document.getElementById("endereco-doador");
let identidadeDoador = document.getElementById("identidade-doador");
let btSalvar = document.getElementById("salvar");
let btCancelar = document.getElementById("cancelar");
let btExcluir = document.getElementById("excluir");
let tableDoadores = document.getElementById("table-doadores");

codigoDoador.addEventListener("focusout", () => codigoDoadorFocusLost());
btSalvar.addEventListener("click", () => btSalvarClick());
btCancelar.addEventListener("click", () => btCancelarClick());
btExcluir.addEventListener("click", () => btExcluirClick());
window.onload = () => {
    carregaTableDoadores();
};

function codigoDoadorFocusLost() {
    const fun = "lerDoador";
    const codigo = codigoDoador.value;
    return axios
    .post(`FunctionsDoador?fun=${fun}&codigo=${codigo}`)
    .then(response => {
        let codigo, nome, cidade, cpfCnpj, endereco, identidade;
        [codigo, nome, cidade, cpfCnpj, endereco, identidade] = response.data.split("|");
        nomeDoador.value = nome;
        cidadeDoador.value = cidade;
        cpfCnpjDoador.value = cpfCnpj;
        enderecoDoador.value = endereco;
        identidadeDoador.value = identidade;       
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function btSalvarClick() {
    const fun = "salvarDoador";
    const codigo = codigoDoador.value;
    const nome = nomeDoador.value;
    const cidade = cidadeDoador.value;
    const cpfCnpj = cpfCnpjDoador.value;
    const endereco = enderecoDoador.value;
    const identidade = identidadeDoador.value;
    return axios
    .post(`FunctionsDoador?fun=${fun}&codigo=${codigo}&nome=${nome}&cidade=${cidade}&cpf_cnpj=${cpfCnpj}&endereco=${endereco}&identidade=${identidade}`)
    .then(response => {
        alert("Doador salvo com sucesso!");
        location.reload();
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function btCancelarClick() {
    location.reload();
}

function btExcluirClick() {
    if (confirm("Deseja realmente excluir o doador?")) {
        const fun = "excluirDoador";
        const codigo = codigoDoador.value;
        return axios
        .post(`FunctionsDoador?fun=${fun}&codigo=${codigo}`)
        .then(response => {
            alert("Doador excluído com sucesso!");
            location.reload();
        })
        .catch(error => {
            alert('oops, algo deu errado!', error);
        });
    }
}

function carregaTableDoadores() {
    const fun = "listarDoadores";
    return axios
    .post(`FunctionsDoador?fun=${fun}`)
    .then(response => {
        let conteudo = `<thead><tr><th scope="col">Código</th><th scope="col">Nome</th></tr></thead><tbody>`;
        let linhas = response.data.split("\n");
        let codigo, nome ;
        for (let linha of linhas){
            if (linha != ""){     
                [codigo, nome] = linha.split("|");
                conteudo += `<tr><th scope="row">${codigo}</th><td>${nome}</td></tr>`;
            }
        }
        conteudo += `</tbody>`;
        tableDoadores.innerHTML = conteudo;
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}


