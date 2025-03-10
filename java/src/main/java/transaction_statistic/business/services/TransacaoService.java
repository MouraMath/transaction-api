package transaction_statistic.business.services;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import transaction_statistic.DTO.TransacaoRequestDTO;
import transaction_statistic.infraestructure.exceptions.UnprocessableEntity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacao = new ArrayList();

    public void adicionarTransacoes(TransacaoRequestDTO dto) {

        log.info("Iniciando registro de transações" + dto);

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maior que atual");
            throw new UnprocessableEntity("");
        }

        if(dto.valor() < 0){
            log.error("Valor não pode ser negativo");
            throw new UnprocessableEntity("");
        }

        listaTransacao.add(dto);
        log.info("Transações adicionas com sucesso");
    }

    public void deletarTransacoes(){
        log.info("Iniciando processo de deletar transações");
        listaTransacao.clear();
        log.info("Transações removidas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer searchInterval){

        log.info("Iniciadas buscas de transações por tempo " +searchInterval);
        OffsetDateTime intervaloDataHora = OffsetDateTime.now().minusSeconds(searchInterval);

        log.info("Retorno de transações com sucesso");
        return listaTransacao.stream()
                .filter(transacoes -> transacoes.dataHora()
                        .isAfter(intervaloDataHora)).toList();
    }


}
