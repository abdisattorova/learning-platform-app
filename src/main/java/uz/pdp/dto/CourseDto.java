package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private int count = 0;
    private boolean isUserAuthor;

    public CourseDto(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public boolean getIsUserAuthor() {
        return isUserAuthor;
    }

    public void setIsUserAuthor(boolean isUserAuthor) {
        this.isUserAuthor = isUserAuthor;
    }
}
