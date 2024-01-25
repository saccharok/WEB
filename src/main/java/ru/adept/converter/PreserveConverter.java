package ru.adept.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.adept.entity.Preserve;
import ru.adept.repo.PreserveRepository;

import java.util.Optional;

@Component
public class PreserveConverter implements Converter<String, Preserve> {

    @Autowired
    PreserveRepository repository;

    @Override
    public Preserve convert(String id) {
        Long parsedId = Long.parseLong(id);
        Optional<Preserve> optional = repository.findById(parsedId);

        return optional.orElse(new Preserve());
    }
}
