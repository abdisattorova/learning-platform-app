package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
    private int id;
    private String name;
    private String description;
    private boolean isActive;
    private int[] authorsIds;
    private String imageUrl;
    private List<AuthorDto> authorDtoList;
    private List<ModuleDto> moduleDtoList;
}
