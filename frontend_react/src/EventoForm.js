import React, { useState } from 'react';

function EventoForm({ onSubmit }) {
  const [descricao, setDescricao] = useState('');
  const [dataInicial, setDataInicial] = useState('');
  const [dataFinal, setDataFinal] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ descricao, dataInicial, dataFinal });
    setDescricao('');
    setDataInicial('');
    setDataFinal('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Descrição:
        <input
          type="text"
          value={descricao}
          onChange={(e) => setDescricao(e.target.value)}
        />
      </label>
      <label>
        Data Inicial:
        <input
          type="date"
          value={dataInicial}
          onChange={(e) => setDataInicial(e.target.value)}
        />
      </label>
      <label>
        Data Final:
        <input
          type="date"
          value={dataFinal}
          onChange={(e) => setDataFinal(e.target.value)}
        />
      </label>
      <button type="submit">Adicionar Evento</button>
    </form>
  );
}

export default EventoForm;