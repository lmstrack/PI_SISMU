let idExposicao = document.getElementById("id-exposicao");
let expositorExposicao = document.getElementById("expositor-exposicao");
let unidadeExposicao = document.getElementById("unidade-exposicao");
let dataInicioExposicao = document.getElementById("data-inicio-exposicao");
let dataFimExposicao = document.getElementById("data-fim-exposicao");
let btSalvar = document.getElementById("salvar");
let btCancelar = document.getElementById("cancelar");
let btExcluir = document.getElementById("excluir");
let tableExposicoes = document.getElementById("table-exposicoes");

idExposicao.addEventListener("focusout", () => idExposicaoFocusLost());
btSalvar.addEventListener("click", () => btSalvarClick());
btCancelar.addEventListener("click", () => btCancelarClick());
btExcluir.addEventListener("click", () => btExcluirClick());
window.onload = () => {
    carregaOpcoesExpositor();
    carregaOpcoesUnidade();
    carregaTableExposicoes();
};

function idExposicaoFocusLost() {
    const fun = "lerExposicao";
    const codigo = idExposicao.value;
    if (codigo == "") {
        return;
    }
    return axios
    .post(`FunctionsExposicao?fun=${fun}&codigo=${codigo}`)
    .then(response => {
        let id, expositor, unidade, dataInicio, dataFim;
        [id, expositor, unidade, dataInicio, dataFim] = response.data.split("|");
        expositorExposicao.value = expositor;
        unidadeExposicao.value = unidade;
        dataInicioExposicao.value = dataInicio;
        dataFimExposicao.value = dataFim;
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function btSalvarClick() {
    const fun = "salvarExposicao";
    const codigo = idExposicao.value;
    const dataInicio = dataInicioExposicao.value;
    const dataFim = dataFimExposicao.value;
    const expositor = expositorExposicao.value;
    const unidade = unidadeExposicao.value;
    return axios
    .post(`FunctionsExposicao?fun=${fun}&codigo=${codigo}&data-inicio=${dataInicio}&data-fim=${dataFim}&expositor=${expositor}&unidade=${unidade}`)
    .then(response => {
        alert("Exposição salva com sucesso!");
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
    if (confirm("Deseja realmente excluir a exposição?")) {
        const fun = "excluirExposicao";
        const codigo = idExposicao.value;
        //const descricao = descricaoCategoria.value;
        return axios
        .post(`FunctionsExposicao?fun=${fun}&codigo=${codigo}`)
        .then(response => {
            alert("Exposição excluída com sucesso!");
            location.reload();
        })
        .catch(error => {
            alert('oops, algo deu errado!', error);
        });
    }
}

function carregaTableExposicoes() {
    const fun = "listarExposicoes";
    return axios
    .post(`FunctionsExposicao?fun=${fun}`)
    .then(response => {
        let conteudo = `<thead><tr><th scope="col">ID</th><th scope="col">Expositor</th><th scope="col">Unidade</th><th scope="col">Data início</th><th scope="col">Data fim</th></tr></thead><tbody>`;
        let linhas = response.data.split("\n");
        let codigo,expositor, unidade, dataInicio, dataFim ;
        for (let linha of linhas){
            if (linha != ""){     
                [codigo, expositor, unidade, dataInicio, dataFim] = linha.split("|");
                conteudo += `<tr><th scope="row">${codigo}</th><td>${expositor}</td><td>${unidade}</td><td>${dataInicio}</td><td>${dataFim}</td></tr>`;
            }
        }
        conteudo += `</tbody>`;
        tableExposicoes.innerHTML = conteudo;
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function carregaOpcoesExpositor() {
    const fun = "listarExpositores";
    return axios
    .post(`FunctionsExposicao?fun=${fun}`)
    .then(response => {
        
        let linhas = response.data.split("\n");
        let codigo,descricao;
        for (let linha of linhas){
            if (linha != ""){     
                var elemento = document.createElement('option');
                [codigo, descricao] = linha.split("|");
                elemento.appendChild(document.createTextNode(descricao));
                elemento.value = codigo;
                expositorExposicao.appendChild(elemento);
            }
        }
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function carregaOpcoesUnidade() {
    const fun = "listarUnidades";
    return axios
    .post(`FunctionsExposicao?fun=${fun}`)
    .then(response => {
        
        let linhas = response.data.split("\n");
        let codigo,descricao;
        for (let linha of linhas){
            if (linha != ""){     
                var elemento = document.createElement('option');
                [codigo, descricao] = linha.split("|");
                elemento.appendChild(document.createTextNode(descricao));
                elemento.value = codigo;
                unidadeExposicao.appendChild(elemento);
            }
        }
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}


