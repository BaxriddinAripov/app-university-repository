package uz.pdp.appspringjpalesson7.payload;

import lombok.Data;

@Data
public class StudentDto {

    private String firstName;
    private String lastName;
    private Integer addressId;
    private Integer groupId;
}
