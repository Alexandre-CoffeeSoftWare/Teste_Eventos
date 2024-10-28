import React, { useState, useEffect, useRef } from 'react';
import './InstituicaoCadastro.css';

function InstituicaoCadastro({ onClose }) {
  const [instituicoes, setInstituicoes] = useState([]);
  const [nomeInstituicao, setNomeInstituicao] = useState('');
  const [tipoInstituicao, setTipoInstituicao] = useState('CONFEDERACAO'); // Valor padrão
  const [isEditing, setIsEditing] = useState(false);
  const [editingInstituicao, setEditingInstituicao] = useState(null);
  const [mensagem, setMensagem] = useState('');
  const [tipoMensagem, setTipoMensagem] = useState('');

  const nomeInstituicaoRef = useRef(null);

  const setMensagemComTimeout = (msg, tipo) => {
    setMensagem(msg);
    setTipoMensagem(tipo);
    setTimeout(() => {
      setMensagem('');
      setTipoMensagem('');
    }, 5000);
  };    

  const setCamposDefault = () => {
    setIsEditing(false);
    setEditingInstituicao(null);
    setNomeInstituicao('');
    setTipoInstituicao('CONFEDERACAO');
    setMensagem('');
  };

  useEffect(() => {
    const fetchInstituicoes = async () => {
      try {
        const response = await fetch('http://localhost:8080/instituicoes');
        if (!response.ok) throw new Error("Erro ao buscar instituições");
        const data = await response.json();        
        setInstituicoes(data);
      } catch (error) {
        console.error(error);
        setMensagemComTimeout('Erro ao carregar instituições.', 'erro');
      }
    };
    fetchInstituicoes();
  }, []);

  const handleNovo = async () => {    
    setCamposDefault();    
    nomeInstituicaoRef.current.focus();
  }

  const handleSalvar = async () => {
    if (!nomeInstituicao.trim()) {
      setMensagemComTimeout('Por favor, insira o nome da instituição antes de salvar.', 'erro');
      return;
    }

    try {
      const method = isEditing ? 'PUT' : 'POST';
      const url = 'http://localhost:8080/instituicoes';

      const bodyData = {
        nome: nomeInstituicao,
        tipoInstituicao: tipoInstituicao,
        ...(isEditing && { id: editingInstituicao.id }) 
      };      

      const response = await fetch(url, {
        method,
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyData)
      });

      if (!response.ok) throw new Error("Erro ao salvar instituição");

      const updatedInstituicao = await response.json();

      if (isEditing) {
        setInstituicoes(instituicoes.map(inst => 
          inst.id === editingInstituicao.id ? updatedInstituicao : inst
        ));
      } else {
        setInstituicoes([...instituicoes, updatedInstituicao]);
      }

      setCamposDefault();     
    } catch (error) {
      console.error(error);
      setMensagemComTimeout('Erro ao salvar a instituição.', 'erro');
    }
  };

  const handleEditar = () => {    
    if (!editingInstituicao) {
      setMensagemComTimeout('Selecione uma instituição para editar.', 'erro');
      return;
    }

    setNomeInstituicao(editingInstituicao.nome);
    setTipoInstituicao(editingInstituicao.tipoInstituicao);
    setIsEditing(true);
  };

  const handleExcluir = async () => {
    if (!editingInstituicao) {
      setMensagemComTimeout('Nenhuma instituição selecionada para excluir.', 'erro');
      return;
    } else {
      if (!window.confirm("Tem certeza que deseja excluir esta instituição?"))
        return;
    }    
    
    try {
      const response = await fetch(`http://localhost:8080/instituicoes/${editingInstituicao.id}`, {
        method: 'DELETE',
      });
     
      if (!response.ok) throw new Error("Erro ao excluir instituição");
      
      setInstituicoes(instituicoes.filter((inst) => inst.id !== editingInstituicao.id));
      setNomeInstituicao('');
      setTipoInstituicao('CONFEDERACAO');
      setIsEditing(false);
      setEditingInstituicao(null);
      setMensagemComTimeout('Instituição excluída com sucesso.', 'sucesso');
    } catch (error) {
      console.error(error);
      setMensagemComTimeout('Erro ao excluir a instituição.', 'erro');
    }    
  };

  const handleSelectInstituicao = (instituicao) => {
    setEditingInstituicao(instituicao);
  };

  return (
    <div className="instituicao-window-container">
      <div className="instituicao-window-header">
        <span>Cadastro de Instituições</span>
        <button className="instituicao-close-button" onClick={onClose}>X</button>
      </div>

      <div className="instituicao-window-body">        
        <div className="instituicao-grid">
          {instituicoes.map((inst) => (
            <div
              key={inst.id}
              onClick={() => handleSelectInstituicao(inst)}
              className={editingInstituicao?.id === inst.id ? 'instituicao-selected' : ''}
            >
              {inst.nome} - {inst.tipoInstituicao}
            </div>
          ))}
          {instituicoes.length === 0 && <div>Nenhuma instituição cadastrada.</div>}
        </div>
        
        <input
          ref={nomeInstituicaoRef}
          type="text"
          value={nomeInstituicao}
          onChange={(e) => setNomeInstituicao(e.target.value)}
          placeholder="Nome da Instituição"
          className="instituicao-input"
        />

        <select
          value={tipoInstituicao}
          onChange={(e) => setTipoInstituicao(e.target.value)}
          className="instituicao-select"
        >
          <option value="CONFEDERACAO">Confederação</option>
          <option value="SINGULAR">Singular</option>
          <option value="CENTRAL">Central</option>
          <option value="COOPERATIVA">Cooperativa</option>
        </select>

        {mensagem && <div className={`instituicao-mensagem ${tipoMensagem}`}>{mensagem}</div>}

        <div className="instituicao-buttons">
          <button className="instituicao-button" onClick={handleNovo}> Novo </button>  
          <button className="instituicao-button" onClick={handleSalvar}> Salvar </button>
          <button className="instituicao-button" onClick={handleEditar}> Editar </button>
          <button className="instituicao-button" onClick={handleExcluir}>Excluir</button>
        </div>
      </div>
    </div>
  );
}

export default InstituicaoCadastro;