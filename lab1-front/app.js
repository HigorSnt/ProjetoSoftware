let URL = 'https://lab01-projsw-ufcg.herokuapp.com/api/disciplinas';
let $table = document.querySelector('table');

function fetch_disciplinas() {
    $table.innerHTML = '<tbody><tr><th>Nome</th><th>Nota</th><th>Apagar?</th></tr></tbody>'

    fetch(URL)
    .then(r => r.json())
    .then(dado => {
        dado.forEach(disciplina => {
            createTable(disciplina);
        });
    });
}

function save() {
    let nome = document.querySelector('#nome').value;
    let nota = document.querySelector('#nota').value;

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
    })
    .then(fetch_disciplinas);
}

function removeAll() {
    let $trs = $table.querySelectorAll('tr');
    console.log($trs)
}

function remove(self) {
    let $id = parseInt(self.path[2].id, 10);
    let $tr = self.srcElement.parentNode.parentNode;
    
    fetch(URL + `/${$id}`, {
        'method': 'DELETE'
    })
    .then($tr.parentNode.removeChild($tr));
}

function createTable(disciplina) {
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