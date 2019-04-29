let codigoUnidade = document.getElementById("codigo-unidade");
let descricaoUnidade = document.getElementById("descricao-unidade");
let categoriaUnidade = document.getElementById("categoria-unidade");
let btSalvar = document.getElementById("salvar");
let btCancelar = document.getElementById("cancelar");
let btExcluir = document.getElementById("excluir");
let tableUnidades = document.getElementById("table-unidades");

codigoUnidade.addEventListener("focusout", () => codigoUnidadeFocusLost());
btSalvar.addEventListener("click", () => btSalvarClick());
btCancelar.addEventListener("click", () => btCancelarClick());
btExcluir.addEventListener("click", () => btExcluirClick());
window.onload = () => {
    carregaOpcoesCategoria();
    carregaTableUnidades();
};

function codigoUnidadeFocusLost() {
    const fun = "lerUnidade";
    const codigo = codigoUnidade.value;
    return axios
    .post(`FunctionsUnidade?fun=${fun}&codigo=${codigo}`)
    .then(response => {
        let codigo, descricao;
        [codigo, descricao] = response.data.split("|");
        descricaoUnidade.value = descricao;
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function btSalvarClick() {
    const fun = "salvarUnidade";
    const codigo = codigoUnidade.value;
    const descricao = descricaoUnidade.value;
    return axios
    .post(`FunctionsUnidade?fun=${fun}&codigo=${codigo}&descricao=${descricao}`)
    .then(response => {
        alert("Unidade salva com sucesso!");
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
    if (confirm("Deseja realmente excluir a unidade?")) {
        const fun = "excluirUnidade";
        const codigo = codigoUnidade.value;
        const descricao = descricaoUnidade.value;
        return axios
        .post(`FunctionsUnidade?fun=${fun}&codigo=${codigo}`)
        .then(response => {
            alert("Unidade excluída com sucesso!");
            location.reload();
        })
        .catch(error => {
            alert('oops, algo deu errado!', error);
        });
    }
}

function carregaTableUnidades() {
    const fun = "listarUnidades";
    return axios
    .post(`FunctionsUnidade?fun=${fun}`)
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
        tableUnidades.innerHTML = conteudo;
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function carregaOpcoesCategoria() {
    const opcoes = ["Opc1", "Opc2", "Opc3", "Opc4", "Opc5"];
    for (var opc of opcoes) {
        var elemento = document.createElement('option');
        elemento.appendChild(document.createTextNode(opc));
        elemento.value = opc.substring(3);
        categoriaUnidade.appendChild(elemento);
    }
}
