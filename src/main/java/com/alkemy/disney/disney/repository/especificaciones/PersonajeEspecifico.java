package com.alkemy.disney.disney.repository.especificaciones;

import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajesEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class PersonajeEspecifico {

    public Specification<PersonajesEntity> getByFilters(String nombre, Integer edad, Set<Long> peliculas) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(nombre)) {
                predicates.add((Predicate) criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombre")),
                        "%" + nombre.toLowerCase() + "%"));
            }
            if (edad != null) {
                predicates.add((Predicate) criteriaBuilder.equal(root.get("edad"), edad));
            }
            if (!CollectionUtils.isEmpty(peliculas)) {
                Join<PeliculaEntity, PersonajesEntity> join = root.join("peliculas", JoinType.INNER);
                Expression<String> peliculaID = join.get("id");
                predicates.add((Predicate) peliculaID.in(peliculas));
            }

            // REMOVER DUCPLICADOS
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        };

    }

}




