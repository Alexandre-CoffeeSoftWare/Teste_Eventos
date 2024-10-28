import React, { useState, useEffect, useRef } from 'react';
import './EventoCadastro.css';

function EventoCadastro({ onClose }) {
  const [eventoNome, setEventoNome] = useState('');
  const [dataInicio, setDataInicio] = useState('');
  const [dataFim, setDataFim] = useState('');
  const [instituicaoSelecionada, setInstituicaoSelecionada] = useState('');
  const [instituicoes, setInstituicoes] = useState([]);
  const [eventos, setEventos] = useState([]);
  const [editingEvento, setEditingEvento] = useState(null);

  const eventoNomeRef = useRef(null);

  const fetchEventos = async () => {
    try {
      const response = await fetch('http://localhost:8080/eventos');
      const data = await response.json();
      setEventos(data);
    } catch (error) {
      console.error('Erro ao buscar eventos:', error);
    }
  };

  const fetchInstituicoes = async () => {
    try {
      const response = await fetch('http://localhost:8080/instituicoes');
      const data = await response.json();
      setInstituicoes(data);
    } catch (error) {
      console.error('Erro ao buscar instituições:', error);
    }
  };

  useEffect(() => {
    fetchEventos();
    fetchInstituicoes();
  }, []);

  const handleNovoEvento = async () => {
    setCamposDefault();
    eventoNomeRef.current.focus();
  }

  const handleSalvarEvento = async () => {
    if (!eventoNome || !dataInicio || !dataFim || !instituicaoSelecionada) {
      alert('Preencha todos os campos!');
      return;
    }

    const eventoData = {
      id: editingEvento ? editingEvento.id : 0,
      nome: eventoNome,
      dataInicial: dataInicio,
      dataFinal: dataFim,
      ativo: true,
      codInstituicao: instituicaoSelecionada,
    };

    const method = editingEvento ? 'PUT' : 'POST';
    const endpoint = editingEvento
      ? `http://localhost:8080/eventos/${editingEvento.id}`
      : 'http://localhost:8080/eventos';

    try {
      const response = await fetch(endpoint, {
        method,
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(eventoData),
      });
      if (response.ok) {
        fetchEventos();
        setCamposDefault();
      } else {
        console.error('Erro ao salvar evento');
      }
    } catch (error) {
      console.error('Erro ao conectar com o servidor:', error);
    }
  };

  const handleEditarEvento = () => {   
    if (!editingEvento) {
        alert('Selecione um evento para editar.');
        return;
      }     
    setEventoNome(editingEvento.nome);
    setDataInicio(editingEvento.dataInicial);
    setDataFim(editingEvento.dataFinal);
    setInstituicaoSelecionada(editingEvento.codInstituicao);
    eventoNomeRef.current.focus();
  };

  const handleSelecionarEvento = (evento) => {
    setCamposDefault();
    setEditingEvento(evento);    
  }

  const handleExcluirEvento = async () => {
    if (!editingEvento) {
      alert('Selecione um evento para excluir.');
      return;
    } else {
      if (!window.confirm("Tem certeza que deseja excluir esta instituição?"))
        return;  
    }
    try {
      const response = await fetch(`http://localhost:8080/eventos/${editingEvento.id}`, {
        method: 'DELETE',
      });
      if (response.ok) {
        fetchEventos();
        setCamposDefault();
      } else {
        console.error('Erro ao excluir evento');
      }
    } catch (error) {
      console.error('Erro ao conectar com o servidor:', error);
    }
  };

  const setCamposDefault = () => {
    setEditingEvento(null);
    setEventoNome('');
    setDataInicio('');
    setDataFim('');
    setInstituicaoSelecionada('');    
  };

  return (
    <div className="evento-window-container">
      <div className="evento-window-header">
        <span>Cadastro de Eventos</span>
        <button className="evento-close-button" onClick={onClose}>X</button>
      </div>

      <div className="evento-lista">
        {eventos.length === 0 ? (
          <div>Nenhum evento cadastrado.</div>
        ) : (
          eventos.map((evento) => (
            <div
              key={evento.id}
              className={`evento-item ${editingEvento?.id === evento.id ? 'evento-item-selected' : ''}`}
              onClick={() => handleSelecionarEvento(evento)}
            >
              <strong>{evento.nome}</strong> - {evento.dataInicial} a {evento.dataFinal}
              <p>Instituição: {instituicoes.find(inst => inst.id === evento.codInstituicao)?.nome || 'N/A'}</p>
            </div>
          ))
        )}
      </div>

      <div className="evento-window-body">
        <select
          value={instituicaoSelecionada}
          onChange={(e) => setInstituicaoSelecionada(e.target.value)}
        >
          <option value="">Selecione uma Instituição</option>
          {instituicoes.map((instituicao) => (
            <option key={instituicao.id} value={instituicao.id}>
              {instituicao.nome}
            </option>
          ))}
        </select>

        <input
          ref={eventoNomeRef}
          type="text"
          value={eventoNome}
          onChange={(e) => setEventoNome(e.target.value)}
          placeholder="Nome do Evento"
        />
        <input
          type="date"
          value={dataInicio}
          onChange={(e) => setDataInicio(e.target.value)}
          placeholder="Data de Início"
        />
        <input
          type="date"
          value={dataFim}
          onChange={(e) => setDataFim(e.target.value)}
          placeholder="Data de Fim"
        />

        <div className="evento-buttons">
          <button className="evento-button" onClick={handleNovoEvento}>Novo</button>
          <button className="evento-button" onClick={handleSalvarEvento}>Salvar</button>
          <button className="evento-button" onClick={handleEditarEvento}>Editar</button>
          <button className="evento-button" onClick={handleExcluirEvento}>Excluir</button>
        </div>
      </div>
    </div>
  );
}

export default EventoCadastro;