let codigoExpositor = document.getElementById("codigo-expositor");
let descricaoExpositor = document.getElementById("descricao-expositor");
let btSalvar = document.getElementById("salvar");
let btCancelar = document.getElementById("cancelar");
let btExcluir = document.getElementById("excluir");
let tableExpositores = document.getElementById("table-expositores");

codigoExpositor.addEventListener("focusout", () => codigoExpositorFocusLost());
btSalvar.addEventListener("click", () => btSalvarClick());
btCancelar.addEventListener("click", () => btCancelarClick());
btExcluir.addEventListener("click", () => btExcluirClick());
window.onload = () => {
    carregaTableExpositores();
};

function codigoExpositorFocusLost() {
    const fun = "lerExpositor";
    const codigo = codigoExpositor.value;
    return axios
    .post(`FunctionsExpositor?fun=${fun}&codigo=${codigo}`)
    .then(response => {
        let codigo,descricao ;
        [codigo, descricao] = response.data.split("|");
        descricaoExpositor.value = descricao;
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function btSalvarClick() {
    const fun = "salvarExpositor";
    const codigo = codigoExpositor.value;
    const descricao = descricaoExpositor.value;
    return axios
    .post(`FunctionsExpositor?fun=${fun}&codigo=${codigo}&descricao=${descricao}`)
    .then(response => {
        alert("Expositor salvo com sucesso!");
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
    if (confirm("Deseja realmente excluir o expositor?")) {
        const fun = "excluirExpositor";
        const codigo = codigoExpositor.value;
        const descricao = descricaoExpositor.value;
        return axios
        .post(`FunctionsExpositor?fun=${fun}&codigo=${codigo}`)
        .then(response => {
            alert("Expositor excluido com sucesso!");
            location.reload();
        })
        .catch(error => {
            alert('oops, algo deu errado!', error);
        });
    }
}

function carregaTableExpositores() {
    const fun = "listarExpositores";
    return axios
    .post(`FunctionsExpositor?fun=${fun}`)
    .then(response => {
        let conteudo = `<thead><tr><th scope="col">Código</th><th scope="col">Descrição</th></tr></thead><tbody>`;
        let linhas = response.data.split("\n");
        let codigo,descricao ;
        for (let linha of linhas){
            if (linha != ""){     
                [codigo, descricao] = linha.split("|");
                conteudo += `<tr><th scope="row">${codigo}</th><td>${descricao}</td></tr>`;
            }
        }
        conteudo += `</tbody>`;
        tableExpositores.innerHTML = conteudo;
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}


