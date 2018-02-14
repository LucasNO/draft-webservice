package com.draft.back.javentus.service;

import com.draft.back.javentus.dto.Rodada;
import com.draft.back.javentus.model.Confronto;
import com.draft.back.javentus.model.Fase;
import com.draft.back.javentus.model.Time;
import com.draft.back.javentus.model.TipoTorneio;
import com.draft.back.javentus.model.Torneio;
import com.draft.back.javentus.repository.TorneioRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class TorneioService {

    @Autowired
    private TorneioRepository torneioRepository;

    @Autowired
    private TimeService timeService;

    @Autowired
    private FaseService faseService;

    @Autowired
    private ConfrontoService confrontoService;

    public List<Torneio> findAll() {
        return torneioRepository.findAll();
    }

    public Torneio findOne(Integer id) {
        return torneioRepository.findOne(id);
    }

    public Torneio carregarTorneioAtivo() {
        return torneioRepository.carregarTorneioAtivo();
    }

    public Torneio save(Torneio torneio) {
        return torneioRepository.save(torneio);
    }

    public Torneio update(Torneio torneio) {
        return torneioRepository.saveAndFlush(torneio);
    }

    public void delete(Integer id) {
        torneioRepository.delete(id);
    }

    public Boolean verificarTorneioAtivo() {
        Torneio t = torneioRepository.carregarTorneioAtivo();
        if (t != null && t.getId() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public void gerarConfrontosTorneio(TipoTorneio tt) {
        if (tt.getDescricao().equals("Grupo e Mata Mata")) {
            gerarConfrontosGrupoMataMata();

        } else if (tt.getDescricao().equals("Pontos Corridos")) {
            gerarConfrontoTodosContraTodos(faseService.findByDescricao("Pontos Corridos"));
        }

    }

    private void gerarConfrontosGrupoMataMata() {
        gerarConfrontoTodosContraTodos(faseService.findByDescricao("1 Fase"));
        gerarConfrontosFaseFinal();

    }

    private void gerarConfrontosFaseFinal() {
        gerarConfrontosRepescagem();
        gerarConfrontosSemiFinal();
        gerarConfrontosFinal();
    }

    private void gerarConfrontosRepescagem() {
        Confronto repescagem = new Confronto();
        repescagem.setFase(faseService.findByDescricao("Repescagem"));
        confrontoService.save(repescagem);

    }

    private void gerarConfrontosSemiFinal() {
        for (int i = 0; i < 4; i++) {
            Confronto semiFinal = new Confronto();
            semiFinal.setFase(faseService.findByDescricao("Semi Final"));
            confrontoService.save(semiFinal);
        }
    }

    private void gerarConfrontosFinal() {
        Confronto jogoFinal = new Confronto();
        jogoFinal.setFase(faseService.findByDescricao("Final"));
        confrontoService.save(jogoFinal);
    }

    private void gerarConfrontoTodosContraTodos(Fase fase) {
        List<Time> times = timeService.findAll();
        List<List<Object>> combinacoes = combina(times, 2);
        List<Confronto> confrontos = new ArrayList<>();

        for (List<Object> combinacao : combinacoes) {
            Confronto confronto = new Confronto();
            Collections.shuffle(combinacao);
            for (Object c : combinacao) {
                if (confronto.getTime1() == null) {
                    confronto.setTime1((Time) c);
                } else {
                    confronto.setTime2((Time) c);
                }
                confronto.setFase(fase);
            }
            confrontos.add(confronto);
        }

        Collections.shuffle(confrontos);
        gerarRodadas(confrontos, times.size());

    }

    private void gerarRodadas(List<Confronto> confrontos, Integer qtdTimes) {

        Integer jogosPorRodadas = confrontos.size() / qtdTimes;
        Integer qtdRodadas = confrontos.size() / jogosPorRodadas;

        List<Rodada> rodadas = new ArrayList<>();

        for (int k = 1; k <= qtdRodadas; k++) {
            Rodada rodada = new Rodada();
            rodada.setQtdJogosPorRodada(jogosPorRodadas);
            List<Confronto> r = new ArrayList<>();
            for (int i = 1; i <= jogosPorRodadas; i++) {
                if (r.isEmpty()) {
                    r.add(confrontos.get(0));
                } else {
                    for (Confronto c1 : confrontos) {
                        for (Confronto c2 : r) {
                            if (r.size() == jogosPorRodadas) {
                                break;
                            }
                            if (c1.getTime1() != c2.getTime1() && c1.getTime1() != c2.getTime2()
                                    && c1.getTime2() != c2.getTime1() && c1.getTime2() != c2.getTime2()) {
                                r.add(c1);
                                break;
                            }
                        }
                    }
                }
            }
            confrontos.removeAll(r);
            rodada.setConfrontos(r);
            rodadas.add(rodada);
        }

        for (Rodada rodada : rodadas) {
            for (Confronto c : rodada.getConfrontos()) {
                confrontoService.save(c);
            }
        }

    }

    private List<List<Object>> combina(List<Time> lista, int n) {

        List<List<Object>> combinacoes
                = new ArrayList<List<Object>>();

        int size = lista.size();
        char[] chars = new char[size];
        for (long i = 0, max = 1 << size; i < max; i++) {

            int soma = 0;

            for (int j = 0; j < size; j++) {
                chars[j]
                        = (char) (((i >>> (size - j - 1)) & 1) + '0');
                if (chars[j] == '1') {
                    soma++;
                }
            }

            if (soma == n) {
                List<Object> combinacao = new ArrayList<Object>();
                for (int k = 0; k < size; k++) {
                    if (chars[k] == '1') {
                        Object obj = lista.get(k);
                        combinacao.add(obj);
                    }
                }
                combinacoes.add(combinacao);
            }
        }
        return combinacoes;
    }
}
