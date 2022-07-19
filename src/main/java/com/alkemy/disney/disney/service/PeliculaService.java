package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;

import java.util.List;

public interface PeliculaService {


        PeliculaDTO save (PeliculaDTO dto) ;

        List<PeliculaDTO> getAllPeliculas  ();


    }
