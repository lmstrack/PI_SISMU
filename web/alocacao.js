let idExposicao = document.getElementById("id-exposicao");
let expositorExposicao = document.getElementById("expositor-exposicao");
let unidadeExposicao = document.getElementById("unidade-exposicao");
let unidadeDatIni = document.getElementById("data-inicio-exposicao");
let unidadeDatFim = document.getElementById("data-fim-exposicao");
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
    carregaCardsExposicoes();
};

function idExposicaoFocusLost() {
    const fun = "lerExposicao";
    const codigo = idExposicao.value;
    return axios
    .post(`FunctionsExposicao?fun=${fun}&codigo=${codigo}`)
    .then(response => {
        let codigo,descricao ;
        [codigo, descricao] = response.data.split("|");
        //descricaoCategoria.value = descricao;
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function btSalvarClick() {
    const fun = "salvarExposicao";
    const codigo = idExposicao.value;
    const expositor = expositorExposicao.value;
    const unidades = unidadeExposicao.value;
    const datIni = unidadeDatIni.value;
    const datFim = unidadeDatFim.value;
    if (datIni > datFim){
        alert("Erro: Data inicial é maior que a data final!");
    } else{
        return axios
        .post(`FunctionsExposicao?fun=${fun}&codigo=${codigo}&expositor=${expositor}&unidades=${unidades}&datIni=${datIni}&datFim=${datFim}`)
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

function carregaCardsExposicoes(){
    let conteudo = `<div class="row">
        <div class="col-sm-4">
            <div class="card" style="">
                <div class="card-header">
                    Teste
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card" style="">
                <div class="card-header">
                    Teste
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card" style="">
                <div class="card-header">
                    Teste
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <p/>
    </div>
    <div class="row">
        <div class="col-sm-4">
            <div class="card" style="">
                <div class="card-header">
                    Teste
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card" style="">
                <div class="card-header">
                    Teste
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card" style="">
                <div class="card-header">
                    Teste
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
            </div>
        </div>
    </div>`;
    
    tableExposicoes.innerHTML = conteudo;
}

function carregaTableExposicoes() {
    const fun = "listarExposicoes";
    return axios
    .post(`FunctionsExposicao?fun=${fun}`)
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
        tableExposicoes.innerHTML = conteudo;
    })
    .catch(error => {
        alert('oops, algo deu errado!', error);
    });
}

function carregaOpcoesExpositor() {
    const opcoes = ["Opc1", "Opc2", "Opc3", "Opc4", "Opc5"];
    for (var opc of opcoes) {
        var elemento = document.createElement('option');
        elemento.appendChild(document.createTextNode(opc));
        elemento.value = opc.substring(3);
        expositorExposicao.appendChild(elemento);
    }
}

function carregaOpcoesUnidade() {
    const opcoes = ["xxx1", "xxx2", "xxx3", "xxx4", "xxx5"];
    for (var opc of opcoes) {
        var elemento = document.createElement('option');
        elemento.setAttribute("class","unidade");
        elemento.appendChild(document.createTextNode(opc));
        elemento.value = opc.substring(3);
        unidadeExposicao.appendChild(elemento);
    }
}