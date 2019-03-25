let codigoCategoria = document.getElementById("codigo-categoria");
let descricaoCategoria = document.getElementById("descricao-categoria");
let btSalvar = document.getElementById("salvar");
let btCancelar = document.getElementById("cancelar");
let btExcluir = document.getElementById("excluir");

codigoCategoria.addEventListener("focusout", () => codigoCategoriaFocusLost());
btSalvar.addEventListener("click", () => btSalvarClick());
btCancelar.addEventListener("click", () => btCancelarClick());
btExcluir.addEventListener("click", () => btExcluirClick());

function codigoCategoriaFocusLost() {
    alert("111q");
}

function btSalvarClick() {
    const fun = "salvarCategoria";
    const codigo = codigoCategoria.value;
    const descricao = descricaoCategoria.value;
    const dados = {
        fun: fun,
        codigo: codigo,
        descricao: descricao
    };
    return axios
    .post(`functions?fun=${fun}&codigo=${codigo}&descricao=${descricao}`)
    .then(response => {
        alert(response.data);
    })
    .catch(error => {
        alert('oops, something went wrong!', error);
    });
}

function btCancelarClick() {
    alert("333");
}

function btExcluirClick() {
    alert("444");
}


