import React, { useState } from 'react';
import { FaBuilding } from 'react-icons/fa';
import { ImAddressBook } from 'react-icons/im';
import InstituicaoCadastro from './InstituicaoCadastro';
import EventoCadastro from './EventoCadastro';
import './App.css';

function App() {
  const [showInstituicaoForm, setShowInstituicaoForm] = useState(false);
  const [showEventoForm, setShowEventoForm] = useState(false);
  const [instituicoes, setInstituicoes] = useState([]);

  const toggleInstituicaoForm = () => {
    setShowInstituicaoForm(!showInstituicaoForm);
  };

  const toggleEventoForm = () => {
    setShowEventoForm(!showEventoForm);
  };

  const adicionarInstituicao = (novaInstituicao) => {
    setInstituicoes([...instituicoes, novaInstituicao]);
  };

  return (
    <div className="container">      
      <div className="sidebar">
        <h1>Sistema de Cadastro</h1>
        <div className="menu-icon" onClick={toggleInstituicaoForm}>
          <FaBuilding size={30} />
          <span>Cadastro de Instituições</span>
        </div>
        <div className="menu-icon" onClick={toggleEventoForm}>
          <ImAddressBook size={30} />
          <span>Cadastro de Eventos</span>
        </div>    
      </div>
      <div className="content">        
        {showInstituicaoForm && (
          <InstituicaoCadastro
            onClose={toggleInstituicaoForm}
            onAdicionar={adicionarInstituicao}
          />
        )}
        {showEventoForm && (
          <EventoCadastro
            instituicoes={instituicoes}
            onClose={toggleEventoForm}
          />
        )}
      </div>
    </div>
  );
}

export default App;