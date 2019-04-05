let codigoCategoria = document.getElementById("codigo-categoria");
let descricaoCategoria = document.getElementById("descricao-categoria");
let btSalvar = document.getElementById("salvar");
let btCancelar = document.getElementById("cancelar");
let btExcluir = document.getElementById("excluir");
let tableCategorias = document.getElementById("table-categorias");

codigoCategoria.addEventListener("focusout", () => codigoCategoriaFocusLost());
btSalvar.addEventListener("click", () => btSalvarClick());
btCancelar.addEventListener("click", () => btCancelarClick());
btExcluir.addEventListener("click", () => btExcluirClick());
window.onload = () => {
    carregaTableCategorias();
};

function codigoCategoriaFocusLost() {
    const fun = "lerCategoria";
    const codigo = codigoCategoria.value;
    return axios
    .post(`FunctionsCategoria?fun=${fun}&codigo=${codigo}`)
    .then(response => {
        let codigo,descricao ;
        [codigo, descricao] = response.data.split("|");
        descricaoCategoria.value = descricao;
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function btSalvarClick() {
    const fun = "salvarCategoria";
    const codigo = codigoCategoria.value;
    const descricao = descricaoCategoria.value;
    return axios
    .post(`FunctionsCategoria?fun=${fun}&codigo=${codigo}&descricao=${descricao}`)
    .then(response => {
        alert("Categoria salva com sucesso!");
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
    const fun = "excluirCategoria";
    const codigo = codigoCategoria.value;
    const descricao = descricaoCategoria.value;
    return axios
    .post(`FunctionsCategoria?fun=${fun}&codigo=${codigo}`)
    .then(response => {
        alert("Categoria excluída com sucesso!");
        location.reload();
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function carregaTableCategorias() {
    const fun = "listarCategorias";
    return axios
    .post(`FunctionsCategoria?fun=${fun}`)
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
        tableCategorias.innerHTML = conteudo;
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}


