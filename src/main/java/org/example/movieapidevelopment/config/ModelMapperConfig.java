package org.example.movieapidevelopment.config;

import org.example.movieapidevelopment.dao.MovieEntity;
import org.example.movieapidevelopment.dto.request.MovieRequestDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();
        mapper.createTypeMap(MovieRequestDto.class, MovieEntity.class)
                .addMapping(src->null,MovieEntity::setId);
        return mapper;
    }
}
