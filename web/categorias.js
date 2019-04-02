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
        alert('oops, something went wrong!', error);
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
        alert('oops, something went wrong!', error);
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
        alert(response.data);
        location.reload();
    })
    .catch(error => {
        alert('oops, something went wrong!', error);
    });
}

function carregaTableCategorias() {
    let conteudo = `                              <thead>
                                <tr>
                                  <th scope="col">Código</th>
                                  <th scope="col">Descrição</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr>
                                  <th scope="row">1</th>
                                  <td>Mark</td>
                                </tr>
                                <tr>
                                  <th scope="row">2</th>
                                  <td>Jacob</td>
                                </tr>
                                <tr>
                                  <th scope="row">3</th>
                                  <td>Larry the Bird</td>
                                </tr>
                                <tr>
                                  <th scope="row">3</th>
                                  <td>Larry the Bird</td>
                                </tr>
                                <tr>
                                  <th scope="row">3</th>
                                  <td>Larry the Bird</td>
                                </tr>
                                <tr>
                                  <th scope="row">3</th>
                                  <td>Larry the Bird</td>
                                </tr>
                                <tr>
                                  <th scope="row">3</th>
                                  <td>Larry the Bird</td>
                                </tr>
                                <tr>
                                  <th scope="row">3</th>
                                  <td>Larry the Bird</td>
                                </tr>
                              </tbody>
`;
    tableCategorias.innerHTML = conteudo;
}


