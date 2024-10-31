import React, { useState, useEffect } from 'react';
import './EventoConsulta.css';

function EventoConsulta({ onClose }) {
  const [eventos, setEventos] = useState([]);

  const fetchEventos = async () => {
    try {
      const response = await fetch('http://localhost:8080/eventos');
      const data = await response.json();
      setEventos(data);
    } catch (error) {
      console.error('Erro ao buscar eventos:', error);
    }
  };

  useEffect(() => {
    fetchEventos();
  }, []);

  return (
    <div className="evento-window-container">
      <div className="evento-window-header">
        <span>Consulta de Eventos</span>
        <button className="evento-close-button" onClick={onClose}>X</button>
      </div>
      
      {/* Legenda de Ativos e Inativos */}
      <div className="evento-legenda">
        <div className="legenda-item ativo">
          <span>Ativo</span>
        </div>
        <div className="legenda-item inativo">
          <span>Inativo</span>
        </div>
      </div>
  
      <div className="evento-lista">
        {eventos.length === 0 ? (
          <div>Nenhum evento cadastrado.</div>
        ) : (
          eventos.map((evento) => (
            <div
              key={evento.id}
              className={`evento-item ${evento.ativo ? 'evento-item-ativo' : 'evento-item-inativo'}`} 
            >
              <strong>{evento.nome}</strong> - {evento.dataInicial} a {evento.dataFinal}
              <p>
                Instituição: {evento.nomeInstituicao || 'N/A'}
              </p>
            </div>
          ))
        )}
      </div>
    </div>
  );
  
}

export default EventoConsulta;