package br.com.yoursupplierapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupDto {

    private String groupName;
    private List<Long> userIds;
    private List<Long> roleIds;

}
