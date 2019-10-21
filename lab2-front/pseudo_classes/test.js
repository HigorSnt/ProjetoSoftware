let assert = require('assert');
let Disciplina = require('./scoord').Disciplina;
let Turma = require('./scoord').Turma;
let Professor = require('./scoord').Professor;
let Estudante = require('./scoord').Estudante;


describe('factory Disciplina', function () {
    let d0;

    before(async () => {
        d0 = new Disciplina('prog1', 'Programação 1', 4, []);
    })

    it('deve criar disciplinas distintas a cada invocação', function () {
        d0 = new Disciplina('prog1', 'Programação 1', 4, []);
        d1 = new Disciplina('prog1', 'Programação 1', 4, []);
        d2 = new Disciplina('prog1', 'Programação 1', 4, []);
        assert.notEqual(d0, d1);
        assert.notEqual(d0, d2);
        assert.notEqual(d1, d2);
    });

    it('deve reter os dados de inicialização', function () {
        assert.equal('prog1', d0.id());
        assert.equal('Programação 1', d0.get_nome());
        assert.equal(4, d0.creditos);
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('deve permitir atualização de nome', function () {
        d0.set_nome('Programação de Computadores I')
        assert.equal('prog1', d0.id());
        assert.equal('Programação de Computadores I', d0.get_nome());
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('não deve permitir atualização de id via set_id', function () {
        assert.throws(function () {
            d0.set_id('outro')
        }, TypeError);
        assert.equal('prog1', d0.id());
    });

});


describe('factory Turma', function () {
    let t1;
    let d1;
    let e1, e2, e3, e4;

    before(async () => {
        d1 = new Disciplina('prog1', 'Programação 1', 4, []);
        t1 = new Turma(d1, '1');
        e1 = new Estudante('11111', 'João', 'joao@yahoo.com', '1234567890', 'https://facebook.com/joao/perfil.jpg');
        e2 = new Estudante('12345', 'Maria', 'maria@gmail.com', '14725836902', 'https://twitter.com/maria/foto-perfil.jpg');
        e3 = new Estudante('12255', 'Pedro', 'pedro@hotmail.com', '36925814775', 'http://linkedin.com/pedro/foto_do_perfil.jpg');
        e4 = new Estudante('15279', 'Ana', 'ana@gmail.com', '45678912337', 'http://linkedin.com/ana/fotinha.jpg');
    });

    it('deve criar turmas diferentes a cada invocação', function () {
        t2 = new Turma(d1, "1");
        t3 = new Turma(d1, "1");

        assert.notEqual(t1, t2);
        assert.notEqual(t1, t3);
        assert.notEqual(t2, t3);
    });

    it('deve reter os dados da inicialização', function () {
        assert.equal(d1, t1.get_disciplina());
        assert.equal('1', t1.get_periodo());
        assert.equal(null, t1.get_professor());
        assert.deepStrictEqual([], t1.get_estudantes());
        assert.equal('planejada', t1.get_status());
    });

    it('deve permitir atualização do período e professor', function () {
        p1 = new Professor('201415145', 'Sei lá', 'seila@computacao.ufcg.edu.br', '32145698703', 'http://linkedin.com/seila/foto_do_perfil.jpg');

        t1.set_periodo('2');
        t1.set_professor(p1);
        assert.equal('2', t1.get_periodo());
        assert.equal(p1, t1.get_professor());
    });

    it('deve ser possível matricular e estudantes e estes são únicos', function () {
        t1.matricular_estudante(e1);
        assert.equal(1, t1.get_estudantes().length);
        t1.matricular_estudante(e1);
        assert.equal(1, t1.get_estudantes().length);

        t1.matricular_estudante(e3);
        assert.equal(2, t1.get_estudantes().length);
        t1.matricular_estudante(e3);
        assert.equal(2, t1.get_estudantes().length);

        t1.matricular_estudante(e2);
        assert.equal(3, t1.get_estudantes().length);
    });

    it('só deve ser possível matricular estudantes se o status da turma for planejada ou ativa', function () {
        assert.equal(3, t1.get_estudantes().length);
        t1.set_status('concluida');
        t1.matricular_estudante(e4);
        assert.equal(3, t1.get_estudantes().length);
        t1.set_status('ativa');
        t1.matricular_estudante(e4);
        assert.equal(4, t1.get_estudantes().length);
    });

    it('ao setar um novo status inválido, o mesmo não valerá', function () {
        t1.set_status('em andamento');
        assert.equal('ativa', t1.get_status());
    });

    it('deve ser possível desmatricular um aluno', function () {
        t1.desmatricular_estudante(e3);
        assert.equal(3, t1.get_estudantes().length);
        t1.desmatricular_estudante(e2);
        assert.equal(2, t1.get_estudantes().length);
        t1.desmatricular_estudante(e1);
        assert.equal(1, t1.get_estudantes().length);
        t1.desmatricular_estudante(e4);
        assert.equal(0, t1.get_estudantes().length);
    });
});


describe('factory Professor', function () {
    let p1, d1, d2, d3, t1, t2, t3;

    before(async () => {
        p1 = new Professor('22314587', 'Raquel', 'raquel@computacao.ufcg.edu.br', '12345674106', 'http://linkedin.com/raquel/foto.jpg');
        d1 = new Disciplina('prog1', 'Programação 1', 4, []);
        d2 = new Disciplina('prog2', 'Programação 2', 4, []);
        d3 = new Disciplina('eda', 'Estrutura de Dados', 4, []);
        t1 = new Turma(d1, '1');
        t2 = new Turma(d2, '2');
        t3 = new Turma(d3, '3');
    });

    it('deve criar professores diferentes a cada invocação', function () {
        p2 = new Professor('26568501', 'Dalton', 'dalton@computacao.ufcg.edu.br', '07728061581', 'http://linkedin.com/dalton/foto.jpg');
        p3 = new Professor('55727296', 'Matheus', 'matheus@computacao.ufcg.edu.br', '48375757376', 'http://linkedin.com/matheus/foto.jpg');

        assert.notEqual(p1, p2);
        assert.notEqual(p1, p3);
        assert.notEqual(p2, p3);
    });

    it('deve reter dados da inicialização', function () {
        assert.equal('Raquel', p1.get_nome());
        assert.equal('22314587', p1.get_matricula());
        assert.equal('raquel@computacao.ufcg.edu.br', p1.get_email());
        assert.equal('12345674106', p1.get_cpf());
        assert.equal('http://linkedin.com/raquel/foto.jpg', p1.get_url_foto());
        assert.deepStrictEqual([], p1.get_turmas());
    });

    it('deve ser possível alterar nome, email e url da foto', function () {
        p1.set_nome('Lívia');
        p1.set_email('livia@computacao.ufcg.edu.br');
        p1.set_url_foto('http://linkedin.com/raquel/foto.jpg');
        assert.equal('Lívia', p1.get_nome());
        assert.equal('livia@computacao.ufcg.edu.br', p1.get_email());
        assert.equal('http://linkedin.com/raquel/foto.jpg', p1.get_url_foto())
    });

    it('não deve ser possível alterar matrícula e cpf', function () {
        assert.throws(function () {
            p1.set_matricula('14796');
        }, TypeError);

        assert.throws(function () {
            p1.set_cpf('00000000000');
        }, TypeError);
    });

    it('deve ser possível alocar turmas', function () {
        p1.aloca_turma(t1);
        assert.equal(1, p1.get_turmas().length);
        p1.aloca_turma(t2);
        assert.equal(2, p1.get_turmas().length);
        p1.aloca_turma(t3);
        assert.equal(3, p1.get_turmas().length);
        p1.aloca_turma(t1);
        assert.equal(3, p1.get_turmas().length);
    });

    it('deve ser possível filtrar as turmas vinculadas por semestre', function () {
        assert.deepStrictEqual([t1], p1.turmas('1'));
        assert.deepStrictEqual([t2], p1.turmas('2'));
        assert.deepStrictEqual([t3], p1.turmas('3'));
    });
});


describe('factory Estudante', function () {
    let e1, d1, d2, d3, t1, t2, t3;

    before(async () => {
        e1 = new Estudante('11111', 'João', 'joao@yahoo.com', '1234567890', 'https://facebook.com/joao/perfil.jpg');
        d1 = new Disciplina('prog1', 'Programação 1', 4, []);
        d2 = new Disciplina('prog2', 'Programação 2', 4, []);
        d3 = new Disciplina('eda', 'Estrutura de Dados', 4, []);
        t1 = new Turma(d1, '1');
        t2 = new Turma(d2, '2');
        t3 = new Turma(d3, '3');
    });

    it('deve criar estudantes diferentes a cada invocação', function () {
        e2 = new Estudante('12345', 'Maria', 'maria@gmail.com', '14725836902', 'https://twitter.com/maria/foto-perfil.jpg');
        e3 = new Estudante('12255', 'Pedro', 'pedro@hotmail.com', '36925814775', 'http://linkedin.com/pedro/foto_do_perfil.jpg');

        assert.notEqual(e1, e2);
        assert.notEqual(e1, e3);
        assert.notEqual(e2, e3);
    });

    it('deve reter os dados da inicialização', function () {
        assert.equal('João', e1.get_nome());
        assert.equal('11111', e1.get_matricula());
        assert.equal('joao@yahoo.com', e1.get_email());
        assert.equal('1234567890', e1.get_cpf());
        assert.equal('https://facebook.com/joao/perfil.jpg', e1.get_url_foto());
        assert.deepStrictEqual([], e1.get_turmas());
    });

    it('deve ser possível alterar nome', function () {
        e1.set_nome('Henrique');
        assert.equal('Henrique', e1.get_nome());
    });

    it('não deve ser possível alterar matrícula e cpf', function () {
        assert.throws(function () {
            e1.set_matricula('14796');
        }, TypeError);

        assert.throws(function () {
            e1.set_cpf('00000000000');
        }, TypeError);
    });

    it('deve ser possível se matricular em uma turma', function () {
        e1.matricula(t1);
        e1.matricula(t2);
        e1.matricula(t3);
        assert.equal(3, e1.get_turmas().length);
        e1.matricula(t3);
        assert.equal(3, e1.get_turmas().length);
    });

    it('deve ser possível filtrar as turmas matriculadas por semestre', function () {
        assert.deepStrictEqual([t1], e1.turmas('1'));
        assert.deepStrictEqual([t2], e1.turmas('2'));
        assert.deepStrictEqual([t3], e1.turmas('3'));
    });
});