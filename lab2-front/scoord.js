const situacao = Object.freeze({
    "P": "planejada",
    "A": "ativa",
    "C": "concluida"
});


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
    let _status = situacao.P;

    t.get_disciplina = () => _disciplina;
    t.get_periodo = () => _periodo;
    t.professor = null;
    t.estudantes = [];
    
    t.matricular_estudante = function (estudante) {
        if ((_status == situacao.P || _status == situacao.A) && 
                !this.estudantes.includes(estudante)) {
            this.estudantes.push(estudante);
        }
    }

    t.desmatricular_estudante = function (estudante) {
        if ((_status == situacao.P || _status == situacao.A) && 
                !this.estudantes.includes(estudante)) {
            this.estudantes.pop(estudante);
        }
    }

    Object.defineProperty(t, 'status', {
        get: () => _status,
        set: function (novo_status) {
            let values = Object.values(situacao);
            if (values.includes(novo_status)) {
                _status = novo_status;
            }
        }
    });

    return t;
}

module.exports = {
    disciplina, 
    turma
}