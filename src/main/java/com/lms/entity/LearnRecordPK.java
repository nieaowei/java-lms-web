package com.lms.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LearnRecordPK implements Serializable {

    private UserEntity userEntity;
    private DocList docList;
    public LearnRecordPK(){

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
