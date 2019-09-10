let URL = 'https://lab01-projsw-ufcg.herokuapp.com/api/disciplinas';
let $table = document.querySelector('table');

function fetch_disciplinas() {
    $table.innerHTML = '<thead><tr><th>Nome</th><th>Nota</th><th>Apagar?</th></tr></thead>';

    fetch(URL)
        .then(r => r.json())
        .then(dado => {
            dado.forEach(disciplina => {
                criaTabela(disciplina);
            });
        });
}

function save() {
    let $inputNome = document.querySelector('#nome');
    let $inputNota = document.querySelector('#nota');

    let nome = $inputNome.value;
    let nota = $inputNota.value;

    let disciplina = `{
        "nome":"${nome}", 
        "nota":"${nota}"
    }`;

    let header = {
        "Content-Type": "application/json"
    };

    fetch(URL, {
        'method': 'POST',
        'body': disciplina,
        'headers': header
    }).then(_ => {
        $inputNome.value = '';
        $inputNota.value = '';
        fetch_disciplinas();
    });
}

function remove() {
    let $tr = this.parentNode.parentNode;
    let $id = $tr.id;

    fetch(URL + `/${$id}`, {
        'method': 'DELETE'
    })
    .then($table.removeChild($tr));
}

function criaTabela(disciplina) {
    let $tr = document.createElement('tr');
    let $tdNome = document.createElement('td');
    let $tdNota = document.createElement('td');
    let $tdApagar = document.createElement('td');
    let $button = document.createElement('button');

    $tr.id = disciplina.id;
    $tdNome.innerText = disciplina.nome;
    $tdNota.innerText = disciplina.nota;

    $button.innerText = 'Apagar';
    $button.setAttribute('type', 'submit');
    $button.addEventListener('click', remove);

    $tdApagar.appendChild($button);
    $tr.appendChild($tdNome);
    $tr.appendChild($tdNota);
    $tr.appendChild($tdApagar);
    $table.appendChild($tr);
}

(function init() {
    let $saveButton = document.querySelector("#salvar");

    fetch_disciplinas();

    $saveButton.addEventListener('click', save);
}());