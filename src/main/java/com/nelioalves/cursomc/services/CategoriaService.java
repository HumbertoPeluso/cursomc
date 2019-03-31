package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(int id){

       /* Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElse(null);*/

        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

    }

    public Categoria insert(Categoria obj){
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return categoriaRepository.save(obj);
    }
}
