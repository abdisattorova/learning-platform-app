package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Zuhridin Bakhriddinov 2/27/2022 4:56 PM
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RateDto {
    private int id;
    private double  rate;
    private int course_id;
    private int user_id;
}
