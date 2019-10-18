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
    let _professor = null;
    let _estudantes = [];

    t.get_disciplina = () => _disciplina;

    t.get_periodo = () => _periodo;
    t.set_periodo = function (novo_periodo) {
        _periodo = novo_periodo;
    }
    
    t.get_professor = () => _professor;
    t.set_professor = function (professor) {
        _professor = professor;
    }

    t.get_estudantes = () => _estudantes;

    t.matricular_estudante = function (estudante) {
        if ((_status == situacao.P || _status == situacao.A) && 
                !_estudantes.includes(estudante)) {
            _estudantes.push(estudante);
        }
    }

    t.desmatricular_estudante = function (estudante) {
        if ((_status == situacao.P || _status == situacao.A)) {
            _estudantes.forEach(function (item, index, arr) {
                if (estudante.get_matricula() === item.get_matricula()) {
                    arr.splice(index, 1);
                }
            });
        }
    }

    t.get_status = () => _status;
    t.set_status = function (novo_status) {
        let values = Object.values(situacao);
        if (values.includes(novo_status)) {
            _status = novo_status;
        }
    }

    return t;
}


function professor(matricula, nome, email, cpf, url_foto) {
    let _matricula = matricula;
    let _nome = nome;
    let _email = email;
    let _cpf = cpf;
    let _url_foto = url_foto;
    let _turmas = [];

    let p = {}

    p.get_matricula = () => _matricula;

    p.get_nome = () => _nome;
    p.set_nome = function (novo_nome) {
        _nome = novo_nome;
    }

    p.get_email = () => _email;
    p.set_email = function (novo_email) {
        _email = novo_email;
    }

    p.get_cpf = () => _cpf;

    p.get_url_foto = () => _url_foto;
    p.set_url_foto = function (nova_url) {
        _url_foto = nova_url;
    }

    p.aloca_turma = function (turma) {
        if (!_turmas.includes(turma)) {
            _turmas.push(turma);
        }
    }

    p.turmas = function (semestre) {
        return _turmas.filter((elemento) => semestre === elemento.get_periodo());
    }

    p.get_turmas = () => _turmas;

    return p;
}


function estudante(matricula, nome, email, cpf, url_foto) {
    let _matricula = matricula;
    let _nome = nome;
    let _email = email;
    let _cpf = cpf;
    let _url_foto = url_foto;
    let _turmas = [];

    let e = {};

    e.get_nome = () => _nome;
    e.set_nome = function (novo_nome) {
        _nome = novo_nome;
    }

    e.get_matricula = () => _matricula;

    e.get_email = () => _email;
    e.set_email = function (novo_email) {
        _email = novo_email;
    }

    e.get_cpf = () => _cpf;

    e.get_url_foto = () => _url_foto;
    e.set_url_foto = function (nova_url) {
        _url_foto = nova_url;
    }

    e.matricula = function (turma) {
        if (!_turmas.includes(turma)) {
            _turmas.push(turma);
        }
    }

    e.turmas = function (semestre) {
        return _turmas.filter((elemento) => semestre === elemento.get_periodo());
    }
    e.get_turmas = () => _turmas;

    return e;
}


module.exports = {
    disciplina, 
    turma,
    professor,
    estudante
}