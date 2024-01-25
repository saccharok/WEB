package ru.adept.model;


import lombok.*;
import ru.adept.entity.Preserve;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlantModel {

    Long id;
    String name;
    String description;
    String researchers;
    String status;
    List<Preserve> preserves;

    public Boolean consistsInPreserves(Preserve preserve) {
        for (Preserve elem : this.preserves) {
            if (elem.equals(preserve))
                return true;
        }
        return false;
    }
}
