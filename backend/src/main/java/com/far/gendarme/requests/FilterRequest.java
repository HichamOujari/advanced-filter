package com.far.gendarme.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {
    private SignleFilter email;
    private SignleFilter grade;
    private SignleFilter formation;
    private SignleFilter anciennete;
    private SignleFilter age;
    private SignleFilter diplome;
}
