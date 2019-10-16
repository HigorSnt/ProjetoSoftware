function disciplina(id, nome, creditos, pre_requisito) {
    let _nome = nome;

    let d = {};
    
    d.creditos = creditos;
    d.pre_requisitos = pre_requisito;
    d.id = () => id;
    d.get_nome = () => _nome;
    d.set_nome = function (novo_nome) {
        _nome = novo_nome;
    }
    
    return d;
}

function turma(disciplina, periodo) {
    let t = {};
    let _disciplina = disciplina;
    let _periodo = periodo;

    t.get_disciplina = () => _disciplina;
    t.get_periodo = () => _periodo;
    t.professor = null;
    t.estudantes = [];
    t.status;
}

module.exports = {
    disciplina
}