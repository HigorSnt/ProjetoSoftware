const situacao = Object.freeze({
    "P": "planejada",
    "A": "ativa",
    "C": "concluida"
});


class Disciplina {

    constructor (id, nome, creditos, pre_requisito) {
        this._nome = nome;
        this._id = id;
        this.creditos = creditos;
        this.pre_requisitos = pre_requisito;
    };

    id() {
        return this._id;
    };

    get_nome() {
        return this._nome;
    };

    set_nome (novo_nome) {
        this._nome = novo_nome;
    };
}


class Turma {

    constructor(disciplina, periodo) {
        this._disciplina = disciplina;
        this._periodo = periodo;
        this._status = situacao.P;
        this._professor = null;
        this._estudantes = [];
    };

    get_disciplina() {
        return this._disciplina;
    };

    get_periodo() {
        return this._periodo;
    };

    set_periodo(novo_periodo) {
        this._periodo = novo_periodo;
    };
    
    get_professor() {
        return this._professor;
    };

    set_professor(professor) {
        this._professor = professor;
    };

    get_estudantes() {
        return this._estudantes;
    };

    matricular_estudante(estudante) {
        if ((this._status == situacao.P || this._status == situacao.A) && 
                !this._estudantes.includes(estudante)) {
            this._estudantes.push(estudante);
        }
    };

    desmatricular_estudante(estudante) {
        if ((this._status == situacao.P || this._status == situacao.A)) {
            this._estudantes.forEach(function (item, index, arr) {
                if (estudante.get_matricula() === item.get_matricula()) {
                    arr.splice(index, 1);
                }
            });
        }
    };

    get_status() {
        return this._status;
    };

    set_status(novo_status) {
        let values = Object.values(situacao);
        if (values.includes(novo_status)) {
            this._status = novo_status;
        }
    };
}


function Pessoa (matricula, nome, email, cpf, url_foto) {
    this._matricula = matricula;
    this._nome = nome;
    this._email = email;
    this._cpf = cpf;
    this._url_foto = url_foto;
    this._turmas = [];
}

Pessoa.prototype = {
    get_matricula: function() {
        return this._matricula;
    },

    get_nome: function () {
        return this._nome;
    },
    set_nome: function (novo_nome) {
        this._nome = novo_nome;
    },

    get_email: function () { 
        return this._email; 
    },
    set_email: function (novo_email) {
        this._email = novo_email;
    },

    get_cpf: function() { return this._cpf; },

    get_url_foto: function() { return this._url_foto; },
    set_url_foto: function (nova_url) {
        this._url_foto = nova_url;
    },

    turmas: function (semestre) {
        return this._turmas.filter((elemento) => semestre === elemento.get_periodo());
    },

    get_turmas: function() { return this._turmas; }
}


function Professor(matricula, nome, email, cpf, url_foto) {
    Pessoa.call(this, matricula, nome, email, cpf, url_foto);
}

Professor.prototype = Object.create(Pessoa.prototype)
Professor.prototype.aloca_turma = function (turma) {
        if (!this._turmas.includes(turma)) {
            this._turmas.push(turma);
        }
    }


function Estudante(matricula, nome, email, cpf, url_foto) {
    Pessoa.call(this, matricula, nome, email, cpf, url_foto);
}

Estudante.prototype = Object.create(Pessoa.prototype)
Estudante.prototype.matricula = function (turma) {
        if (!this._turmas.includes(turma)) {
            this._turmas.push(turma);
        }
    }


exports.Disciplina = Disciplina;
exports.Turma = Turma;
exports.Estudante = Estudante;
exports.Professor = Professor;