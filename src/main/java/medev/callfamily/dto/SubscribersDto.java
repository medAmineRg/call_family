package medev.callfamily.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscribersDto {

    private Long id;
    @Column(name = "email", nullable = false)
    @NotEmpty
    @Size(max = 100,message = "Email must be at max 100 char")
    private String email;
}
