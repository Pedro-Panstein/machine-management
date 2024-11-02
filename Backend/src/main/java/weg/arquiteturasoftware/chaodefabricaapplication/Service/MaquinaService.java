package weg.arquiteturasoftware.chaodefabricaapplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weg.arquiteturasoftware.chaodefabricaapplication.DTO.RequestMaquinaDTO;
import weg.arquiteturasoftware.chaodefabricaapplication.DTO.ResponseMaquinaDTO;
import weg.arquiteturasoftware.chaodefabricaapplication.Entity.Maquina;
import weg.arquiteturasoftware.chaodefabricaapplication.Repository.MaquinaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaquinaService {

    @Autowired
    private MaquinaRepository maquinaRepository;

    // Converte RequestMaquinaDTO para entidade Maquina
    private Maquina DTOtoEntity(RequestMaquinaDTO dto) {
        return new Maquina(
                0, // ID gerado automaticamente
                dto.nome(),
                false, // Valor default para operando
                "default_status" // Valor default para status
        );
    }

    // Converte entidade Maquina para ResponseMaquinaDTO
    private ResponseMaquinaDTO EntityToDTO(Maquina maquina) {
        return new ResponseMaquinaDTO(
                maquina.getId(),
                maquina.getNome(),
                maquina.isOperando(),
                maquina.getStatus()
        );
    }

    // Adiciona uma nova máquina
    public ResponseMaquinaDTO addMaquina(RequestMaquinaDTO maquinaDTO) {
        Maquina maquina = DTOtoEntity(maquinaDTO);
        Maquina savedMaquina = maquinaRepository.save(maquina);
        return EntityToDTO(savedMaquina);
    }

    // Retorna todas as máquinas como lista de ResponseMaquinaDTO
    public List<ResponseMaquinaDTO> getMaquinas() {
        return maquinaRepository.findAll().stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    // Retorna uma máquina por ID como ResponseMaquinaDTO
    public ResponseMaquinaDTO getMaquinaById(int id) {
        Maquina maquina = maquinaRepository.findById(id).orElse(null);
        return maquina != null ? EntityToDTO(maquina) : null;
    }

    // Exclui uma máquina
    public String deleteMaquina(int id) {
        maquinaRepository.deleteById(id);
        return "Máquina removida com sucesso!";
    }

    // Atualiza uma máquina
    public ResponseMaquinaDTO updateMaquina(RequestMaquinaDTO maquinaDTO, int id) {
        Maquina maquinaToUpdate = maquinaRepository.findById(id).orElse(null);
        if (maquinaToUpdate != null) {
            maquinaToUpdate.setNome(maquinaDTO.nome());
            Maquina updatedMaquina = maquinaRepository.save(maquinaToUpdate);
            return EntityToDTO(updatedMaquina);
        }
        return null;
    }

    // Altera o status de operação da máquina
    public String alterarStatusOperando(int id) {
        Maquina maquina = maquinaRepository.findById(id).orElse(null);
        if (maquina != null) {
            maquina.setOperando(!maquina.isOperando());
            maquinaRepository.save(maquina);
            return maquina.isOperando() ? "Máquina " + id + " ligada" : "Máquina " + id + " desligada";
        }
        return "Máquina não encontrada";
    }
}
