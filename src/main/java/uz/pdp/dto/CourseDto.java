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
    private int allTasksNum;
    private int solvedTasksNum;
    private List<AuthorDto> authorDtoList;
    private List<ModuleDto> moduleDtoList;
    private int count=0;

    public CourseDto(int id, String name, String description, boolean isActive, int[] authorsIds, String imageUrl, List<AuthorDto> authorDtoList, List<ModuleDto> moduleDtoList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.authorsIds = authorsIds;
        this.imageUrl = imageUrl;
        this.authorDtoList = authorDtoList;
        this.moduleDtoList = moduleDtoList;
    }

    public CourseDto( String name, int count) {
        this.name = name;
        this.count = count;
    }
}
