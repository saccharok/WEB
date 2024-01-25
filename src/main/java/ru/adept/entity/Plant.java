package ru.adept.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plant")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "researchers")
    String researchers;

    @Column(name = "status")
    String status;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PreservesAndPlant> preserves;

    public String getStringByPreserves() {
        StringBuilder str = new StringBuilder();
        if (preserves.isEmpty())
            str.append("не находится в заповедниках");
        else {
            for (int i = 0; i < preserves.size() - 1; i++)
                str.append(preserves.get(i).preserve.getName()).append(", ");
            str.append(preserves.get(preserves.size() - 1).preserve.getName());
        }

        return str.toString();
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\": \"" + name + "\", " +
                "\"description\": \"" + description + "\", " +
                "\"researchers\": \"" + researchers + "\", " +
                "\"status\": \"" + status + "\", " +
                "\"preserves\": \"" + this.getStringByPreserves() + "\"}";
    }
}
