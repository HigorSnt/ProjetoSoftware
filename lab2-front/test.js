let assert = require('assert');
let disciplina = require('./scoord').disciplina;
let turma = require('./scoord').turma;

describe('factory Disciplina', function() {
    let d0;

    before(async () => {
        d0 = disciplina('prog1', 'Programação 1', 4, []);
    })

    it('deve criar disciplinas distintas a cada invocação', function(){
        d0 = disciplina('prog1', 'Programação 1', 4, []);
        d1 = disciplina('prog1', 'Programação 1', 4, []);
        d2 = disciplina('prog1', 'Programação 1', 4, []);
        assert.notEqual(d0, d1);
        assert.notEqual(d0, d2);
        assert.notEqual(d1, d2);
    });

    it('deve reter os dados de inicialização', function(){
        assert.equal('prog1', d0.id());
        assert.equal('Programação 1', d0.get_nome());
        assert.equal(4, d0.creditos);
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('deve permitir atualização de nome', function(){
        d0.set_nome('Programação de Computadores I')
        assert.equal('prog1', d0.id());
        assert.equal('Programação de Computadores I', d0.get_nome());
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('não deve permitir atualização de id via set_id', function(){
        assert.throws(function () {
            d0.set_id('outro')
        }, TypeError);
        assert.equal('prog1', d0.id());
    });
});


describe('factory Turma', function () {
    let t1;
    let d1;

    before (async () => {
        d1 = disciplina('prog1', 'Programação 1', 4, []);
        t1 = turma(d1, "1");
    });

    it ('deve criar turmas diferentes a cada invocação', function () {
        t2 = turma(d1, "1");
        t3 = turma(d1, "1");
        assert.notEqual(t1, t2);
        assert.notEqual(t1, t3);
        assert.notEqual(t2, t3);
    });

    it ('deve reter os dados da inicialização', function(){
        assert.equal(d1, t1.get_disciplina());
        assert.equal('1', t1.get_periodo());
        assert.equal(null, t1.get_professor());
        assert.deepStrictEqual([], t1.get_estudantes());
        assert.equal('planejada', t1.get_status());
    });
})