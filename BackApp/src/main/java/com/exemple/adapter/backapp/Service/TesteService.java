package com.exemple.adapter.backapp.Service;
//
//import com.exemple.adapter.backapp.Dto.TesteDTO;
//import com.exemple.adapter.backapp.Model.TesteModel;
//import com.exemple.adapter.backapp.Repository.TesteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
///**
// * Serviço para testes
// */
//@Service
//public class TesteService {
//
//    @Autowired
//    private TesteRepository testeRepository;
//
//    public List<TesteDTO> listarTodos() {
//        return testeRepository.findAll()
//                .stream()
//                .map(this::converterParaDTO)
//                .collect(Collectors.toList());
//    }
//
//    public Optional<TesteDTO> obterPorId(Long id) {
//        return testeRepository.findById(id)
//                .map(this::converterParaDTO);
//    }
//
//    public TesteDTO criar(TesteDTO dto) {
//        TesteModel modelo = converterParaModelo(dto);
//        TesteModel salvo = testeRepository.save(modelo);
//        return converterParaDTO(salvo);
//    }
//
//    public TesteDTO atualizar(Long id, TesteDTO dto) {
//        Optional<TesteModel> modelo = testeRepository.findById(id);
//        if (modelo.isPresent()) {
//            TesteModel m = modelo.get();
//            m.setNome(dto.getNome());
//            m.setDescricao(dto.getDescricao());
//            TesteModel atualizado = testeRepository.save(m);
//            return converterParaDTO(atualizado);
//        }
//        return null;
//    }
//
//    public void deletar(Long id) {
//        testeRepository.deleteById(id);
//    }
//
//    private TesteDTO converterParaDTO(TesteModel modelo) {
//        return new TesteDTO(modelo.getId(), modelo.getNome(), modelo.getDescricao());
//    }
//
//    private TesteModel converterParaModelo(TesteDTO dto) {
//        return new TesteModel(dto.getNome(), dto.getDescricao());
//    }
//}
//
