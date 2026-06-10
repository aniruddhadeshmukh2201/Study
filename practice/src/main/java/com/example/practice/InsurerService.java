package com.example.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class InsurerService {
    

    public InsurerService() {
        this.loadInsurer("TATA", 0, 20);
        this.loadInsurer("ICICI", 20, 40);
        this.loadInsurer("AIG", 40, 1000);
    }

    private List<Insurer> insurers = new ArrayList<>();

    public List<Insurer> getInsurers(){
        return insurers;
    }

    public void loadInsurer(String name, Integer Start, Integer End) {
        insurers.add(new Insurer(name, Start, End));
    }


    public Optional<InsurerDTO> getInsurer(InsurerRequestDTO insurerRequestDTO) {
        for(Insurer i : this.insurers) {
            if(insurerRequestDTO.getSal() >= i.getBracketStart() && insurerRequestDTO.getSal() <= i.getBracketEnd()) {
                return Optional.of(new InsurerDTO(insurerRequestDTO.getName(), insurerRequestDTO.getSal(), i.getName()));
            }
        }
        return Optional.of(null);
    }

}
