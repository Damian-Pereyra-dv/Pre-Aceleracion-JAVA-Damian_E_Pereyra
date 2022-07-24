package com.alkemy.disney.disney.repository.spec;

import com.alkemy.disney.disney.dto.MovieFiltersDTO;

import com.alkemy.disney.disney.entity.MovieEntity;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

@Component
public class MovieSpecifications {
    public Specification<MovieEntity> getByFilters(MovieFiltersDTO movieFiltersDTO) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //By title
            if(StringUtils.hasLength(movieFiltersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieFiltersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }

            //By genre
            if(StringUtils.hasLength(movieFiltersDTO.getGenre())) {
                Long genreId = Long.parseLong(movieFiltersDTO.getGenre());
                predicates.add(
                        criteriaBuilder.equal(root.get("genreId"), genreId)
                );
            }

            query.distinct(true);

            //by order
            String orderByField = "title";
            query.orderBy(
                    movieFiltersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
