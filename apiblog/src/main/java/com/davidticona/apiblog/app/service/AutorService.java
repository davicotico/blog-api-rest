package com.davidticona.apiblog.app.service;

import com.davidticona.apiblog.app.data.dto.AutorCreateDto;

public interface AutorService {
    Integer crear(AutorCreateDto dto);
}
