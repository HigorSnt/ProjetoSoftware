let URL = 'https://lab01-projsw-ufcg.herokuapp.com/api/disciplinas';

let $div = document.querySelector('div');

function fetch_disciplinas() {
    fetch(URL)
    .then(response => response.json())
    .then(dados => {
        dados.forEach(disciplina => {
            let $p = document.createElement('p');
            $p.innerText = disciplina.nome + " - " + disciplina.nota;
            $div.appendChild($p);
        });
    })
}

fetch_disciplinas()